package ExtraStuff;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class DataDrivenTest {
    //Identify the test case column by scanning the entire first row
    //once column is identified then scan entire test cases column to identify purchase Test case row
    //after you grab purchase test case row pull all the data of that row and feed into test case
    public static void main(String[] args) throws IOException {
        //fileInput
        FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\OneDrive\\DemoData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        //navigating to exact sheet.
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                //Identify the test case column by scanning the entire first row
                Iterator<Row> rows = sheet.iterator(); //sheet is a collection of rows
                Row firstRow = rows.next();
                Iterator<Cell> ce = firstRow.cellIterator(); //row is a collection of cells
                int k = 0;
                int column = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
                        column = k;

                    }
                    k++;
                }
                System.out.println(column);
                //once column is identified then scan entire test cases column to identify purchase Test case row
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase")) {
                        //after you grab purchase test case row pull all the data of that row and feed into test case
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            System.out.println(cv.next().getStringCellValue());
                        }
                    }
                }
            }
        }
    }
}
