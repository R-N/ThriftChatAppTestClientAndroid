package com.linearch.thrifttest;

/**
 * Created by MojoMacW7 on 16/10/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;

import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

import chat.*;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.params.HttpClientParams;
import cz.msebera.android.httpclient.config.ConnectionConfig;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.impl.io.SessionOutputBufferImpl;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParamConfig;
import cz.msebera.android.httpclient.params.HttpParamsNames;
import cz.msebera.android.httpclient.impl.client.DefaultConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.protocol.HTTP;
import util.LockWrapper;

import android.database.sqlite.*;
import android.view.View;
import android.widget.Toast;


public class ChatClient {
    public static int defaultMessageGetLimit = 50;
    public static Chat.Client singleton = null;
    public static ClientSession session = null;
    public static long revision = 0;
    public static LockWrapper lock = new LockWrapper(new ReentrantLock());

    public static Map<Long, User> usersById = new HashMap<Long, User>();

    public static String salt = "257";

    public static SQLiteDatabase db = null;

    public static Activity activity = null;
    public static LayoutInflater layoutInflater = null;
    public static View view = null;

    public static void setActivity(Activity activity){
        ChatClient.activity = activity;
        layoutInflater = getLayoutInflater();
        view = getView();
    }

    public static void init(Activity act){
        try {
            setActivity(act);
            Chat.Client cli = createInstance();
        }catch (Exception ex){
            snackbar(ex);
        }
    }

    public static Activity getActivity(){
        return activity;
    }

    public static LayoutInflater getLayoutInflater(){
        if (activity == null){
            return null;
        }
        return activity.getLayoutInflater();
    }

    public static View getView(){
        if (activity == null){
            return null;
        }
        View currentFocus =  activity.getWindow().getCurrentFocus();
        if (currentFocus == null){
            return null;
        }
        return currentFocus.getRootView();
    }
    public static void snackbar(ChatException ex){
        ex.printStackTrace();
        snackbar("ChatException : " + ex.code.toString());
    }

    public static void snackbar(Exception ex){
        ex.printStackTrace();
        snackbar(ex.toString());
    }

    public static void snackbar(String text){
        System.out.println("Snackbar '" + text + "'");
        View view = getView();
        if (view == null){
            return;
        }
        Snackbar.make(getView(), text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public static void toast(Exception ex){
        ex.printStackTrace();
        toast(ex.toString());
    }

    public static void toast(String text){
        System.out.println("Toast '" + text + "'");
        if (activity == null){
            return;
        }
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
    }

    public static void runOnUiThread(Runnable run){
        activity.runOnUiThread(run);
    }

    public static Chat.Client getSingleton() throws Exception {
        try (LockWrapper l = lock.lock(10)){
            if (singleton == null){
                return createInstance();
            }
            return singleton;
        }
    }

    private static void dbInit() throws Exception{
        try (LockWrapper l = lock.lock(10)){
            db = activity.openOrCreateDatabase("chat", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS UserInfos(name TEXT UNIQUE, value TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Users(id SERIAL PRIMARY KEY, username TEXT UNIQUE, revision BIGINT DEFAULT 0)");
            db.execSQL("CREATE TABLE IF NOT EXISTS PersonalChats (second BIGINT UNIQUE, lastId BIGINT DEFAULT 0)");
            db.execSQL("CREATE TABLE IF NOT EXISTS Rooms(id SERIAL PRIMARY KEY, revision BIGINT DEFAULT 0, messageRevision BIGINT DEFAULT 0)");
            db.execSQL("CREATE TABLE IF NOT EXISTS RoomMemberIds(id BIGINT, userId BIGINT, CONSTRAINT roomMember UNIQUE(id, userId))");
            db.execSQL("CREATE TABLE IF NOT EXISTS Groups(id SERIAL PRIMARY KEY, name TEXT, revision BIGINT DEFAULT 0, messageRevision BIGINT DEFAULT 0)");
            db.execSQL("CREATE TABLE IF NOT EXISTS GroupMemberIds(id BIGINT, userId BIGINT, CONSTRAINT groupMember UNIQUE (id, userId))");
            db.execSQL("CREATE TABLE IF NOT EXISTS Messages(id BIGINT, senderId BIGINT, chatroomType INTEGER, chatroomId BIGINT, timestamp BIGINT, contentType INTEGER, content TEXT, CONSTRAINT message UNIQUE(id, chatroomType, chatroomId))");

            printUserInfos();
        }
    }

    public static void printUserInfos(){

        try{
            System.out.println("Printing userinfos");
            Cursor rs = db.rawQuery("SELECT name, value FROM UserInfos", null);
            while(rs.moveToNext()){
                System.out.println("UserInfo '" + rs.getString(0) + "' : '" + rs.getString(1) + "'");
            }
            System.out.println("Printing userinfos done");
        }catch (Exception ex){
            snackbar(ex);
        }
    }

    public static void setSession(ClientSession newSession) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            session = newSession;
            System.out.println("Setting certificate");
            setUserInfo("certificate", session.certificate);
            System.out.println("Setting id");
            setUserInfo("id", String.valueOf(session.id));

            System.out.println("Getting userinfo");
            ChatClient.getUserByIdIfNotExists(session.id);
        }
    }

    public static void setUserInfo(String name, String value) throws InterruptedException{
        try (LockWrapper l = lock.lock(10)){
            try{
                System.out.println("Setting '" + name + "' to '" + value + "'");
                db.execSQL("INSERT OR REPLACE INTO UserInfos(name, value) VALUES(?, ?)", new String[]{name, value});
                System.out.println("Setting '" + name + "' to '" + value + "' succceded");
                printUserInfos();
            }catch (Exception ex){
                snackbar(ex);
            }
        }
    }

    public static String getUserInfo(String name){
        try{
            System.out.println("Getting '" + name +"'");
            Cursor resultSet = db.rawQuery("SELECT value FROM UserInfos WHERE name=?", new String[]{name});
            if (resultSet.moveToFirst()){
                System.out.println("Getting '" + name +"' succeeded");
                return resultSet.getString(0);
            }
            System.out.println("Getting '" + name +"' failed");
            printUserInfos();
        }catch (Exception ex){
            snackbar(ex);
        }
        return null;
    }

    public static ClientSession tryGetSession() throws Exception{
        String cert = getUserInfo("certificate");
        if (cert != null && !cert.isEmpty()) {
            String sId = getUserInfo("id");
            if (sId != null && !sId.isEmpty()){
                long id = Long.parseLong(sId);
                return getSession(id, cert);
            }else {
                snackbar("No id found");
            }
        }else{
            snackbar("No certificate found");
        }
        return null;
    }

    public static Chat.Client createInstance() throws Exception{
        try (LockWrapper l = lock.lock(10)){
            
            HttpClientBuilder builder = HttpClientBuilder.create();
            builder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

            HttpClient cli = builder.build();

            THttpPersist transport = new THttpPersist("http://gradlethrift.herokuapp.com/chat", cli);

            transport.setCustomHeader("connection", "keep-alive");
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            singleton = new Chat.Client(protocol);
            try {
                dbInit();
            }catch (Exception ex){
                snackbar(ex);
            }
            return singleton;
        }
    }

    public static ClientSession getSession(long id, String certificate) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            setSession(singleton.getSession(id, certificate));
            return session;
        }
    }


    public static String md5(String s) throws Exception{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(s.getBytes("UTF-8"));
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }

    public static User getUserById(long id) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            User user = getSingleton().getUserById(session.session, id);
            if (user != null){
                usersById.put(id, user);
            }
            return user;
        }
    }

    public static User getUserByIdIfNotExists(long id) throws Exception{
        if (usersById.containsKey(id)){
            return usersById.get(id);
        }
        return getUserById(id);
    }

    public static ClientSession signup(String username, String password) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            setSession(getSingleton().signup(username, md5(password+salt+username)));
            return session;
        }
    }
    public static ClientSession login(String username, String password) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            setSession(getSingleton().login(username, md5(password+salt+username)));
            return session;
        }
    }
    public static MessageBox getOldMessagesDB(ChatroomType chatroomType, long chatroomId, long revision, int count) throws Exception {
        try (LockWrapper l = lock.lock(10)) {
            MessageBox ret = new MessageBox(chatroomType, chatroomId, new LinkedList<Message>(), 0);
            Cursor rs = db.rawQuery("SELECT id, senderId, timestamp, contentType, content FROM Messages WHERE chatroomType=? AND chatroomId=? AND id < ? ORDER BY id DESC LIMIT ?",
                    new String[]{String.valueOf(chatroomType.getValue()), String.valueOf(chatroomId), String.valueOf(revision), String.valueOf(count)});
            while (rs.moveToNext()) {
                ret.addToMessages(new Message(
                        rs.getLong(0),
                        rs.getLong(1),
                        rs.getLong(2),
                        MessageContentType.findByValue(rs.getInt(3)),
                        rs.getString(4)
                ));
            }
            int size = ret.messages.size();
            if (size > 0){
                ret.revision = ret.messages.get(size-1).id;
            }

            return ret;
        }
    }
    public static MessageBox getOldMessages(ChatroomType chatroomType, long chatroomId, long revision, int count) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            MessageBox mb = getSingleton().getOldMessages(session.session, chatroomType, chatroomId, revision, count);
            return mb;
        }
    }
    public static MessageBox getOldMessagesAll(ChatroomType chatroomType, long chatroomId, long revision, int count) throws Exception {
        try (LockWrapper l = lock.lock(10)){
            MessageBox mb = getOldMessagesDB(chatroomType, chatroomId, revision, count);
            int delta = count - mb.messages.size();
            if (delta > 0){
                MessageBox mb2 = getOldMessages(chatroomType, chatroomId, mb.revision, delta);
                if (mb2.messages.size() > 0){
                    mb.messages.addAll(mb2.messages);
                    mb.revision = mb2.revision;
                }
            }
            return mb;
        }
    }
    public static MessageBox getLastMessagesDB(ChatroomType chatroomType, long chatroomId, int count){
        MessageBox ret = new MessageBox(chatroomType, chatroomId, new LinkedList<Message>(), 0);
        Cursor rs = db.rawQuery("SELECT id, senderId, timestamp, contentType, content FROM Messages WHERE chatroomType=? AND chatroomId=? ORDER BY id DESC LIMIT ?",
                new String[]{String.valueOf(chatroomType.getValue()), String.valueOf(chatroomId), String.valueOf(count)});
        while(rs.moveToNext()){
            ret.addToMessages(new Message(
                    rs.getLong(0),
                    rs.getLong(1),
                    rs.getLong(2),
                    MessageContentType.findByValue(rs.getInt(3)),
                    rs.getString(4)
            ));
        }
        int size = ret.messages.size();
        if (size > 0){
            ret.revision = ret.messages.get(0).id;
        }
        snackbar("getLastMessagesDB " + size);
        return ret;
    }
    public static MessageBox getLastMessages(ChatroomType chatroomType, long chatroomId, int count) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            MessageBox mb = getSingleton().getLastMessages(session.session, chatroomType, chatroomId, count, 0);
            revision = mb.revision;
            snackbar("getLastMessagesDB " + mb.messages.size());
            return mb;
        }
    }

    public static MessageBox getLastMessagesAll(ChatroomType chatroomType, long chatroomId, int count) throws Exception {
        try (LockWrapper l = lock.lock(10)){
            MessageBox mb = getLastMessagesDB(chatroomType, chatroomId, count);
            int delta = count - mb.messages.size();
            if (delta > 0){
                mb = getLastMessages(chatroomType, chatroomId, count);
            }
            return mb;
        }
    }
    public static MessageBox getNewMessages(ChatroomType chatroomType, long chatroomId, long revision, int count) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            MessageBox mb = getSingleton().getNewMessages(session.session, chatroomType, chatroomId, revision, count);
            return mb;
        }
    }
    public static CompleteMessage sendMessage(ChatroomType chatroomType, long chatroomId, String text) throws Exception{
        try (LockWrapper l = lock.lock(10)){
            CompleteMessage message = new CompleteMessage(chatroomType, chatroomId, new Message(0L, 0L, 0L, MessageContentType.text, text));
            message = getSingleton().sendMessage(session.session, message);
            return message;
        }
    }

    public static List<Operation> fetchOperations() throws Exception{
        try (LockWrapper l = lock.lock(10)){
            List<Operation> ops = getSingleton().fetchOperations(session.session);
            for (Operation op : ops){
                switch(op.type){
                    case notifyMessages:{
                        ChatBox.getChatBox(ChatroomType.findByValue(op.intParam1), op.longParam1).getNewMessages(defaultMessageGetLimit);
                        break;
                    }
                }
            }
            return ops;
        }
    }

    public static void fetchOperationsForever(){
        new ChatAsyncTask<Void>().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                new Callable<Void>(){
                    public Void call(){
                        try{
                            getLastMessages(ChatroomType.group, 0, defaultMessageGetLimit);
                        }catch (Exception ex){
                            snackbar(ex);
                        }
                        while(true){
                            try{
                                long millis = System.currentTimeMillis();
                                fetchOperations();
                                millis = System.currentTimeMillis() - millis;
                                if (millis < 1000){
                                    Thread.sleep(1000-millis);
                                }
                            }catch (Exception ex){
                                snackbar(ex);
                            }
                        }
                    }
                }
        );
    }
}
