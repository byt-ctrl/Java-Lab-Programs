# Flight Booking System - Code Analysis

## Overview
The Flight Booking System is a comprehensive Java application that simulates a complete airline reservation system. It provides flight search, booking management, seat availability tracking, and cancellation services through a layered architecture with clear separation of concerns between data models, business logic, validation, and user interface.

## System Architecture

### Core Design Pattern
- **Layered Architecture**: Clear separation between data models, business logic, validation, and presentation layers
- **Manager Pattern**: Dedicated manager classes handle specific business domains (flights and bookings)
- **Validation Layer**: Centralized input validation for data integrity
- **MVC-inspired Structure**: User interface acts as controller, managers as models, with separated business logic

## Code Block Analysis

### 1. Data Models

#### Flight Class
```java
class Flight {
    private String flightId, source, destination;
    private Date departureTime, arrivalTime;
    private int totalSeats, availableSeats;
    private double price;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
}
```
**Purpose**: Core flight information container
- **Immutable Identification**: Flight ID, source, and destination cannot be changed after creation
- **Time Management**: Uses `Date` objects for precise departure/arrival timing
- **Seat Tracking**: Maintains both total capacity and current availability
- **Pricing**: Double precision for accurate financial calculations
- **Formatting**: Static date formatter ensures consistent timestamp display

#### Flight Methods Analysis
```java
public String toString() {
    return String.format("Flight %s : %s -> %s | Departure : %s | Arrival : %s | Available : %d/%d | Price : Rs. %.2f",
            flightId, source, destination, DATE_FORMAT.format(departureTime), 
            DATE_FORMAT.format(arrivalTime), availableSeats, totalSeats, price);
}
```
**Logic**: Comprehensive flight summary with formatted dates, availability ratio, and currency formatting

#### Booking Class
```java
class Booking {
    private String bookingId, passengerNames, flightId;
    private int seatsBooked;
    private double totalPrice;
    private Date bookingDate;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
}
```
**Purpose**: Booking transaction record
- **Unique Identification**: Timestamp-based booking ID generation
- **Passenger Management**: Comma-separated passenger names storage
- **Flight Association**: Links booking to specific flight via flight ID
- **Financial Tracking**: Total transaction amount with precise calculations
- **Audit Trail**: Automatic booking timestamp for record keeping

### 2. Business Logic Layer

#### FlightManager Class
```java
class FlightManager {
    private List<Flight> flights = new ArrayList<>();
    
    public FlightManager() {
        initializeFlights();
    }
}
```
**Purpose**: Central flight inventory management

#### Flight Initialization
```java
private void initializeFlights() {
    long currentTime = System.currentTimeMillis();
    flights.add(new Flight("F001", "New York", "London", 
        new Date(currentTime + 86400000), 
        new Date(currentTime + 172800000), 150, 59999.99));
    // Additional flight configurations...
}
```
**Logic**: Dynamic flight scheduling based on current time
- **Future Scheduling**: All flights scheduled for future dates (1+ days ahead)
- **Realistic Timing**: Appropriate flight durations between cities
- **Diverse Routes**: Mix of international and domestic flights
- **Varied Capacity**: Different aircraft sizes (100-200 seats)
- **Market Pricing**: Realistic price ranges based on route distance

#### Core FlightManager Methods

##### Search Functionality
```java
public List<Flight> searchFlights(String source, String destination) {
    List<Flight> results = new ArrayList<>();
    for (Flight flight : flights) {
        if (flight.getSource().equalsIgnoreCase(source) && 
            flight.getDestination().equalsIgnoreCase(destination) &&
            flight.getAvailableSeats() > 0) {
            results.add(flight);
        }
    }
    return results;
}
```
**Search Logic**:
- **Case-Insensitive Matching**: Handles user input variations
- **Availability Filter**: Only returns flights with available seats
- **Route Matching**: Exact source and destination matching
- **Result Collection**: Returns list of matching flights

##### Seat Availability Management
```java
public boolean updateSeatAvailability(String flightId, int seats, boolean book) {
    Flight flight = findFlightById(flightId);
    if (flight == null) return false;
    
    if (book) {
        if (flight.getAvailableSeats() >= seats) {
            flight.setAvailableSeats(flight.getAvailableSeats() - seats);
            return true;
        }
    } else {
        flight.setAvailableSeats(flight.getAvailableSeats() + seats);
        return true;
    }
    return false;
}
```
**Logic**: Atomic seat availability updates
- **Booking Mode**: Validates sufficient seats before decrementing
- **Cancellation Mode**: Immediately increments available seats
- **Validation**: Ensures flight exists before any modifications
- **Boolean Response**: Clear success/failure indication

#### BookingManager Class
```java
class BookingManager {
    private Map<String, Booking> bookings = new HashMap<>();
}
```
**Purpose**: Booking lifecycle management using HashMap for O(1) lookup

#### Booking Operations

##### Booking Creation
```java
public String createBooking(String passengerNames, String flightId, int seats, double totalPrice) {
    String bookingId = "B" + System.currentTimeMillis();
    bookings.put(bookingId, new Booking(bookingId, passengerNames, flightId, seats, totalPrice));
    return bookingId;
}
```
**Logic**: Unique ID generation using system timestamp
- **Prefix Pattern**: "B" prefix for easy booking identification
- **Timestamp Uniqueness**: Millisecond precision prevents ID collisions
- **Immediate Storage**: Booking stored immediately upon creation
- **Return Confirmation**: Returns booking ID for user reference

##### Booking Retrieval and Cancellation
```java
public Booking getBooking(String bookingId) {
    return bookings.get(bookingId);
}

public boolean cancelBooking(String bookingId) {
    return bookings.remove(bookingId) != null;
}
```
**Logic**: Simple HashMap operations with null-safe returns

### 3. Validation Layer

#### Validator Class
```java
class Validator {
    public static boolean isValidName(String name) {
        return name != null && name.trim().length() > 1 && name.matches("[a-zA-Z\\s]+");
    }
}
```
**Validation Rules**:

##### Name Validation
- **Null Check**: Prevents null pointer exceptions
- **Length Validation**: Minimum 2 characters after trimming
- **Character Restriction**: Only letters and spaces allowed
- **Regex Pattern**: `[a-zA-Z\\s]+` ensures proper name format

##### ID Validation
```java
public static boolean isValidFlightId(String flightId) {
    return flightId != null && flightId.startsWith("F") && flightId.length() > 1;
}

public static boolean isValidBookingId(String bookingId) {
    return bookingId != null && bookingId.startsWith("B") && bookingId.length() > 1;
}
```
**Logic**: Prefix-based ID validation with length checking

##### Seat and Passenger Validation
```java
public static boolean isValidSeatCount(int seats, int available) {
    return seats > 0 && seats <= 10 && seats <= available;
}

public static boolean isValidPassengerCount(int count) {
    return count > 0 && count <= 10;
}
```
**Business Rules**:
- **Positive Numbers**: No zero or negative bookings
- **Maximum Limit**: 10 passengers per booking (business constraint)
- **Availability Check**: Cannot book more seats than available

### 4. User Interface Layer

#### UserInterface Class
```java
class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private FlightManager flightManager;
    private BookingManager bookingManager;
}
```
**Purpose**: Complete user interaction management with dependency injection

#### Menu System
```java
public void showMenu() {
    System.out.println("\n========== FLIGHT BOOKING SYSTEM ==========");
    System.out.println("1. Search Flights by Route");
    System.out.println("2. Book Flight");
    System.out.println("3. View Booking");
    System.out.println("4. Cancel Booking");
    System.out.println("5. View All Flights");
    System.out.println("6. View All Bookings");
    System.out.println("7. Exit");
    // ...
}
```
**Design**: Professional menu layout with clear numbering and visual separation

#### Input Handling
```java
public int getChoice() {
    try {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    } catch (Exception e) {
        scanner.nextLine(); // Clear invalid input
        return -1;
    }
}
```
**Error Handling**: Exception-safe input with automatic cleanup

#### Core UI Operations

##### Flight Search Interface
```java
public void searchFlightsByRoute() {
    System.out.print("Enter source city : ");
    String source = scanner.nextLine().trim();
    System.out.print("Enter destination city : ");
    String destination = scanner.nextLine().trim();

    if (source.isEmpty() || destination.isEmpty()) {
        System.out.println("Source and destination cannot be empty !");
        return;
    }

    List<Flight> results = flightManager.searchFlights(source, destination);
    displayFlights(results);
}
```
**Logic Flow**:
1. **Input Collection**: Gather source and destination
2. **Basic Validation**: Check for empty inputs
3. **Search Execution**: Delegate to FlightManager
4. **Result Display**: Format and show results to user

##### Booking Process
```java
public void bookFlight() {
    // Flight ID validation
    // Flight existence check
    // Seat availability verification
    // Passenger count validation
    // Name collection loop
    // Cost calculation
    // Confirmation process
    // Atomic booking execution
}
```

**Detailed Booking Flow**:

1. **Flight Selection Validation**
```java
System.out.print("Enter flight ID : ");
String flightId = scanner.nextLine().trim().toUpperCase();

if (!Validator.isValidFlightId(flightId)) {
    System.out.println("Invalid flight ID format !");
    return;
}
```

2. **Passenger Information Collection**
```java
for (int a = 1; a <= passengerCount; a++) {
    System.out.print("Enter name of passenger " + a + " : ");
    String name = scanner.nextLine().trim();
    if (!Validator.isValidName(name)) {
        System.out.println("Invalid passenger name !");
        return;
    }
    passengerNames.append(name);
    if (a < passengerCount) {
        passengerNames.append(" , ");
    }
}
```
**Logic**: Iterative name collection with validation and comma separation

3. **Confirmation and Execution**
```java
double total = passengerCount * flight.getPrice();
System.out.printf("Total cost : Rs. %.2f\n", total);
System.out.print("Confirm booking ? (y/n) : ");
String confirm = scanner.nextLine().trim();

if (confirm.equalsIgnoreCase("y")) {
    if (flightManager.updateSeatAvailability(flightId, passengerCount, true)) {
        String bookingId = bookingManager.createBooking(passengerNames.toString(), flightId, passengerCount, total);
        System.out.println("Booking successful! Booking ID : " + bookingId);
    }
}
```
**Logic**: Price calculation, user confirmation, and atomic booking execution

##### Cancellation Process
```java
public void cancelBooking() {
    // Booking ID validation
    // Booking existence check
    // Refund calculation display
    // Confirmation request
    // Atomic cancellation (seat restoration + booking removal)
}
```

**Cancellation Flow**:
1. **Booking Lookup**: Verify booking exists
2. **Refund Display**: Show refund amount to user
3. **Confirmation**: Require explicit user confirmation
4. **Atomic Operation**: Both seat restoration and booking removal must succeed

### 5. Main Application Controller

#### Main Class
```java
class Main {
    public static void main(String[] args) {
        try {
            FlightManager flightManager = new FlightManager();
            BookingManager bookingManager = new BookingManager();
            UserInterface ui = new UserInterface(flightManager, bookingManager);
            
            while (true) {
                // Menu display and choice processing
            }
        } catch (Exception e) {
            System.out.println("Critical error in application : " + e.getMessage());
        }
    }
}
```

**Application Lifecycle**:
1. **Component Initialization**: Create all manager instances
2. **Dependency Injection**: Pass managers to UI layer
3. **Main Loop**: Continuous menu processing until exit
4. **Exception Handling**: Top-level error catching for system stability

#### Menu Processing Logic
```java
while (true) {
    try {
        ui.showMenu();
        int choice = ui.getChoice();

        switch (choice) {
            case 1: ui.searchFlightsByRoute(); break;
            case 2: ui.bookFlight(); break;
            case 3: ui.viewBooking(); break;
            case 4: ui.cancelBooking(); break;
            case 5: ui.viewAllFlights(); break;
            case 6: ui.viewAllBookings(); break;
            case 7: 
                ui.exitSystem();
                return;
            default: 
                System.out.println("Invalid option! Please choose between 1-7.");
        }
    } catch (Exception e) {
        System.out.println("An error occurred. Please try again.");
    }
}
```

## Key Algorithms and Data Structures

### Search Algorithm
**Linear Search with Filtering**:
- Time Complexity: O(n) where n is number of flights
- Space Complexity: O(m) where m is number of matching flights
- Case-insensitive string matching
- Multiple criteria filtering (source, destination, availability)

### Booking ID Generation
**Timestamp-based Unique IDs**:
```java
String bookingId = "B" + System.currentTimeMillis();
```
- **Uniqueness**: Millisecond precision prevents collisions
- **Readability**: "B" prefix for easy identification
- **Sortability**: Chronological ordering inherent in design

### Data Storage Strategy
- **Flights**: ArrayList for sequential access and iteration
- **Bookings**: HashMap for O(1) lookup by booking ID
- **In-Memory**: All data stored in runtime memory for session persistence

### Seat Availability Algorithm
**Atomic Updates**:
```java
// Booking: Check availability before decrementing
if (flight.getAvailableSeats() >= seats) {
    flight.setAvailableSeats(flight.getAvailableSeats() - seats);
    return true;
}

// Cancellation: Immediate increment
flight.setAvailableSeats(flight.getAvailableSeats() + seats);
```

## System Features

### Flight Management
- **Dynamic Scheduling**: Flights scheduled relative to current time
- **Route-based Search**: Source/destination filtering with case-insensitive matching
- **Real-time Availability**: Seat counts updated with each booking/cancellation
- **Comprehensive Display**: Formatted flight information with all relevant details

### Booking System
- **Multi-passenger Support**: Up to 10 passengers per booking
- **Unique ID Generation**: Timestamp-based booking identification
- **Price Calculation**: Automatic total calculation based on passenger count
- **Confirmation Process**: User confirmation required for all transactions

### Validation Framework
- **Input Sanitization**: Comprehensive validation for all user inputs
- **Business Rules**: Enforced passenger limits and seat availability
- **Format Validation**: ID format checking with appropriate prefixes
- **Name Validation**: Regex-based name format enforcement

### User Experience
- **Professional Interface**: Clean menu system with clear options
- **Error Handling**: Graceful handling of invalid inputs
- **Confirmation Dialogs**: User confirmation for critical operations
- **Comprehensive Feedback**: Detailed success/failure messages

### Data Integrity
- **Atomic Operations**: Booking and cancellation operations are transaction-safe
- **Validation Gates**: Multiple validation checkpoints prevent invalid data
- **Consistent State**: System maintains consistent flight and booking state
- **Error Recovery**: Invalid operations don't compromise system state

## Technical Implementation Notes

### Exception Handling Strategy
- **Input Validation**: All scanner inputs wrapped in try-catch blocks
- **Graceful Degradation**: Invalid inputs result in error messages, not crashes
- **Resource Management**: Scanner properly closed on system exit
- **Top-level Safety**: Main method catches critical exceptions

### Memory Management
- **Collection Usage**: Appropriate data structures for different access patterns
- **Object Lifecycle**: Proper object creation and reference management
- **No Memory Leaks**: No circular references or unclosed resources

### Date and Time Handling
- **SimpleDateFormat**: Consistent date formatting across the system
- **Future Scheduling**: All flights scheduled for future dates
- **Timestamp Precision**: Millisecond precision for unique ID generation