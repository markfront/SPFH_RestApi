## Introduction

This repository contains code to run SinglePageFullHtml (SPFH) (https://github.com/markfront/SinglePageFullHtml) as a restful service.

## Getting Started

### Clone/Compile/Package
```
$ git clone https://github.com/markfront/SPFH_RestApi.git
$ cd SPFH_RestApi
$ ./mvnw clean package
```

### Start the Service

```
$ ./mvnw spring-boot:run
```

The service will be run on http://localhost:8080

### Call the Service

Enter the following in browser address bar or using GET method in Postman:
```
http://localhost:8080/fget?url=<url>
```

For example, http://localhost:8080/fget?url=https://www.wonderslist.com/10-most-amazing-places-on-earth

After about 10-30 seconds, you should see the result returned as JSON.
