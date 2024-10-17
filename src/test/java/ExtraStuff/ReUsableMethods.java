package ExtraStuff;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class ReUsableMethods {
    public static JsonPath rawToJson(String response){
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }
}
