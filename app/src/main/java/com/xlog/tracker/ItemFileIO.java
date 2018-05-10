package com.xlog.tracker;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ItemFileIO {

    private Context context;
    private File itemsFolder;
    private File itemsFile;

    public ItemFileIO(Context context) {
        this.context = context;
        itemsFolder = new File(context.getFilesDir() + "/items");
        itemsFile = new File(itemsFolder + "/items.dat");
        itemsFolder.mkdirs();
        if (!itemsFile.exists()) {
            try {
                itemsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Item> load() throws IOException {
        ArrayList<Item> items = new ArrayList<>();
        BufferedReader reader = getBufferedReader(itemsFile);
        String line;
        Log.d("Load tag", "Before loop");
        while ((line = reader.readLine()) != null) {
            Log.d("Load tag", "line read:\n" + line);
            Item item = new Item(line);
            items.add(item);
            item.print();
        }
        Log.d("Load tag", "After loop");
        reader.close();
        return items;
    }

    public void writeItem(Item item, int amount) {
        try {
            File itemFile = new File(itemsFolder.getAbsolutePath() + "/" + item.getName() + ".dat");
            BufferedWriter bw = new BufferedWriter(new FileWriter(itemFile, true));
            String content = getDateTimeString() + " " + Integer.toString(amount) + "\n";
            Log.d("Write item", "File path: " + itemFile.getAbsolutePath());
            Log.d("Write item", "Content: " + content);
            bw.append(content);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateItemsFile(item);
    }

    /**
     * Finds the item line in file and updates it.
     * @param item
     */
    private void updateItemsFile(Item item) {
        String desiredLine = item.getDataString(); // the line in file meant to be found and updated.
        BufferedReader reader = getBufferedReader(itemsFile);
        StringBuilder strBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(item.getName() + ",")) { // todo: find other way to match string
                    line = item.getDataString();
                }
                strBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            Log.d("Update items file", "IOException while BufferedReader.readLine()");
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(itemsFile));
            bw.append(strBuilder);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            Log.d("Update items file", "IOException while creating new BufferedWriter()");
        }
    }

    private String getDateTimeString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH mm");
        Date date = new Date();
        return formatter.format(date);
    }

    private BufferedReader getBufferedReader(File file) {
        BufferedReader reader = null;
        FileInputStream fis = null;
        Log.d("getBufferedReader()", file.getAbsolutePath());
        try {
            fis = new FileInputStream(file);
            InputStreamReader streamReader = new InputStreamReader(fis);
            reader = new BufferedReader(streamReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }
}
