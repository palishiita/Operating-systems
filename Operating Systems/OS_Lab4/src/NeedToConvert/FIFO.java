package NeedToConvert;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO {
    public static int pageReplacement(int[] pageReferencesArray, int numberOfFrames) {
        Queue<Integer> frames = new LinkedList<>() {
            @Override
            public boolean add(Integer elem) {
                if (size() >= numberOfFrames)
                    poll();
                return super.add(elem);
            }
        };
        int faults = 0;
        for (int elem : pageReferencesArray) {
            if (!frames.contains(elem)) {
                frames.add(elem);
                faults++;
            }
        }
        return faults;
    }
}
