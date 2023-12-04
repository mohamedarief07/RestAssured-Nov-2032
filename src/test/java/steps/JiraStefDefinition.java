package steps;

import java.io.File;

import org.hamcrest.Matchers;

import com.github.javafaker.Faker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JiraStefDefinition extends Common {



	@Given("set the Jira EndPoint")
	public void set_the_jira_end_point() {
		RestAssured.baseURI = "https://resiapinov23mak.atlassian.net//rest/api/2/";
	}

	@Given("set the Jira Auth")
	public void set_the_jira_auth() {
		RestAssured.authentication = RestAssured.preemptive().basic("mohamedarief.m@gmail.com",
				"ATATT3xFfGF0gTHhx9p6_l4VrvFOvqTLjBeMRSUMlK9IYLwPS_-Ve0TUPNBAA9uGvqOvBOBUv2LdEGbqyhJ2f9NIZau7kKNXhXWo-po7tw9F27LEZvmjG59nRxxrIJulbCHzTv9o4F5C0exAPDqgFov41s9NxPKfo3eMEexAnI-PYLAXfu2BiSc=99CB4462");
	}

	@When("Create Issue with string Body {string}")
	public void create_issue_with_string_body(String body) {
		input = RestAssured.given().contentType("application/json").when().body(body);
		response = input.post("issue");
		response.prettyPrint();

		Issue_Id = response.jsonPath().get("id");

		System.out.println("The Response Code for Create-->" + response.getStatusCode());

		System.out.println("The Response line for Create-->" + response.getStatusLine());
	}

	@When("Create Issue with random Body")
	public void create_issue_with_random_body() {
		Faker faker = new Faker();
		String summary = faker.lorem().sentence();
		String description = faker.lorem().paragraph();

		this.issuePayload = String.format(
				"{\"fields\":{\"project\":{\"key\":\"JIR\"},\"summary\":\"%s\",\"description\":\"%s\",\"issuetype\":{\"name\":\"Bug\"}}}",
				summary, description);

		input = RestAssured.given().contentType("application/json").when().body(issuePayload);
		response = input.post("issue");
		response.prettyPrint();

		Issue_Id = response.jsonPath().get("id");

		System.out.println("The Response Code for Create-->" + response.getStatusCode());

		System.out.println("The Response line for Create-->" + response.getStatusLine());
	}

	@When("Create Issue with multiple Data {string}")
	public void create_issue_with_multiple_data(String body) {
		input = RestAssured.given().contentType("application/json").when().body(body);
		response = input.post("issue");
		response.prettyPrint();

		Issue_Id = response.jsonPath().get("id");

		System.out.println("The Response Code for Create-->" + response.getStatusCode());

		System.out.println("The Response line for Create-->" + response.getStatusLine());
	}

	@When("Update Issue with string Body {string}")
	public void update_issue_with_string_body(String body) {
		input = RestAssured.given().contentType("application/json").when().body(body);
		response = input.put("issue/" + Issue_Id);
		response.prettyPrint();

		System.out.println("The Response Code for Update-->" + response.getStatusCode());

		System.out.println("The Response line for Update-->" + response.getStatusLine());
	}

	@When("delete Issue")
	public void delete_issue() {

		response = RestAssured.delete("issue/" + Issue_Id);
		

		System.out.println("The Response Code for Delete-->" + response.getStatusCode());

		System.out.println("The Response line for Delete-->" + response.getStatusLine());

	}
	
	@When("I make a GET request to the Jira API")
	public void getAllIssues() {
		response=RestAssured.get("search?jql=project=JiraLearning2023");
		
		System.out.println("The Response Code for Get all issues-->" + response.getStatusCode());

		System.out.println("The Response line for Get all issues-->" + response.getStatusLine());
	}

	@Then("validate response code as {int}")
	public void validateStatusCode(int responseCode) {
		response.then().assertThat().statusCode(Matchers.equalTo(responseCode));
	}

}
