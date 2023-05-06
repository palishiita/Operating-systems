package FrameAllocationAlgorithms;

public class Equal_FIFO extends AllocatingFramesClass{

    public Equal_FIFO(int[][] processes, int frames) {
        super(processes, frames);
    }

    public int doAlgorithm() {
        int frames_per_process = frames / processes_FIFO.length;
        //Assign equal number of frames for every process
        for (int i = 0; i < processes_FIFO.length; i++) {
            processes_FIFO[i].setFramesCount(frames_per_process);
        }
        //Do LRU for all processes and get page failures
        for (int i = 0; i < processes_FIFO.length; i++) {
            //processes_FIFO[i].doAll();
            pageFaultCounter += processes_FIFO[i].getPageFrameCounter();
        }
        return pageFaultCounter;
    }

}
