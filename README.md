# Gerimedica

#GET
GET http://localhost:8080/api/observations/271636001


#POST
POST http://localhost:8080/api/observations/
Content-Type: application/json

```
{
    "source": "ZIB",
    "codeListCode": "ZIB001",
    "code": "271636001",
    "displayValue": "Polsslag regelmatig",
    "longDescription": "The long description is necessary",
    "fromDate": "2019-01-01",
    "toDate": "",
    "sortingPriority": 1
}
```

#PUT
POST http://localhost:8080/api/observations/1
Content-Type: application/json

```
{
    "source": "ZIB",
    "codeListCode": "ZIB001",
    "code": "271636001",
    "displayValue": "Polsslag regelmatig",
    "longDescription": "The long description updated is necessary",
    "fromDate": "2019-01-01",
    "toDate": "2021-04-19",
    "sortingPriority": 2
}
```

#DELETE
DELETE http://localhost:8080/api/observations/ZIB001