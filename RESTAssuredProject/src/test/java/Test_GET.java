import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
//import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.List;

public class Test_GET {

    String trelloKey = "31d08ad7b936719994c3ed9dfb42d0c7"; // Key & Token for getting access to the trello
    String trelloToken = "7e62e8474906d93eac62e3fc4136e0392c4003c216c148cc16aa85ee7c679163";
    String trelloURI = "https://api.trello.com";
    
    String trelloIdList = "60c1bf199a1aee89c496978d";

    String newCardID; // The ID of a new created card
    String cardName = "Cat and Dog"; // The name of a new created card
    
    String cardComment = "The comments are added by ReastAssured"; // Comments, that added to the card
    String cardCComment = "The first comment just created by ReastAssured card"; // Comments, that added to the just created card
        
    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
            given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }
    
    
    
    @Test
	public void getCardsList( ) { // get a list of the cards at at the existing dashBoard (trelloIdList)
		
    	Response response = doGetRequest(trelloURI+"/1/lists/"+trelloIdList+"/cards?key="+trelloKey+"&token="+trelloToken);
    	
    	String currentCardId = response.jsonPath().getString("id");
    	System.out.println(currentCardId);
    	
    	List<String> jsonResponse = response.jsonPath().getList("$");
    	int i = jsonResponse.size();
    	System.out.println(i);
    	i--;
    	
    	String currentCardId2 = response.jsonPath().getString("id["+i+"]");
    	System.out.println(currentCardId2);   	
    	    	
    	    	
		given()
		   .get(trelloURI+"/1/lists/"+trelloIdList+"/cards?key="+trelloKey+"&token="+trelloToken)		
		.then()
		   .statusCode(200);
	}
    
    @Test
    public void getResponse() {
    	String nextTitleLink = 
    			given().
    			when().
    			  get(trelloURI+"/1/lists/"+trelloIdList+"/cards?key="+trelloKey+"&token="+trelloToken).
    			then().
    			  extract().
    			  path("id");
    	
    	System.out.println(nextTitleLink);
  
 }
    
/*	
	@Test
	public void addComentsToTheCard() { // add comments to the existing Card 
		given()
		  .body("")
		.when()
		  .post(trelloURI +"/1/cards/60c88799482e2f51bc7083e8/actions/comments?key="+ trelloKey +"&token="+ trelloToken +"&text="+ cardComment)
		.then()
		  .statusCode(200);  

	}
*/

/*    
	@Test
	public void testCreatNewCard() { // create a new  card at the existing dashBoard (trelloIdList)
		
		given().
		  body("").
		when().
		  post(trelloURI +"/1/cards?key="+ trelloKey +"&token="+ trelloToken +"&idList="+ trelloIdList +"&name="+ cardName).
		then().
		  statusCode(200).
		  log().all();
		
	} 
*/	
}
