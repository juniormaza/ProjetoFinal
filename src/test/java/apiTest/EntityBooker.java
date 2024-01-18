package apiTest;

import java.util.Date;

public class EntityBooker {
    public String firstname;
    public String lastname;
    public double totalprice;
    public boolean depositpaid;
    public String additionalneeds;

    public BookingDates bookingdates;
}

class BookingDates {
    public String checkin;
    public String checkout;

    public BookingDates(String checkin, String checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDates() {

    }
}
