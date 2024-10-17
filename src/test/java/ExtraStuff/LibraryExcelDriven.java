package ExtraStuff;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LibraryExcelDriven {
    @Test
    public void addBook() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json")
                .body("{\r\n    \"name\": \"Learn Appium Automation with Java\",\r\n    \"isbn\": \"book\",\r\n    \"aisle\": \"01\",\r\n    \"author\": \"John foer\"\r\n}\n")
                .when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
        JsonPath json = ReUsableMethods.rawToJson(response);
        String id = json.get("ID");
        System.out.println(id);

    }
}
