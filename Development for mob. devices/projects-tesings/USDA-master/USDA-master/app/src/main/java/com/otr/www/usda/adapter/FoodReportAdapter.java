package com.otr.www.usda.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.otr.www.usda.bin.FoodReport;

/**
 * Created by pingyaoooo on 2016/4/20.
 */
public class FoodReportAdapter<T> extends BaseAdapter {
    private FoodReport report;

    public FoodReportAdapter(FoodReport report) {
        this.report = report;
    }

    @Override

    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
