package ecommerce;

import ecommerce.pojo.request.LoginRequest;
import ecommerce.pojo.response.AddProductResponse;
import ecommerce.pojo.response.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class EcommerceAPITest {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("sakshiagg428@gmail.com");
        loginRequest.setUserPassword("Sakshi@123");
        RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);

        LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login")
                .then().log().all().extract().response().as(LoginResponse.class);
        System.out.println(loginResponse.getToken());
        String token = loginResponse.getToken();
        System.out.println(loginResponse.getUserId());
        String userId = loginResponse.getUserId();


        //Add Product
        System.out.println("Adding product now...");
        RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token).build();

        RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq)
                .param("productName", "Adidas Women Shoes")
                .param("productAddedBy", userId).param("productCategory", "fashion")
                .param("productSubCategory", "shoes").param("productPrice", 11500)
                .param("productDescription", "Addias Originals").param("productFor", "women")
                .multiPart("productImage", new File("C:\\Users\\DELL\\Downloads\\81NKlRw7m9L._AC_UL1500_.jpg"));

        AddProductResponse addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all()
                .extract().response().as(AddProductResponse.class);
        String productId = addProductResponse.getProductId();

    }
}
