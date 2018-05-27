package com.xlog.tracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        //final ItemFileIO itemFileIo = new ItemFileIO(this);

        Button newItemButton = (Button) findViewById(R.id.btNewItemSubmit);
        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etName = (EditText) findViewById(R.id.etNewItemName);
                EditText etInfo = (EditText) findViewById(R.id.etNewItemInfo);
                if (Util.isEmpty(etName) || Util.isEmpty(etInfo)) {
                    if (Util.isEmpty(etName)) {
                        etName.setError("Item name can't be empty");
                    } else if (Util.isEmpty(etInfo)) {
                        etInfo.setError("Item info can't be empty");
                    }
                } else {
                    String itemName = etName.getText().toString();
                    String itemInfo = etInfo.getText().toString();
                    Item item = new Item(itemName, itemInfo, 0);
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("item", item);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }


}
