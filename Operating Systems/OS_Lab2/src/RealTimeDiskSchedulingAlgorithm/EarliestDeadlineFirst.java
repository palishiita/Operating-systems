package RealTimeDiskSchedulingAlgorithm;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.PriorityQueue;

public class EarliestDeadlineFirst {
    int headPosition;
    int headMovement;
    int totalTracks; // disk size or capacity
    public EarliestDeadlineFirst(MyProcess[] array, int totalTracks) {
        this.totalTracks = totalTracks;
        headPosition = 0;
        headMovement = 0;

        // processing array
        ArrayDeque<MyProcess> queue = new ArrayDeque<>();
        PriorityQueue<MyProcess> deadlineQueue = new PriorityQueue<>(1, MyProcess.DeadlineComp); // sorts by itself
        int time = 0;
        int i = 0;
        while (i < array.length || !queue.isEmpty() || !deadlineQueue.isEmpty()) {
            while (i < array.length && time == array[i].getArrivalTime()) {
                if (array[i].getDeadline() != 0) {
                    deadlineQueue.add(array[i]);
                } else {
                    queue.add(array[i]);
                }
                i++;
            }

            if(!deadlineQueue.isEmpty()) {
                while (!deadlineQueue.isEmpty()) {
                    if(Math.abs(deadlineQueue.peek().getTrack() - headPosition) > Objects.requireNonNull(deadlineQueue.peek()).getDeadline()){
                        deadlineQueue.poll();
                        continue;
                    }
                    MyProcess process = deadlineQueue.peek();
                    if (process != null) {
                        if (process.getTrack() > headPosition) {
                            headPosition++;
                            headMovement++;
                        } else if (process.getTrack() < headPosition) {
                            headPosition--;
                            headMovement++;
                        }
                    }
                    if (Objects.requireNonNull(process).getTrack() == headPosition) {
                        deadlineQueue.remove();
                    }
                }
            } else if (!queue.isEmpty()){
                MyProcess process = queue.getFirst();
                if (process.getTrack() > headPosition) {
                    headPosition++;
                    headMovement++;
                } else if (process.getTrack() < headPosition){
                    headPosition--;
                    headMovement++;
                }
                if (process.getTrack() == headPosition){
                    queue.removeFirst();
                }
            }
            time++;
        }
    }

}

