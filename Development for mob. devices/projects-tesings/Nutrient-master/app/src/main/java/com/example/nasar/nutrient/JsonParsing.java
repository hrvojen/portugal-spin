package com.example.nasar.nutrient;


import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by nasar on 6/10/15.
 */
public class JsonParsing {
    String tag ="myTag";
    String foodName = null;
    String foodGroup = null;
    String foodID = null;
    Object obj;
    JSONObject obj2, obj3, obj4, obj5, obj6;
    JSONArray array, user_array;
    String fat = null;
    String protein = null;
    String sugar = null;
    String carbs = null;

    public String extractFoodInfo(String data) {

        try {

            JSONParser parser = new JSONParser();
            obj = parser.parse(data);
            array = new JSONArray();
            array.add(obj);

            user_array = (JSONArray) array;

            JSONObject obj2 = (JSONObject) user_array.get(0);
            JSONObject obj3 = (JSONObject) obj2.get("list");

            JSONArray obj4 = (JSONArray) obj3.get("item");
            JSONObject obj5 = (JSONObject) obj4.get(0);

            foodName = obj5.get("name").toString();
            foodGroup = obj5.get("group").toString();
            foodID = obj5.get("ndbno").toString();
            Log.d(tag, foodName);
            Log.d(tag, foodGroup);
            Log.d(tag, foodID);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "nothing";

    }



    public void getNutrient(String data) {
        try {

            JSONParser parser = new JSONParser();
            obj = parser.parse(data);
            array = new JSONArray();
            array.add(obj);

            //  System.out.println("report: " + array.get(0));
            System.out.println("");

            user_array = (JSONArray) array;

            JSONObject obj2 = (JSONObject) user_array.get(0);
            JSONObject obj3 = (JSONObject) obj2.get("report");
            System.out.println("");
            JSONObject food = (JSONObject) obj3.get("food");
            JSONArray nutrientsArray = (JSONArray) food.get("nutrients");
            //       System.out.println("size of nutrientsArray: " + nutrientsArray.size());

            for (int i = 0; i < nutrientsArray.size(); i++) {
                JSONObject info = (JSONObject) nutrientsArray.get(i);
                //           //         System.out.println("info: " + info.);
                System.out.println("name: " + info.get("name"));
                if (info.get("name").equals("Protein")) {

                    Log.d(tag, info.get("name").toString());

//                  System.out.println("found");
//                  System.out.println("name: " + info.get("name"));
//                   System.out.println("unit: " + info.get("unit"));
//                    System.out.println("value: " + info.get("value"));
//                   System.out.println("nutrient_id: " +info.get("nutrient_id"));
//                   JSONArray measurements = (JSONArray) info.get("measures");
//                   System.out.println("");
//                   System.out.println("first measurement:  " +measurements.get(0));
//                   System.out.println("");
//                   System.out.println("second measurement:  " +measurements.get(1));
//                   System.out.println("");
//                   System.out.println("third measurement:  " +measurements.get(2));
//                   System.out.println("");
                    break;
                }
            }


        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
        public String getFoodID(){
            return foodID;
    }
    }


