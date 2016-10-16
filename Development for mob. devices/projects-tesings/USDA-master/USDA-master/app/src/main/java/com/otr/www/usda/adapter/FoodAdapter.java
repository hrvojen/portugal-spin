package com.otr.www.usda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.otr.www.usda.bin.Food;

import java.util.List;

/**
 * Created by pingyaoooo on 2016/4/19.
 */
public class FoodAdapter extends ArrayAdapter<Food> {

    private List<Food> list;
    private LayoutInflater inflater;


    public FoodAdapter(Context context, int resource, List<Food> objects) {
        super(context, resource, objects);
        list = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Food getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.valueOf(list.get(position).getNdbno());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);

        textView.setText(list.get(position).getName());

        return textView;
    }
}
