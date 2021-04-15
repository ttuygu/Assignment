package Rest_assured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class DeleteMethod {

        @Test
        public void deleteEmployee() {

            RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

            Response response = null;

            try {
                response = RestAssured.given()
                        .contentType(ContentType.JSON)
                        .delete("/delete/11400");
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Response :" + response.asString());
            //log.info(respose.asString())
            System.out.println("Status Code :" + response.getStatusCode());

        }
    }
