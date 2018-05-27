package com.xlog.tracker;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemListAdapter extends ArrayAdapter {

    private final Activity context;
    ArrayList<Item> items;
    private ItemFileIO itemFileIO;

    public ItemListAdapter(Activity context, ArrayList<Item> items, ItemFileIO itemFileIO) {
        super(context, R.layout.itemlist_row, items);
        this.items = items;
        this.context = context;
        this.itemFileIO = itemFileIO;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.itemlist_row, null, true);
        TextView nameTextField = (TextView) rowView.findViewById(R.id.tvItemName);
        final TextView infoTextField = (TextView) rowView.findViewById(R.id.tvItemInfo);
        Item item = items.get(position);
        nameTextField.setText(item.getName());
        infoTextField.setText(makeItemInfoString(item));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvItemName = (TextView) rowView.findViewById(R.id.tvItemName);
                String itemName = tvItemName.getText().toString();
                Log.d("Row click", "Item clicked: " + itemName);
                Intent intent = new Intent(context, ItemRecordsActivity.class);
                intent.putExtra("itemName", itemName);
                context.startActivity(intent);
            }
        });

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

    // todo: find better way to update list without making copy
    public void refreshListView(ArrayList<Item> items) {
        ArrayList<Item> newList = new ArrayList(items);
        clear();
        addAll(newList);
        items = newList;
        notifyDataSetChanged();
    }


}
