# JavaEE JSON Web Token (JWT)
Simple JavaEE REST service secured with Soteria and JSON Web Token.

## Installation

### Requirements
* JDK 1.8
* Apache Maven
* Application Server (Glassfish/Payara/Wildfly/TomEE)

### Compile and Package

Being Maven centric, you can compile and package it with `mvn clean package` or `mvn clean install`.
Then deploy into application server.

### Test Sample

#### Request JSON Web Token
To be able access the resources, you need JSON Web Token. Retrieve the token via `tokens` endpoint.

```
curl -i \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-X POST  \
-d '{"username": "username", "password": "password"}' \
http://[YOUR_HOST]:[YOUR_PORT]/apps/resources/tokens
```

The token will provided on the Header Response when everything is ok.

#### Request the Resources
Having the JSON Web Token, put this on header request as `Authorization: Bearer [JSON Web Token]`

```
curl -i \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ" \
http://[YOUR_HOST]:[YOUR_PORT]/apps/resources/books
```

## License
This work is licensed under a [MIT License](https://choosealicense.com/licenses/mit/).