package com.xlog.tracker;

import android.widget.EditText;

public final class Util {

    public static boolean isEmpty(EditText et) {
        if (et.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }
}
