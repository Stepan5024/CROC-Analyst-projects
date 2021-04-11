import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {





    public static void main(String[] args) throws IOException {
        HashMap<String, HashMap<String, Integer>> hashMapInvolvement = new HashMap<>();

        System.out.println("Введите список <название дп> \"tab\" <название активности>");


        Scanner sc = new Scanner(System.in);
        String s1;
        s1 = sc.nextLine();

        while (s1.compareTo("#") != 0) {
            s1 = sc.nextLine();
            if (s1.split("\t").length < 1 || s1.contains("#")) break;
            String[] temp = s1.split("\t");
         //   System.out.println("Департамент " + temp[0] + " миссия " + temp[1]);
            String DP = temp[0];
            String activity = temp[1];
            if(hashMapInvolvement.get(DP) == null){
                //создаем новую запись про службу или дп
                HashMap<String, Integer> nameMissionAndCount = new HashMap<>();
                nameMissionAndCount.put(activity, 1);
                hashMapInvolvement.put(DP, nameMissionAndCount);
            }else{
                // добавляем единицу к существующей записи
                HashMap<String, Integer> nameMissionAndCount = hashMapInvolvement.get(DP);
                if(nameMissionAndCount.get(activity) == null){
                    // такой миссии не было и надо создать новую
                    nameMissionAndCount.put(activity, 1);
                    hashMapInvolvement.put(DP, nameMissionAndCount);
                }else{
                    // добавляем единичку к предыдущей миссии
                    int count = nameMissionAndCount.get(activity);
                    nameMissionAndCount.put(activity, count + 1);
                    hashMapInvolvement.put(DP, nameMissionAndCount);
                }

            }

        } // конец цикла while


        PrintSimpleHashMap(hashMapInvolvement);



    }
    public static void PrintSimpleHashMap(HashMap<String, HashMap<String, Integer>> hashMap) {
        System.out.println();
        System.out.println();
        for (Map.Entry<String,HashMap<String, Integer>> entry : hashMap.entrySet()) {

            HashMap<String, Integer> list = entry.getValue();
            System.out.println(entry.getKey() + "\t" + list.size());

            for (Map.Entry<String, Integer> entry2 : list.entrySet()) {
                System.out.println(entry2.getKey() + "\t" + entry2.getValue());
            }

        }

    }
}
