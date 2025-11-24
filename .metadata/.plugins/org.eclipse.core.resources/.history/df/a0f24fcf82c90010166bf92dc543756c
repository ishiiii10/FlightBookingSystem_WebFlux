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
<img width="1460" height="839" alt="Screenshot 2025-11-24 at 7 53 57 PM" src="https://github.com/user-attachments/assets/6c53e5ef-fb51-432b-a4c9-30c10bc413c8" />

### Fetch Airline
<img width="1349" height="882" alt="Screenshot 2025-11-25 at 2 40 16 AM" src="https://github.com/user-attachments/assets/3e94de59-61d8-4e15-9a20-b6dbad64b786" />

### Add Flight to Inventory
<img width="1335" height="773" alt="Screenshot 2025-11-25 at 2 40 25 AM" src="https://github.com/user-attachments/assets/1d82faab-1778-4de7-b5d7-d23be335e082" />

### Search Flight
<img width="1452" height="863" alt="Screenshot 2025-11-25 at 2 40 44 AM" src="https://github.com/user-attachments/assets/db769d15-da0d-4876-9c91-feaa96b2fde3" />

### Book Flight
<img width="1454" height="801" alt="Screenshot 2025-11-25 at 2 40 59 AM" src="https://github.com/user-attachments/assets/ffaede5b-ddbc-4bda-9cf1-0577fdbaf41a" />

### Get Ticket
<img width="1367" height="837" alt="Screenshot 2025-11-25 at 2 41 08 AM" src="https://github.com/user-attachments/assets/2f25d968-44c4-4785-b323-20473a8a70d2" />

### Get history
<img width="1464" height="861" alt="Screenshot 2025-11-25 at 2 41 15 AM" src="https://github.com/user-attachments/assets/106b9bd5-433f-4e96-a5b5-298f625d4388" />

### Cancel Booking
<img width="1426" height="828" alt="Screenshot 2025-11-25 at 2 48 11 AM" src="https://github.com/user-attachments/assets/ed39b3a7-08af-435a-9314-6c6843da2fdc" />

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






