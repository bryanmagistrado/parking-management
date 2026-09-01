# Parking Lot Management API

A Java 8 Spring Boot RESTful API using Spring Data JPA and an H2 in-memory database to manage parking lots, vehicles, and live parking sessions.

## 🛠️ Prerequisites
Before running this application, ensure you have the following installed:
* Java Development Kit (JDK) 8 or higher
* Apache Maven 3.6+ (or use the included Maven Wrapper scripts)

---

## 🚀 Building the Project
To compile the source code and build the standalone executable JAR file, open your terminal/command prompt and navigate to the project root directory.

### 🪟 Windows (Command Prompt / PowerShell)
```cmd
# Using the Maven Wrapper script
mvnw.cmd clean package

# Using system Maven (if installed)
mvn clean package
```

### 🍎 macOS & 🐧 Linux
```bash
# Add execution permissions to the wrapper if needed (first-time only)
chmod +x mvnw

# Run the build script
./mvnw clean package
```
*The compiled runnable JAR file will be generated inside the `target/` directory as `parking-app-0.0.1-SNAPSHOT.jar`.*

---

## 🏃 Running the Application
You can launch the application immediately using the Spring Boot plugin or by executing the packaged JAR directly.

### Option 1: Run via Maven Wrapper
* **Windows:** `mvnw.cmd spring-boot:run`
* **macOS / Linux:** `./mvnw spring-boot:run`

### Option 2: Run the Standalone JAR (All Platforms)
```bash
java -jar target/parking-app-0.0.1-SNAPSHOT.jar
```
*The application will boot on port `8080` (http://localhost:8080).*

---

## 🧪 Importing Postman Collections
A pre-configured Postman collection with sample payloads for all valid/invalid registration, check-in, and inspection scenarios is available directly inside this repository.

* **File Path:** `postman/parking-lot-api.postman_collection.json`
* **How to use:** Open Postman, click the **Import** button in the top-left pane, select this file from your local directory, and you can instantly test all endpoints with ready-to-use payloads.

---

## 🔍 H2 Database Console
An in-memory database is active during runtime. You can inspect the live database tables by visiting:
* **Console URL:** http://localhost:8080/h2-console
* **JDBC URL:** `jdbc:h2:mem:parkingdb`
* **Username:** `sa`
* **Password:** *(Leave blank)*

---

## 📡 API Endpoints Summary

### Parking Lots
* `POST /api/parking-lots` - Register a new parking lot
* `GET /api/parking-lots/{lotId}/capacity` - View occupied/available spaces
* `GET /api/parking-lots/{lotId}/vehicles` - List currently parked vehicles

### Vehicles
* `POST /api/vehicles` - Register a vehicle

### Parking Operations
* `POST /api/parking/check-in` - Park a vehicle
* `POST /api/parking/check-out/{licensePlate}` - Check-out a vehicle