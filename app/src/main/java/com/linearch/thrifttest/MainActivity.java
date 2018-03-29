package com.linearch.thrifttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.concurrent.Callable;

import chat.ChatException;
import chat.ClientSession;

public class MainActivity extends AppCompatActivity {
    EditText usernameField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        System.out.println("CHAT BUILD 1");

        ChatClient.init(this);

        usernameField = (EditText)findViewById(R.id.usernameField);
        passwordField = (EditText)findViewById(R.id.passwordField);
        Button registerButton = (Button)findViewById(R.id.registerButton);
        Button loginButton = (Button)findViewById(R.id.loginButton);

        final MainActivity act = this;
        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view){

                        final String username = getUsername();
                        final String password = getPassword();
                        if (username == null || password == null){
                            return;
                        }
                        new ChatAsyncTask<Void>().execute(
                                new Callable<Void>(){
                                    public Void call(){
                                        try{
                                            ChatClient.signup(username, password);
                                            //toast("Signup successful");
                                            ChatClient.snackbar("Signup successful");
                                            openChat();
                                        }catch (ChatException ex){
                                            ChatClient.snackbar(ex);
                                        }catch (Exception ex){
                                            ChatClient.snackbar(ex);
                                        }
                                        return null;
                                    }

                                }
                        );
                    }
                }
        );
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        final String username = getUsername();
                        final String password = getPassword();
                        if (username == null || password == null){
                            return;
                        }
                        new ChatAsyncTask<Void>().execute(
                                new Callable<Void>(){
                                    public Void call(){
                                        try{
                                            ChatClient.login(username, password);
                                            ChatClient.snackbar("Login successful");
                                            openChat();
                                        }catch (ChatException ex){
                                            ChatClient.snackbar("Error : " + ex.code.name());
                                        }catch (Exception ex){
                                            ex.printStackTrace();
                                        }
                                        return null;
                                    }
                                }
                        );
                    }
                }
        );
        TryLoginCertificate();
    }

    public void TryLoginCertificate(){
        new ChatAsyncTask<Void>().execute(
                new Callable<Void>(){
                    public Void call(){
                        try{
                            ClientSession session = ChatClient.tryGetSession();
                            if (session != null){
                                ChatClient.snackbar("Logged in with certificate");
                                openChat();
                                return null;
                            }else{
                                ChatClient.snackbar("Certificate login failed");
                            }
                        }catch (ChatException ex){
                            ChatClient.snackbar(ex);
                        }catch (Exception ex){
                            ChatClient.snackbar(ex);
                        }
                        return null;
                    }
                }
        );
    }

    public String getUsername(){
        String username = usernameField.getText().toString();
        if (username.isEmpty()){
            ChatClient.snackbar("Username can't be empty");
            return null;
        }
        return username;
    }

    public String getPassword(){
        final String password = passwordField.getText().toString();
        if (password.isEmpty()){
            ChatClient.snackbar("Password can't be empty");
            return null;
        }
        return password;
    }

    public void openChat(){
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
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
