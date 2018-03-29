package com.linearch.thrifttest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by MojoMacW7 on 22/10/2017.
 */

public class ChatAdapter extends BaseAdapter {
    private ChatBox chatBox;

    Object tagChatMine = (Object)"chat_mine";
    Object tagChatOthers = (Object)"chat_others";

    public ChatAdapter(ChatBox chatBox){
        this.chatBox = chatBox;
    }

    public void setChatBox(ChatBox chatBox){
        this.chatBox = chatBox;
        update();
    }

    public void update(){
        ChatClient.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                notifyDataSetChanged();
            }
        });
    }

    public void extendItems(final List<ChatData> items){
        chatBox.extendItems(items);
    }

    public void addItem(final ChatData item){
        chatBox.addItem(item);
    }

    public void sort(){
        chatBox.sort();
    }

    @Override
    public int getCount(){
        return chatBox.getCount();
    }

    @Override
    public ChatData getItem(int position){
        return chatBox.getItem(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ChatData chat = getItem(position);
        if (convertView == null){
            if (chat.senderId == ChatClient.session.id){
                convertView = ChatClient.getLayoutInflater().inflate(R.layout.chat_mine, null);
                convertView.setTag(tagChatMine);
            }else{
                convertView = ChatClient.getLayoutInflater().inflate(R.layout.chat_others, null);
                convertView.setTag(tagChatOthers);
            }
        }else{
            if (chat.senderId == ChatClient.session.id){
                if (convertView.getTag() != tagChatMine){
                    convertView = ChatClient.getLayoutInflater().inflate(R.layout.chat_mine, null);
                    convertView.setTag(tagChatMine);
                }
            }else{
                if (convertView.getTag() != tagChatOthers){
                    convertView = ChatClient.getLayoutInflater().inflate(R.layout.chat_others, null);
                    convertView.setTag(tagChatOthers);
                }
            }
        }
        try {
            ((TextView) convertView.findViewById(R.id.usernameText)).setText(ChatClient.getUserByIdIfNotExists(chat.senderId).name);
        }catch (Exception ex){
            ChatClient.snackbar(ex);
        }
        ((TextView)convertView.findViewById(R.id.timestampText)).setText(chat.getFormattedTime());
        ((TextView)convertView.findViewById(R.id.chatText)).setText(chat.content);
        return convertView;
    }
}