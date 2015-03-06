package com.kerboocorp.next.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kerboocorp.next.R;
import com.kerboocorp.next.adapters.StuffAdapter;
import com.kerboocorp.next.managers.StuffManager;
import com.melnykov.fab.FloatingActionButton;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.stuffList)
    RecyclerView stuffListView;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private LinearLayoutManager linearLayoutManager;
    private StuffAdapter stuffAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);


        linearLayoutManager = new LinearLayoutManager(this);
        stuffListView.setLayoutManager(linearLayoutManager);
        stuffListView.setItemAnimator(new DefaultItemAnimator());

        stuffAdapter = new StuffAdapter(R.layout.list_item_stuff, this);
        stuffListView.setAdapter(stuffAdapter);

        stuffAdapter.addItemList(StuffManager.getInstance().findStuffList());

        fab.attachToRecyclerView(stuffListView);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
