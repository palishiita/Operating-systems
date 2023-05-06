package Algorithms;

import Memory.creatingPageFrame;

import java.util.Queue;

public class ApproximatedLeastRecentlyUsed extends PageReplacementAlgorithm {

    // bit=0 (false) and bit=1 (true)

    @Override
    public void replacePage() {
        Queue<creatingPageFrame> queue = physicalMemory.queue;
        boolean referenceBit = false;
        int indexToRemove = 0;
        while (!referenceBit) {
            assert queue.peek() != null;
            if (queue.peek().chance == 1) {
                queue.peek().chance = 0;
                queue.add(queue.poll());
            } else if (queue.peek().chance == 0) {
                referenceBit = true;
                indexToRemove = queue.poll().address;
            }
        }
        physicalMemory.removeFrame(indexToRemove);
    }

}


