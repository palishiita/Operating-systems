package Algorithms;

import Memory.creatingPageFrame;

import java.util.ArrayList;

public class FirstInFirstOut extends PageReplacementAlgorithm {
    @Override
    public void replacePage() {
        ArrayList<creatingPageFrame> frameList = physicalMemory.getFrameList();
        creatingPageFrame toRemove = frameList.get(0);
        for (int i = 1; i < frameList.size(); i++) {
            if (frameList.get(i).timePageAddedInFrame < toRemove.timePageAddedInFrame) {
                toRemove = frameList.get(i);
            }
        }
        physicalMemory.removeFrame(toRemove.address);
    }
}
