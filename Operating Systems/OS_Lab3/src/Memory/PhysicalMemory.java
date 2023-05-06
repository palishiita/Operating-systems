package Memory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PhysicalMemory {

    private final int size; // physical memory size equals frame size
    private final ArrayList<creatingPageFrame> frameList;
    public Queue<creatingPageFrame> queue;

    public PhysicalMemory(int size) {
        this.size = size;
        this.frameList = new ArrayList<>();
        this.queue = new LinkedList<>();
    }

    // getter
    public ArrayList<creatingPageFrame> getFrameList() {
        return frameList;
    }

    public boolean addFrameToPhysicalMemory(creatingPageFrame addFrame) {
        for (int i = 0; i < frameList.size(); i++) {
            if (frameList.get(i).address == addFrame.address) {
                frameList.get(i).TimeOfLastUse = Timer.getInstance().getTime();
                frameList.get(i).chance = 1;
                for (creatingPageFrame frame : queue) {
                    if (frame.address == addFrame.address) {
                        frame.chance = 1;
                    }
                }
                return true;
            }
        }

        if (frameList.size() > size) {
            return false;
        }

        addFrame.inMemory = true;
        Timer timer = Timer.getInstance();
        addFrame.TimeOfLastUse = timer.getTime();
        addFrame.timePageAddedInFrame = timer.getTime();
        addFrame.chance = 1;
        frameList.add(addFrame);
        queue.add(addFrame);
        return true;
    }


    public void removeFrame(int address) {
        for (int i = 0; i < frameList.size(); i++) {
            creatingPageFrame currentFrame = frameList.get(i);
            if (currentFrame.address == address) {
                currentFrame.inMemory = false;
                frameList.remove(i);
                return;
            }
        }
    }


}