package com.linearch.thrifttest;

import android.database.sqlite.SQLiteStatement;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import chat.Message;
import chat.MessageContentType;

/**
 * Created by MojoMacW7 on 18/10/2017.
 */

public class ChatData{
    public long id = 0;
    public long senderId = 0;
    public long timestamp;
    public MessageContentType contentType;
    public String content = "";
    public ChatBox chatBox = null;

    public ChatData(long id, long senderId, long timestamp, MessageContentType contentType, String content){
        this.id = id;
        this.senderId = senderId;
        this.contentType = contentType;
        this.timestamp = timestamp;
        this.content = content;
    }

    public ChatData(Message message){
        this.id = message.id;
        this.senderId = message.senderId;
        this.contentType = message.contentType;
        this.timestamp = message.timestamp;
        this.content = message.content;
    }

    public LocalDateTime getLocalTimestamp() {
        return new LocalDateTime(timestamp * 1000);
    }
    public String getFormattedTime(){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("h:mm a");
        return dtf.print(getLocalTimestamp());
    }
}
