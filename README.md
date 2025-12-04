# Online Clothing Store

## **Overview**

This project is a  **Java-based e-commerce web application** for an online clothing store. It demonstrates the core features of an e-commerce system, including:

* Customer login/auto-registration
* Product browsing
* Size & color variant management
* Shopping cart functionality
* Order placement and confirmation


## **User Manual**

Before running the project, ensure you have:

* Java JDK 17+
* MySQL Server installed
* Apache Tomcat 10+ installed
* Maven

---

## **Database Setup & Connection**

Make sure MySQL is running before launching the app.

Open:

```
src/main/resources/application.properties
```

Update:

```java
spring.datasource.username=root
spring.datasource.password=password

```

Open:

```
src/main/java/com/project/store/DBInitializer.java
```

Update:

```java
private static final String user = "root";
private static final String password = "password";
```
Once your login information is updated, run the DBInitializer.java class to create the database.

---

## **How to Run the Project**

### **1. Build the Project Using Maven**

In the project root directory:

```
mvn clean install
```

This compiles the Java code and prepares it for deployment.

### **2. Deploy to Apache Tomcat**

Open:
```
src/main/java/com/project/StoreApplication.java
```
Run this class. This will start the Tomcat browser.

---

## **Accessing the Application**

Once Tomcat is running, open your browser:

```
http://localhost:8080/
```

You should see the **Customer Login Page**.

---
