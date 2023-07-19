package com.bptn.CucumberAutomation.stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiTestingSteps {

	Response response;

	@Given("the base URL is {string}")
	public void the_base_url_is(String baseURL) {

		RestAssured.baseURI = baseURL;

	}

	@When("I perform a GET request to {string}")
	public void i_perform_a_get_request_to(String endpoint) {

		this.response = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get(endpoint)
				.then()
				.extract()
				.response();

	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(Integer statusCode) {
		assertEquals(statusCode, this.response.getStatusCode());
	}

	@Then("the title of the second post should be {string}")
	public void the_title_of_the_second_post_should_be(String expectedTitle) {

		assertEquals(expectedTitle, response.jsonPath().getString("title[1]"));
	}
	
	@When("I perform a GET request to {string} with the query parameter {string}")
	public void i_perform_a_get_request_to_with_the_query_parameter(String endpoint, String queryParam) {
		this.response = RestAssured.given()
                .contentType(ContentType.JSON)
                .param(queryParam)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
	}

	@Then("the email at index {int} should be {string}")
	public void the_email_at_index_should_be(int index, String expectedEmail) {
		assertEquals(expectedEmail, response.jsonPath().getString("email[" + index + "]"));
	}


}
