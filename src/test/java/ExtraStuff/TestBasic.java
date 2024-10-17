package ExtraStuff;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TestBasic {
    @Test
    public void addPet(){
        String name ="Sherlock";
        String status="new";
        RestAssured.baseURI="https://petstore.swagger.io/v2";
        Response responseBody=RestAssured.given().log().headers().contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParam("name",name)
                .formParam("status",status)
                .when()
                .post("pet/123")
                .then().statusCode(201).extract().response();
//        String type=form(responseBody).get("Type");
//        String type1=form(responseBody).get("message");
//        JsonPath jsonpath=new JsonPath(responseBody);
//        String data=jsonpath.getString("message");
    }

    ResponseSpecification responseSpecification;
    @BeforeClass
    public void setupResponseSpecification()
    {
        // Create a ResponseSpecification
        responseSpecification=  RestAssured.expect();
        responseSpecification.contentType(ContentType.JSON);
        responseSpecification.statusCode(200);
        responseSpecification.time(lessThan(5000L));
        responseSpecification.statusLine("HTTP/1.1 200 OK");

    }


    @Test
    public void random() {
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Sakshi");
        requestParams.put("job", "SDET-2");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toString());
        Response response = httpRequest.request(Method.POST,"/users");
        int responseCode = response.statusCode();
        String createdAt = response.path("createdAt");
        System.out.println(createdAt);
        assertThat(response.<String>path("name"), equalTo("Sakshi"));
//        assertEquals(response.path("name"), equalTo("Sakshi"));
        long timeTaken = response.time();
        System.out.println(timeTaken);
        response.prettyPrint();



    }



}

