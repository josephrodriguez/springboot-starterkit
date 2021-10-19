# Learning Spring Boot

Hello, this was an exciting learning project on current technologies such as Spring Boot, Docker and GitHub actions in which I leave you my knowledge learned in this learning process in the hope that it will be useful to you. You will be able to find the main problems that you can present, as well as their solutions. Enjoy it.

## Run

- **IntelliJ IDEA 2021.2.2:**

The setup configuration file can be found on `.run\LearningSpringBootApplication.run.xml`:

```
<component name="ProjectRunConfigurationManager">
  <configuration default="false" name="LearningSpringBootApplication" type="Application" factoryName="Application">
    <option name="ALTERNATIVE_JRE_PATH" value="BUNDLED" />
    <option name="ALTERNATIVE_JRE_PATH_ENABLED" value="true" />
    <option name="MAIN_CLASS_NAME" value="com.josephrodriguez.learning.springboot.LearningSpringBootApplication" />
    <module name="Learning.SpringBoot" />
    <method v="2">
      <option name="Make" enabled="true" />
    </method>
  </configuration>
</component>
```

- **Docker**

Download and run with Docker image from Docker Hub:

```
docker run -p 8080:8080 josephrodriguez/learning-spring-boot
```

- **docker-compose**

```
docker-compose up
```


## REST Endpoint

The Tomcat embedded server is exposed by port 8080:

> ## GET

**Request:**
```
GET http://localhost:8080/api/documents
```

**Response:**
```
[
    {
        "code": "271636001",
        "codeListCode": "ZIB001",
        "source": "ZIB",
        "displayValue": "Polsslag regelmatig",
        "longDescription": "The long description is necessary",
        "fromDate": "2019-01-01",
        "toDate": null,
        "sortingPriority": 1
    }
]
```

> ## GET

**Request:**
```
GET http://localhost:8080/api/documents/271636001
```

**Response:**
```
{
    "code": "271636001",
    "codeListCode": "ZIB001",
    "source": "ZIB",
    "displayValue": "Polsslag regelmatig",
    "longDescription": "The long description is necessary",
    "fromDate": "2019-01-01",
    "toDate": null,
    "sortingPriority": 1
}
```

> ## POST 

**Request:**
```
POST http://localhost:8080/api/documents/
Content-Type: application/json

{
  "code": "415945006",
  "codeListCode": "ZIB003",
  "source": "ZIB",
  "displayValue": "Orale temperatuur (onder de tong)",
  "longDescription": "",
  "fromDate": "2019-01-01",
  "toDate": null,
  "sortingPriority": 2
}

```

**Response:**
```
{
  "code": "415945006",
  "codeListCode": "ZIB003",
  "source": "ZIB",
  "displayValue": "Orale temperatuur (onder de tong)",
  "longDescription": "",
  "fromDate": "2019-01-01",
  "toDate": null,
  "sortingPriority": 2
}
```

> ## PUT

**Request:**

```
PUT http://localhost:8080/api/documents/698832009
Content-Type: application/json


{
    "code": "698832009",
    "codeListCode": "ZIB003",
    "source": "ZIB",
    "displayValue": "Blaastemperatuur",
    "longDescription": "",
    "fromDate": "2019-01-01T00:00:00.000+00:00",
    "toDate": null,
    "sortingPriority": null
}
```

**Response:**
```
{
    "code": "698832009",
    "codeListCode": "ZIB003",
    "source": "ZIB",
    "displayValue": "Blaastemperatuur",
    "longDescription": "",
    "fromDate": "2019-01-01T00:00:00.000+00:00",
    "toDate": null,
    "sortingPriority": null
}
```


> ## DELETE

**REquest:**
```
DELETE http://localhost:8080/api/documents/698832009
```

**Response:**
```
200
```
