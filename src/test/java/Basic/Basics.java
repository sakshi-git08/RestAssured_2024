package Basic;

import ExtraStuff.Payloads;
import ExtraStuff.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

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
        JsonPath json = ReUsableMethods.rawToJson(response);
        String placeId = json.get("place_id");
        System.out.println(placeId);

        //Add Place -> Update Place with New Address -> Get Place to validate if New Address is
        // present in response or not

        //update Place
        String newAddress = "Summer Wak, Africa";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId +"\",\n" +
                        "\"address\":\"" + newAddress +"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("/maps/api/place/update/json").then().log().all()
                .assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Get place
        String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId)
                .header("Content-Type","application/json")
                .when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String actualAddress = js.getString("address");
        Assert.assertEquals(actualAddress, newAddress);


    }



}
