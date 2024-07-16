package Basic;

import ExtraStuff.Payloads;
import io.restassured.path.json.JsonPath;

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

        //5. Print no of copies sold by RPA Course
        //6. Verify if Sum of all Course prices matches with Purchase Amount

    }
}
