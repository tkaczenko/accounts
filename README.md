**CREATING USER:**
curl -X POST http://localhost:8081/create -H "Content-Type: application/json" -d 
"""{"id":1,"enabled":true,"login":"ivanov111","email":"ivanov@gmail.com","name":"Ivan","surname":"Ivanov","roles":["User"],"groups":["5720a6bf56d06b2bbf907230"],"permissions":["Some"],"info":{"phone":"+380682321234","company":"INC"},"created":"2004-01-02T21:13:45-07:00",
"hash":123456,"sessionTime":15}"""

| Answers                                                  |
-----------------------------------------------------------|
| {"code":200,"message":"Success"}                         |
| {"code":400,"message":"Error"}                           |
| {"code":404,"message":"Someone already has that login"}  |

**UPDATING USER'S INFORMATION:**
curl -X POST http://localhost:8081/update -H "Content-Type: application/json" -d 
"""{"id":1,"enabled":false,"login":"ivanov222","email":"ivanov2@gmail.com","name":"Ivan","surname":"Ivanov","roles":["Admin"],"groups":["5720a6bf56d06b2bbf907230"],"permissions":["Some"],"info":{"phone":"+380682321234","company":"Ltd"},"created":"2012-01-02T21:13:45-07:00","hash":123456,"sessionTime":15}"""

| Answers                                                    |
-------------------------------------------------------------|
| {"code":200,"message":"Success"}                           |
| {"code":404,"message":"Someone already has that login"}    | 
| {"code":400,"message":"Error"}                             |
| {"code":404,"message":"This id doesn't match any document"}|

**UPDATING PASSWORD:**
curl -X POST http://localhost:8081/update_password -H "Content-Type: application/json" -d """{"id":1,login":"ivanov111","hash":123456}"""

| Answers                                                    |
-------------------------------------------------------------|
| {"code":200,"message":"Success"}                           |
| {"code":404,"message":"Someone already has that login"}    | 
| {"code":400,"message":"Error"}                             |
| {"code":404,"message":"This id doesn't match any document"}|

**UPDATING PASSWORD BY USER:**
curl -X POST http://localhost:8081/profile/update_password -H "Content-Type: application/json" -d """{"login":"ivanov111",oldHash":123456,"hash":654321}"""

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"Success"}                             |
| {"code":400,"message":"Error"}                               |
| {"code":404,"message":"This login doesn't match any account"}|
| {"code":404,"message":"Wrong password"}                      |

**UPDATING USER'S INFORMATION BY USER:**
curl -X POST http://localhost:8081/profile/update_info -H "Content-Type: application/json" -d """{"login":"ivanov111","info":{"phone":"+3806632324","company":"Ltd"}}"""

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"Success"}                             |
| {"code":400,"message":"Error"}                               |
| {"code":404,"message":"This login doesn't match any account"}|
| {"code":404,"message":"Wrong password"}                      |

**LIST OF ALL ACCOUNTS:**
curl -X POST http://localhost:8081/list -H "Content-Type: application/json" -d """{}"""

| Answers                                                      |
---------------------------------------------------------------|
|                                                              | 
| {"code":400,"message":"Error"}                               |
| {"code":204,"message":"No Content"}                          |

**ENABLE USER:**
curl -X POST http://localhost:8081/enable -H "Content-Type: application/json" -d """{"id":1}"""

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"Success"}                             |
| {"code":400,"message":"Error"}                               |
| {"code":404,"message":"This id doesn't match any document"}  |


**DISABLE USER:**
curl -X POST http://localhost:8081/disable -H "Content-Type: application/json" -d """{"id":1}"""

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"Success"}                             |
| {"code":400,"message":"Error"}                               |
| {"code":404,"message":"This id doesn't match any document"}  |


**DELETE GROUP:**
curl -X POST http://localhost:8081/delete_group -H "Content-Type: application/json" -d

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"Success"}                             |
| {"code":400,"message":"Error"}                               |
| {"code":404,"message":"This id doesn't match any document"}  |

**DELETE ROLE:**
curl -X POST http://localhost:8081/acl/roles/delete_role -H "Content-Type: application/json" -d 

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"Success"}                             |
| {"code":400,"message":"Error"}                               |
| {"code":404,"message":"This id doesn't match any document"}  |

**INSERT ROLE:**
curl -X POST http://localhost:8081/insert_role -H "Content-Type: application/json" -d

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"Success"}                             |
| {"code":400,"message":"Error"}                               |
| {"code":404,"message":"This id doesn't match any document"}  |

**INSERT GROUP:**
curl -X POST http://localhost:8081/insert_group -H "Content-Type: application/json" -d """{"id":1,"name":"Some group"}""

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"Success"}                             |
| {"code":400,"message":"Error"}                               |
| {"code":404,"message":"This id doesn't match any document"}  |

**SESSION TIME OF ACCOUNT:**
curl -X POST http://localhost:8081/session_time -H "Content-Type: application/json" -d """{"login":"ivanov111"}"""

| Answers                                                      |
---------------------------------------------------------------|
| {"code":200,"message":"20"}                                  |
| {"code":404,"message":"This login doesn't match any account"}|

**LIST OF ACCOUNT PERMISSIONS:**
curl -X POST http://localhost:8081/permissions -H "Content-Type: application/json" -d """{"id":1}"""

| Answers                                                      |
---------------------------------------------------------------|
|                                                              |
| {"code":404,"message":"This id doesn't match any document"}  |
