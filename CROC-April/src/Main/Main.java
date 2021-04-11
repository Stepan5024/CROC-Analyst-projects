package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, HashMap<String, Integer>> hashMapInvolvement = new HashMap<>();

        // получаем файл в формате xls
        FileInputStream file = new FileInputStream(new File("C:\\simplexcel.xls"));

// формируем из файла экземпляр HSSFWorkbook
        HSSFWorkbook workbook = new HSSFWorkbook(file);

// выбираем первый лист для обработки
// нумерация начинается с 0
        HSSFSheet sheet = workbook.getSheetAt(0);

// получаем Iterator по всем строкам в листе
        Iterator<Row> rowIterator = sheet.iterator();

// получаем Iterator по всем ячейкам в строке
        Iterator<Cell> cellIterator = row.cellIterator();

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
