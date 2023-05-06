package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.LRU;

public class Proportional_LRU extends AllocatingFramesClass {

    public Proportional_LRU(int[][] processes, int frames) {
        super(processes, frames);
    }

    @Override
    public int doAlgorithm() {
        int processesLength = 0;
        for (LRU process : processes_LRU) {
            processesLength += process.getPageReferencesArray().length;
        }
        for (LRU process : processes_LRU) {
            int framesPerProcess = frames * process.getPageReferencesArray().length / processesLength;
            process.setFramesCount(framesPerProcess);
        }
        for (LRU process : processes_LRU) {
            process.doAll();
            pageFaultCounter += process.getPageFrameCounter();
        }
        return pageFaultCounter;
    }
}
