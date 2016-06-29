**CREATING USER:**
curl -X POST http://localhost:8081/create -H "Content-Type: application/json" -d """{"enabled":true,"login":"ivanov111","email":"ivanov@gmail.com","name":"Ivan","surname":"Ivanov","roles":["User"],"groups":["5720a6bf56d06b2bbf907230"],"permissions":[],"info":{"phone":"+380682233456","company":"Inc"},"created":"2004-01-02T21:13:45-07:00","hash":123456,"sessionTime":15}"""

| Answers                                                  |
-----------------------------------------------------------|
| {"code":200,"message":"Success"}                         |
| {"code":400,"message":""Error""}                   	   |
| {"code":404,"message":"Someone already has that login"}  |

