package Memory;

import Algorithms.OptimalPageReplacement;
import Algorithms.PageReplacementAlgorithm;

import java.util.ArrayList;

public class VirtualMemory {
    ArrayList<PageFrame> pageList;
    PhysicalMemory physicalMemory;
    PageReplacementAlgorithm pageReplacementAlgorithm;

    // getter
    int changeOfPageCounter = 0;

    public int getChangeOfPageCounter() {
        return changeOfPageCounter;
    }

    public VirtualMemory(PageReplacementAlgorithm pageReplacementAlgorithm, int PageFrame, int frame) {
        this.pageReplacementAlgorithm = pageReplacementAlgorithm;
        this.physicalMemory = new PhysicalMemory(PageFrame);
        pageList = new ArrayList<>();
        for (int i = 0; i < PageFrame; i++)
            pageList.add(new PageFrame(i + 1));
        this.pageReplacementAlgorithm.setPhysicalMemory(physicalMemory);
    }

    public void addPageToPhysicalMemory(int address) {
        Timer.getInstance().increaseTime(1);
        creatingPageFrame toAdd = pageList.get(address - 1);
        if (!physicalMemory.addFrameToPhysicalMemory(toAdd)) {
            changeOfPageCounter++;
            // replacing page using page replacement algorithm
            pageReplacementAlgorithm.replacePage();
            physicalMemory.addFrameToPhysicalMemory(toAdd);
        }
    }

    public void addPageToPhysicalMemoryOPT(int address, ArrayList<Integer> pendingRequest) {
        Timer.getInstance().increaseTime(1);
        creatingPageFrame toAdd = pageList.get(address - 1);
        if (!physicalMemory.addFrameToPhysicalMemory(toAdd)) {
            changeOfPageCounter++;
            ((OptimalPageReplacement) pageReplacementAlgorithm).replacePage_OptimalPageReplacement(pendingRequest, pageList.size());
            physicalMemory.addFrameToPhysicalMemory(toAdd);
        }
    }


}
