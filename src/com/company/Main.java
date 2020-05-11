package com.company;

import java.io.File;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static int NUMBER_THREADS = 2000;

    public static void main(String[] args) throws IOException {

        ArrayList<File> files = new Files().NeededFiles();

        HashMap<String, ArrayList<String>> FullInvertedIndex = new HashMap<String, ArrayList<String>>();

        long start = System.currentTimeMillis();

        InvertedIndex[] ThreadArray = new InvertedIndex[NUMBER_THREADS];

        for(int i = 0; i < NUMBER_THREADS; i++) {
            ThreadArray[i] = new InvertedIndex(files.size() / NUMBER_THREADS * i,
                    i == (NUMBER_THREADS - 1) ? files.size() : files.size() / NUMBER_THREADS * (i + 1),
                    files);
            ThreadArray[i].start();
        }

        for (int i = 0; i < NUMBER_THREADS; i++) {
            try {
                ThreadArray[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int OneThread = 0; OneThread < NUMBER_THREADS; OneThread++) {
            ThreadArray[OneThread].getInvertedIdx().forEach((k, v) -> FullInvertedIndex.merge(k, v, (v1, v2) -> {
                ArrayList<String> list = new ArrayList<>(v1);
                list.addAll(v2);
                return list;
            }));
        }

        System.out.println("Processing time: " + (System.currentTimeMillis() - start));

        FileWriter fw = new FileWriter( "C:\\InvertedIdx.txt" );

        for(Map.Entry<String, ArrayList<String>> entry: FullInvertedIndex.entrySet()) {
            fw.write(entry.getKey() + " : " + entry.getValue() + "\n");
        }

        fw.close();
    }
}

