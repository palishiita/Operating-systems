package NeedToConvert;

import java.util.ArrayList;
import java.util.Collections;

public class RAND {

    public static int pageReplacement(int[] pageReferencesArray, int numberOfFrames) {
        int faults = 0;
        ArrayList<Integer> frames = new ArrayList<>();
        for (int elem : pageReferencesArray) {
            if (!(frames.contains(elem))) {
                if (frames.size() < numberOfFrames) {
                    frames.add(elem);
                } else {
                    Collections.shuffle(frames);
                    frames.set(0, elem);
                }
                faults++;
            }
        }
        return faults;
    }

}
