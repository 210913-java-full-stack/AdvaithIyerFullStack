package Models;

import javax.persistence.Entity;


public class Booking {
    private int ticketnumber;
    private int ssn;
    private int flightnumber;

    public Booking() {
    }

    public int getTicketnumber() {
        return ticketnumber;
    }

    public void setTicketnumber(int ticketnumber) {
        this.ticketnumber = ticketnumber;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public int getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(int flightnumber) {
        this.flightnumber = flightnumber;
    }
}
