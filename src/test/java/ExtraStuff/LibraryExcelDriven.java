package ExtraStuff;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LibraryExcelDriven {
    @Test
    public void addBook() throws IOException {
        DataDrivenTest d = new DataDrivenTest();
        ArrayList data = d.getData("AddBook", "RestAssured");
        Map<String, Object> jsonAsMap = new HashMap<>();
        //0 is your testcase name you can't use that
        jsonAsMap.put("name", data.get(1));
        jsonAsMap.put("isbn", data.get(2));
        jsonAsMap.put("aisle", data.get(3));
        jsonAsMap.put("author", data.get(4));
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json")
                .body(jsonAsMap)
                //.body("{\r\n    \"name\": \"Learn Appium Automation with Java\",\r\n    \"isbn\": \"book\",\r\n    \"aisle\": \"01\",\r\n    \"author\": \"John foer\"\r\n}\n")
                .when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
        JsonPath json = ReUsableMethods.rawToJson(response);
        String id = json.get("ID");
        System.out.println(id);

    }
}
