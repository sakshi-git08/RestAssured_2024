package Basic;

import ExtraStuff.Payloads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class TestCourses {
    public static void main(String[] args) {
    /*
    In json [] -> JSONArray
    Note: This size() method can only be applied on Array
     */

        JsonPath js = new JsonPath(Payloads.coursePrice());
        //1. Print No of courses returned by API
        int coursesSize = js.get("courses.size()"); // applicable for json array
        System.out.println(coursesSize);
        //2.Print Purchase Amount
        int purchaseAmt = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmt);
        //3. Print Title of the first course
        String firstCourseTitle = js.get("courses[0].title");
        System.out.println(firstCourseTitle);
        //4. Print All course titles and their respective Prices
        for (int i = 0; i < coursesSize; i++) {
            String courseTitle = js.getString("courses[" + i + "].title");
            int price = js.get("courses[" + i + "].price");
            System.out.println(courseTitle + " and it's price is : " + price);
        }

        //5. Print no of copies sold by RPA Course
//        String copies = null;
        for (int i = 0; i < coursesSize; i++) {
            String courseTitle = js.getString("courses[" + i + "].title");

            if (courseTitle.equalsIgnoreCase("RPA")) {
                int copies = js.getInt("courses[" + i + "].copies");
                System.out.println(copies + " sold by RPA Course");
                break;
            }
        }
        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for (int i = 0; i < coursesSize; i++) {
            int price = js.get("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            sum += price * copies;
            System.out.println(sum);
        }
        Assert.assertEquals(sum, purchaseAmt);

    }
}
