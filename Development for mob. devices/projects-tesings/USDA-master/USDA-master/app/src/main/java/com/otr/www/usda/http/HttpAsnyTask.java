package com.otr.www.usda.http;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.otr.www.usda.adapter.FoodAdapter;
import com.otr.www.usda.bin.Food;
import com.otr.www.usda.ndbapi.NDB_API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pingyaoooo on 2016/4/19.
 */
public class HttpAsnyTask extends AsyncTask<String, Void, List<Food>> {
    private Handler handler;
    private OkHttpClient okHttpClient;
    private Context context;


    FoodAdapter adapter;

    public HttpAsnyTask() {
        okHttpClient = new OkHttpClient();
    }

    public HttpAsnyTask(Handler handler) {
        this.handler = handler;
        okHttpClient = new OkHttpClient();

    }

    public HttpAsnyTask(Handler handler, FoodAdapter arrayAdapter) {
        this.handler = handler;
        okHttpClient = new OkHttpClient();
        adapter = arrayAdapter;

    }

    public HttpAsnyTask(Context context, Handler handler, FoodAdapter adapter) {
        this.handler = handler;
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    protected List<Food> doInBackground(String... params) {

        List<Food>  foods = new ArrayList<Food>();
        String url = NDB_API.getSearchUrl(params[0]);

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();{
                String json = response.body().string();
                //Log.i("TAG","response data"+ json);


                JSONObject forecastJson = null;

                    forecastJson = new JSONObject(json);
                    JSONObject list = forecastJson.getJSONObject(NDB_API.LIST);
                    String name = list.get(NDB_API.Q).toString();
                    JSONArray foodArray = list.getJSONArray(NDB_API.ITEM);
                    for (int i = 0; i < foodArray.length(); i++) {
                        JSONObject foodObj = foodArray.getJSONObject(i);
                        String foodGroup = foodObj.getString(NDB_API.GROUP);
                        String foodName = foodObj.getString(NDB_API.NAME);
                        String foodId = foodObj.getString(NDB_API.NDBNO);

                        Food food = new Food();
                        food.setGroup(foodGroup);
                        food.setName(foodName);
                        food.setNdbno(foodId);
                        foods.add(food);

                    }
            }
            return foods;

        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(List<Food> foods) {
        final List<Food> foodList = foods;

        if (foodList == null) {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(context.getApplicationContext(), "food is doesn't search", Toast.LENGTH_SHORT).show();
//                }
//            });
            Toast.makeText(context.getApplicationContext(), "food is doesn't search", Toast.LENGTH_SHORT).show();

        }else {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    adapter.clear();
//                    adapter.addAll(foodList);
//                    adapter.notifyDataSetChanged();
//
//                }
//            });
            adapter.clear();
            adapter.addAll(foodList);
            adapter.notifyDataSetChanged();
        }



    }
}
