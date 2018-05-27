package com.xlog.tracker;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ItemRecord {
    private String dateTime;
    private String info;

    ItemRecord(String dataString) {
        parseDataString(dataString);
    }

    public String getInfo() {
        return info;
    }

    public String getDateTime() {
        return dateTime.toString();
    }

    // todo: Find better way to process this data
    private void parseDataString(String dataString) {
        Scanner scanner = new Scanner(dataString);
        StringBuilder sb = new StringBuilder();
        sb.append(scanner.next());
        sb.append(scanner.next());
        sb.append(scanner.next());
        sb.append(scanner.next());
        sb.append(scanner.next());
        String input = sb.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmm");
        try {
            Date date = sdf.parse(input);
            sdf = new SimpleDateFormat("HH:MM dd/MM/yyyy");
            dateTime = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        info = scanner.next();
        Log.d("Item Record", dateTime);
        Log.d("Item Record", "Info: " + info);
        scanner.close();
    }
}
