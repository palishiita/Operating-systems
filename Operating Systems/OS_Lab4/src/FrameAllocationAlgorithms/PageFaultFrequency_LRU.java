package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.LRU;

public class PageFaultFrequency_LRU extends AllocatingFramesClass {

    public PageFaultFrequency_LRU(int[][] processes, int framesCount) {
        super(processes, framesCount);
    }

    @Override
    public int doAlgorithm() {
        int framesPerProcess = frames / processes_LRU.length; //divide each process to equal frames
        for (LRU process : processes_LRU) {
            process.setFramesCount(framesPerProcess);
        }
        boolean isAllDone = false;
        int counter = 0;
        int[] pageFaultsArray = new int[processes_LRU.length];
        int[] priority_array = new int[processes_LRU.length];
        int availableCounter = 0;
        while (!isAllDone) {
            counter++;
            isAllDone = true;
            for (LRU process : processes_LRU) {
                if (!process.pageReplacementLRU()) { //if LRU failed
                    isAllDone = false;
                }
            }
            if (counter >= framesPerProcess) {
                //over all processes
                for (int i = 0; i < processes_LRU.length; ++i) {
                    //assign to priority processes PF and subtract pages faults
                    priority_array[i] = processes_LRU[i].getPageFrameCounter() - pageFaultsArray[i];
                    //set page faults
                    pageFaultsArray[i] = processes_LRU[i].getPageFrameCounter();
                    //give frame back you don't need it !!!
                    if (priority_array[i] <= counter / 3 && processes_LRU[i].getFramesCount() > 1) {
                        processes_LRU[i].removeFrame();
                        availableCounter++;
                    }
                }
                while (availableCounter > 0 && counter > 0) {
                    //while i < processes count and available frames
                    for (int i = 0; i < processes_LRU.length && availableCounter > 0; ++i) {
                        //if you need more frames I will give you one
                        if (priority_array[i] >= counter) {
                            processes_LRU[i].addFrame();
                            availableCounter--;
                        }
                    }
                    counter--;
                }
                counter = 0;
            }

        }

        // TO GET PAGE FAULTS FROM LEAST RECENTLY USED ALGORITHM
        for (LRU process : processes_LRU) {
            pageFaultCounter += process.getPageFrameCounter();
        }

        return pageFaultCounter;
    }
}

