package ExtraStuff;

import java.io.IOException;
import java.util.ArrayList;

public class TestExcelDataDriven {
    public static void main(String[] args) throws IOException {
        DataDrivenTest d = new DataDrivenTest();
        ArrayList<String> data = d.getData("Add Profile", "testdata");
        for(int i = 0; i < data.size(); i++){
            System.out.println(data.get(i));
        }
    }
}
