package NeedToConvert;

import java.util.LinkedList;
import java.util.Queue;

public class LRU {

    public static int pageReplacement(int[] pageReferencesArray, int numberOfFrames) {
        int faults = 0;
        Queue<Integer> frames = new LinkedList<>();
        for (int elem : pageReferencesArray) {
            if (frames.contains(elem)) {
                frames.remove(elem);
                frames.add(elem);
                continue;
            }
            if (frames.size() >= numberOfFrames) {
                frames.poll();
            }
            frames.add(elem);
            faults++;
        }
        return faults;
    }

}
