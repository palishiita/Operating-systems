package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.FIFO;

public class PageFaultFrequency_FIFO extends AllocatingFramesClass {

    public PageFaultFrequency_FIFO(int[][] processes, int framesCount) {
        super(processes, framesCount);
    }

    @Override
    public int doAlgorithm() {
        int framesPerProcess = frames / processes_FIFO.length; //divide each process to equal frames
        for (FIFO process : processes_FIFO) {
            process.setFramesCount(framesPerProcess);
        }
        boolean isAllDone = false;
        int counter = 0;
        int[] pageFaultsArray = new int[processes_FIFO.length];
        int[] priority_array = new int[processes_FIFO.length];
        int availableCounter = 0;
        while (!isAllDone) {
            counter++;
            isAllDone = true;
            for (FIFO process : processes_FIFO) {
                if (!process.pageReplacement()) { //if LRU failed
                    isAllDone = false;
                }
            }
            if (counter >= framesPerProcess) {
                //over all processes
                for (int i = 0; i < processes_FIFO.length; ++i) {
                    //assign to priority processes PF and subtract pages faults
                    priority_array[i] = processes_FIFO[i].getPageFrameCounter() - pageFaultsArray[i];
                    //set page faults
                    pageFaultsArray[i] = processes_FIFO[i].getPageFrameCounter();
                    //give frame back you don't need it !!!
                    if (priority_array[i] <= counter / 3 && processes_FIFO[i].getFramesCount() > 1) {
                        processes_FIFO[i].removeFrame();
                        availableCounter++;
                    }
                }
                while (availableCounter > 0 && counter > 0) {
                    //while i < processes count and available frames
                    for (int i = 0; i < processes_FIFO.length && availableCounter > 0; ++i) {
                        //if you need more frames I will give you one
                        if (priority_array[i] >= counter) {
                            processes_FIFO[i].addFrame();
                            availableCounter--;
                        }
                    }
                    counter--;
                }
                counter = 0;
            }

        }
        for (FIFO process : processes_FIFO) {
            pageFaultCounter += process.getPageFrameCounter();
        }

        return pageFaultCounter;
    }
}

