package Basic;

import Payload.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) {
        //validate if Add Place API is working as expected.

        //given - all input details
        //when - Submit the api - resource, HTTP method
        //Then - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payloads.addPlace()).when().post("maps/api/place/add/json").then()
                .assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath json = new JsonPath(response);
        String placeId = json.get("place_id");
        System.out.println(placeId);
    }
    //Add Place -> Update Place with New Address -> Get Place to validate if New Address is
    // present in response or not

}
