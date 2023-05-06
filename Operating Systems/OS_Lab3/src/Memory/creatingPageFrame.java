package Memory;

public abstract class creatingPageFrame {
    public int address;
    public boolean inMemory;
    public int timePageAddedInFrame;
    public int TimeOfLastUse;
    public int chance;

    public creatingPageFrame(int address) {
        this.address = address;
        inMemory = false;
        timePageAddedInFrame = 0;
        TimeOfLastUse = 0;
        chance = 1;
    }

}
