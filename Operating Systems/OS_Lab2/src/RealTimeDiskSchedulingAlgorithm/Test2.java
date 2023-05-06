package RealTimeDiskSchedulingAlgorithm;
import java.util.Arrays;
import java.util.Random;

// Real time scheduling EDF

public class Test2 {

    static int processesNumber = 150;
    static int processesDeadlineNumber = 20;
    static int maxArrivalTime = 10;
    static int minArrivalTime = 1;
    static int maxDeadline = 10;
    static int minDeadline = 0;
    static int diskCapacity = 200;

    static MyProcess[] makeArray() {
        MyProcess[] array = new MyProcess[processesNumber+processesDeadlineNumber];
        Random rand = new Random();
        for(int i = 0; i < processesNumber; i++) {
            array[i] = new MyProcess(
                    rand.nextInt(maxArrivalTime-minArrivalTime)+minArrivalTime,
                    rand.nextInt(diskCapacity),
                    rand.nextInt(maxDeadline-minDeadline)+minDeadline);
        }
        for(int i = processesNumber; i < processesDeadlineNumber+processesNumber; i++) {
            array[i] = new MyProcess(
                    rand.nextInt(maxArrivalTime-minArrivalTime)+minArrivalTime,
                    rand.nextInt(diskCapacity),
                    0);
        }
        // smallest arrival time first -> creating deadline
        Arrays.sort(array, MyProcess.ArrivalTimeComp);
        return array;
    }

    static MyProcess[] copy(MyProcess[] array) {
        MyProcess[] result = new MyProcess[array.length];
        for (int i = 0; i < result.length; i++){
            result[i] = array[i].clone();
        }
        return result;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        int TotalDiskMovement = 0;
        int seriesNumber = 1;
        for (int i = 0; i < seriesNumber; i++) {
            MyProcess[] array = makeArray();
            EarliestDeadlineFirst earliestDeadlineFirst = new EarliestDeadlineFirst(copy(array), diskCapacity);
            TotalDiskMovement += earliestDeadlineFirst.headMovement;
        }
        System.out.println("Number of processes: " + (processesNumber + processesDeadlineNumber) + " (" + processesDeadlineNumber + " with deadline)" + "\n");
        System.out.println("EDF:" + TotalDiskMovement);
    }

}


