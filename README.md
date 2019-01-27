# 311-Chicago-Incidents
Manage 311 Chicago Incidents .The whole concept and datasets are described [here](https://www.kaggle.com/chicago/chicago-311-service-requests) . You can create a new incident report,upvote a specific incident provided you know the unique Report ID and run default searches on the database.All the above can be done via POSTs and GETs through a HTTP Client for API testing(e.g. Postman etc.)

## Setup
* You have to use a `MongoDB` Database . You need to create a database named `NoSQL-311CI`.
* Change the file `application.properties` to match the Mongo database name.
