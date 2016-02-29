TAA-Project 2015-2016
===================

- This project is a Spring Hibernate application, it is a REST API that manage the following entities:
    - Developers
    - Tasks
    - ProductBacklog
    - UserStories
   
- It is part of the ISTIC TAA course and use docker and docker-compose in order to build the following architecture:
    - An nginx in reverse proxy used as a load balancer
    - 3 instances of the spring application
    - A mySQL database
   
----------
[TOC]

----------

##0. usage

- Using the docker-compose file with builded version on the docker hub:
    - `docker-compose up -d`
    - Get a coffee. "need to pull images"
    - The application is available at `myapp.taa.fr:8080`

- Simple test

##1. Spring - hibernate

- Domain :
    - I used **JPA** annotations on the domain entities.
    
- Repositories :
    - Spring data offers interfaces such as JPARepository and PagingAndSortingRepository that will generate REST queries
    for the Entity passed in the generic place holder.
    - spring data can also create custom queries based on functionName in the interface (see DeveloperRepository)
    
- Services :
    - because our API do really simple and straight forward REST services, the services classes are not necessary.
    The repositories are directly injected into REST Controllers (based on Jhipster reverse-engineering)
    
- Rest Controller:
    - enable CORS with `@crossOrigin` annotations, this allow testing the front end(GWT/AngularJS projects) with the back end API 

- Swagger :
    - Available at `myapp.taa.fr:8080/swagger-ui.html`
    - generated with maven plugin and `@EnableSwagger2` annotation in ApplicationSpring

----------

##2. Database

- The database use a mySQL server
- The schema generation is handled  by`Liquibase` tool:
    - In case of changes of database name, user name and user pwd in the **spring configuration file**,
    you also need to make these changes in **pom.xml liquibase plugin**
    - To update your schema with changes in your domain entities use 
        - `mvn liquibase:createChangelog`
    - To generate your database from the schema 
        - `mvn liquibase:update`
    - To delete your database 
        - `mvn liquibase:dropAll`
 
----------

##3. Docker

- Docker containers allow to build the project and run it. using Dockerfile-init also generate the mysql from the schema
- Docker-compose deploy the full architecture from latest build on docker hub


----------

##4. Project Issues and possible enhancements

- nginx-proxy do not support compose-file V2, otherwise we could have use docker-compose 
in order to build our containers using both Dockerfile and Dockerfile-init files. instead of using builded images on docker hub
We also could have use the `depends_on` key, allowing to build the containers in a specific order.
    - https://github.com/jwilder/nginx-proxy/issues/362  
    - https://docs.docker.com/compose/compose-file/#version-2