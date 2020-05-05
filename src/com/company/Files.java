package com.company;

import java.io.File;
import java.util.ArrayList;

public class Files {

        int N = 12500;
        int V = 26;
        public ArrayList<File> folders = new ArrayList<>();
        public ArrayList<File> files = new ArrayList<>();

        public ArrayList<File> NeededFiles() {

            folders.add(new File("C:\\aclImdb\\test\\neg"));
            folders.add(new File("C:\\aclImdb\\test\\pos"));
            folders.add(new File("C:\\aclImdb\\train\\neg"));
            folders.add(new File("C:\\aclImdb\\train\\pos"));
            folders.add(new File("C:\\aclImdb\\train\\unsup"));

            for (int i = 0; i < folders.size() - 1; i++) {
                for (int k = N / 50 * V; k < N / 50 * (V + 1); k++) {
                    for (File file : folders.get(i).listFiles()) {
                        if (file.getName().startsWith(String.valueOf(k))) {
                            files.add(file);
                        }
                    }
                }
            }
            N = 50000;
            for (int k = N / 50 * V; k < N / 50 * (V + 1); k++) {
                for (File file : folders.get(4).listFiles()) {
                    if (file.getName().startsWith(String.valueOf(k))) {
                        files.add(file);
                    }
                }
            }

            return files;
        }
}
