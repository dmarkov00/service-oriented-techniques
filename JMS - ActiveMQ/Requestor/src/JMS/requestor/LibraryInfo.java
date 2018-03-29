package JMS.requestor;

public class LibraryInfo {
    public LibraryInfo() {
        this.address = new Address();
    }

    private String workingHours = "From 10:00 to 22:00";
    private int nrOfBooks = 1002;
    private Address address;

    @Override
    public String toString() {
        return "Library info ->" +
                "Working hours: '" + workingHours+
                ", Books amount: " + nrOfBooks +
                ", Street: " + address.street +
                ", Nr: " + address.nr;

    }
}
