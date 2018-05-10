package com.xlog.tracker;

import android.util.Log;
import android.view.View;

import java.util.Scanner;

public class Item {
    private String name;
    private String info;
    private int count;

    public Item(String name, String info, int count) {
        this.name = name;
        this.info = info;
        this.count = count;
    }

    public Item(String itemInfo) {
        Scanner scanner = new Scanner(itemInfo);
        scanner.useDelimiter(",");
        name = scanner.next();
        count = scanner.nextInt();
        info = scanner.next();
        scanner.close();
    }

    public void print() {
        String info = this.name + ", " + this.info + " ," + Integer.toString(count);
        Log.d("Button Tag", info + "-----------------------");
    }

    public void increase() {
        this.count ++;
    }

    public void increase(int amount) {
        count += amount;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getStringCount() {
        return Integer.toString(count);
    }

    /**
     * @return String of Item data to be used for writing in items.dat
     */
    public String getDataString() {
        return name + "," + getStringCount() + "," + info;
    }

}
