package com.example.Task2;

import com.example.Task1.ExcelRead;

import java.util.ArrayList;
import java.util.HashMap;

public class Task2 {
    public static HashMap<String, HashMap<String, Integer>> hashMapInvolvement;
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<String> listTemp = new ArrayList<>();

        hashMapInvolvement = new HashMap<>();
        new ExcelRead2();
        System.out.println("------------------0---------------");
        int y = 0;
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            if (y == 30) {
                listTemp.add(s);
                s = "";
                y = 30;
            } else {
                y++;
                s = s + list.get(i) + "\t";
            }
        }
        System.out.println("\nВведен список <название дп> \"tab\" <название активности>");
        for (int i = 0; i < listTemp.size(); i++) {
            System.out.println(listTemp.get(i));
        }
    }
}
