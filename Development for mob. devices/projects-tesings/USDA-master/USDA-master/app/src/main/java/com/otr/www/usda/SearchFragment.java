package com.otr.www.usda;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;

/**
 * Created by pingyaoooo on 2016/4/19.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {
    private Button searchButton;
    private EditText searchText;
    String search;
    public  static OkHttpClient client;

    private Callbacks mCallbacks = sDummyCallbacks;

    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onButtoneClicked(String foodName);
    }
    private static Callbacks sDummyCallbacks = new Callbacks() {

        @Override
        public void onButtoneClicked(String foodName) {

        }
    };
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Activities containing this fragment must implement its callbacks.
        if (!(context instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchButton = (Button) view.findViewById(R.id.button_search);
        searchText = (EditText) view.findViewById(R.id.edit_text_search);

        searchButton.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {


        search = searchText.getText().toString().trim();
        if (search.length() != 0) {

            mCallbacks.onButtoneClicked(search);
        } else {
            Toast.makeText(getContext(), "please enter content", Toast.LENGTH_SHORT).show();
        }

    }

}
