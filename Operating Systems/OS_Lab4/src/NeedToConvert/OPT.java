package NeedToConvert;

import java.util.ArrayList;
import java.util.LinkedList;

public class OPT {

    public static int pageReplacement(int[] pageReferencesArray, int numberOfFrames) {
        int faults = 0;
        ArrayList<Integer> references = new ArrayList<>();
        for (int i : pageReferencesArray)
            references.add(i);
        LinkedList<Integer> frames = new LinkedList<>();
        for (int i = 0; i < pageReferencesArray.length; i++) {
            references.set(i, -1);
            int elem = pageReferencesArray[i];
            if (frames.contains(elem))
                continue;
            if (frames.size() >= numberOfFrames) {
                int max = -1;
                for (int temp : frames) {
                    int index = references.indexOf(temp);
                    if (index > max)
                        max = index;
                    if (index == -1) {
                        max = -1;
                        frames.remove((Integer) temp);
                        break;
                    }
                }
                if (max != -1) {
                    frames.remove(references.get(max));
                }
            }
            frames.add(elem);
            faults++;
        }
        return faults;
    }

}
