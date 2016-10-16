package com.otr.www.usda.http;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.otr.www.usda.adapter.FoodReportAdapter;
import com.otr.www.usda.bin.FoodReport;
import com.otr.www.usda.ndbapi.NDB_API;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pingyaoooo on 2016/4/19.
 */
public class HttpAsnyTaskReport extends AsyncTask<String, Void, FoodReport> {
    private Handler handler;
    private OkHttpClient okHttpClient;



    FoodReportAdapter adapter;
    TextView textView;

    public HttpAsnyTaskReport() {
        okHttpClient = new OkHttpClient();
    }

    public HttpAsnyTaskReport(Handler handler, FoodReportAdapter adapter) {
        this.handler = handler;
        okHttpClient = new OkHttpClient();
        this.adapter = adapter;

    }

    public HttpAsnyTaskReport(Handler handler, TextView textView) {
        this.handler = handler;
        okHttpClient = new OkHttpClient();
        this.textView = textView;

    }

    @Override
    protected FoodReport doInBackground(String... params) {
        FoodReport foodReport = null;
        String url = NDB_API.getFoodReportsUrl(params[0]);

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String json = response.body().string();
            //Log.i("TAG","response data"+ json);
            Gson gson = new Gson();
            foodReport = gson.fromJson(json, FoodReport.class);

            return foodReport;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foodReport;
    }

    @Override
    protected void onPostExecute(FoodReport report) {
        if (report != null) {
            final FoodReport fReport = report;
            Log.i("RAG", fReport.getReport().getFood().getName());

            handler.post(new Runnable() {
                @Override
                public void run() {
                    FoodReport.Food food = fReport.getReport().getFood();
                    List<FoodReport.Nutrient> nutrients = food.getNutrients();
                    textView.setText(
                            food.getName() + "\n"

                    );
                    for (int i = 0; i < nutrients.size(); i++) {
                        textView.append(nutrients.get(i).getGroup());
                        textView.append(nutrients.get(i).getName());
                        textView.append(nutrients.get(i).getValue());
                        textView.append(nutrients.get(i).getUnit());

                    }
                }
            });
        }
        else{
            Log.i("RAG", "no do in background");

        }


    }
}
