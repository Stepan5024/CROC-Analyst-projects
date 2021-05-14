package com.example.Task4;

import com.example.Task1.ExcelRead;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task4 {
    public static HashMap<String, ArrayList<int[]>> finalPrint = new HashMap<>();


    public static HashMap<String, HashMap<String, HashMap<Integer, Integer>>> hashMapEarnMoney;

    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        hashMapEarnMoney = new HashMap<>();
        new ExcelRead4();
        System.out.println("------------------0---------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("\nВведен список <название дп> \"tab\" <название активности>");

        for (int i = 1; i < list.size(); i++) {
            String s1 = list.get(i);
            if (s1.split("\t").length < 1 || s1.contains("#")) break;
            String[] temp = s1.split("\t");
            System.out.println("Департамент " + temp[0] + " Крок-код " + temp[1] + " кол-во бобров " + temp[2]);
            String DP = temp[0];
            String CROCcode = temp[1];
            int money = (int) Double.parseDouble(temp[2]);

            if (hashMapEarnMoney.get(DP) == null) {
                //создаем новую запись про службу или дп
                HashMap<String, HashMap<Integer, Integer>> CROCcodeAndCounts = new HashMap<>();
                HashMap<Integer, Integer> tempCounts = new HashMap<>();
                tempCounts.put(money, 1);
                CROCcodeAndCounts.put(CROCcode, tempCounts);
                hashMapEarnMoney.put(DP, CROCcodeAndCounts);

            } else {
                // добавляем единицу к существующей записи
                HashMap<String, HashMap<Integer, Integer>> CROCcodeAndCounts = hashMapEarnMoney.get(DP);
                HashMap<Integer, Integer> map = CROCcodeAndCounts.get(CROCcode);

                if (CROCcodeAndCounts.get(CROCcode) == null) {
                    // такого кроккода  не было и надо создать новый
                    map = new HashMap<Integer, Integer>();
                    map.put(money, 1);

                    CROCcodeAndCounts.put(CROCcode, map);
                    hashMapEarnMoney.put(DP, CROCcodeAndCounts);
                    //    System.out.println("Положили название департамента "  + DP + " название активности "  + activity);

                } else {
                    // добавляем единичку к предыдущей миссии
                    int countMoney = 0;
                    int countDel = 0;
                    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                        countMoney = e.getKey();
                        countDel = e.getValue();
                    }

                    map.put(money + countMoney, countDel + 1);
                    CROCcodeAndCounts.put(CROCcode, map);
                    hashMapEarnMoney.put(DP, CROCcodeAndCounts);
                    //   System.out.println("Положили название департамента "  + DP + " название активности "  + activity + " в кол-ве " + count + 1);

                }

            }

        }

        PrintSimpleHashMap(hashMapEarnMoney);
        PrintY(finalPrint);
    }
    public static void PrintY(HashMap<String, ArrayList<int[]>> list){
        System.out.println();
        System.out.println();
        for (Map.Entry<String, ArrayList<int[]>> entry : list.entrySet()) {
            System.out.println(entry.getKey());
            ArrayList<int[]> pos = entry.getValue();
            int[] t1 = pos.get(0);
            int[] t2 = pos.get(1);
            System.out.println("График 1");
            for (int i = 0; i < t1.length; i++) {
                System.out.println(t1[i]);
            }
            System.out.println("График 2");
            for (int i = 0; i < t2.length; i++) {
                System.out.println(t2[i]);
            }
        }

    }
    public static void PrintSimpleHashMap(HashMap<String, HashMap<String, HashMap<Integer, Integer>>> hashMap) {
        System.out.println();
        System.out.println();
        for (Map.Entry<String, HashMap<String, HashMap<Integer, Integer>>> entry : hashMap.entrySet()) {

            HashMap<String, HashMap<Integer, Integer>> listMap = entry.getValue();
            System.out.println();
            System.out.println(entry.getKey() + "\t" + " Привет " + list.size());
            System.out.println();
            String DP = entry.getKey();
            int[] Earns = new int[4]; // график по заработкам
            int[] Activitis = new int[5]; // график по вовлеченности
            Activitis[4] = list.size() - entry.getValue().size();



            for (Map.Entry<String, HashMap<Integer, Integer>> entry2 : listMap.entrySet()) {
                System.out.print(entry2.getKey());
                for (Map.Entry<Integer, Integer> entry3 : entry2.getValue().entrySet()) {
                    System.out.println("\t" + entry3.getKey() + "\t" + entry3.getValue());
                    int money = entry3.getKey();
                    int kol = entry3.getValue();

                    if (money > 3200) {
                        Earns[0]++;

                    } else if (money > 2000) {
                        Earns[1]++;

                    } else if (money > 1000) {
                        Earns[2]++;
                    } else if (money < 1000) {
                        Earns[3]++;
                    }

                    if (kol > 10) {
                        Activitis[0]++;
                    } else if (kol > 5) {
                        Activitis[1]++;
                    } else if (kol > 3) {
                        Activitis[2]++;
                    } else if (kol >= 1) {
                        Activitis[3]++;
                    }

                }
            }

            ArrayList<int[]> list = new ArrayList<>();
            list.add(Earns);
            list.add(Activitis);
            finalPrint.put(DP, list);

        }

    }
}
