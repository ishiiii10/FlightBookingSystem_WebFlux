# ✈️ Reactive Flight Booking System (Spring WebFlux + MongoDB)
A fully reactive airline reservation platform built with Spring Boot WebFlux, MongoDB, and Project Reactor.
Users can search flights, book tickets, cancel bookings, and view ticket history — all in a non-blocking reactive pipeline.

## 🚀 Features
```
 Feature                                   Notes                                                     
 -------------------------------------     --------------------------------------------------------- 
 Create Airline                            Prevents duplicate airline codes                          
 Add Flight to Inventory                   Validates city routes and seat limits                     
 Search Flights (One-way / Round Trip)     Return flight optional based on availability              
 Book Ticket                               Generates PNR, assigns seats, validates passengers        
 Cancel Ticket                             Updates status & restores seat count                      
 Get Ticket Details                        Shows passengers, route, airline and journey info         
 Booking History                           Fetch by passenger email                                  
 Validation                                Enforced through DTO validators and custom exceptions     
 Reactive Programming                      Built using `Mono` & `Flux`                               
 Error Handling                            Custom exception messages + proper HTTP codes             
 Unit Tests                                Controllers & Services tested via Mockito + WebTestClient 
```
## 🛠️ Tech Stack
```
 Layer              Technology                     
 -----------------  ------------------------------ 
 Backend Framework  Spring Boot 3 + WebFlux        
 Database           MongoDB (Reactive)             
 Reactive Engine    Project Reactor                
 Build System       Maven                          
 Testing            JUnit5, Mockito, WebTestClient 
 Language           Java 17                        
```
## 📂 Project Structure
```
src/
 ├─ main/java/com.FlightBooking
 │   ├─ controller/     (API layer)
 │   ├─ service/        (Business logic)
 │   ├─ repository/     (Reactive Repositories)
 │   ├─ entity/         (Mongo Models)
 │   ├─ dto/request     (Incoming payloads)
 │   ├─ dto/response    (API responses)
 │   ├─ exceptions/     (Custom handlers)
 │   └─ enums/          (BookingStatus, City, Gender, etc.)
 │
 └─ test/java/com.FlightBooking
     ├─ controller/     (WebTestClient tests)
     └─ service/        (Mockito unit tests)
```
## ⚙️ Setup & Installation
### 1️⃣ Clone the repository
git clone https://github.com/yourusername/flight-booking-webflux.git
cd flight-booking-webflux

### 2️⃣ Start MongoDB
Use local MongoDB (default port: 27017) or Atlas.

### 3️⃣ Configure application.properties
server.port=9000
spring.application.name=Webflux_FlightBooking
spring.data.mongodb.uri=mongodb://localhost:27017/flightdb
spring.data.mongodb.database=flightdb

### 4️⃣ Run the application
mvn spring-boot:run

### 🧪 Running Tests
mvn test
Test coverage includes:
Airline Controller + Service tests
Flight Controller + Service tests
Booking Controller + Service tests

## 🧑‍💻 Available API Endpoints
### ✈ Airline Management
```
 Method  Endpoint                        Description      
 ------  ------------------------------  ---------------- 
 `POST`  `/api/v1.0/flight/airline`      Create airline   
 `GET`   `/api/v1.0/flight/airline/all`  Get all airlines 
```

### 🛫 Flight Inventory
```
 Method                                                    Endpoint 
 --------------------------------------------------------  -------- 
 `POST` `/api/v1.0/flight/airline/inventory` → Add flight           
 `POST` `/api/v1.0/flight/search` → Search flights                  
```

### 🎟 Booking APIs
```
 Method    Endpoint                                    Purpose               
 --------  ------------------------------------------  --------------------- 
 `POST`    `/api/v1.0/flight/booking/{flightId}`       Book ticket           
 `DELETE`  `/api/v1.0/flight/booking/cancel/{pnr}`     Cancel booking        
 `GET`     `/api/v1.0/flight/ticket/{pnr}`             Get ticket details    
 `GET`     `/api/v1.0/flight/booking/history/{email}`  Fetch booking history 
```
### 🧰 Error Handling Examples
```
 Scenario                 Status             Message                                 
 -----------------------  -----------------  --------------------------------------- 
 Airline already exists   `409 CONFLICT`     `"Airline with code AI already exists"` 
 Flight not found         `404 NOT FOUND`    `"Flight not found"`                    
 Seats unavailable        `400 BAD REQUEST`  `"Not enough available seats"`          
 Passenger phone invalid  `400 BAD REQUEST`  `"Contact number must be 10 digits"`    
```
## 📌 Postman APIs 
### Create Airline
<img width="1433" height="845" alt="Screenshot 2025-11-25 at 3 29 56 AM" src="https://github.com/user-attachments/assets/1719388a-0ec5-4edb-8373-2c91cc30d2a2" />


### Fetch Airline
<img width="1396" height="806" alt="Screenshot 2025-11-25 at 3 30 25 AM" src="https://github.com/user-attachments/assets/36ae4967-c0e4-49bb-baea-881275b5221c" />

### Add Flight to Inventory
<img width="1388" height="802" alt="Screenshot 2025-11-25 at 3 30 59 AM" src="https://github.com/user-attachments/assets/95bdbe36-56c8-4df7-98b2-b2286ddad60a" />


### Search Flight
<img width="1445" height="813" alt="Screenshot 2025-11-25 at 3 32 02 AM" src="https://github.com/user-attachments/assets/d961ee0a-ed40-4c96-bc80-c329d0efffe9" />



### Book Flight
<img width="1462" height="813" alt="Screenshot 2025-11-25 at 3 33 25 AM" src="https://github.com/user-attachments/assets/b63b8b12-8e45-44cb-beaa-a3ddca670f7e" />


### Get Ticket
<img width="1379" height="857" alt="Screenshot 2025-11-25 at 3 33 59 AM" src="https://github.com/user-attachments/assets/57a1556c-ea72-459b-91e5-bb28e46e6fa5" />


### Get history
<img width="1358" height="843" alt="Screenshot 2025-11-25 at 3 34 13 AM" src="https://github.com/user-attachments/assets/f3e24295-dc7f-42ca-9eee-a26a1f78a781" />


### Cancel Booking
<img width="1399" height="821" alt="Screenshot 2025-11-25 at 3 34 40 AM" src="https://github.com/user-attachments/assets/43b63d81-8028-4e1b-b7dc-e2f1ff02033d" />


## 📌 Database Schema Diagram

![PHOTO-2025-11-25-02-34-57](https://github.com/user-attachments/assets/87c509f3-9300-4ddd-b23e-dd0ff3304e51)


## 📌 Sonar Qube Report

![PHOTO-2025-11-25-02-13-29](https://github.com/user-attachments/assets/4f529e8c-fbd6-474f-800e-07d9d1d0baf9)

## 📌 Sonar Qube Summary

<img width="1419" height="907" alt="Screenshot 2025-11-25 at 2 24 16 AM" src="https://github.com/user-attachments/assets/16bae9e2-1ddc-4ea9-844a-e7dc89a6c312" />

## 📌 Jmeter Report

<img width="822" height="436" alt="Screenshot 2025-11-25 at 2 27 19 AM" src="https://github.com/user-attachments/assets/37670bd0-293c-4644-8192-2b5e61edb9a7" />
<img width="859" height="524" alt="Screenshot 2025-11-25 at 2 27 27 AM" src="https://github.com/user-attachments/assets/2bb00cc7-2a10-4599-8d26-62eff8cbeda7" />
<img width="1469" height="446" alt="Screenshot 2025-11-25 at 2 27 40 AM" src="https://github.com/user-attachments/assets/fcda1305-e65e-4fa2-9abe-2cbeff5b4164" />




