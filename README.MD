GET: localhost:8080/billionaire

GET: localhost:8080/billionaire/{id}

GET: localhost:8080/billionaire/search?first=Aliko

DELETE: localhost:8080/billionaire/{id}

POST: localhost:8080/billionaire
-body:
{
    "first-name": "Aliko",
    "lastName": "Dangote",
    "career": "Billionaire Industrialist1",
    "address": {
        "street": "aaa",
        "city": "cccc"
    }, 
    "offices": [
        {
        "country": "CN",
        "employee": 10
    },
    
        {
        "country": "DE",
        "employee": 11
    }
   ]
}

PUT: localhost:8080/billionaire
-body:
{
    "id": 4,
    "first-name": "Aliko2",
    "lastName": "Dangote1",
    "career": "Billionaire Industrialist2"
}

Error handler
1. POST billionaire: body without first-name, validationException
2. POST billionaire2: body without lastname, MethodArgumentNotValidException