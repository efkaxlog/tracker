package com.xlog.tracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemRecordsListAdapter extends ArrayAdapter {

    private final Activity context;
    private ItemFileIO itemFileIo;
    private ArrayList<ItemRecord> records;

    public ItemRecordsListAdapter(Activity context, ItemFileIO itemFileIo, ArrayList<ItemRecord> records) {
        super(context, R.layout.item_record_row, records);
        this.context = context;
        this.itemFileIo = itemFileIo;
        this.records = records;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_record_row, null, true);
        TextView itemInfo = (TextView) rowView.findViewById(R.id.tvItemInfo);
        TextView dateTime = (TextView) rowView.findViewById(R.id.tvDatetime);
        ItemRecord record = records.get(position);
        itemInfo.setText(record.getInfo());
        dateTime.setText(record.getDateTime());
        return rowView;
    }
}
