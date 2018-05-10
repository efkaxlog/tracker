package com.xlog.tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView itemList;
    private ItemFileIO itemFileIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemFileIO = new ItemFileIO(this);
        ArrayList<Item> items = null;
        try {
            items = itemFileIO.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ItemListAdapter listAdapter = new ItemListAdapter(this, items, itemFileIO);
        itemList = (ListView) findViewById(R.id.item_list);
        itemList.setAdapter(listAdapter);
    }
}
