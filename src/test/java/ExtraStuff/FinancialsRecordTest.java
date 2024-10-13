package ExtraStuff;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class FinancialsRecordTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\Downloads\\FinancialsSampleData\\Financials_Sample_Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        System.out.println(sheets);
        for (int i = 0; i < sheets; i++) {

            if (workbook.getSheetName(i).equalsIgnoreCase("Financials")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> ce = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("Account")) {
                        column = k;
                    }
                    k++;
                }
                System.out.println(column);
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Software/Hardware Expense")) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            if (true) {
                                System.out.println(cv.next().getStringCellValue());

                            } else if (cv.next().getCellType() == CellType.NUMERIC) {
                                System.out.println(cv.next().getNumericCellValue());
                            } else {

                            }
                        }
                    }

                }
            }
        }
    }
}