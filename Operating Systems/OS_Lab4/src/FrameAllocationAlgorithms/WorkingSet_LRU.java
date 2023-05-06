package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.LRU;

import java.util.HashSet;

public class WorkingSet_LRU extends AllocatingFramesClass {

    public WorkingSet_LRU(int[][] processes, int frames_counter) {
        super(processes, frames_counter);
    }

    @Override
    public int doAlgorithm() {
        int framesPerProcess = (frames / processes_LRU.length);
        // list of the latest references, HashSet doesn't allow duplicate values
        var WorkingSet = new HashSet[processes_LRU.length];
        for (int i = 0; i < processes_LRU.length; ++i) {
            processes_LRU[i].setFramesCount(framesPerProcess);
            WorkingSet[i] = new HashSet<>();
        }

        boolean isAllDone = false;
        int WorkingSetSizeCount = 0;
        int available_counter = 0;
        while (!isAllDone) {
            WorkingSetSizeCount++;
            isAllDone = true;
            for (int i = 0; i < processes_LRU.length; i++) {
                if (!processes_LRU[i].pageReplacementLRU()) { //run Page.LRU, and if is done
                    WorkingSet[i].add(processes_LRU[i].getLastUsed()); //last frame I used
                    isAllDone = false;
                }
            }

            // WorkingSetSizeCount is over 2*frames for process
            boolean condition = WorkingSetSizeCount >= 2 / framesPerProcess;
            if (condition) {
                for (int i = 0; i < processes_LRU.length; ++i) {
                    if (processes_LRU[i].isDone()) {
                        available_counter += processes_LRU[i].getFramesCount();//if process is done we release frames
                        processes_LRU[i].setFramesCount(0); //no frames for process
                    }

                    for (int j = WorkingSet[i].size() - processes_LRU[i].getFramesCount(); j > 0 &&
                            processes_LRU[i].getFramesCount() > 1; j--) {
                        available_counter++;
                        processes_LRU[i].removeFrame();
                    }
                }

                while (available_counter > 0 && WorkingSetSizeCount > 0) {
                    for (int i = 0; i < processes_LRU.length && available_counter > 0; i++) {
                        int needed_counter = WorkingSet[i].size() - processes_LRU[i].getFramesCount();
                        //WORKING SET - WS, .size() = WSS
                        /* When the number of available frames is exceeded by the D factor (point 1) one of the active
                         processes must be paused and freed frames forwarded to other processes (in line with the
                         proportionality principle). (point 2) */
                        if (needed_counter > 0) {
                            if (available_counter < needed_counter) {   //(point 1)
                                available_counter += processes_LRU[i].getFramesCount();
                                processes_LRU[i].setFramesCount(0);    //(point 2)
                            } else {
                                processes_LRU[i].addFrame();
                                available_counter--;
                            }
                        }
                    }
                    WorkingSetSizeCount--;
                }
                WorkingSetSizeCount = 0;
                //clear WS after interval or done of process
                for (int i = 0; i < WorkingSet.length; i++) {
                    if (processes_LRU[i].getFramesCount() > 0 || processes_LRU[i].isDone()) {
                        WorkingSet[i].clear();
                    }
                }

            }
        }
        for (LRU process : processes_LRU) {
            pageFaultCounter += process.getPageFrameCounter();
        }
        return pageFaultCounter;
    }

}

