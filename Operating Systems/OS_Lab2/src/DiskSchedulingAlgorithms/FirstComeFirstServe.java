package DiskSchedulingAlgorithms;

public final class FirstComeFirstServe extends DiskSchedulingAlgorithm {

    public FirstComeFirstServe(int[] requestQueue) {
        super();
        name = "FCFS";
        this.requestQueue = requestQueue;
        resultMatrix = new int[2][requestQueue.length];
        algorithm();
    }

    @Override
    public void algorithm() {
        resultMatrix[0] = requestQueue;
        totalSeekTime = DiskSchedulingInterface.buildResultMatrix(resultMatrix, headPosition);
    }

    @Override
    public void displayResults() {
        System.out.println("First Come First Serve algorithm:");
        DiskSchedulingInterface.displayResults(resultMatrix);
        System.out.println("Total seek/execution time (movement of head): " + totalSeekTime);
    }

    @Override
    public void displayDetailedResults() {
        System.out.println("First Come First Serve algorithm:");
        DiskSchedulingInterface.displayDetailedResults(resultMatrix, headPosition, totalTracksMaxReq, 0);
    }

}
