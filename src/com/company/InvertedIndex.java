package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InvertedIndex extends Thread {
    int startIndex;
    int endIndex;
    ArrayList<File> processingFiles;
    HashMap<String, String> HashMapOneThread = new HashMap<String, String>();
    public InvertedIndex(int startIndex, int endIndex, ArrayList<File> processingFiles) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.processingFiles = processingFiles;
    }

    public HashMap<String, String> getInvertedIdx() {
        return HashMapOneThread;
    }

    @Override
    public void run() {

        for (int idx = startIndex; idx < endIndex; idx++) {
            try (BufferedReader file = new BufferedReader(new FileReader((File) processingFiles.get(idx)))) {
                String line;
                String[] words = new String[0];
                while ((line = file.readLine()) != null) {
                    words = line.replaceAll("[\\W]", " ").toLowerCase().trim().split(" ");
                }
                for (String word: words) {
                    if(HashMapOneThread.containsKey(word)) {
                        HashMapOneThread.put(word, (HashMapOneThread.get(word) + ", " + processingFiles.get(idx)));
                    }
                    else {
                        HashMapOneThread.put(word, processingFiles.get(idx).getName());
                    }
                }

            }catch (IOException e) {
                System.out.println("File:  not found.");
            }
        }

    }
}
