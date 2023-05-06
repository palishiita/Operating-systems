package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.RAND;

public class Proportional_RAND extends AllocatingFramesClass {

    public Proportional_RAND(int[][] processes, int framesCount) {
        super(processes, framesCount);
    }

    @Override
    public int doAlgorithm() {
        int processesLength = 0;
        for (RAND process : processes_RAND) {
            processesLength += process.getPageReferencesArray().length;
        }
        for (RAND process : processes_RAND) {
            int framesPerProcess = frames * process.getPageReferencesArray().length / processesLength;
            process.setFramesCount(framesPerProcess);
        }
        for (RAND process : processes_RAND) {
            process.doAll();
            pageFaultCounter += process.getPageFrameCounter();
        }
        return pageFaultCounter;
    }
}
