class SalesTicket {
    public void printTicket() {
        System.out.println("This is text.");
    }
};

class Additional extends SalesTicket {
    SalesTicket ticket;

    Additional(SalesTicket ticket) {
        this.ticket = ticket;
    }

    public void printTicket() {
        ticket.printTicket();
    }
};

class Head extends Additional {
    Head(SalesTicket ticket) {
        super(ticket);
    }

    public void printTicket() {
        System.out.println("This is head");
        ticket.printTicket();
    }
};

class Foot extends Additional {
    Foot(SalesTicket ticket) {
        super(ticket);
    }

    public void printTicket() {
        ticket.printTicket();
        System.out.println("This is foot");
    }
};

public class Application {
    public static void main(String args[]) {
        SalesTicket t = new SalesTicket();
        t.printTicket();
        System.out.println("-----------------");
        SalesTicket h = new Head(t);
        SalesTicket b = new Foot(h);
        b.printTicket();
    }
}