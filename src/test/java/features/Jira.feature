@Sharif
Feature: Jira CRUD Functionality

#Background:
#Given set the Jira EndPoint
#And set the Jira Auth

Scenario: Create Issue

   When Create Issue with string Body '{"fields":{"project":{"key":"JIR"},"summary":"create issue in RA project","description":"Creating of an issue using project keys and issue type names using the REST API","issuetype":{"name":"Bug"}}}'
   Then validate response code as 201

Scenario Outline: Create Issue with multiple Data

   When Create Issue with multiple Data '{"fields":{"project":{"key":"JIR"},"summary":"<summary>","description":"<desc>","issuetype":{"name":"Bug"}}}'
   Then validate response code as 201

  Examples: 
    | summary             | desc                         | 
    | URL is not working  | Jira Base URL is not wotking | 
    | Auth is not working | Jira Auth is not wotking     | 

Scenario Outline: Create Issue with Faker

   When Create Issue with random Body
   Then validate response code as <ExpectedResponseCode>

  Examples: 
    | ExpectedResponseCode | 
    | 201                  | 
    | 201                  | 
    | 201                  | 
    | 201                  | 

Scenario: Update Issue

   When Update Issue with string Body '{"fields":{"project":{"key":"JIR"},"summary":"create issue in Arief project","description":"Creating of an issue using project keys and issue type names using the Arief REST API","issuetype":{"name":"Bug"}}}'
   Then validate response code as 204

Scenario: Delete Issue

   When delete Issue
   Then validate response code as 204

Scenario: Get all Issues

   When I make a GET request to the Jira API
   Then validate response code as 200