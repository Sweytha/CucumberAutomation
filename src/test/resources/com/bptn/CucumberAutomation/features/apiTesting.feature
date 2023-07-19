Feature: API Testing

  Background: 
    Given the base URL is "https://jsonplaceholder.typicode.com"

  Scenario: Perform a GET request to retrieve posts
    When I perform a GET request to "/posts"
    Then the response status code should be 200
    And the title of the second post should be "qui est esse"

  Scenario: Perform a GET request with query parameters
    When I perform a GET request to "/comments" with the query parameter "postId=2"
    Then the response status code should be 200
    And the email at index 8 should be "Meghan_Littel@rene.us"
