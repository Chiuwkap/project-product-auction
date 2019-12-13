# project-product-auction
Author: Chi Trinh & Olof Kastrup

### Application:
A REST application with Spring Boot

### To run this project you will need following:
* Maven
* JDK 11 
* PostgreSQL for connection: postgres (user and password: postgres)
  and then create another database call auction. You can simple change database-string in application.properties file.


* Optional: If you want to generate JaCoCo test coverage you will need a maven plugin in pom.xml for JaCoCo

```
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.7.7.201606060606</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```


### To run project:
```mvn spring-boot:run```

To view the API documentation in swagger when server is running, go to http://localhost:8080/swagger-ui.html in your browser.

### To run test:
```mvn test```

### Test coverage:

```mvn test jacoco:report```
you can see the report in /target/site/jacoco/index.html 

### Technologies:
* Maven
* Spring Boot 
* PostgreSQL
* Liquibase
* Swagger2
* JaCoCo
* Mockito
* LogBack (Spring Boot default logging)

### Notes:
* If you are using IntelliJ you might need an plugin for Lombok library
* If the technologies versions are deprecated you can always change it in pom.xml
