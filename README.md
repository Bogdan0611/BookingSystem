# Booking System

A client-server booking application built with Java that demonstrates network programming concepts, database integration, and RESTful API design. The system allows users to manage time slot reservations through both REST endpoints and socket connections.

## What it does

This is a booking management system where users can reserve time slots. The application uses a client-server architecture where the server handles all business logic and data persistence while clients connect to make reservations, view availability, and manage their bookings.

The project demonstrates practical implementation of network protocols, concurrent client handling, database transactions, and separation of concerns in a distributed system.

## Technologies

- **Backend Framework**: Quarkus (lightweight Java framework for cloud-native applications)
- **Database**: PostgreSQL with Hibernate ORM for data persistence
- **Communication**: Dual protocol support - HTTP/REST and TCP Sockets
- **Build Tool**: Maven
- **Java Version**: 17+

## Architecture

The application consists of three main components:

1. **Client Application** - Console-based Java application that connects to the server
2. **REST API Server** - HTTP endpoints for CRUD operations on bookings
3. **Socket Server** - TCP socket server for real-time client communication
4. **Database Layer** - PostgreSQL database with JPA/Hibernate entities

Communication flow:
- Client connects to server via sockets (port 9090) or HTTP (port 8080)
- Server validates requests and performs database operations
- Database stores booking records with user information and time slots
- Responses are sent back to clients with operation results

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+ (or Docker for containerized database)

## Setup

### Database Setup

Option 1 - Manual PostgreSQL installation:

```bash
# Create database
createdb booking_system

# The application will auto-create tables on first run
```

Option 2 - Using Docker:

```bash
docker run --name booking-postgres \
  -e POSTGRES_DB=booking_system \
  -e POSTGRES_USER=bookinguser \
  -e POSTGRES_PASSWORD=bookingpass \
  -p 5432:5432 \
  -d postgres:15
```

### Configuration

Edit `Server/src/main/resources/application.properties` if you need custom database settings:

```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=bookinguser
quarkus.datasource.password=bookingpass
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/booking_system
```

## Running the Application

### Start the Server

Navigate to the Server directory and run in development mode:

```bash
cd Server
mvn quarkus:dev
```

The server will start on:
- HTTP/REST: http://localhost:8080
- Socket Server: localhost:9090

In dev mode, Quarkus provides a dev UI at http://localhost:8080/q/dev

### Start the Client

Open a new terminal and navigate to the Client directory:

```bash
cd Client
mvn compile exec:java
```

The client will connect to the server and display an interactive menu.

## Features

The booking system supports the following operations:

- **List Available Slots** - View all time slots that haven't been booked yet
- **Make a Booking** - Reserve an available time slot with your name/email
- **View My Bookings** - See all bookings associated with your account
- **Cancel Booking** - Remove a reservation and free up the time slot
- **Admin Functions** - View all bookings across all users (server-side)

Each booking includes:
- Unique booking ID
- User information (name, email)
- Time slot (date and time)
- Booking status (active/cancelled)
- Timestamp of when booking was made

## Technical Details

### REST API Endpoints

The server exposes RESTful endpoints (examples):

```
GET    /api/bookings          - List all bookings
POST   /api/bookings          - Create new booking
GET    /api/bookings/{id}     - Get specific booking
DELETE /api/bookings/{id}     - Cancel booking
GET    /api/timeslots         - View available slots
```

### Socket Communication

The socket server listens on port 9090 and handles:
- Multiple concurrent client connections
- Custom protocol for booking operations
- Real-time availability updates
- Connection state management

### Project Structure

```
BookingSystem/
├── Server/
│   ├── src/main/java/
│   │   ├── entities/         # JPA entities (Booking, TimeSlot, etc.)
│   │   ├── resources/        # REST endpoints
│   │   ├── services/         # Business logic
│   │   └── sockets/          # Socket server implementation
│   └── src/main/resources/
│       └── application.properties
├── Client/
│   └── src/main/java/
│       └── # Client application code
└── README.md
```

## Development

### Building for Production

Create a production-ready uber-jar:

```bash
cd Server
mvn package -Dquarkus.package.jar.type=uber-jar
```

Run the packaged application:

```bash
java -jar target/server-1.0-SNAPSHOT-runner.jar
```

### Native Compilation

Compile to native executable with GraalVM:

```bash
mvn package -Dnative
```

Or using container build:

```bash
mvn package -Dnative -Dquarkus.native.container-build=true
```

### Testing

The application includes unit tests and integration tests. Run them with:

```bash
mvn test
```

## Troubleshooting

**Database connection issues:**
- Verify PostgreSQL is running: `pg_isready`
- Check credentials in application.properties
- Ensure database exists: `psql -l | grep booking_system`

**Port already in use:**
- Change ports in application.properties
- Kill existing process: `lsof -ti:8080 | xargs kill -9`

**Client can't connect:**
- Verify server is running and listening
- Check firewall settings
- Confirm socket port 9090 is accessible

## Notes

This project was created as part of the MIP (Middleware and Integration Platforms) course. It demonstrates fundamental concepts in distributed systems, network programming, and enterprise application architecture.

The implementation focuses on code clarity and educational value rather than production-grade optimizations.