package FrameAllocationAlgorithms;

public class Equal_LRU extends AllocatingFramesClass {

    public Equal_LRU(int[][] processes, int frames) {
        super(processes, frames);
    }

    public int doAlgorithm() {
        int frames_per_process = frames / processes_LRU.length;
        //Assign equal number of frames for every process
        for (int i = 0; i < processes_LRU.length; i++) {
            processes_LRU[i].setFramesCount(frames_per_process);
        }
        //Do LRU for all processes and get page failures
        for (int i = 0; i < processes_LRU.length; i++) {
            processes_LRU[i].doAll(); // doAll() has page replacement method
            pageFaultCounter += processes_LRU[i].getPageFrameCounter();
        }
        return pageFaultCounter;
    }

}
