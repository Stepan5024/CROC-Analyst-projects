package com.example.Task4;

import com.example.Task2.Task2;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class ExcelRead4 {
    private final String FILE = "Отчет.xlsx";
    private final boolean directly = false;

    String[] data = new String[4];

    private XSSFWorkbook book;
    private XSSFSheet sheet;
    private int i = 0;

    public ExcelRead4() {
        if (directly)
            openBookDirectly(FILE);
        else
            openBook(FILE);
        if (book != null) {
            System.out.println("Книга Excel открыта");
            sheet = book.getSheetAt(1);
            if (sheet != null) {
                System.out.println("Страница открыта");
                readCells();
            } else {
                System.out.println("Страница не найдена");
            }
            try {
                if (!directly)
                    book.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Ошибка чтения файла Excel");
    }

    private void openBook(final String path) {
        try {
            File file = new File(path);
            book = (XSSFWorkbook) WorkbookFactory.create(file);

//			InputStream is = new FileInputStream(FILE);
//			book = (XSSFWorkbook) WorkbookFactory.create(is);
//			is.close();
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    private void openBookDirectly(final String path) {
        File file = new File(path);
        try {
            OPCPackage pkg = OPCPackage.open(file);
            book = new XSSFWorkbook(pkg);
            pkg.close();
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private void printCell(XSSFRow row, XSSFCell cell) {

        if (i == 3) {
            i = 0;
            String s = data[0] + "\t" + data[1] + "\t" + data[2];

            System.out.println("Строка равна " + s);
            Task4.list.add(s);
        }
        DataFormatter formatter = new DataFormatter();
        CellReference cellRef = new CellReference(row.getRowNum(),
                cell.getColumnIndex());
	/*	System.out.print(cellRef.formatAsString());
		System.out.print(" : ");*/

        // get the text that appears in the cell by getting
        // the cell value and applying any data formats
        // (Date, 0.00, 1.23e9, $1.23, etc)

		/*String text = formatter.formatCellValue(cell);
		System.out.print(text + " / ");*/

        // Вывод значения в консоль
        switch (cell.getCellTypeEnum()) {
            case STRING:
                System.out.println(cell.getRichStringCellValue()
                        .getString());
                data[i] = cell.getRichStringCellValue().getString();
                Task2.list.add(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.println(cell.getDateCellValue());
                    data[i] = String.valueOf(cell.getDateCellValue());
                    Task2.list.add(String.valueOf(cell.getDateCellValue()));
                } else {
                    System.out.println(cell.getNumericCellValue());
                    data[i] = String.valueOf(cell.getNumericCellValue());
                    Task2.list.add(String.valueOf(cell.getNumericCellValue()));
                }
                break;
            case BOOLEAN:
                System.out.println(cell.getBooleanCellValue());
                data[i] = String.valueOf(cell.getBooleanCellValue());
                Task2.list.add(String.valueOf(cell.getBooleanCellValue()));
                break;
            case FORMULA:
                System.out.println(cell.getCellFormula());
                data[i] = cell.getCellFormula();
                Task2.list.add(cell.getCellFormula());
                break;
            case BLANK:
                System.out.println();
                data[i] = String.valueOf(cell.getColumnIndex());
                Task2.list.add(null);
                break;
            default:
                data[i] = String.valueOf(cell.getColumnIndex());
                System.out.println();
        }
        i++;
        System.out.println("Счетчик " + i + " значение в data " + data[i]);
    }

    private void readCells() {
        // Определение граничных строк обработки
        int rowStart = Math.min(0, sheet.getFirstRowNum());
        int rowEnd = Math.max(100, sheet.getLastRowNum());

        for (int rw = rowStart; rw < rowEnd; rw++) {
            XSSFRow row = sheet.getRow(rw);
            if (row == null) {
                // System.out.println("row '" + rw + "' is not created");
                continue;
            }
            short minCol = row.getFirstCellNum();
            short maxCol = row.getLastCellNum();
            for (short col = minCol; col < maxCol; col++) {
                // @SuppressWarnings("deprecation")
                // XSSFCell cell = rw.getCell(col, XSSFRow.RETURN_BLANK_AS_NULL);
                XSSFCell cell = row.getCell(col);
                if (cell == null) {
                    // System.out.println("cell '" + col + "' is not created");
                    continue;
                }
                printCell(row, cell);
            }
        }
    }
}
