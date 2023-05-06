import static java.lang.String.format;

@SuppressWarnings("ALL")
public class ToStringMethod {

    // fields
    private final String description;
    private final double averageLoad;
    private final double averageDeviation;
    private final int loadRequests;
    private final int loadMigrations;

    // constructor and fields
    public ToStringMethod(String description,
                          double averageLoad,
                          double averageDeviation,
                          int loadRequests,
                          int loadMigrations)
    {
        this.description = description;
        this.averageLoad = averageLoad;
        this.averageDeviation = averageDeviation;
        this.loadRequests = loadRequests;
        this.loadMigrations = loadMigrations;
    }

    /*
    The java string format() method returns the formatted string by given locale, format and arguments.
    %d = Decimal Integer
    %s = String value
    %.2f = float
    */
    @Override
    public String toString() {
        return format(
                // this is the description
                "%s:\n1) Average load: %.2f\n2) Average deviation: %.2f\n"
                        + "3) Number of inquiries about the load: %d\n4) Number of migrations: %d\n",
                description, averageLoad, averageDeviation, loadRequests, loadMigrations);
    }


}