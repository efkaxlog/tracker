package com.xlog.tracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView itemList;
    private ItemListAdapter listAdapter;
    private ItemFileIO itemFileIO;
    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemFileIO = new ItemFileIO(this);
        try {
            items = itemFileIO.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listAdapter = new ItemListAdapter(this, items, itemFileIO);
        itemList = (ListView) findViewById(R.id.item_list);
        itemList.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_item:
                newItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void newItem() {
        Log.d("Action menu add", "Adding new item...");
        Intent intent = new Intent(this, NewItemActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Item item = (Item) data.getSerializableExtra("item");
                itemFileIO.newItem(item);
                items.add(item);
                listAdapter.refreshListView(items);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // nothing
            }
        }
    }

    private void itemRecords(String itemName) {

    }
}