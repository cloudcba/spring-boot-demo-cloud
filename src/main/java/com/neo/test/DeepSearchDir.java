package com.neo.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DeepSearchDir {


    static Map<Integer, String> treeStrMap = new HashMap<>();


    /**
     * @param args
     */
    public static void main(String[] args) {
        File dir = new File("D:\\file");

        listDir2(dir, 0, "");

        for (Map.Entry<Integer, String> entry : treeStrMap.entrySet()) {
            int mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey + ":" + mapValue);
        }
    }


    //    static String treeStr = "";
    static int n = 0;


    private static void listDir2(File dir, int length, String treeStr) {

        File files[] = dir.listFiles();
        String treeArray[] = treeStr.split(",");
//处理闭环情况
        boolean isCycle = false;
        for (int i = 0; i < treeArray.length; i++) {
            String nodeName = treeArray[i];
            if (nodeName.equals(dir.getName())) {
//形成闭环时将不再拼接链条
                isCycle = true;
            }
        }


        if (!isCycle) {
            treeStr = treeStr + dir.getName() + ",";
        }

        //treeStr = treeStr + dir.getName() + ",";


        if (files.length == 0) {
            if (length >= 2) {

                treeStrMap.put(treeStrMap.size(), treeStr);
                n++;
                treeStr = treeStr.replace(dir.getName() + ",", "");
                if (n == length) {
                    treeStr = "";
                }
            } else {
                treeStrMap.put(treeStrMap.size(), treeStr);
                treeStr = "";
            }

        }

        //不处理闭环的情况
        for (File f : files) {
            if (f.isDirectory() && !isCycle) {
                listDir2(f, files.length, treeStr);
            }
//            if (f.isDirectory()) {
//                listDir2(f, files.length,treeStr);
//            }
        }
    }

}