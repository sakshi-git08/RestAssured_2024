package ExtraStuff;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Random {


    @Test
    public void jsonIterateArr() {

        //base URI with Rest Assured class
        RestAssured.baseURI = "https://run.mocky.io/v3";

        //obtain Response from GET request
        Response res = given()
                .when()
                .get("/8ec8f4f7-8e68-4f4b-ad18-4f0940d40bb7");
        res.prettyPrint();

        //convert JSON to string
        JsonPath j = new JsonPath(res.asString());

        //get values of JSON array after getting array size
        int s = j.getInt("Location.size()");
        for (int i = 0; i < s; i++) {
            String state = j.getString("Location[" + i + "].State");
            String zip = j.getString("Location[" + i + "].zip");
            System.out.println(state);
            System.out.println(zip);
        }
    }


}
