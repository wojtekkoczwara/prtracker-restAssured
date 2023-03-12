package testPOC;

import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.transform.stc.POJO;
import helpers.ResourcesProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.GetSnatch;
import pojo.PostSnatch;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SnatchRequestResponseE2ESpecTest {

    private RequestSpecification requestSpecBuilder;
    private ResponseSpecification responseSpecBuilder;
    private String exercisesBaseEndpoint = "/exercises";

    private String contentType = "Content-Type";
    private String applicationJson = "application/json";
    private String textPlain = "text/plain;charset=UTF-8";

    private String exerciseName = "snatch";
    private String scoreDate = "2023-01-01";
    private int prWeight = 160;
    private int lastId;

    PostSnatch snatchPosted;
    GetSnatch snatchReceived;

    @BeforeClass
    public void setUp(){

         requestSpecBuilder = new RequestSpecBuilder().setUrlEncodingEnabled(false)
                 .setBaseUri(ResourcesProvider.getBaseUri())
                .setContentType(ContentType.JSON).build();
         responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200)
                 .build();
    }

    @Test(priority = 0)
    public void getAllPrs(){
        Response response = given().log().all().spec(requestSpecBuilder).when().get(exercisesBaseEndpoint)
                .then().spec(responseSpecBuilder).assertThat().contentType(equalTo(applicationJson)).extract().response();
        List<LinkedHashMap> prList = response.as(List.class);

//        this part is just examinatory, for fun
       LinkedHashMap<Object, Object> object1 = prList.get(prList.size() -1 );
       GetSnatch snatch = new ObjectMapper().convertValue(object1, GetSnatch.class);

//       here we take id to verify in the next test
       lastId = snatch.getId();
       Assert.assertTrue(prList.size() > 0,
               "prList empty, it definitely shouldn't be like that");
    }

    @Test(priority = 1)
    public void postPr() {
        snatchPosted = new PostSnatch(exerciseName, scoreDate, prWeight);

        Response response = given().log().all().spec(requestSpecBuilder)
                .body(snatchPosted).expect().defaultParser(Parser.JSON).when()
                .post(exercisesBaseEndpoint).then()
                .spec(responseSpecBuilder).log().all()
                .assertThat().body("scoreDate", equalTo(this.scoreDate)).extract().response();


        String responseString = response.asString();
        JsonPath path = JsonPath.from(responseString);
        snatchReceived = response.as(GetSnatch.class);

        Assert.assertEquals(snatchReceived.getId() ,lastId + 1
                , "either id incrementation failed or id's just not match");
        Assert.assertEquals(snatchPosted.getPrWeight(), snatchReceived.getPrWeight(), "values not match," +
                "prWeight received should be " + snatchPosted.getPrWeight());

        System.out.println(ToStringBuilder
                .reflectionToString(snatchPosted, ToStringStyle.JSON_STYLE));
    }

    @Test(priority = 2)
    public void deletePr() {
        Response response = given().log().all().spec(requestSpecBuilder).when().delete(exercisesBaseEndpoint
                + "/" + lastId)
                .then().spec(responseSpecBuilder).assertThat().contentType(equalTo(textPlain)).extract().response();
        String responseString = response.asString();
        Assert.assertEquals(responseString, "Deleted exercise with exerciseId: " + lastId,
                "values not match");
        System.out.println(responseString);
    }
}
