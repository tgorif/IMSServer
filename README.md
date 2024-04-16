# **IMSServer**

IMSServer is a Java-based Inventory Management System (IMS) that utilizes MongoDB for data persistence. The project is built with Maven and uses the Spring Boot framework.

## **Features**

- SKU Management: The system allows for the creation, retrieval, and deletion of of entities and data based on a stock keeping id

- Inventory History: The system keeps track of inventory changes, recording the barcode, date, and operation type (addition or deletion).

- RESTful API: The system exposes a RESTful API for interacting with data and entities.

## **Setup**

The MongoDB connection URI and database name have to be specified in a `application.properties` file unter /ressources.
