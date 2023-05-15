# TodoManagement-Spring
TodoManagement App using spring boot 3

# Spring Boot "TodoManagement" Task Project

This is a sample Java / Maven / Spring Bootapplication that expose CRUD endpoints for Todos and tasks entities

## How to Run 


* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -D target/todo-management-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
Once the application runs you should see something like this

```
2023-05-06T09:17:52.175+02:00  INFO 27900 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-05-06T09:17:52.186+02:00  INFO 27900 --- [  restartedMain] c.a.t.TodoManagementApplication          : Started TodoManagementApplication in 2.192 seconds (process running for 2.47)
```

## About the Service

The service is just a simple Todos task REST service. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL.

More interestingly.

Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single jar with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern

Here are some endpoints you can call:

### Query Todos, Tasks resources:

```
POST http://localhost:8080/todo
GET http://localhost:8080/todo
PUT  http://localhost:8080/todo/1
DELETE http://localhost:8080/todo/1
POST http://localhost:8080/todo/{todoId}/task
GET http://localhost:8080/todo/{todoId}/tasks
```

### Create a Todo resource

```
POST localhost:8080/todo
Accept: application/json
Content-Type: application/json

{
    "name": "Todo 1",
    "description": "deploy it now!",
    "tasks": [
        {
            "name": "new task",
            "description" : "Dooooo it now"
        },
        {
            "name": "new task2",
            "description" : "Content....."
        }
    ]
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/todo/1
```

### Retrieve a list of Todos

```
localhost:8080/todo
Response: HTTP 200
Content: Todo list 
```

### Update a Todo resource

```
PUT localhost:8080/todo
Accept: application/json
Content-Type: application/json

{
    "id": 1,
    "name": "Todo One updated",
    "description": "deploy it now!"
}

RESPONSE: HTTP 200 (OK)
```

### Delete a Todo resource

```
DELETE localhost:8080/todo/1


RESPONSE: HTTP 200 (OK)
```
