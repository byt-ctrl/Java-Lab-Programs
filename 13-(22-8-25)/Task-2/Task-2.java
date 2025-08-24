/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [22-08-25]
 | Program Definition : 2. Flight booking system (discuss in details and explain what features can be used).
 -------------------------------------------------------------*/

// simple implementation of flight booking system
// features include searching flights, booking tickets, and viewing booking details etc.

import java.text.SimpleDateFormat;
import java.util.*;

class Flight {
    private String flightId,source,destination;
    private Date departureTime,arrivalTime;
    private int totalSeats,availableSeats;
    private double price;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

    public Flight(String id , String src , String dest , Date dep , Date arr , int seats , double price) 
    {
        this.flightId=id;
        this.source=src;
        this.destination=dest;
        this.departureTime=dep;
        this.arrivalTime=arr;
        this.totalSeats=seats;
        this.availableSeats=seats;
        this.price=price;
    }

    public String getFlightId() { return flightId; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public Date getDepartureTime() { return departureTime; }
    public Date getArrivalTime() { return arrivalTime; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
    public double getPrice() { return price; }
    public void setAvailableSeats(int seats) { this.availableSeats = seats; }

    @Override
    public String toString() 
    {
        return String.format("Flight %s : %s -> %s | Departure : %s | Arrival : %s | Available : %d/%d | Price : Rs. %.2f",
                flightId,source,destination,DATE_FORMAT.format(departureTime), 
                DATE_FORMAT.format(arrivalTime),availableSeats,totalSeats,price);
    }
}

class Booking {
    private String bookingId, passengerNames, flightId;
    private int seatsBooked;
    private double totalPrice;
    private Date bookingDate;
    private static final SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    public Booking(String id,String names,String flightId,int seats,double price) 
    {
        this.bookingId=id;
        this.passengerNames=names;
        this.flightId=flightId;
        this.seatsBooked=seats;
        this.totalPrice=price;
        this.bookingDate=new Date();
    }

    public String getBookingId() { return bookingId; }
    public String getPassengerNames() { return passengerNames; }
    public String getFlightId() { return flightId; }
    public int getSeatsBooked() { return seatsBooked; }
    public double getTotalPrice() { return totalPrice; }
    public Date getBookingDate() { return bookingDate; }

    @Override
    public String toString() 
    {
        return String.format("Booking %s | Passengers: %s | Flight: %s | Seats: %d | Total: Rs. %.2f | Date: %s",
                bookingId,passengerNames,flightId,seatsBooked,totalPrice,DATE_FORMAT.format(bookingDate));
    }
}

class FlightManager {
    private List<Flight> flights=new ArrayList<>();
    
    public FlightManager() {
        initializeFlights();
    }

    // can be customized as per requirements
    private void initializeFlights() 
    {
        long currentTime = System.currentTimeMillis();
        flights.add(new Flight("F001", "New York", "London", 
            new Date(currentTime + 86400000), 
            new Date(currentTime + 172800000), 150, 59999.99));
        flights.add(new Flight("F002", "London", "Paris", 
            new Date(currentTime + 172800000), 
            new Date(currentTime + 259200000), 120, 14999.99));
        flights.add(new Flight("F003", "New York", "Tokyo", 
            new Date(currentTime + 259200000), 
            new Date(currentTime + 345600000), 200, 89999.99));
        flights.add(new Flight("F004", "Delhi", "Mumbai", 
            new Date(currentTime + 86400000), 
            new Date(currentTime + 108000000), 180, 7999.99));
        flights.add(new Flight("F005", "Bangalore", "Chennai", 
            new Date(currentTime + 172800000), 
            new Date(currentTime + 194400000), 100, 4999.99));
    }
    
    public List<Flight> searchFlights(String source,String destination) 
    {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) 
        {
            if (flight.getSource().equalsIgnoreCase(source) && 
                flight.getDestination().equalsIgnoreCase(destination) &&
                flight.getAvailableSeats() > 0) {
                results.add(flight);
            }
        }
        return results;
    }
    
    public Flight findFlightById(String flightId) 
    {
        for (Flight flight : flights) 
        {
            if (flight.getFlightId().equals(flightId)) 
            {
                return flight;
            }
        }
        return null;
    }
    
    public List<Flight> getAllFlights() 
    {
        return new ArrayList<>(flights);
    }
    
    public boolean updateSeatAvailability(String flightId, int seats, boolean book) 
    {
        Flight flight = findFlightById(flightId);
        if (flight == null) return false;
        
        if (book) 
        {
            if (flight.getAvailableSeats() >= seats) 
            {
                flight.setAvailableSeats(flight.getAvailableSeats() - seats);
                return true;
            }
        } 
        else 
        {
            flight.setAvailableSeats(flight.getAvailableSeats() + seats);
            return true;
        }
        return false;
    }
}

class BookingManager 
{
    private Map<String, Booking> bookings = new HashMap<>();
    
    public String createBooking(String passengerNames, String flightId, int seats, double totalPrice) 
    {
        String bookingId = "B" + System.currentTimeMillis();
        bookings.put(bookingId, new Booking(bookingId, passengerNames, flightId, seats, totalPrice));
        return bookingId;
    }
    
    public Booking getBooking(String bookingId) 
    {
        return bookings.get(bookingId);
    }
    
    public boolean cancelBooking(String bookingId) 
    {
        return bookings.remove(bookingId) != null;
    }
    
    public List<Booking> getAllBookings() 
    {
        return new ArrayList<>(bookings.values());
    }
}

class Validator {
    public static boolean isValidName(String name) 
    {
        return name != null && name.trim().length() > 1 && name.matches("[a-zA-Z\\s]+");
    }
    
    public static boolean isValidFlightId(String flightId) 
    {
        return flightId != null && flightId.startsWith("F") && flightId.length() > 1;
    }
    
    public static boolean isValidBookingId(String bookingId) 
    {
        return bookingId != null && bookingId.startsWith("B") && bookingId.length() > 1;
    }
    
    public static boolean isValidSeatCount(int seats, int available) 
    {
        return seats > 0 && seats <= 10 && seats <= available;
    }
    
    public static boolean isValidPassengerCount(int count) 
    {
        return count > 0 && count <= 10;
    }
}

class UserInterface {
    private Scanner scanner=new Scanner(System.in);
    private FlightManager flightManager;
    private BookingManager bookingManager;
    
    public UserInterface(FlightManager fm, BookingManager bm) 
    {
        this.flightManager = fm;
        this.bookingManager = bm;
    }
    
    public void showMenu() 
    {
        System.out.println("\n========== FLIGHT BOOKING SYSTEM ==========");
        System.out.println("1. Search Flights by Route");
        System.out.println("2. Book Flight");
        System.out.println("3. View Booking");
        System.out.println("4. Cancel Booking");
        System.out.println("5. View All Flights");
        System.out.println("6. View All Bookings");
        System.out.println("7. Exit");
        System.out.println("==========================================");
        System.out.print("Choose an option (1-7): ");
    }
    
    public int getChoice() 
    {
        try 
        {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
    
    public void searchFlightsByRoute() 
    {
        System.out.print("Enter source city : ");
        String source=scanner.nextLine().trim();
        System.out.print("Enter destination city : ");
        String destination = scanner.nextLine().trim();

        if (source.isEmpty() || destination.isEmpty()) 
         {
            System.out.println("Source and destination cannot be empty !");
            return;
        }

        List<Flight> results=flightManager.searchFlights(source,destination);
        displayFlights(results);
    }
    
    private void displayFlights(List<Flight> flights) 
    {
        if (flights.isEmpty()) 
        {
            System.out.println("No flights found for this route.");
        } 
        else 
        {
            System.out.println("\nAvailable Flights :");
            for (int a = 0; a < flights.size(); a++) 
            {
                System.out.println((a + 1) + ". " + flights.get(a));
            }
        }
    }
    
    public void bookFlight() 
    {
        System.out.print("Enter flight ID : ");
        String flightId = scanner.nextLine().trim().toUpperCase();
        
        if (!Validator.isValidFlightId(flightId)) 
        {
            System.out.println("Invalid flight ID format !");
            return;
        }
        
        Flight flight=flightManager.findFlightById(flightId);
        if (flight == null) 
        {
            System.out.println("Flight not found !");
            return;
        }

        if (flight.getAvailableSeats() <= 0) 
        {
            System.out.println("No seats available on this flight !");
            return;
        }

        // ask for number of passengers
        System.out.print("Enter number of passengers (1-10) : ");
        int passengerCount;

        try {
            passengerCount = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } 
        catch (Exception e) 
        {
            System.out.println("Invalid passenger count !");
            scanner.nextLine(); // clear invalid input
            return;
        }

        if (!Validator.isValidPassengerCount(passengerCount) || passengerCount > flight.getAvailableSeats()) 
        {
            System.out.println("Invalid number of passengers !");
            return;
        }

        // get passenger names
        StringBuilder passengerNames = new StringBuilder();
        
        for (int a=1 ; a<=passengerCount ; a++) 
        {
            System.out.print("Enter name of passenger " + a + " : ");
            String name=scanner.nextLine().trim();
            if (!Validator.isValidName(name)) 
            {
                System.out.println("Invalid passenger name !");
                return;
            }
            passengerNames.append(name);
            if (a < passengerCount)  
            {
                passengerNames.append(" , ");
            }
        }

        double total = passengerCount * flight.getPrice();
        System.out.printf("Total cost : Rs. %.2f\n", total);
        System.out.print("Confirm booking ? (y/n) : ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) 
        {
            if (flightManager.updateSeatAvailability(flightId , passengerCount , true)) 
            {
                String bookingId = bookingManager.createBooking(passengerNames.toString(), flightId, passengerCount, total);
                System.out.println("Booking successful! Booking ID : " + bookingId);
            } 
            else 
            {
                System.out.println("Booking failed !");
            }
        } 
        else 
        {
            System.out.println("Booking cancelled.");
        }
    }
    
    public void viewBooking() 
    {
        System.out.print("Enter booking ID : ");
        String bookingId=scanner.nextLine().trim().toUpperCase();
        
        if (!Validator.isValidBookingId(bookingId)) {
            System.out.println("Invalid booking ID format !");
            return;
        }
        
        Booking booking=bookingManager.getBooking(bookingId);
       
        if (booking == null) 
        {
            System.out.println("Booking not found !");
        } 
        else 
        {
            System.out.println("\nBooking Details:");
            System.out.println(booking);
        }
    }
    
    public void cancelBooking() {
        System.out.print("Enter booking ID : ");
        String bookingId = scanner.nextLine().trim().toUpperCase();
        
        if (!Validator.isValidBookingId(bookingId)) 
        {
            System.out.println("Invalid booking ID format !");
            return;
        }
        
        Booking booking = bookingManager.getBooking(bookingId);
        if (booking == null) {
            System.out.println("Booking not found !");
            return;
        }

        System.out.printf("Refund amount: Rs. %.2f\n", booking.getTotalPrice());
        System.out.print("Confirm cancellation? (y/n): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            if (flightManager.updateSeatAvailability(booking.getFlightId(), booking.getSeatsBooked(), false) &&
                bookingManager.cancelBooking(bookingId)) {
                System.out.println("Booking cancelled successfully !");
            } else {
                System.out.println("Cancellation failed !");
            }
        } 
        else 
        {
            System.out.println("Cancellation aborted.");
        }
    }
    
    public void viewAllFlights() 
    {
        System.out.println("\nAll Flights :");
        for (Flight flight : flightManager.getAllFlights()) 
        {
            System.out.println(flight);
        }
    }
    
    public void viewAllBookings() 
    {
        List<Booking> bookings = bookingManager.getAllBookings();
        if (bookings.isEmpty()) 
        {
            System.out.println("No bookings found.");
        } 
        else 
        {
            System.out.println("\nAll Bookings :");
            for (Booking booking : bookings) 
            {
                System.out.println(booking);
            }
        }
    }
    
    public void exitSystem() 
    {
        System.out.println("Thank you for using Flight Booking System !");
        System.out.println("Have a safe journey !");
        scanner.close();
    }
    
    public void closeScanner() {
        scanner.close();
    }
}

class Main {
    public static void main(String[] args) 
    {
        try 
        {
            FlightManager flightManager=new FlightManager();
            BookingManager bookingManager=new BookingManager();
            UserInterface ui=new UserInterface(flightManager , bookingManager);
            
            System.out.println("Welcome to Flight Booking System !");
            
            while (true) 
            {
                try {
                    ui.showMenu();
                    int choice=ui.getChoice();

                    switch (choice) {
                        case 1 : ui.searchFlightsByRoute(); break;
                        case 2 : ui.bookFlight(); break;
                        case 3 : ui.viewBooking(); break;
                        case 4 : ui.cancelBooking(); break;
                        case 5 : ui.viewAllFlights(); break;
                        case 6 : ui.viewAllBookings(); break;
                        case 7: 
                            ui.exitSystem();
                            return;
                        default : 
                            System.out.println("Invalid option! Please choose between 1-7.");
                    }
                } 
                catch (Exception e) 
                {
                    System.out.println("An error occurred. Please try again.");
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Critical error in application : " + e.getMessage());
        }
    }
}



// output example

/* 

Welcome to Flight Booking System !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 5

All Flights :
Flight F001 : New York -> London | Departure : 08-Dec-2023 14:30 | Arrival : 09-Dec-2023 14:30 | Available : 150/150 | Price : Rs. 59999.99
Flight F002 : London -> Paris | Departure : 09-Dec-2023 14:30 | Arrival : 10-Dec-2023 14:30 | Available : 120/120 | Price : Rs. 14999.99
Flight F003 : New York -> Tokyo | Departure : 10-Dec-2023 14:30 | Arrival : 11-Dec-2023 14:30 | Available : 200/200 | Price : Rs. 89999.99
Flight F004 : Delhi -> Mumbai | Departure : 08-Dec-2023 14:30 | Arrival : 08-Dec-2023 20:30 | Available : 180/180 | Price : Rs. 7999.99
Flight F005 : Bangalore -> Chennai | Departure : 09-Dec-2023 14:30 | Arrival : 09-Dec-2023 16:30 | Available : 100/100 | Price : Rs. 4999.99

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 1
Enter source city : New York
Enter destination city : London

Available Flights :
1. Flight F001 : New York -> London | Departure : 08-Dec-2023 14:30 | Arrival : 09-Dec-2023 14:30 | Available : 150/150 | Price : Rs. 59999.99

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 1
Enter source city : Mumbai
Enter destination city : Delhi

No flights found for this route.

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 2
Enter flight ID : F001
Enter number of passengers (1-10) : 2
Enter name of passenger 1 : John Smith
Enter name of passenger 2 : Jane Doe
Total cost : Rs. 119999.98
Confirm booking ? (y/n) : y
Booking successful! Booking ID : B1701954205486

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 2
Enter flight ID : F004
Enter number of passengers (1-10) : 3
Enter name of passenger 1 : Raj Kumar
Enter name of passenger 2 : Priya Sharma
Enter name of passenger 3 : Amit Patel
Total cost : Rs. 23999.97
Confirm booking ? (y/n) : y
Booking successful! Booking ID : B1701954245621

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 3
Enter booking ID : B1701954205486

Booking Details:
Booking B1701954205486 | Passengers: John Smith , Jane Doe | Flight: F001 | Seats: 2 | Total: Rs. 119999.98 | Date: 07-Dec-2023 14:30:05

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 3
Enter booking ID : B9999999999999
Booking not found !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 3
Enter booking ID : XYZ123
Invalid booking ID format !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 6

All Bookings :
Booking B1701954205486 | Passengers: John Smith , Jane Doe | Flight: F001 | Seats: 2 | Total: Rs. 119999.98 | Date: 07-Dec-2023 14:30:05
Booking B1701954245621 | Passengers: Raj Kumar , Priya Sharma , Amit Patel | Flight: F004 | Seats: 3 | Total: Rs. 23999.97 | Date: 07-Dec-2023 14:30:45

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 5

All Flights :
Flight F001 : New York -> London | Departure : 08-Dec-2023 14:30 | Arrival : 09-Dec-2023 14:30 | Available : 148/150 | Price : Rs. 59999.99
Flight F002 : London -> Paris | Departure : 09-Dec-2023 14:30 | Arrival : 10-Dec-2023 14:30 | Available : 120/120 | Price : Rs. 14999.99
Flight F003 : New York -> Tokyo | Departure : 10-Dec-2023 14:30 | Arrival : 11-Dec-2023 14:30 | Available : 200/200 | Price : Rs. 89999.99
Flight F004 : Delhi -> Mumbai | Departure : 08-Dec-2023 14:30 | Arrival : 08-Dec-2023 20:30 | Available : 177/180 | Price : Rs. 7999.99
Flight F005 : Bangalore -> Chennai | Departure : 09-Dec-2023 14:30 | Arrival : 09-Dec-2023 16:30 | Available : 100/100 | Price : Rs. 4999.99

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 2
Enter flight ID : F001
Enter number of passengers (1-10) : abc
Invalid passenger count !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 2
Enter flight ID : F001
Enter number of passengers (1-10) : 15
Invalid number of passengers !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 2
Enter flight ID : F001
Enter number of passengers (1-10) : 149
Invalid number of passengers !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 2
Enter flight ID : F001
Enter number of passengers (1-10) : 3
Enter name of passenger 1 : Michael Johnson
Enter name of passenger 2 : Sarah Wilson
Enter name of passenger 3 : 123
Invalid passenger name !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 2
Enter flight ID : F999
Flight not found !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 2
Enter flight ID : XYZ123
Invalid flight ID format !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 4
Enter booking ID : B1701954205486
Refund amount: Rs. 119999.98
Confirm cancellation? (y/n): y
Booking cancelled successfully !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 4
Enter booking ID : B1701954205486
Booking not found !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 4
Enter booking ID : XYZ789
Invalid booking ID format !

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 4
Enter booking ID : B1701954245621
Refund amount: Rs. 23999.97
Confirm cancellation? (y/n): n
Cancellation aborted.

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 5

All Flights :
Flight F001 : New York -> London | Departure : 08-Dec-2023 14:30 | Arrival : 09-Dec-2023 14:30 | Available : 150/150 | Price : Rs. 59999.99
Flight F002 : London -> Paris | Departure : 09-Dec-2023 14:30 | Arrival : 10-Dec-2023 14:30 | Available : 120/120 | Price : Rs. 14999.99
Flight F003 : New York -> Tokyo | Departure : 10-Dec-2023 14:30 | Arrival : 11-Dec-2023 14:30 | Available : 200/200 | Price : Rs. 89999.99
Flight F004 : Delhi -> Mumbai | Departure : 08-Dec-2023 14:30 | Arrival : 08-Dec-2023 20:30 | Available : 177/180 | Price : Rs. 7999.99
Flight F005 : Bangalore -> Chennai | Departure : 09-Dec-2023 14:30 | Arrival : 09-Dec-2023 16:30 | Available : 100/100 | Price : Rs. 4999.99

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 6

All Bookings :
Booking B1701954245621 | Passengers: Raj Kumar , Priya Sharma , Amit Patel | Flight: F004 | Seats: 3 | Total: Rs. 23999.97 | Date: 07-Dec-2023 14:30:45

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 9
Invalid option! Please choose between 1-7.

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): abc
An error occurred. Please try again.

========== FLIGHT BOOKING SYSTEM ==========
1. Search Flights by Route
2. Book Flight
3. View Booking
4. Cancel Booking
5. View All Flights
6. View All Bookings
7. Exit
==========================================
Choose an option (1-7): 7
Thank you for using Flight Booking System !
Have a safe journey !


*/