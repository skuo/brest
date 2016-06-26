brest
=====

Build
-----

mvn clean install -P local

Fabric
-----------
fab build_and_debug # debug port at 4000, jetty listens at 8080

Postman
-----------
http://localhost:8080/brest-web/uploadFile #params: file and name

Swagger-SpringMvc
-----------
http://localhost:8080/brest-web/api-docs
