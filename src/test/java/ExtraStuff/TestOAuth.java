package ExtraStuff;

import io.restassured.authentication.OAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestOAuth {
    @Test
    public void test(){
//        RequestSpecification authServerRequest = new RequestSpecBuilder()
//                .setBaseUri("https://rahulshettyacademy.com/")
//                .addFormParam("cleint_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//                .addFormParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
//                .addFormParam("grant_type", "client_credentials")
//                .addFormParam("scope", "trust").build();
//                .setContentType(ContentType.JSON)
//                .setAccept(ContentType.JSON).build();

        String response = given().formParams("cleint_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type", "client_credentials")
                .formParams("scope", "trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String access_token = js.getString("access_token");
        String response2 = given().queryParam(access_token)
                .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
        System.out.println(response2);
    }
}
