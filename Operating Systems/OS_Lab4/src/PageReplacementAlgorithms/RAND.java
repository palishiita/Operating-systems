package PageReplacementAlgorithms;

import java.util.LinkedList;

public class RAND {

    private int PageFrameCounter;
    private final int[] getPageReferencesArray;
    private int FramesCount;
    private final LinkedList<Integer> frameList;

    private int doneCounter = 0;

    public RAND(int[] pageReferencesArray) {
        this.getPageReferencesArray = pageReferencesArray;
        this.PageFrameCounter = 0;
        this.FramesCount = 0;
        frameList = new LinkedList<>();
    }

    public int getLastUsed() {
        return getPageReferencesArray[doneCounter - 1];
    }

    public int getFramesCount() {
        return FramesCount;
    }

    public void setFramesCount(int framesCount) {
        this.FramesCount = framesCount;
    }

    public int getPageFrameCounter() {
        return PageFrameCounter;
    }

    public int[] getPageReferencesArray() {
        return getPageReferencesArray;
    }

    public boolean isDone() {
        return doneCounter >= getPageReferencesArray.length;
    }

    public void addFrame() {
        FramesCount++;
    }

    public void removeFrame() {
        FramesCount--;
    }

    public void doAll() {
        while (doneCounter < getPageReferencesArray.length) {
            pageReplacement();
        }
    }

    public boolean pageReplacement() {
        while (frameList.size() > FramesCount)
            frameList.removeFirst(); // LinkedList class Removes and returns the first element from this list.
        if (doneCounter < getPageReferencesArray.length) {
            if (FramesCount == 0) return false;
            if (!frameList.contains(getPageReferencesArray[doneCounter])) {
                PageFrameCounter++;
                if (frameList.size() < FramesCount) {
                } else {
                    frameList.removeFirst();
                }
                frameList.addLast(getPageReferencesArray[doneCounter]);
            } else {
                int x = frameList.remove(frameList.indexOf(getPageReferencesArray[doneCounter]));
                frameList.addLast(x);
            }
            doneCounter++;
            return false;
        }
        return true;
    }


}