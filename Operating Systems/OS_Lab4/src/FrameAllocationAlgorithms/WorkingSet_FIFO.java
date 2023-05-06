package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.FIFO;

import java.util.HashSet;

public class WorkingSet_FIFO extends AllocatingFramesClass {

    public WorkingSet_FIFO(int[][] processes, int frames_counter) {
        super(processes, frames_counter);
    }

    @Override
    public int doAlgorithm() {
        //equal
        int framesPerProcess = frames / processes_FIFO.length;

        //list of the latest references
        HashSet<Integer>[] WorkingSet = new HashSet[processes_FIFO.length];
        for (int i = 0; i < processes_FIFO.length; ++i) {
            processes_FIFO[i].setFramesCount(framesPerProcess);
            WorkingSet[i] = new HashSet<>();
        }

        boolean isAllDone = false;
        int c = 0; //mentioned in hints
        int available_counter = 0;

        while (!isAllDone) {
            c++;
            isAllDone = true;
            for (int i = 0; i < processes_FIFO.length; i++) {
                if (!processes_FIFO[i].pageReplacement()) { //run LRU, and if is done
                    WorkingSet[i].add(processes_FIFO[i].getLastUsed()); //last frame I used
                    isAllDone = false;
                }
            }

            // c is over 2* frames for process
            // frequency of WSS count

            boolean condition = c >=  2 / framesPerProcess;

            if (condition) {
                for (int i = 0; i < processes_FIFO.length; ++i) {
                    if (processes_FIFO[i].isDone()) {
                        available_counter += processes_FIFO[i].getFramesCount();//if process is done we release frames
                        processes_FIFO[i].setFramesCount(0); //no frames for process
                    }
                    /* for every process as long as D is less than the number of frames available in the system each
                    receives as many frames from processes as its WSS.
                     */
                    for (int j = WorkingSet[i].size() - processes_FIFO[i].getFramesCount(); j > 0 && processes_FIFO[i].getFramesCount() > 1; j--) {
                        available_counter++;
                        processes_FIFO[i].removeFrame();
                    }
                }


                while (available_counter > 0 && c > 0) {
                    for (int i = 0; i < processes_FIFO.length && available_counter > 0; i++) {
                        int needed_counter = WorkingSet[i].size() - processes_FIFO[i].getFramesCount();
                        //WORKING SET - WS, .size() = WSS
                        /* When the number of available frames is exceeded by the D factor (point 1) one of the active
                         processes must be paused and freed frames forwarded to other processes (in line with the
                         proportionality principle). (point 2) */
                        if (needed_counter > 0) {
                            if (available_counter < needed_counter) {   //(point 1)
                                available_counter += processes_FIFO[i].getFramesCount();
                                processes_FIFO[i].setFramesCount(0);    //(point 2)
                            } else {
                                processes_FIFO[i].addFrame();
                                available_counter--;
                            }
                        }
                    }
                    c--;
                }

                c = 0;

                //clear WS after interval or done of process
                for (int i = 0; i < WorkingSet.length; i++) {
                    if (processes_FIFO[i].getFramesCount() > 0 || processes_FIFO[i].isDone()) {
                        WorkingSet[i].clear();
                    }
                }

            }

        }

        for (FIFO process : processes_FIFO) {
            pageFaultCounter += process.getPageFrameCounter();
        }

        return pageFaultCounter;
    }
}
