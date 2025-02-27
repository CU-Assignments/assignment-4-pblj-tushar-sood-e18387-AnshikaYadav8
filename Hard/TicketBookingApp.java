import java.util.*;

class TicketBookingSystem {
    private int availableSeats;

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public synchronized boolean bookSeat(String name) {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println(name + " successfully booked a seat. Remaining: " + availableSeats);
            return true;
        } else {
            System.out.println(name + " failed to book. No seats available.");
            return false;
        }
    }
}

class Passenger extends Thread {
    private TicketBookingSystem system;
    private String passengerName;

    public Passenger(TicketBookingSystem system, String passengerName, int priority) {
        this.system = system;
        this.passengerName = passengerName;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(passengerName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(5);

        Passenger p1 = new Passenger(bookingSystem, "VIP 1", Thread.MAX_PRIORITY);
        Passenger p2 = new Passenger(bookingSystem, "VIP 2", Thread.MAX_PRIORITY);
        Passenger p3 = new Passenger(bookingSystem, "Regular 1", Thread.NORM_PRIORITY);
        Passenger p4 = new Passenger(bookingSystem, "Regular 2", Thread.NORM_PRIORITY);
        Passenger p5 = new Passenger(bookingSystem, "Regular 3", Thread.NORM_PRIORITY);
        Passenger p6 = new Passenger(bookingSystem, "Late Comer", Thread.MIN_PRIORITY);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
    }
}
