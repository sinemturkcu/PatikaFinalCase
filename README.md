# Weather Application

The purpose of this project is to develop a weather application that provides weather forecasts using a RESTful web service. Users have the ability to query the weather forecast for a particular city. Estimates are provided at 3-hour intervals covering a 5-day period. The application receives weather forecast data from the OpenWeatherMap API.

## Used technologies

* Spring Boot
* Spring Boot Security
* Hibernate
* MySQL
* OpenAPI
* Lombok
* JUnit

## OpenApi specification - Sinem Türkçü

![image](https://github.com/sinemturkcu/PatikaFinalCase/assets/42895382/7897bd57-ecda-4166-81ed-1d47af1e036e)

![image](https://github.com/sinemturkcu/PatikaFinalCase/assets/42895382/1c533c12-aab4-4b7a-aad2-80d9e82600ec)

## Run It on Your Computer

I used MySQL in the project. If you want to use MySQL, you must first create a schema in MySQL. Then you should write the spring.datasource.url , spring.datasource.username, spring.datasource.password fields from the application.properties file according to your own database.

```
spring.datasource.url=jdbc:mysql://localhost:3306/{schema_name}
spring.datasource.username={username}
spring.datasource.password={password}
```

If you want to use another database management system, you should download the related dependency pom.xml file and change the datasource driver field. Then you should do the above steps again.
