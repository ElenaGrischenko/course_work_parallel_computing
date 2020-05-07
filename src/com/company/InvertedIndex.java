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
    HashMap<String, ArrayList<String>> HashMapOneThread = new HashMap<String, ArrayList<String>>();
    public InvertedIndex(int startIndex, int endIndex, ArrayList<File> processingFiles) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.processingFiles = processingFiles;
    }

    public HashMap<String, ArrayList<String>> getInvertedIdx() {
        return HashMapOneThread;
    }

    public void addWordToHashMap(String key, int WordPosition) {
        if(HashMapOneThread.containsKey(key)) {
            ArrayList<String> list = HashMapOneThread.get(key);
            list.add(String.valueOf(WordPosition + 1));
            HashMapOneThread.put(key, list);
        }
        else {
            ArrayList<String> list = new ArrayList<String>();
            list.add(String.valueOf(WordPosition + 1));
            HashMapOneThread.put(key, list);
        }
    }

    @Override
    public void run() {
        for (int idx = startIndex; idx < endIndex; idx++) {
            try (BufferedReader file = new BufferedReader(new FileReader((File) processingFiles.get(idx)))) {
                String line;
                String[] words = new String[0];
                while ((line = file.readLine()) != null) {
                    words = line.replaceAll("[^a-zA-Z0-9 ]", "")
                            .replaceAll(" +", " ")
                            .trim()
                            .toLowerCase()
                            .split(" ");
                }
                int PositionInLine = 0;
                for (String word: words) {
                     addWordToHashMap(word, PositionInLine);
                     PositionInLine++;
                }
                for (String word: words) {
                    ArrayList<String> list = HashMapOneThread.get(word);
                    if (!list.contains(processingFiles.get(idx).getParent() + "\\" + processingFiles.get(idx).getName())) {
                        list.add(processingFiles.get(idx).getParent() + "\\" + processingFiles.get(idx).getName());
                        HashMapOneThread.put(word, list);
                    }
                }
            }catch (IOException e) {
                System.out.println("File:  not found.");
            }
        }
    }
}
