**********************************************************************

## 1.Task

This project is an REST API developed in Java using Spring boot.

This project is an simple CRUD app for maintaining tasks

### 1.1 Generate Token

This App creates JWT token which is valid for 1 hour. This token is required for all endpoints

### 1.2 Create Tasks

 POST /task/ Places with below body create task
 
 ```
 {
     "summary": "TestSummary",
     "details": "TestDetails",
     "status": "ACTIVE",
     "targetTime":"2021-07-20T04:42:55.142Z"
 }
 ```
 
### 1.2 Retrieve Task

 GET /task/{taskId} returns the task details
 
 ```
    {
        "id": "e65b910a-7a7e-46c6-816d-b2a11c488364",
        "summary": "TestSummary",
        "details": "TestDetails",
        "status": "ACTIVE",
        "targetTime": "2021-07-20T04:42:55.142Z",
        "creationTime": "2021-07-28T10:26:04.078484+02:00"
    }
 ```
 
 If there are no bets available for the given taskId returns 404
 
 ### 1.3 Update Task
 
 PUT /task/ with the below body updates the given task if task is present
 
```
{
        "id": "e65b910a-7a7e-46c6-816d-b2a11c488364",
        "summary": "TestSummary",
        "details": "TestDetails",
        "status": "ACTIVE",
        "targetTime": "2021-07-20T04:42:55.142Z",
        "creationTime": "2021-07-28T10:26:04.078484+02:00"
    }
```

### 1.4 Delete Task

DELETE /task/taskId deletes the task if present


**********************************************************************

## 2. Technical Details:

### 2.1 Tools&Framework:

   The below are the list of tools and framework used in the project!

* [SpringBoot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - for Dependency Management
* [Java](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Java 11 as Programming language
 
### 2.2 Key Features to highlight:

  1.Handled invalid requests with appropriate error message
  2.Added Open API for docs
  

### 2.3 Assumptions

  1. I have not added most rigourous test coverage even though its something possible given more time
  2. Using in memory db for managing tasks , although can persist it, if required 
  3. Have not created a role for user and have hardcoded although this can be persisted
  4. In case of DELETE endpoint, a soft delete is being performed with an idea that after few days it can be archived/deleted
  
**********************************************************************
 
## 3.Spring Open API
 
 If this application is run locally,then swagger UI can be accessed at
 
http://localhost:8081/v3/api-docs 

Swagger can be accessed at

http://localhost:8081/swagger-ui.html

**********************************************************************

## 4.Run Application

Below command can be used to invoke the application

```
mvn spring-boot:run
```
Make sure to populate the SECRET key in the properties file as its used for signing

**********************************************************************