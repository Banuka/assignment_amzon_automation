package util;

import java.io.*;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    public static Object[] getTableArray(String FilePath) throws Exception {
        String[] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet sheet = ExcelWBook.getSheetAt(0);
            int noOfRows = sheet.getPhysicalNumberOfRows();
            tabArray = new String[noOfRows];
            Iterator<Row> itr = sheet.iterator();
            int index = 0;
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    tabArray[index] = cell.getStringCellValue();
                    index ++;
                }
                System.out.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tabArray;
    }


    public static String[][] getExcelData(String fileName){

        String[][] data = null;
        try
        {
            FileInputStream ExcelFile = new FileInputStream(fileName);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet sheet = ExcelWBook.getSheetAt(0);
            XSSFRow row = sheet.getRow(0);

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRows][noOfCols];

            for(int i =0; i<noOfRows;i++){
                for(int j=0;j<noOfCols;j++){
                    row = sheet.getRow(i);
                    cell= row.getCell(j);
                    data[i][j] = cell.getStringCellValue();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}