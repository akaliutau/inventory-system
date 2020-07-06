# About

Inventory Viewing system is a simple solution for managing inventory records. It consists from two parts: the back-end service and the front-end server.

The back-end service is built on the basis of Spring Boot with H2 in-memory database for persistence level. 

Due to potentially big amount of inventory records I decided to implement server-side variant of pagination: only a small subset of all records needed to be displayed is queried on server and returned to front-end side. This approach is considered to be the best practice when dealing with Big Data and will guarantee low response time for page and smooth UX, regardless of size of the database.

Back-end has only one end-point at `` /api/v1/items``

Full API Documentation for back-end server's API is available on http://localhost:9000/swagger-ui.html. Swagger UI works in the latest versions of Chrome, Safari, Firefox, and Edge.

The front-end server is built using React 16 library with Material Design styling. This is a single-page application allowing to view and navigate through all available inventory records. Database is pre-populated with 10 sample records. Page-size limit is set to 5 records/page, just to demonstrate pagination functionality.

<br/>


## Installation

In order to build and run both back-end and front-end the following prerequisites are needed:

[node.js 12+ (npm 6.13+)](https://nodejs.org/en/download/)

[JDK 8+](https://openjdk.java.net/)

[Lombok](https://projectlombok.org/download)


<br/>

## Building

This project is organized as a typical multi-module project with the following structure:


+ <b> inventory-service </b> --  module containing source code of back-end server

+ <b> inventory-ui </b> -- module containing source code of front-end server



##### Building Back-end Server:
Run from the project's root directory:
``mvn clean package ``

##### Building Front-end Server:
Run from /inventory-ui directory (where the package.json is located):
``npm install``

## Usage
##### Back-end Server:

- run ``org.warehouse.MainApp`` class using IDE
or 
- run ``mvn spring-boot:run`` from /inventory-service directory using command line

Server will start on localhost:9000 address. One can point browser to http://localhost:9000/swagger-ui.html to see API documentation


##### Front-end Server:

- run ``npm start`` from /inventory-ui directory, this will compile the React app and automatically launch it in the browser on the URL localhost:3000

