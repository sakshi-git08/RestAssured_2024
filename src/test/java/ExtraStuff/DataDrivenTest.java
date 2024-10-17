package ExtraStuff;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.stream.ImageInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDrivenTest {
    public ArrayList<String> getData(String testcaseName, String sheetName) throws IOException {
        ArrayList<String> a = new ArrayList<>();
        //fileInput
        FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\OneDrive\\DemoData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        //navigating to exact sheet.
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
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
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
                        //after you grab purchase test case row pull all the data of that row and feed into test case
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                a.add(c.getStringCellValue());
                            } else {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }

        }
        return a;
    }
}

//Identify the test case column by scanning the entire first row
//once column is identified then scan entire test cases column to identify purchase Test case row
//after you grab purchase test case row pull all the data of that row and feed into test case



