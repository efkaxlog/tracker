package com.xlog.tracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemListAdapter extends ArrayAdapter {

    private final Activity context;
    ArrayList<Item> items;
    private ItemFileIO itemFileIO;

    public ItemListAdapter(Activity context, ArrayList<Item> items, ItemFileIO itemFileIO) {
        super(context, R.layout.itemlist_row, items.toArray());
        this.items = items;
        this.context = context;
        this.itemFileIO = itemFileIO;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.itemlist_row, null, true);
        TextView nameTextField = (TextView) rowView.findViewById(R.id.itemTextView);
        final TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextView);
        Item item = items.get(position);
        nameTextField.setText(item.getName());
        infoTextField.setText(makeItemInfoString(item));
        Button addButton = (Button) rowView.findViewById((R.id.addButton));
        addButton.setTag(item);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = (Item) v.getTag();
                item.increase();
                itemFileIO.writeItem(item, 1);
                infoTextField.setText(makeItemInfoString(item));
            }
        });
        return rowView;
    }

    private String makeItemInfoString(Item item) {
        return item.getStringCount() + " " + item.getInfo();
    }


}
