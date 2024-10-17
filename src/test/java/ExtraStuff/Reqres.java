package ExtraStuff;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Reqres {

    public static String id = "2";

    @BeforeClass
    public void setup()
    {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "api";
    }


    @Test(enabled=true)
    public void IterateJSONArray()
    {
        Response res = given()
                .queryParam("page", "2")
                .when()
                .get("/users/");

        JsonPath js = new JsonPath(res.asString());

        int size = js.getInt("data.size()");
        System.out.println("size is: "+size);


        for(int i = 0; i < size; i++)
        {
            System.out.println(js.getString("data["+i+"].email"));
        }
    }
}


