package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.RAND;

public class PageFaultFrequency_RAND extends AllocatingFramesClass {

    public PageFaultFrequency_RAND(int[][] processes, int framesCount) {
        super(processes, framesCount);
    }

    @Override
    public int doAlgorithm() {
        int framesPerProcess = frames / processes_RAND.length; //divide each process to equal frames
        for (RAND process : processes_RAND) {
            process.setFramesCount(framesPerProcess);
        }
        boolean isAllDone = false;
        int counter = 0;
        int[] pageFaultsArray = new int[processes_RAND.length];
        int[] priority_array = new int[processes_RAND.length];
        int availableCounter = 0;
        while (!isAllDone) {
            counter++;
            isAllDone = true;
            for (RAND process : processes_RAND) {
                if (!process.pageReplacement()) { //if LRU failed
                    isAllDone = false;
                }
            }
            if (counter >= framesPerProcess) {
                //over all processes
                for (int i = 0; i < processes_RAND.length; ++i) {
                    //assign to priority processes PF and subtract pages faults
                    priority_array[i] = processes_RAND[i].getPageFrameCounter() - pageFaultsArray[i];
                    //set page faults
                    pageFaultsArray[i] = processes_RAND[i].getPageFrameCounter();
                    //give frame back you don't need it !!!
                    if (priority_array[i] <= counter / 3 && processes_RAND[i].getFramesCount() > 1) {
                        processes_RAND[i].removeFrame();
                        availableCounter++;
                    }
                }
                while (availableCounter > 0 && counter > 0) {
                    //while i < processes count and available frames
                    for (int i = 0; i < processes_RAND.length && availableCounter > 0; ++i) {
                        //if you need more frames I will give you one
                        if (priority_array[i] >= counter) {
                            processes_RAND[i].addFrame();
                            availableCounter--;
                        }
                    }
                    counter--;
                }
                counter = 0;
            }

        }
        for (RAND process : processes_RAND) {
            pageFaultCounter += process.getPageFrameCounter();
        }

        return pageFaultCounter;
    }
}


