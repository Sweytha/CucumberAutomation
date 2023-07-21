package com.bptn.CucumberAutomation.stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ApiTestingSteps {

	
	 
	Response response;
	
	@Autowired
	ObjectMapper objectMapper;

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

	@And("the title of the second post should be {string}")
	public void the_title_of_the_second_post_should_be(String expectedTitle) {

		assertEquals(expectedTitle, this.response.jsonPath().getString("title[1]"));
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

	@And("the email at index {int} should be {string}")
	public void the_email_at_index_should_be(int index, String expectedEmail) {
		assertEquals(expectedEmail, this.response.jsonPath().getString("email[" + index + "]"));
	}

	@When("I perform a POST request to {string} with the following data")
	public void i_perform_a_post_request_to_with_the_following_data(String endpoint, DataTable data) throws JsonProcessingException{
		   this.response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(this.objectMapper.writeValueAsString(data.asMap()))
	                .when()
	                .post(endpoint)
	                .then()
	                .extract()
	                .response();
		
	}

	@And("the title should be {string}")
	public void the_post_title_should_be(String expectedTitle) {
		assertEquals(expectedTitle, this.response.jsonPath().getString("title"));
	}

	@And("the body should be {string}")
	public void the_post_body_should_be(String expectedBody) {
		assertEquals(expectedBody, this.response.jsonPath().getString("body"));
	}

	@And("the userId should be {string}")
	public void the_post_userId_should_be(String expectedUserId) {
		assertEquals(expectedUserId, this.response.jsonPath().getString("userId"));
	}

	@And("the id should be a positive integer")
	public void the_post_id_should_be_positive_integer() {
		int id = this.response.jsonPath().getInt("id");
		assertTrue(id > 0, "Id should be a positive integer.");
	}
	
}
