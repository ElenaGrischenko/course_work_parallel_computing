package com.company;

import java.io.File;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static int NUMBER_THREADS = 1;
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<File> files = new Files().NeededFiles();
        HashMap<String, File> InvertedIdx = new HashMap<String, File>();
        int Size = files.size();
        InvertedIndex ThreadArray[] = new InvertedIndex[NUMBER_THREADS];
        for(int i = 0; i < NUMBER_THREADS; i++) {
            ThreadArray[i] = new InvertedIndex(Size/NUMBER_THREADS * i, i == (NUMBER_THREADS - 1)?Size:Size/NUMBER_THREADS * (i-1), files);
            ThreadArray[i].start();
        }
        for (int i = 0; i < NUMBER_THREADS; i++) {
            ThreadArray[i].join();
        }
        FileWriter fw = new FileWriter( "C:\\InvertedIdx.txt" );
        for (int i = 0; i < NUMBER_THREADS; i++) {
            for(Map.Entry<String, String> entry: ThreadArray[i].getInvertedIdx().entrySet())
                fw.write(entry.getKey() + " - " + entry.getValue() + "\n");
        }
        fw.close();


        /*
        File folder = new File("C:\\aclImdb\\test\\neg");
        ArrayList<File> ff = new ArrayList<>();
        for ( k = N/50*28; k < N/50*29; k++) {
            for (File file : folder.listFiles()) {
                    if (file.getName().startsWith(String.valueOf(k))) {
                        ff.add(file);
                    }
            }
        }
        System.out.println(ff.size());

        for(k = 0; k < ff.size(); k++) {
            FileReader fr = new FileReader(ff.get(k).getAbsolutePath());
            Scanner scan = new Scanner(fr);
            //System.out.println(scan.nextLine());
            fr.close();
        }
        FileReader fr = new FileReader(ff.get(0).getAbsolutePath());
        Scanner scan = new Scanner(fr);

        //System.out.println(Arrays.toString(scan.nextLine().split("\\s*(\\s|,|!|'|:|;|-|_|\\.)\\s*")));
        fr.close();
        */
    }
}

