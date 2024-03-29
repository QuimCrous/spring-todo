<h2 align="center">Todo API</h2>

[![CodeFactor](https://www.codefactor.io/repository/github/quimcrous/spring-todo/badge)](https://www.codefactor.io/repository/github/quimcrous/spring-todo)

This is an API I have created for a technical test. In this project, I have created a REST API for tasks, where users can view, create, edit, and delete them while adding a layer of security to perform checks (such as ensuring that the user is logged in to access the different functions and that they cannot edit or delete tasks from other users).

#### Information

To create this project, I used the Java Spring Boot framework using Maven for dependencies, connected to a MySQL database, and used Thymeleaf to create a front-end for the API. To establish connections between the API, database, and front-end, I used the following Maven dependencies and configuration in application.properties.

###### pom.xml

~~~
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.thymeleaf</groupId>
			<artifactId>thymeleaf</artifactId>
			<version>3.1.1.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
~~~

In the pom.xml are listed the next dependencies:

- JDBC Data
- JPA Data
- Web
- Security
- Devtools
- MySQL connector
- Spring-boot test
- Security test
- Validation
- Thymeleaf
- Spring-boot & thymeleaf connection
- Junit


###### application.properties
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

In the last part, we have the configuration with Thymeleaf, where we enable it and specify where to find the files to create the front-end visualization to consume the API.

#### Requirements

For this project, I have used Java version 17, which is required to use the API. It is also necessary to create the schema in MySQL before initializing the application using a script that is located at the root of this project called 'schema.sql', which can be executed from Workbench or from the command terminal

#### Installation

Once the requirements are met, we just need to make sure to load the Maven dependencies found in the 'pom.xml' file located at the root of the project (this can be done from the same editor or from the command terminal). Once we have the dependencies, we can initialize the API.

We can do it from the editor or through the command line by navigating to the project directory and using the following command:

~~~
$ ./mvnw spring-boot:run
~~~


#### Architecture of the Project

The project is built as follows: We have the models section where the entities for tasks and users are constructed. For the latter, we use an embedded class for the users' address. We also have the repositories section where the repositories for each entity are located with the functions that will serve as queries for our functionalities.

We also have a configuration directory where we declare the Spring security configuration properties, as well as a customuserdetails class that allows us to make extra use of security login data. In the security configuration class, we mark the routes declared in the controller that require the user to be logged in to access the data.

Afterwards, we have the services where most of the API logic is located. We have the UserService that provides us with all the functionalities of the API, such as creating, modifying, retrieving and deleting tasks. This service has an interface that we use to link it to the controller so that if changes need to be made to the service later, it will not affect the controller. We also have a CustomUserDetailsService that serves to load user information upon login.

Then, we have the UserController that provides us with different endpoints to be able to use the API. We have the options of a REST API to retrieve, create, edit, and delete tasks. I have also added the option to create and delete users even though it was not a requirement of the technical test.

We also have the WebController where the different endpoints are located to consume the API from the front-end generated with Thymeleaf. It has the same options of create, edit, read, and delete all as the UserController but directly from the front-end.

Finally, in the resources/templates folder, we have the different templates used in the WebController to visualize the front-end with which to consume the API. These templates are connected with the Tailwind framework for styling the page.

#### Usage

###### Using UserController
To use the API after completing the requirements and installation, you would only need to activate or upload it to a web server along with the database. Once the API is activated, you only have to make calls to the following endpoints written in the controller to get the data.

Please note that before being able to access the routes from the browser, a prompt will appear asking for a username and login. You can use any of these two:
- user: jhonny_87 / password: 123456
- user: miky_doe / password: 123456

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

###### Using WebController

To use the WebController, you also need to fulfill the requirements of the UserController, and to access the web and its different options, you also need to be a registered user (you can use the user data explained in the UserController step).

By accessing the URL localhost:8082/ directly, you can access the gettodos page after logging in.

![img.png](img.png)

Here, all the todos that exist in the database are displayed, showing the title, the username of the assigned User, the country where the user comes from, and whether the todo is complete or not.

If we click on the different sections of the table header, the table data will be sorted according to the selected option. In addition, there is a form where we can enter a username to filter the todos or a text to filter the todos that contain that text in the title of the todo.

Using the navigation bar, we can access the different options of the API. If we click on "Create Todo", it takes us to a view where we have the option to add a new task. We have a field where we can add the title of the todo, then we have a user selector to link the todo to a specific user (the user must be registered to appear in the selector), and finally, a checkmark in case we want to mark it as completed.

![img_1.png](img_1.png)

In the "My Todos" option, a table will be displayed with the todos assigned to the logged-in user, with an option to edit and/or delete them.

![img_2.png](img_2.png)

Finally, we have the "Logout" option to log out of the session.

#### Postman Collection for API Requests

The project includes a file named 'Spring Todo.postman_collection.json' that contains a set of API requests used to verify the proper functionality of the API using the Postman application. The responses given by the API are also displayed

#### Technical challenge

For the creation of this API, what has been most challenging for me is the inclusion of result pagination, as it is something completely new to me. However, it is very useful because it restricts the amount of data that needs to be moved per request, making the whole process consume fewer resources and more scalable. On the other hand, this has caused me a big problem as I have not been able to create direct tests of the service or repository since the object returned by pagination could not be managed well from Java code.

Nevertheless, I am satisfied with the result of the controller tests where it shows that the controller endpoints, the security used to make checks, such as the logic of the services and the repositories work, because if something fails, the endpoints will not function properly. However, I have decided to add repository and service tests to make the necessary checks.



#### Contact

Joaquim Crous - [@quim_dev](https://twitter.com/quim_dev) - joaquimcrous@gmail.com

Project Link: [https://github.com/QuimCrous](https://github.com/QuimCrous/spring-todo)

Presentation Link: [https://www.linkedin.com/in/joaquim-crous-mayné/](https://www.linkedin.com/in/joaquim-crous-mayné/)

