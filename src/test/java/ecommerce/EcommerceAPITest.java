package ecommerce;

import ecommerce.pojo.request.CreateOrderRequest;
import ecommerce.pojo.request.LoginRequest;
import ecommerce.pojo.request.Orders;
import ecommerce.pojo.response.AddProductResponse;
import ecommerce.pojo.response.CreateOrderResponse;
import ecommerce.pojo.response.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.net.CacheResponse;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommerceAPITest {
    private static String BASE_URI = "https://rahulshettyacademy.com";

    public static void main(String[] args) {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("sakshiagg428@gmail.com");
        loginRequest.setUserPassword("Sakshi@123");
        RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(loginRequest);
//relaxedHTTPSValidation: It bypasses any SSL certification if required in the api
        LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login")
                .then().log().all().extract().response().as(LoginResponse.class);
        System.out.println(loginResponse.getToken());
        String token = loginResponse.getToken();
        System.out.println(loginResponse.getUserId());
        String userId = loginResponse.getUserId();


        //Add Product
        System.out.println("Adding product now...");
        RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri(BASE_URI)
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

        //Create order
        System.out.println("--------------Create order----------------------");
        RequestSpecification createOrderReqSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI).addHeader("Authorization", token)
                .setContentType(ContentType.JSON).build();
        Orders orders = new Orders();
        orders.setCountry("India");
        orders.setProductOrderedId(productId);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList.add(orders);
        createOrderRequest.setOrders(ordersList);


        RequestSpecification createOrderReq = given().log().all().spec(createOrderReqSpec)
                .body(createOrderRequest);
        CreateOrderResponse createOrderResponse = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all()
                .extract().response().as(CreateOrderResponse.class);
        System.out.println("Product Id is : " + createOrderResponse.getProductOrderId());
        System.out.println("Order Id is : " + createOrderResponse.getOrders());
        System.out.println(createOrderResponse.getMessage());

        //Delete Product
        System.out.println("---------Delete Product---------");
        RequestSpecification deleteProductReq = new RequestSpecBuilder()
                .setBaseUri(BASE_URI).addHeader("Authorization", token)
                .setContentType(ContentType.JSON).build();
        RequestSpecification deleteProdReq = given().log().all().spec(deleteProductReq)
                .pathParams("productId", productId);
        String deletedProduct = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response().asString();
        JsonPath js = new JsonPath(deletedProduct);
        String message = js.getString("message");
        Assert.assertEquals("Product Deleted Successfully", message);
    }
}
