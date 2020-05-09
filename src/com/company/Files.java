package com.company;

import java.io.File;
import java.util.ArrayList;

public class Files {

        public static int N1 = 12500;
        public static int N2 = 50000;
        public static int V = 26;

        public ArrayList<File> folders = new ArrayList<>();
        public ArrayList<File> files = new ArrayList<>();

        public ArrayList<File> NeededFiles() {

            folders.add(new File("C:\\aclImdb\\test\\neg"));
            folders.add(new File("C:\\aclImdb\\test\\pos"));
            folders.add(new File("C:\\aclImdb\\train\\neg"));
            folders.add(new File("C:\\aclImdb\\train\\pos"));
            folders.add(new File("C:\\aclImdb\\train\\unsup"));

            for (int ProcessingFolder = 0; ProcessingFolder < folders.size() - 1; ProcessingFolder++) {
                for (int FileBeginWith = N1 / 50 * V; FileBeginWith < N1 / 50 * (V + 1); FileBeginWith++) {
                    for (File file : folders.get(ProcessingFolder).listFiles()) {
                        if (file.getName().startsWith(String.valueOf(FileBeginWith))) {
                            files.add(file.getAbsoluteFile());
                        }
                    }
                }
            }

            for (int FileBeginWith = N2 / 50 * V; FileBeginWith < N2 / 50 * (V + 1); FileBeginWith++) {
                for (File file : folders.get(4).listFiles()) {
                    if (file.getName().startsWith(String.valueOf(FileBeginWith))) {
                        files.add(file.getAbsoluteFile());
                    }
                }
            }

            return files;
        }
}
