package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.FIFO;

public class Proportional_FIFO extends AllocatingFramesClass {

    public Proportional_FIFO(int[][] processes, int framesCount) {
        super(processes, framesCount);
    }

    @Override
    public int doAlgorithm() {
        int processesLength = 0;
        for (FIFO process : processes_FIFO) {
            processesLength += process.getPageReferencesArray().length;
        }
        for (FIFO process : processes_FIFO) {
            int framesPerProcess = frames * process.getPageReferencesArray().length / processesLength;
            process.setFramesCount(framesPerProcess);
        }
        for (FIFO process : processes_FIFO) {
            //process.doAll();
            pageFaultCounter += process.getPageFrameCounter();
        }
        return pageFaultCounter;
    }

}
