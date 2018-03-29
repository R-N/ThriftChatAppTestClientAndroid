package com.linearch.thrifttest;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.Callable;

import chat.ChatroomType;
import chat.CompleteMessage;
import chat.Message;
import chat.MessageBox;

/**
 * Created by MojoMacW7 on 22/10/2017.
 */

public class ChatBox {
    public ChatroomType chatroomType;
    public long chatroomId;
    private ChatAdapter adapter = null;
    public List<ChatData> chats = new ArrayList<ChatData>(ChatClient.defaultMessageGetLimit);
    public long revision = 0;

    public ChatBox(ChatroomType chatroomType, long chatroomId) throws Exception{
        this(chatroomType, chatroomId, 0);
    }
    public ChatBox(ChatroomType chatroomType, long chatroomId, long revision)  throws Exception{
        this.revision = revision;
        this.chatroomType = chatroomType;
        this.chatroomId = chatroomId;
        init();
    }

    public void init() throws Exception{
        switch(chatroomType){
            case group:{
                Cursor rs = ChatClient.db.rawQuery("SELECT messageRevision FROM Groups WHERE id=" + chatroomId, null);
                if (rs.moveToFirst()){
                    revision = rs.getLong(0);
                }
                break;
            }
        }
        if (revision == 0){
            getLastMessages(ChatClient.defaultMessageGetLimit);
        }else{
            getNewMessages(ChatClient.defaultMessageGetLimit);
        }
    }

    public int parse(MessageBox mb){
        if (mb != null && mb.revision != 0){
            revision = mb.revision;
            int size = mb.messages.size();
            if (size > 0){
                ListIterator li = mb.messages.listIterator(size);
                List<ChatData> list = new LinkedList<ChatData>(chats);
                while(li.hasPrevious()) {
                    Message m = (Message)li.previous();
                    ChatData chat = new ChatData(m);
                    try {
                        ChatClient.getUserByIdIfNotExists(chat.senderId);
                    }catch (Exception ex){
                        ChatClient.snackbar(ex);
                    }
                    list.add(chat);
                    insert(chat);
                }
                setList(list);
                return size;
            }
        }
        return 0;
    }

    public void insert(ChatData chat){
        SQLiteStatement stmt = ChatClient.db.compileStatement("INSERT OR REPLACE INTO Messages(id, senderId, chatroomType, chatroomId, timestamp, contentType, content) VALUES(?, ?, ?, ?, ?, ?, ?)");
        insert(chat, stmt);
    }
    public void insert(ChatData chat, SQLiteStatement stmt){
        stmt.bindLong(1, chat.id);
        stmt.bindLong(2, chat.senderId);
        stmt.bindLong(3, chatroomType.getValue());
        stmt.bindLong(4, chatroomId);
        stmt.bindLong(5, chat.timestamp);
        stmt.bindLong(6, chat.contentType.getValue());
        stmt.bindString(7, chat.content);
        long in = stmt.executeInsert();
        System.out.println("Inserted " + in);
    }

    public void getOldMessages(final int count) throws Exception{
        new ChatAsyncTask<Void>().execute(
                new Callable<Void>(){
                    public Void call(){
                        try{
                            parse(ChatClient.getOldMessagesAll(chatroomType, chatroomId, revision, count));
                        }catch(Exception ex){
                            ChatClient.snackbar(ex);
                        }
                        return null;
                    }
                }
        );
    }

    public void getLastMessages(final int count) throws Exception{
        new ChatAsyncTask<Void>().execute(
                new Callable<Void>(){
                    public Void call(){
                        try{
                            int c = parse(ChatClient.getLastMessagesAll(chatroomType, chatroomId, count));
                            if (c == 0){
                                parse(ChatClient.getLastMessagesAll(chatroomType, chatroomId, count));
                            }
                        }catch(Exception ex){
                            ChatClient.snackbar(ex);
                        }
                        return null;
                    }
                }
        );
    }

    public void getNewMessages(final int count) throws Exception{
        new ChatAsyncTask<Void>().execute(
                new Callable<Void>(){
                    public Void call(){
                        try{
                            int size = parse(ChatClient.getNewMessages(chatroomType, chatroomId, revision, count));
                            if (size == count){
                                getNewMessages(count);
                            }
                        }catch(Exception ex){
                            ChatClient.snackbar(ex);
                        }
                        return null;
                    }
                }
        );
    }

    public void sendMessage(final String text) throws Exception{
        new ChatAsyncTask<Void>().execute(
                new Callable<Void>(){
                    public Void call(){
                        try{
                            CompleteMessage msg = ChatClient.sendMessage(chatroomType, chatroomId, text);
                        }catch(Exception ex){
                            ChatClient.snackbar(ex);
                        }
                        return null;
                    }
                }
        );
    }

    public void setList(final List<ChatData> list){

        ChatClient.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                Collections.sort(list, new Comparator<ChatData>(){
                    @Override
                    public int compare(ChatData lhs, ChatData rhs){
                        return Long.compare(lhs.id, rhs.id);
                    }
                });
                revision = list.get(list.size()-1).id;
                chats = list;
                notifyDataSetChanged();
            }
        });
    }



    public static Map<ChatroomType, Map<Long, ChatBox>> chatBoxes = new HashMap<ChatroomType, Map<Long, ChatBox>>();

    public static ChatBox getChatBox(ChatroomType chatroomType, long chatroomId) throws Exception{
        if (!chatBoxes.containsKey(chatroomType)){
            chatBoxes.put(chatroomType, new HashMap<Long, ChatBox>());
        }
        Map<Long, ChatBox> map = chatBoxes.get(chatroomType);
        if (!map.containsKey(chatroomId)){
            map.put(chatroomId, new ChatBox(chatroomType, chatroomId));
        }

        return map.get(chatroomId);
    }

    public static ChatBox getChatBoxIfExists(ChatroomType chatroomType, long chatroomId){
        if (!chatBoxes.containsKey(chatroomType)){
            return null;
        }
        Map<Long, ChatBox> map = chatBoxes.get(chatroomType);
        if (!map.containsKey(chatroomId)){
            return null;
        }

        return map.get(chatroomId);
    }

    public ChatAdapter getAdapter(){
        if (adapter == null){
            adapter = new ChatAdapter(this);
        }
        return adapter;
    }

    public void setListView(ListView listView){
        listView.setAdapter(getAdapter());
    }

    public void setAdapter(ChatAdapter adapter){
        this.adapter = adapter;
        adapter.setChatBox(this);
    }

    public void extendItems(final List<ChatData> items){
        ChatClient.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                chats.addAll(items);
                sort();
                notifyDataSetChanged();
            }
        });
    }


    public void addItem(final ChatData item){
        ChatClient.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                chats.add(item);
                notifyDataSetChanged();
            }
        });
    }

    public void update(){
        if (adapter != null){
            adapter.update();
        }
    }

    public void notifyDataSetChanged(){
        if (adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    public void sort(){
        ChatClient.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                Collections.sort(chats, new Comparator<ChatData>(){
                    @Override
                    public int compare(ChatData lhs, ChatData rhs){
                        return Long.compare(lhs.id, rhs.id);
                    }
                });
                notifyDataSetChanged();
            }
        });
    }

    public int getCount(){
        return chats.size();
    }

    public ChatData getItem(int position){
        return chats.get(position);
    }

    public long getItemId(int position){
        return position;
    }
}
