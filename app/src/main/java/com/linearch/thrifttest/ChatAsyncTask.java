package com.linearch.thrifttest;

import android.os.AsyncTask;

import java.util.concurrent.Callable;

import chat.ChatException;

/**
 * Created by MojoMacW7 on 23/10/2017.
 */

public class ChatAsyncTask<T> extends AsyncTask<Callable<T>, Integer, T> {
    /*public ChatAsyncTask (Callable<T>... callables){
        execute(callables);
    }*/
    public AsyncTask<Callable<T>, Integer, T> executeSelf (){
        return execute(
                new Callable<T>(){
                    public T call(){
                        return callable();
                    }
                }
        );
    }
    public AsyncTask<Callable<T>, Integer, T> executeSelfOnExecutor (){
        return executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                new Callable<T>(){
                    public T call(){
                        return callable();
                    }
                }
        );
    }

    public T callable(){
        return null;
    }

    @Override
    protected T doInBackground(Callable<T>... callables) {
        for (Callable<T> callable : callables){
            try{
                callable.call();
            }catch (Exception ex){
                ChatClient.snackbar(ex);
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... progress) {
    }
    @Override
    protected void onPostExecute(T ret) {
    }
}
