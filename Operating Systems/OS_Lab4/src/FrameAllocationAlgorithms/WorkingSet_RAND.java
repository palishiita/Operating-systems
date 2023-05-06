package FrameAllocationAlgorithms;

import PageReplacementAlgorithms.RAND;

import java.util.HashSet;

public class WorkingSet_RAND extends AllocatingFramesClass {

    public WorkingSet_RAND(int[][] processes, int frames_counter) {
        super(processes, frames_counter);
    }

    @Override
    public int doAlgorithm() {
        //equal
        int framesPerProcess = frames / processes_RAND.length;

        //list of the latest references
        HashSet<Integer>[] WorkingSet = new HashSet[processes_RAND.length];
        for (int i = 0; i < processes_RAND.length; ++i) {
            processes_RAND[i].setFramesCount(framesPerProcess);
            WorkingSet[i] = new HashSet<>();
        }

        boolean isAllDone = false;
        int c = 0; //mentioned in hints
        int available_counter = 0;

        while (!isAllDone) {
            c++;
            isAllDone = true;
            for (int i = 0; i < processes_RAND.length; i++) {
                if (!processes_RAND[i].pageReplacement()) { //run LRU, and if is done
                    WorkingSet[i].add(processes_RAND[i].getLastUsed()); //last frame I used
                    isAllDone = false;
                }
            }

            // c is over 2* frames for process
            // frequency of WSS count

            boolean condition = c >=  2 / framesPerProcess;

            if (condition) {
                for (int i = 0; i < processes_RAND.length; ++i) {
                    if (processes_RAND[i].isDone()) {
                        available_counter += processes_RAND[i].getFramesCount();//if process is done we release frames
                        processes_RAND[i].setFramesCount(0); //no frames for process
                    }
                    /* for every process as long as D is less than the number of frames available in the system each
                    receives as many frames from processes as its WSS.
                     */
                    for (int j = WorkingSet[i].size() - processes_RAND[i].getFramesCount(); j > 0 && processes_RAND[i].getFramesCount() > 1; j--) {
                        available_counter++;
                        processes_RAND[i].removeFrame();
                    }
                }


                while (available_counter > 0 && c > 0) {
                    for (int i = 0; i < processes_RAND.length && available_counter > 0; i++) {
                        int needed_counter = WorkingSet[i].size() - processes_RAND[i].getFramesCount();
                        //WORKING SET - WS, .size() = WSS
                        /* When the number of available frames is exceeded by the D factor (point 1) one of the active
                         processes must be paused and freed frames forwarded to other processes (in line with the
                         proportionality principle). (point 2) */
                        if (needed_counter > 0) {
                            if (available_counter < needed_counter) {   //(point 1)
                                available_counter += processes_RAND[i].getFramesCount();
                                processes_RAND[i].setFramesCount(0);    //(point 2)
                            } else {
                                processes_RAND[i].addFrame();
                                available_counter--;
                            }
                        }
                    }
                    c--;
                }

                c = 0;

                //clear WS after interval or done of process
                for (int i = 0; i < WorkingSet.length; i++) {
                    if (processes_RAND[i].getFramesCount() > 0 || processes_RAND[i].isDone()) {
                        WorkingSet[i].clear();
                    }
                }

            }

        }

        for (RAND process : processes_RAND) {
            pageFaultCounter += process.getPageFrameCounter();
        }

        return pageFaultCounter;
    }
}