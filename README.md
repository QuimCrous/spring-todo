<h2 align="center">Todo API</h2>

[![CodeFactor](https://www.codefactor.io/repository/github/quimcrous/spring-todo/badge)](https://www.codefactor.io/repository/github/quimcrous/spring-todo)

This is an API I have created for a technical test. In this project, I have created a REST API for tasks, where users can view, create, edit, and delete them while adding a layer of security to perform checks (such as ensuring that the user is logged in to access the different functions and that they cannot edit or delete tasks from other users)

#### Information

To create this project, I have used the Spring Boot framework in Java using Maven for dependencies, and connected it to a MySQL database. To connect the API with the database, I am using a Spring dependency to connect with MySQL with the following configuration in the application.properties file.

~~~
spring.datasource.url=jdbc:mysql://localhost:3306/todo?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create


server.port=8082
spring.jpa.show-sql=true

spring.thymeleaf.enabled=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML5
spring.resources.static-locations=classpath:/static/
~~~

The first line adds the path where the MySQL database is located, which in my case was localhost:3306 due to my MySQL configuration, and specifies the name of the schema to be used, which in my case is 'todo'

On the second and third lines, we need to specify the username and password that we have assigned to MySQL to connect to the database correctly. Then, on the fifth line, we add the 'create' function, which is the one I have decided to use to showcase the project as it initializes the data each time the API is used, but this can be changed according to needs or when moving to production. Finally, the last two lines indicate the port that the API will use and that we want to display JPA commands in the console to have a visualization of what the API is doing.

#### Requirements

For this project, I have used Java version 17, which is required to use the API. It is also necessary to create the schema in MySQL before initializing the application using a script that is located at the root of this project called 'schema.sql', which can be executed from Workbench or from the command terminal

#### Installation

Once the requirements are met, we just need to make sure to load the Maven dependencies found in the 'pom.xml' file located at the root of the project (this can be done from the same editor or from the command terminal). Once we have the dependencies, we can initialize the API.

#### Architecture of the Project

The project is built as follows: We have the models section where the entities for tasks and users are constructed. For the latter, we use an embedded class for the users' address. We also have the repositories section where the repositories for each entity are located with the functions that will serve as queries for our functionalities.

We also have a configuration directory where we declare the Spring security configuration properties, as well as a customuserdetails class that allows us to make extra use of security login data. In the security configuration class, we mark the routes declared in the controller that require the user to be logged in to access the data.

Afterwards, we have the services where most of the API logic is located. We have the UserService that provides us with all the functionalities of the API, such as creating, modifying, retrieving and deleting tasks. This service has an interface that we use to link it to the controller so that if changes need to be made to the service later, it will not affect the controller. We also have a CustomUserDetailsService that serves to load user information upon login.

Finally, we have the UserController that provides us with different endpoints to be able to use the API. We have the options of a REST API to retrieve, create, edit, and delete tasks. I have also added the option to create and delete users even though it was not a requirement of the technical test

#### Usage

To use the API after completing the requirements and installation, you would only need to activate or upload it to a web server along with the database. Once the API is activated, you only have to make calls to the following endpoints written in the controller to get the data.

~~~
@PostMapping("/todos/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody TodoDTO todoDTO)
    
@PutMapping("/todos/edit")
    @ResponseStatus(HttpStatus.OK)
    public Todo editTodo(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long id, @RequestBody TodoDTO todoDTO)
    
@DeleteMapping("/todos/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long todoId)
    
@GetMapping("/todos/get-todos")
    @ResponseStatus(HttpStatus.OK)
    public Page<List<Object[]>> getTodos(@RequestParam(required = false) String searchText, @RequestParam(required = false) String userName, Pageable pageable)
~~~

In the technical test, it is required that the GET request shows paginated results. To achieve this from the backend, I have used the Pageable object, which by default gives us pagination of the information from the database. But to comply with the test requirements that only ten entries are shown each time a request is made, we can add a parameter in the URL called "size" and indicate that we want 10 entries. We can also request the specific page we want using the "page" parameter, and even sort the results using "sort".

#### Postman Collection for API Requests

The project includes a file named 'Spring Todo.postman_collection.json' that contains a set of API requests used to verify the proper functionality of the API using the Postman application. The responses given by the API are also displayed

#### Technical challenge

For the creation of this API, what has been most challenging for me is the inclusion of result pagination, as it is something completely new to me. However, it is very useful because it restricts the amount of data that needs to be moved per request, making the whole process consume fewer resources and more scalable. On the other hand, this has caused me a big problem as I have not been able to create direct tests of the service or repository since the object returned by pagination could not be managed well from Java code.

Nevertheless, I am satisfied with the result of the controller tests where it shows that the controller endpoints, the security used to make checks, such as the logic of the services and the repositories work, because if something fails, the endpoints will not function properly. However, I have decided to add repository and service tests to make the necessary checks.



#### Contact

Joaquim Crous - [@quim_dev](https://twitter.com/quim_dev) - joaquimcrous@gmail.com

Project Link: [https://github.com/QuimCrous](https://github.com/QuimCrous/spring-todo)

Presentation Link: [https://www.linkedin.com/in/joaquim-crous-mayné/](https://www.linkedin.com/in/joaquim-crous-mayné/)

