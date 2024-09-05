package Learn_REST_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import REST_files.PayLoad;

public class REST_API {
	public static void main(String args[]) {

		// Given- All input details - URL, Query Parameter, Header
		// When - Submit the API - resources. http method
		// Then - Validate the response - Status code

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String Load = given()
				.queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(PayLoad.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().body().asPrettyString();
		System.out.println(Load);
		
		JsonPath path = new JsonPath(Load);
		String jsonObject = path.getString("place_id");
	//	Object jsonObject = path.getJsonObject("place_id");
		System.out.println(jsonObject);
		System.out.println("Order is Created");


		
		
		

	}
}
