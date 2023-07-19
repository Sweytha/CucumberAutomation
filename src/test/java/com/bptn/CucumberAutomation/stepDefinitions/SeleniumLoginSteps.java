/*package com.bptn.CucumberAutomation.stepDefinitions;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumLoginSteps {

	private WebDriver driver;

	// Navigates to the specified URL
	@When("I navigate to {string}")
	public void i_navigate_to(String url) {
		// Set up the WebDriver
		driver = WebDriverManager.chromedriver().create();
		// Navigate to the given URL
		driver.get(url);
	}

	// Performs login with the given username and password
	@When("I login with the username {string} and password {string}")
	public void i_login_with_the_username_and_password(String username, String password) {
		// Enter the username
		driver.findElement(By.id("username")).sendKeys(username);
		// Enter the password
		driver.findElement(By.id("password")).sendKeys(password);
	}

	// Clicks the Submit button
	@When("I click Submit")
	public void i_click_submit() {
		driver.findElement(By.cssSelector("button")).click();
	}

	// Verifies if the specified message is displayed
	@Then("I see the message {string}")
	public void i_see_the_message(String result) {
		try {
			// Check if the message is displayed
			driver.findElement(By.xpath("//*[contains(text(), '" + result + "')]"));
		} catch (NoSuchElementException e) {
			// Throw an assertion error if the message is not found
			throw new AssertionError("\"" + result + "\" not available in results");
		} finally {
			// Quit the WebDriver
			driver.quit();
		}
	}
}*/