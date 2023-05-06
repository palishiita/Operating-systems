package Algorithms;

import Memory.creatingPageFrame;

import java.util.ArrayList;
import java.util.Random;

public class RANDOM extends PageReplacementAlgorithm {
    @Override
    public void replacePage() {
        ArrayList<creatingPageFrame> frameList = physicalMemory.getFrameList();
        Random random = new Random();
        int indexToRemove = random.nextInt(frameList.size());
        physicalMemory.removeFrame(frameList.get(indexToRemove).address);
    }
}
