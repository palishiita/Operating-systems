package DiskSchedulingAlgorithms;
import java.util.*;

public final class CScan extends DiskSchedulingAlgorithm {

    public CScan(int[] requestQueue) {
        super();
        name = "CSCAN";
        this.requestQueue = requestQueue;
        resultMatrix = new int[2][requestQueue.length + 2];
        algorithm();
    }

    @Override
    public  void algorithm() {
        Integer[] requestQueueBox = Arrays.stream(requestQueue).boxed().sorted().toArray(Integer[]::new);
        ArrayList<Integer> requestArrList = new ArrayList<>(Arrays.asList(requestQueueBox));
        ArrayList<Integer> seekSequence = new ArrayList<>();
        boolean taskDone = false;
        if (requestArrList.get(0) >= headPosition && requestArrList.get(requestArrList.size() - 1) <= totalTracksMaxReq - 1){
            resultMatrix[0] = Arrays.stream(requestQueueBox).filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
            resultMatrix[1] = new int[resultMatrix[0].length];
            totalSeekTime = DiskSchedulingInterface.buildResultMatrix(resultMatrix, headPosition);
            return;
        }

        for (Integer j: requestArrList) {
            if (j >= headPosition) {
                ArrayList<Integer> firstHalf = new ArrayList<>(requestArrList.subList(requestArrList.indexOf(j), requestArrList.size()));
                firstHalf.add(totalTracksMaxReq - 1);
                firstHalf.add(0);
                ArrayList<Integer> secondHalf = new ArrayList<>(requestArrList.subList(0,requestArrList.indexOf(j)));
                seekSequence.addAll(firstHalf);
                seekSequence.addAll(secondHalf);
                resultMatrix[0] = seekSequence.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
                taskDone = true;
                break;
            }
        }

        if (!taskDone) {
            seekSequence.addAll(requestArrList);
            Collections.reverse(seekSequence);
            seekSequence.add(0);
            seekSequence.add(totalTracksMaxReq - 1);
            Collections.reverse(seekSequence);
            resultMatrix[0] = seekSequence.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
        }
        totalSeekTime = DiskSchedulingInterface.buildResultMatrix(resultMatrix, headPosition, requestArrList, totalTracksMaxReq,2);
    }

    @Override
    public void displayResults() {
        System.out.println("C-Scan algorithm:");
        DiskSchedulingInterface.displayResults(resultMatrix);
        System.out.println("Total seek/execution time (movement of head)  " + totalSeekTime);
    }

    @Override
    public void displayDetailedResults() {
        System.out.println("C-Scan algorithm:");
        int[][] tempResultMatrix =  new int[2][resultMatrix[0].length];
        System.arraycopy(resultMatrix[0],0,tempResultMatrix[0],0, resultMatrix[0].length);
        System.arraycopy(resultMatrix[1],0,tempResultMatrix[1],0, resultMatrix[0].length);
        DiskSchedulingInterface.displayDetailedResults(tempResultMatrix, headPosition, totalTracksMaxReq, 2);
    }

}