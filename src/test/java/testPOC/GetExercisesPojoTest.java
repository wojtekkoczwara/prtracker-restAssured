package testPOC;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.PRPojo;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetExercisesPojoTest {

    @Test
    public void getExercisesList(){

        RestAssured.baseURI = "http://localhost:8080/api/exercises";
        Response response = given().when().get().then().assertThat().statusCode(200).extract()
                .response();
        String listOfElementsInPlainString = response.asString();
        PRPojo[] prPojos = response.as(PRPojo[].class);
        List<PRPojo> prPojosList = Arrays.stream(prPojos).toList();
        System.out.println(prPojosList);
    }
}
