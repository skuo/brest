brest
=====

Gradle Build
-----
gradlew build # set gradle version in gradle/wrapper/gradle-wrapper.properties

# debug
java -server -Xms1700M -Xmx1700M -Xdebug -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=y -jar build/libs/brest-0.1.0.jar > console.log 2>&1 &
# run
java -server -Xms1700M -Xmx1700M -Xdebug -jar build/libs/brest-0.1.0.jar > console.log 2>&1 &
java -jar build/libs/brest-0.1.0.jar

# gradle-node-plugin
https://github.com/srs/gradle-node-plugin



Maven Build
-----
mvn spring-boot:run. 

or
mvn clean install

# debug
java -server -Xms1700M -Xmx1700M -Xdebug -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=y -jar target/brest-0.1.0.jar > console.log 2>&1 &
# run
java -server -Xms1700M -Xmx1700M -Xdebug -jar target/brest-0.1.0.jar > console.log 2>&1 &
java -jar target/brest-0.1.0.jar 


Smoke Test
-----
http://localhost:8080/greeting # the default name parameter is "World"
http://localhost:8080/greeting?name=steve

TODO
-----
* change webapp application context, port number
* implement filter
* package and deploy
* gradle node.js and npm tasks

Fabric
-----------
fab build_and_debug # debug port at 4000, jetty listens at 8080

Postman
-----------
#http://localhost:8080/brest-web/uploadFile #params: file and name

Swagger-SpringMvc
-----------
http://localhost:8080/brest-web/api-docs
http://localhost:8080/v2/api-docs?group=greetings
http://localhost:8080/swagger-ui.html

Spring Data REST API
-----------
curl localhost:8080/api
curl localhost:8080/api/employees
curl -X POST localhost:8080/api/employees -d '{"firstName":"Bilbo","lastName":"Baggins","description":"burglar"}' -H 'Content-Type:application/json'

React and Spring Data REST API
-----------
In a browser, go to "localhost:8080"

# with paging and an example HAL response
$ curl localhost:8080/api/employees?size=2
{
  "_embedded" : {
    "employees" : [ {
      "firstName" : "Frodo",
      "lastName" : "Baggins",
      "description" : "ring bearer",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/employees/1"
        },
        "employee" : {
          "href" : "http://localhost:8080/api/employees/1"
        }
      }
    }, {
      "firstName" : "Bilbo",
      "lastName" : "Baggins",
      "description" : "burglar",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/employees/2"
        },
        "employee" : {
          "href" : "http://localhost:8080/api/employees/2"
        }
      }
    } ]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost:8080/api/employees?page=0&size=2"
    },
    "self" : {
      "href" : "http://localhost:8080/api/employees"
    },
    "next" : {
      "href" : "http://localhost:8080/api/employees?page=1&size=2"
    },
    "last" : {
      "href" : "http://localhost:8080/api/employees?page=2&size=2"
    },
    "profile" : {
      "href" : "http://localhost:8080/api/profile/employees"
    }
  },
  "page" : {
    "size" : 2,
    "totalElements" : 6,
    "totalPages" : 3,
    "number" : 0
  }
}

$ curl http://localhost:8080/api/profile/employees -H 'Accept:application/schema+json'
{
  "title" : "Employee",
  "properties" : {
    "firstName" : {
      "title" : "First name",
      "readOnly" : false,
      "type" : "string"
    },
    "lastName" : {
      "title" : "Last name",
      "readOnly" : false,
      "type" : "string"
    },
    "description" : {
      "title" : "Description",
      "readOnly" : false,
      "type" : "string"
    }
  },
  "definitions" : { },
  "type" : "object",
  "$schema" : "http://json-schema.org/draft-04/schema#"
}
