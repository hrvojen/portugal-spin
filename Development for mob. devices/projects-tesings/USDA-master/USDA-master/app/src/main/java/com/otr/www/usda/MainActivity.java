package com.otr.www.usda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.otr.www.usda.bin.Person;

public class MainActivity extends AppCompatActivity
        implements ItemListFragment.Callbacks,SearchFragment.Callbacks {

    private FragmentManager fragmentManager;
    private boolean flag;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_center, new SearchFragment())
                .commit();


        Gson gson = new Gson();
        Person person = new Person("aaalili");
        Person.Chileren chileren = new Person.Chileren("vavava");
        person.setChileren(chileren);

        String json = gson.toJson(person);
        Person person1 = gson.fromJson(json, Person.class);
        Log.i("RAG", json);
        Log.i("RAG", person1.getName());
        Log.i("RAG", person1.getChileren().getName());

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onItemSelected(String id) {
        Intent detailIntent = new Intent(this, ItemDetailActivity.class);
        detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
        startActivity(detailIntent);
    }

    @Override
    public void onButtoneClicked(String foodName) {

        Bundle args = new Bundle();
        args.putString("FOOD_NAME", foodName);


        ItemListFragment itemListFragment = new ItemListFragment();
        itemListFragment.setArguments(args);

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_center, itemListFragment)
                .commit();
        flag = true;
    }

    @Override
    public void onBackPressed() {
        if (flag) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_center, new SearchFragment())
                    .commit();
            flag = false;
        }else {


            super.onBackPressed();
        }

    }


}
