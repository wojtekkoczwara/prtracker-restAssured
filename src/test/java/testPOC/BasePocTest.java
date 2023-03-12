package testPOC;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.GetSnatch;
import pojo.PostSnatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static testResources.RunJson.addRun5km;

public class BasePocTest {
    private String contentType = "Content-Type";
    private String applicationJson = "application/json";

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://localhost:8080/api/exercises";
    }

    @Test
    public void addRun5Pr(){

        given().log().all().header(contentType, applicationJson)
                .body(addRun5km())
                .when().post().then().log().all().assertThat().statusCode(200).body("scoreDate",
                        equalTo("2023-02-22")).body("secPerKilometer",
                        equalTo(400));

    }

    @Test
    public void addRun5PrReadFromFile() throws IOException {

        given().log().all().header(contentType, applicationJson)
                .body(new String(Files.readAllBytes(Paths.get("src/test/java/testResources/pr.json"))))
                .when().post().then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void getPrList(){
        given().when().get().then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void getPrListAndExtractResponseAsString(){
       String response = given().log().all().header(contentType, applicationJson)
                .body(addRun5km())
                .when().post().then().assertThat().statusCode(200).extract().asString();
        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);
        String secPerKm = jsonPath.getString("secPerKilometer");
        String id = jsonPath.getString("id");
        System.out.println(id);

        given().log().all().pathParam("id", id).when().get("/{id}").then().log().all().assertThat()
                .statusCode(200);
    }


//    deserialization
    @Test
    public void getAsPojo(){
        GetSnatch gs = given().pathParam("id", 5).expect().defaultParser(Parser.JSON)
                .when().get("/{id}").as(GetSnatch.class);

        System.out.println(gs.getExerciseName());
        System.out.println(gs.getPrWeight());
    }

    //    serialization
    @Test
    public void addAsPojo(){
        PostSnatch snatch = new PostSnatch("snatch", "2022-04-02", 160);
        System.out.println(given().header(contentType, applicationJson).
                body(snatch).expect().defaultParser(Parser.JSON)
                .when().post().then().assertThat().statusCode(200).extract().response().asString());
    }

}