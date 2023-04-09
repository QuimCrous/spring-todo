Todo backend Project

Título y descripción breve: Empieza con un título y una descripción breve que capturen la esencia de tu API y su propósito.

Información sobre la base de datos: Explica el tipo de base de datos que estás usando y cómo se conecta con tu aplicación. Por ejemplo, puedes incluir información sobre la configuración de la conexión a la base de datos en el archivo application.properties.

Requisitos previos: Detalla los requisitos previos para ejecutar tu API. Por ejemplo, puede ser necesario tener una versión específica de Java instalada.

Instalación: Explica cómo instalar y ejecutar tu API. Por ejemplo, si estás utilizando Maven, puedes incluir un comando para compilar y empaquetar tu aplicación, así como otro comando para ejecutarla.

Uso: Describe cómo utilizar tu API. Incluye información sobre los endpoints disponibles, los parámetros y las respuestas. Puedes incluir ejemplos de solicitudes y respuestas, así como instrucciones para autenticar las solicitudes si es necesario.

Ejemplos de código: Proporciona algunos ejemplos de código para ilustrar cómo utilizar tu API. Por ejemplo, puedes incluir fragmentos de código Java que muestren cómo realizar una solicitud HTTP utilizando la biblioteca RestTemplate de Spring.

Contribución: Anima a los usuarios a contribuir a tu proyecto. Explica cómo pueden hacerlo, por ejemplo, abriendo un problema en GitHub o enviando una solicitud de extracción.

Licencia: Indica la licencia de tu proyecto. Puedes utilizar una plantilla de licencia como la proporcionada por la Open Source Initiative.

Contacto: Proporciona información sobre cómo ponerse en contacto contigo si los usuarios tienen preguntas o comentarios sobre tu API.


## Todo API

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
~~~

The first line adds the path where the MySQL database is located, which in my case was localhost:3306 due to my MySQL configuration, and specifies the name of the schema to be used, which in my case is 'todo'

On the second and third lines, we need to specify the username and password that we have assigned to MySQL to connect to the database correctly. Then, on the fifth line, we add the 'create' function, which is the one I have decided to use to showcase the project as it initializes the data each time the API is used, but this can be changed according to needs or when moving to production. Finally, the last two lines indicate the port that the API will use and that we want to display JPA commands in the console to have a visualization of what the API is doing.

#### Requirements

For this project, I have used Java version 17, which is required to use the API. It is also necessary to create the schema in MySQL before initializing the application using a script that is located at the root of this project called 'schema.sql', which can be executed from Workbench or from the command terminal

#### Installation

Once the requirements are met, we just need to make sure to load the Maven dependencies found in the 'pom.xml' file located at the root of the project (this can be done from the same editor or from the command terminal). Once we have the dependencies, we can initialize the API.

#### Architecture of the Project

The project is built as follows: We have the models section where the entities for tasks and users are constructed. For the latter, we use an embedded class for the users' address. We also have the repositories section where the repositories for each entity are located with the functions that will serve as queries for our functionalities.

We also have a configuration directory where we declare the Spring security configuration properties, as well as a customuserdetails class that allows us to make extra use of security login data.



