package com.xlog.tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ItemRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_records);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        Log.d("Item name", itemName);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRecords);
        toolbar.setTitle(itemName);
        setSupportActionBar(toolbar);

        ItemFileIO itemFileIo = new ItemFileIO(this);
        ArrayList<ItemRecord> records = new ArrayList<>();
        try {
            records = itemFileIo.getItemRecords(itemName);
            Collections.reverse(records); // so most recent records are on top
        } catch (IOException e) {
            e.printStackTrace();
        }

        ItemRecordsListAdapter adapter = new ItemRecordsListAdapter(this, itemFileIo, records);
        ListView recordList = (ListView) findViewById(R.id.lvRecords);
        recordList.setAdapter(adapter);
    }



}
