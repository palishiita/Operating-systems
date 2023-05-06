package Algorithms;

import Memory.creatingPageFrame;

import java.util.ArrayList;

public class OptimalPageReplacement extends PageReplacementAlgorithm {

    public void replacePage_OptimalPageReplacement(ArrayList<Integer> pendingRequest, int NumberOfPages) {
        ArrayList<Integer> count = new ArrayList<>();
        for (int i = 0; i < physicalMemory.getFrameList().size(); i++) {
            count.add(10000);
        }
        int index = 0;
        int maxValue = 0;
        for (int i = 0; i < count.size(); i++) {
            if (count.get(i) > maxValue) {
                maxValue = count.get(i);
                index = i;
            }
        }

        ArrayList<creatingPageFrame> physicalMemoryArray = physicalMemory.getFrameList();
        for (int i = 0; i < physicalMemoryArray.size(); i++) {
            int counter = 0;
            for (int j = 0; j < pendingRequest.size(); j++) {
                if (pendingRequest.get(j) == physicalMemoryArray.get(i).address) {
                    count.set(i, counter);
                    break;
                }
                counter++;
            }
        }

        creatingPageFrame toRemove = physicalMemoryArray.get(index);
        physicalMemory.removeFrame(toRemove.address);
    }

    @Override
    public void replacePage() {}

}