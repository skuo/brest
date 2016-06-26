brest
=====

Gradle Build
-----
gradlew build # set gradle version in gradle/wrapper/gradle-wrapper.properties
java -jar build/libs/brest-0.1.0.jar

Maven Build
-----
mvn spring-boot:run. 

or
mvn clean install
java -jar target/brest-0.1.0.jar

Smoke Test
-----
http://localhost:8080/greeting # the default name parameter is "World"
http://localhost:8080/greeting?name=steve

TODO
-----
* debug boot application
* specify log file and access log
* package and deploy

Fabric
-----------
fab build_and_debug # debug port at 4000, jetty listens at 8080

Postman
-----------
http://localhost:8080/brest-web/uploadFile #params: file and name

Swagger-SpringMvc
-----------
http://localhost:8080/brest-web/api-docs
