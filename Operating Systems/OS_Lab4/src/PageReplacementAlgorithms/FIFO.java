package PageReplacementAlgorithms;

import java.util.LinkedList;

public class FIFO {
    private int PageFrameCounter;
    private final int[] getPageReferencesArray;
    private int FramesCount;
    private final LinkedList<Integer> frameList;
    private int doneCounter = 0;

    public FIFO(int[] pageReferencesArray) {
        this.getPageReferencesArray = pageReferencesArray; // reference
        this.PageFrameCounter = 0; // pages
        this.FramesCount = 0; // frames
        frameList = new LinkedList<>(); // frame list
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

    public boolean pageReplacement() {
        while (frameList.size() > FramesCount) {
            frameList.removeFirst(); // LinkedList class Removes and returns the first element from this list.
        }
        if (doneCounter < getPageReferencesArray.length) {
            if (FramesCount == 0) return false; // no page added to frame
            if (!frameList.contains(getPageReferencesArray[doneCounter])) {
                PageFrameCounter++;
                frameList.addLast(getPageReferencesArray[doneCounter]); // Appends the specified element to the end of this list
            } else {
                int i = frameList.remove(frameList.indexOf(getPageReferencesArray[doneCounter]));
                frameList.addLast(i);
            }
            doneCounter++;
            return false;
        }
        return true;
    }
}