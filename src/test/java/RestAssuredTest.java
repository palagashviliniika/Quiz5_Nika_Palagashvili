import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
    @Test
    public void firstTask(){
        Response response = given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat().
                statusCode(200).
                extract().
                response();
//        System.out.println(response.asString());

        JsonPath jsonPath = new JsonPath(response.asString());

        String firstCircuit = jsonPath.getString("MRData.CircuitTable.Circuits.Location.country[1]");
        String fifthCircuit = jsonPath.getString("MRData.CircuitTable.Circuits.Location.country[5]");

//        System.out.println(firstCircuit);
//        System.out.println(fifthCircuit);

//        Response response1 = given().
//                when().
//                get("http://ergast.com/api/f1/circuits/americas.json").
//                then().
//                extract().response();
//
//        System.out.println(response1.asString());

//        String response2 = given().
//                when().
//                get("http://ergast.com/api/f1/circuits/hungaroring.json").
//                then().
//                extract().response().path("MRData.CircuitTable.Circuits");

        assertThat(firstCircuit, equalTo("USA"));
        assertThat(fifthCircuit, equalTo("Hungary"));
    }
}
