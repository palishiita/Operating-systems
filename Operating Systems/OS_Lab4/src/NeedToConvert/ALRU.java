package NeedToConvert;

import java.util.LinkedList;

public class ALRU {

    public static int pageReplacement(int[] pageReferencesArray, int numberOfFrames) {
        int faults = 0;
        class Frame {
            final int frame;
            boolean used = false;
            public Frame(int frame) {
                this.frame = frame;
            }
            @Override
            public boolean equals(Object other) {
                return other instanceof Frame && frame == ((Frame) other).frame;
            }
        }
        LinkedList<Frame> frames = new LinkedList<>();
        for (int i : pageReferencesArray) {
            Frame elem = new Frame(i);
            if (frames.contains(elem)) {
                frames.get(frames.indexOf(elem)).used = true;
                continue;
            }
            if (frames.size() >= numberOfFrames) {
                int looser = 0;
                for (int j = 0; j < frames.size(); j++) {
                    if (!frames.get(j).used) {
                        looser = j;
                        break;
                    }
                }
                frames.remove(looser);
            }
            frames.add(elem);
            faults++;
        }
        return faults;
    }

}
