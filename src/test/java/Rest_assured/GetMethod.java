package Rest_assured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetMethod {
    /* API automation using Rest-assured or Karate API Test
1. Use any 2 methods(Get, and Delete) from end-points listed in
http://dummy.restapiexample.com/
2. Perform assertions for
   - Get & Delete - Status code to be Successful.
   - Get - Return specific Employee details(Any).
   - Delete - "message": "successfully! deleted Records".
     */

    @Test
    public void test01() {
        //testing get method
        //Using restAssured library, in maven built framework doing my automation.
        given().
                when().
                get("http://dummy.restapiexample.com/api/v1/employee/1").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test02() {
        //here validating the previous
        given().//precondition
                when().//condition
                get("http://dummy.restapiexample.com/api/v1/employee/1").//action
                then().//post condition
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                header("Content-Length", equalTo("4551"));


    }
}
