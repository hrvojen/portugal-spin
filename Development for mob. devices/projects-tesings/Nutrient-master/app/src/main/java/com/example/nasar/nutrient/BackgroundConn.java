package com.example.nasar.nutrient;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nasar on 6/6/15.
 */
public class BackgroundConn extends AsyncTask<String, Void, String> {
    Activity view;
    String search;
    String tag = "myTag";
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String results = null;
    BackgroundConn(Activity view, String search){
        this.view = view;
        this.search = search;

    }
    @Override
    protected String doInBackground(String... strings) {
            JsonParsing parse = new JsonParsing();

        String api_key = "qT0Yk38b5soWCULl1KsmqJQOxcFe7tXzcksyOrB5";
        String linkID =  "http://api.nal.usda.gov/ndb/search/?format=json&q="+search+"&sort=n&max=1&offset=0&api_key="+api_key;//qT0Yk38b5soWCULl1KsmqJQOxcFe7tXzcksyOrB5";//+api_key;

           // Log.d("myTag", results);
        //   textView.setText(results);

           String data = getJson(linkID);
        //    Log.d(tag, data);
         parse.extractFoodInfo(data);
         // String id = extractId(data);
        String id = parse.getFoodID();
            Log.d(tag,id);
            String jsonFood ="http://api.nal.usda.gov/ndb/reports/?ndbno="+id+"&type=b&format=json&api_key="+api_key;
         //   String foodInfo = getJson(jsonFood);
        String foodInfo = getJson(jsonFood);
        parse.getNutrient(foodInfo);
      //  Log.d(tag, foodInfo);
        if (urlConnection != null)
            urlConnection.disconnect();

        return foodInfo;
    }

    private String extractId(String data){
          final String list = "list";
    //        Log.d(tag, data);
        try {
            JSONObject obj = new JSONObject(data);
            Log.d(tag,obj.getString(list).toString());

            String [] str = obj.toString().split("(?<!\\\\),");  // split of comma but ignore commas inside string
        //    Log.d(tag,str.toString());
        // Log.d(tag, str[5].toString());
            String item = str[5].toString();
            String id = item.substring(item.length()-6, item.length()-1);
            Log.d(tag,id);
            Log.d(tag, "inside cleanUpData method");

            return id;

        } catch (JSONException e) {
            Log.d(tag, " exception inside cleanUpData");
            Log.d(tag, e.getMessage().toString());
            e.printStackTrace();
        }
            return list;
    }

    @Override
    protected void onPostExecute(String  s) {
        super.onPostExecute(s);
     //  TextView textView = (TextView) view.findViewById(R.id.results);
    //    textView.setText(s);

    }

    private String getJson(String link){


        try {
            // c
            URL url = new URL(link);
            Log.d(tag, url.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d(tag,  "open connection");
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.d(tag, "url connected");
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if(inputStream == null){
                Log.d(tag,  "inputStream was null");
                return  null;
            }


            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine())  != null){
                buffer.append(line +"\n");
            }

            if (buffer.length() == 0) {
                Log.d(tag,  "Length of buffer was zero");
                // Stream was empty.  No point in parsing.
                return null;
            }
            results = buffer.toString();
            return  results;
        } catch (IOException e){
            Log.d(tag,  e.getMessage());
            e.printStackTrace();
        }

        finally {
            urlConnection.disconnect();
        }
        return null;
    }
}
