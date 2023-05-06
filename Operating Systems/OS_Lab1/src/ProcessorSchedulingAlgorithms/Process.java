package ProcessorSchedulingAlgorithms;

public class Process {

    private int PID;
    private int arrivalTime;
    private int completionTime; // Time taken for the execution to complete, starting from arrival time.
    private int CPUBurst; //the amount of time the process uses the processor before it is no longer ready.
    private int waitTime;
    //private int responseTime;
    private int turnaroundTime;
    private int cpuBurstLeft;

    // constructor
    public Process(int pid, int arrival, int CPUBurst) {
        setPID(pid);
        setArrivalTime(arrival);
        setCPUBurst(CPUBurst);
        setCPUBurstLeft(CPUBurst);
        //the remaining count of CPU Burst left for a process when preempted (for RR and SRTF)
        //for rr & psjf to keep track of remaining cpuBurst time when a process is preempted without overwriting original CPUBust data member.
    }

    public int getPID(){
        return PID;
    }

    public void setPID(int pid){
        PID = pid;
    }

    public int getArrivalTime(){
        return arrivalTime;
    }

    public void setArrivalTime(int aT){
        arrivalTime = aT;
    }

    public int getCPUBurst(){
        return CPUBurst;
    }

    public void setCPUBurst(int CPUBurst){
        this.CPUBurst = CPUBurst;
    }

    public int getCompletionTime(){
        return completionTime;
    }

    public void setCompletionTime(int cT){
        completionTime = cT;
    }

    public int getWaitTime(){
        return waitTime;
    }

    public void setWaitTime(int wt){
        waitTime = wt;
    }

    public int getTurnaroundTime(){
        return turnaroundTime;
    }

    public void setTurnaroundTime(int tt){
        turnaroundTime = tt;
    }

    public int getCPUBurstLeft(){
        return cpuBurstLeft;
    }

    public void setCPUBurstLeft(int bl){
        cpuBurstLeft = bl;
    }

}