package DiskSchedulingAlgorithms;

public abstract class DiskSchedulingAlgorithm implements DiskSchedulingInterface{

    protected String name;
    protected int totalSeekTime; // Holds the total number of seek operations done.
    protected int[] requestQueue; // Array consisting of requests in their order of arrival.
    protected static int headPosition; // Initial position of the head.
    protected static int totalTracksMaxReq; // Total number of tracks == max request

    protected int[][] resultMatrix;

    @Override
    public abstract void algorithm();

    public abstract void displayResults();

    public abstract void displayDetailedResults();

}
