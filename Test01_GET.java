import java.util.ArrayList;
import java.util.Random;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.json.simple.JSONObject;


public class Test01_GET {
	
	@Test
	void test1()	{
		
		Random r = new Random();
		int low = 1;
		int high = 100;
		int result = r.nextInt(high-low) + low;
		String s=String.valueOf(result);
		
		System.out.println("***************Test case 1************");
		//test case 1		
		Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/"+s+"/comments");
		String emails = response.jsonPath().getString("email");
		System.out.println("List of emails for user["+s+"]"+emails);
		
		System.out.println("***************Test case 2************");
		//test case 2
		ArrayList<Integer> posts =
		given()
        .get("https://jsonplaceholder.typicode.com/posts/"+s+"/comments")
        .then()
        .extract()
        .response()
        .path("postId");
		
		int c=0;
		for(int i=1; i<=100;i++)	{
			if(posts.contains(i))	{
				c+=1;
			}
		}
		if(c!=0)	{
			System.out.println("Post IDs are with in 1-100 range");
		}
		
		System.out.println("***************Test case 3************");
		// test case 3
		try	{
		JSONObject request = new JSONObject();
		request.put("id", result);
		request.put("title", "Hakuna Matata");
		request.put("body", "alpha beta gama yama");
		Response res2 = RestAssured.get("https://jsonplaceholder.typicode.com/posts/"+s);
		given().
			body(request.toJSONString()).
			when().
			post("https://jsonplaceholder.typicode.com/posts").then().
			statusCode(201);
		
		System.out.println("Post successful");
		}
		catch (Exception e) {
			System.out.println("Exception occurred"+e);
		}
		
	}
	
	
	void test2()	{
		
	}
}
