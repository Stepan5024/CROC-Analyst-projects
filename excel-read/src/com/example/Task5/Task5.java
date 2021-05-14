package com.example.Task5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task5 {
    public static HashMap<String, HashMap<String, String>> hashMapInvolvement;
    public static HashMap<String, HashMap<String, Integer>> FinalPrint;

    public static ArrayList<String> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        hashMapInvolvement = new HashMap<>();
        FinalPrint = new HashMap<>();
        new ExcelRead();
        System.out.println("------------------0---------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("\nВведен список <название дп> \"tab\" <название активности>");

        for (int i = 0; i < list.size(); i++) {
            String s1 = list.get(i);
            if (s1.split("\t").length < 2 || s1.contains("#")) break;
            String[] temp = s1.split("\t");
            System.out.println("Департамент " + temp[0] + " крокКод  " + temp[1] + " Миссия " + temp[2]);
            String DP = temp[0];
            String code = temp[1];
            String activity = temp[2];

            if (hashMapInvolvement.get(activity) == null) {
                //впервые выидим активность создаем инициализацию и кладем 1
                HashMap<String, String> hashWorker = new HashMap<>();
                hashWorker.put(code, DP);
                hashMapInvolvement.put(activity, hashWorker);

            } else {
                // должны добавить нового сотрудника в список
                HashMap<String, String> hashWorker = hashMapInvolvement.get(activity);
                //запоминаем и добавляем нового сотрудника принявшего участие
                hashWorker.putIfAbsent(code, DP);
                hashMapInvolvement.put(activity, hashWorker);

            }

        }

        PrintSimpleHashMap(hashMapInvolvement);

        printFinalResult(FinalPrint);

    }

    public static void PrintSimpleHashMap(HashMap<String, HashMap<String, String>> hashMap) {
        System.out.println();
        System.out.println();
        for (Map.Entry<String, HashMap<String, String>> entry : hashMap.entrySet()) {

            HashMap<String, String> list = entry.getValue();
            System.out.println();
            System.out.println(entry.getKey() + "\t" + list.size());
            String activnost = entry.getKey();

            System.out.println();
            for (Map.Entry<String, String> entry2 : list.entrySet()) {
                System.out.println(entry2.getKey() + "\t" + entry2.getValue());

                String DP = entry2.getValue();
                if (FinalPrint.get(DP) == null) {
                    // в финальном массиве нет такого ДП надо его создать и положить 1 в такую активность
                    HashMap<String, Integer> hashActivnost = new HashMap<>();
                    hashActivnost.put(activnost, 1);
                    FinalPrint.put(DP, hashActivnost);
                } else {
                    HashMap<String, Integer> hashActivnost = FinalPrint.get(DP);
                    if(hashActivnost.get(activnost) == null){
                        hashActivnost.put(activnost, 1) ;
                    }else{
                        int count = hashActivnost.get(activnost);
                        hashActivnost.put(activnost, count + 1);
                    }
                    FinalPrint.put(DP, hashActivnost);
                }
            }


        }

    }

    public static void printFinalResult(HashMap<String, HashMap<String, Integer>> FinalPrint) {

        System.out.println();
        System.out.println();
        System.out.println("-------------------------*-------------------------");
        for (Map.Entry<String, HashMap<String, Integer>> entry : FinalPrint.entrySet()) {

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
