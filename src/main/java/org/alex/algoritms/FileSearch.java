package org.alex.algoritms;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {
    public static void search(File root, ArrayList<File> al) {
        if (root.isDirectory()){
           File[] directoryFiles =  root.listFiles();
           if (directoryFiles != null){
               for (File file : directoryFiles) {
                   if (file.isDirectory()){
                       search(file, al);
                   } else{
                       if (file.getName().endsWith(".jpg")) al.add(file);
                   }
               }
           }
        }
    }

}
