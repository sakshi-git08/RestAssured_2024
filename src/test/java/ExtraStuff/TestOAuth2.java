package ExtraStuff;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class TestOAuth2 {
    @Test
    public void oAuth2_0() throws InterruptedException {

//        WebDriver driver = new ChromeDriver();
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//        driver.manage().window().maximize();
////        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("testingautomation333");
//        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Password_123");
//        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
//        driver.findElement(By.xpath("//span[text()='Continue']")).click();
//        Thread.sleep(4000);
//        String url = driver.getCurrentUrl();
        String newUrl = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AVG7fiQ7q7RrUjNinPC-i7vtRFK7IALkGgZ06r8J7GppccAXeYk4bDkJ-PjqY_5kmFOzsA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
        String partialCode = newUrl.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);
        String accessTokenResponse = given()
                .urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code").when()
                .log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js = new JsonPath(accessTokenResponse);
        String access_token = js.getString("access_token");
        System.out.println(accessTokenResponse);

        String response = given().queryParam("access_token", access_token).when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(response);


    }
}
