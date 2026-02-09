# Booking System

Client-server booking application

This is my project for the MIP course (Tema 2). It's a client-server booking application.

## Structure

- **Server**: Java application (using Quarkus) that handles the database and logic.
- **Client**: Simple Java console app that connects to the server.

## How to run

### Prerequisites
- Java 17 or higher
- Maven
- PostgreSQL (or Docker for the database)

### 1. Start the Server
Go to the `Server` folder and run:

```bash
cd Server
# If you have docker installed, Quarkus might start a dev db automatically
mvn quarkus:dev
```

Or just compile and run the jar if you built it.

### 2. Start the Client
Open a new terminal, go to the `Client` folder:

```bash
cd Client
mvn compile exec:java
```

## Features
- List available time slots
- Make a booking
- View my bookings
- Cancel a booking

The server runs on port 8080 (HTTP) and 9090 (Socket).
