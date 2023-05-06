package Algorithms;

import Memory.PhysicalMemory;

public abstract class PageReplacementAlgorithm {

    PhysicalMemory physicalMemory = null;
    public void setPhysicalMemory(PhysicalMemory physicalMemory) {
        this.physicalMemory = physicalMemory;
    }

    public abstract void replacePage();

}
