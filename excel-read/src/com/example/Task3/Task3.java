package com.example.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {


        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        //  SearchIdenticalString();
        // PrintArrayList(SearchIdenticalString());
        System.out.println("Введите список всех миссий");
        Scanner sc = new Scanner(System.in);
        String s1;
        s1 = sc.nextLine();

        while (s1.compareTo("#") != 0) {
            s1 = sc.nextLine();
            String[] temp = s1.split("\t");
            list.add(s1);
        }
        System.out.println("Введите текущие миссии");
        Scanner sc2 = new Scanner(System.in);
        String s2;
        s2 = sc2.nextLine();

        while (s2.compareTo("#") != 0) {
            s2 = sc2.nextLine();
            String[] temp = s2.split("\t");
            list2.add(s2);
        }
        for (int i = 0; i < list2.size(); i++) {
            list.remove(list2.get(i));
        }
        System.out.println("После удаления");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}
