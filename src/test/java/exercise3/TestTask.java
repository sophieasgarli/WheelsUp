package exercise3;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestTask {
    String expectedTwitter= "https://twitter.com/WheelsUp";
    String expectedInstagram="http://instagram.com/wheelsup8760";

    @Test
    public void runTest() {
        displayEditedKeys();
//		verifySocialAccounts("twitter", "instagram");

    }

    public void displayEditedKeys(){
        HashMap<String, String> map=submitGETRequest();
        System.out.println("Count of keys: "+map.size());
        //removed fields, google_tag_manager, mapbox_accesstoken, and footer_disclaimer.
        map.remove("google_tag_manager");
        map.remove("mapbox_accesstoken");
        map.remove("footer_disclaimer");
        System.out.println("Count of keys: "+map.size());
        //update email
        map.put("email", "sophie.asgarli@gmail.com");
        System.out.println(map.get("email"));
    }

    public void verifySocialAccounts(String twitter, String instagram) {
        HashMap<String, String> map=submitGETRequest();
        Assert.assertEquals(expectedTwitter, map.get(twitter) );
        Assert.assertEquals(expectedInstagram, map.get(instagram) );
    }

    //this method is to submit request
    public HashMap<String, String> submitGETRequest() {
        Response resp=given()
                .when()
                .get("https://wheelsup.com/_mock_/initial-data.json");
        resp.then().statusCode(200);
        ////retrieve keys from response and parse to HashMap
        HashMap<String, String>map=JsonPath.read(resp.asString(), "$.keys");
        return map;
    }






}


