# Ifttt CallBackService serverless API (deployed on GCP App Engine using google cloud platform)

If you need a call back to your webhook in the ifttt platform, you can use this webservice. You can see all the apis using the swagger ui:

[`Swagger UI`](http://localhost:8080/swagger-ui.html)

**`CallBackController`** contains the apis for scheduling the call back.
**`PingController`** is more for debugging the service.

The CallBackService project is created with [`gcp-appengine`] using Spring Boot 2 and Java 8.

You can debug the project by pinging the service at `/ping` that accepts `GET` request.

###How to create app engine using the Google cloud cli: 

**`gcloud app create --region northamerica-northeast1`**

###How to build and deploy using the Google cloud cli: 

**`mvn clean package appengine:deploy`**

###How to browse to your app when deployed 

**`gcloud app browse`**






