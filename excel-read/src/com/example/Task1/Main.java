package com.example.Task1;

import com.example.Task1.ExcelRead;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static HashMap<String, HashMap<String, Integer>> hashMapInvolvement;

    public static ArrayList<String> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        hashMapInvolvement = new HashMap<>();

        new ExcelRead();
        System.out.println("------------------0---------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("\nВведен список <название дп> \"tab\" <название активности>");

        for (int i = 0; i < list.size(); i++) {
            String s1 = list.get(i);
            if (s1.split("\t").length < 1 || s1.contains("#")) break;
            String[] temp = s1.split("\t");
            System.out.println("Департамент " + temp[0] + " миссия " + temp[1]);
            String DP = temp[0];
            String activity = temp[1];
            if (hashMapInvolvement.get(DP) == null) {
                //создаем новую запись про службу или дп
                HashMap<String, Integer> nameMissionAndCount = new HashMap<>();
                nameMissionAndCount.put(activity, 1);
                hashMapInvolvement.put(DP, nameMissionAndCount);
                System.out.println("Положили название департамента "  + DP + " название активности "  + activity);

            } else {
                // добавляем единицу к существующей записи
                HashMap<String, Integer> nameMissionAndCount = hashMapInvolvement.get(DP);
                if (nameMissionAndCount.get(activity) == null) {
                    // такой миссии не было и надо создать новую
                    nameMissionAndCount.put(activity, 1);
                    hashMapInvolvement.put(DP, nameMissionAndCount);
                    System.out.println("Положили название департамента "  + DP + " название активности "  + activity);

                } else {
                    // добавляем единичку к предыдущей миссии
                    int count = nameMissionAndCount.get(activity);
                    nameMissionAndCount.put(activity, count + 1);
                    hashMapInvolvement.put(DP, nameMissionAndCount);
                    System.out.println("Положили название департамента "  + DP + " название активности "  + activity + " в кол-ве " + count + 1);

                }

            }

        }

        PrintSimpleHashMap(hashMapInvolvement);


    }

    public static void PrintSimpleHashMap(HashMap<String, HashMap<String, Integer>> hashMap) {
        System.out.println();
        System.out.println();
        for (Map.Entry<String, HashMap<String, Integer>> entry : hashMap.entrySet()) {

            HashMap<String, Integer> list = entry.getValue();
            System.out.println();
            System.out.println(entry.getKey() + "\t" + list.size());
            System.out.println();
            for (Map.Entry<String, Integer> entry2 : list.entrySet()) {
                System.out.println(entry2.getKey() + "\t" + entry2.getValue());
            }

        }

    }
}
