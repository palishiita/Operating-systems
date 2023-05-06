package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.*;

public abstract class AllocatingFramesClass {

    // fields
    protected int frames;
    protected int pageFaultCounter;
    protected FIFO[] processes_FIFO;
    protected LRU[] processes_LRU;
    protected ALRU[] processes_ALRU;
    protected OPT[] processes_OPT;
    protected RAND[] processes_RAND;

    // constructor
    public AllocatingFramesClass(int[][] processes, int frames) {
        // GLOBAL FRAME ALLOCATION!!
        this.frames = frames;
        // FIFO
        this.processes_FIFO = new FIFO[processes.length];
        for (int i = 0; i < processes.length; ++i)
            this.processes_FIFO[i] = new FIFO(processes[i]);
        // LRU
        this.processes_LRU = new LRU[processes.length];
        for (int i = 0; i < processes.length; ++i)
            this.processes_LRU[i] = new LRU(processes[i]);
        // ALRU
        this.processes_ALRU = new ALRU[processes.length];
        for (int i = 0; i < processes.length; ++i)
            this.processes_ALRU[i] = new ALRU(processes[i]);
        // OPT
        this.processes_OPT = new OPT[processes.length];
        for (int i = 0; i < processes.length; ++i)
            this.processes_OPT[i] = new OPT(processes[i]);
        // RAND
        this.processes_RAND = new RAND[processes.length];
        for (int i = 0; i < processes.length; ++i)
            this.processes_RAND[i] = new RAND(processes[i]);
    }

    // abstract method
    public abstract int doAlgorithm();

}

    /*
    // FIFO
    public int getResultForProcessFIFO(int processNumber) {
        return processes_FIFO[processNumber].getPageFrameCounter();
    }
    // LRU
    public int getResultForProcessLRU(int processNumber) {
        return processes_LRU[processNumber].getPageFrameCounter();
    }
     */
    // ALRU
    /*
    public int getResultForProcessALRU(int processNumber) {
        return processes_ALRU[processNumber].getPageFrameCounter();
    }
     */
     // OPT
    /*
    public int getResultForProcessLRU(int processNumber) {
        return processes_LRU[processNumber].getPageFrameCounter();
    }
    */
    // RAND
    /*
    public int getResultForProcessLRU(int processNumber) {
        return processes_LRU[processNumber].getPageFrameCounter();
    }
     */
