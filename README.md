# 311-Chicago-Incidents
Manage 311 Chicago Incidents .The whole concept and datasets are described [here](https://www.kaggle.com/chicago/chicago-311-service-requests) . You can create a new incident report,update an existing one provided you know the unique Report ID ,run default searches on the database, search all incidents regarding a specific Zip Code or Address . To do all the above you have to register first.

## Setup
* You have to use a `Postgres` Database . You need to create a schema named `311-Chicago-Incidents`
* Change the file `application.properties` to match your postgres credentials
* `Hibernate` will take care of creating the schema tables
