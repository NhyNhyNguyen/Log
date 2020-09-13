package com.company;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Data> datas = new ArrayList<Data>();
        Map<Integer, Integer> results = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\Log.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(1);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int[] array = new int[4];
                int i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

                    DataFormatter formatter = new DataFormatter();
                    array[i] = Integer.parseInt(formatter.formatCellValue(cell, evaluator));
                    i++;
                }
                datas.add(new Data(array[0], array[1], array[2], array[3]));
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int k = 0;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getSourceType() == 0 && results.get(datas.get(i).getUserId()) == null) {
                int sum = 0;
                for (int j = 0; j < i; j++) {
                    if (datas.get(j).getUserId() == datas.get(i).getUserId()) {
                        sum += datas.get(j).getBonus();
                    }
                }
                sum -= (datas.get(i).getTotal() - datas.get(i).getBonus());
                System.out.println(k++ + " " + datas.get(i).getUserId() + " " + sum);
                results.put(datas.get(i).getUserId(), sum);
            }
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Result");

        int row_num = 0;
        for (Integer key : results.keySet()) {
            Row row = sheet.createRow(row_num++);
            Cell cell = row.createCell(0);
            cell.setCellValue(key);
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(results.get(key));
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\LogResult1.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
