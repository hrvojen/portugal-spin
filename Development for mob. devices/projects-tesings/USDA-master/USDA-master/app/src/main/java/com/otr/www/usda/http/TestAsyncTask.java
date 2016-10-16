package com.otr.www.usda.http;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by pingyaoooo on 2016/4/20.
 */
public class TestAsyncTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... params) {
        Log.i("RAG", "do in background");
        String a = null;
        for (int i = 0; i < 10000; i++) {
            a += "b";

        }

        return a;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("RAG", "do pre");

    }

    @Override
    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
        Log.i("RAG", s);

    }
}
