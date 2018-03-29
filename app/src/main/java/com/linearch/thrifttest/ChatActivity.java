package com.linearch.thrifttest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import chat.ChatroomType;
import chat.Message;
import chat.MessageBox;

public class ChatActivity extends AppCompatActivity {

    ChatBox chatBox;
    EditText chatField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        ChatClient.setActivity(this);
        ListView listView = (ListView)findViewById(R.id.chatListView);
        try {
            chatBox = ChatBox.getChatBox(ChatroomType.group, 0);
            chatBox.setListView(listView);
        }catch (Exception ex){
            ChatClient.snackbar(ex);
        }
        ChatClient.fetchOperationsForever();

        chatField = (EditText)findViewById(R.id.chatField);

        ImageButton sendButton = (ImageButton)findViewById(R.id.sendChatButton);

        sendButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int len = chatField.length();
                        if(len == 0){
                            ChatClient.snackbar("Can't send empty chat");
                            return;
                        }else if (len > 1000){
                            ChatClient.snackbar("Message can't be longer than 1000 characters");
                            return;
                        }
                        String text = chatField.getText().toString();
                        try {
                            chatBox.sendMessage(text);
                            chatField.setText("");
                            chatField.getText().clear();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
