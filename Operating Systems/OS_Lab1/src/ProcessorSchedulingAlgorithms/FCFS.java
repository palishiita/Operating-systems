package ProcessorSchedulingAlgorithms;
import java.util.ArrayList;

public class FCFS extends Scheduler {

    private int systemCount;
    private int idleCount;

    public FCFS(){
        super(); //class parent class Scheduler
        systemCount = 0; // set time in system to 0
        idleCount = 0; //counter for time no process is allocated the cpu
    }

    public void firstComeFirstServe(ArrayList<Process> jobs) {
        int totalProcesses = jobs.size(); // total jobs that will be allocated the cpu
        int job = 0; // first job to arrive
        int cpuBurstCounter = 0; // this will keep track of the current processes cpuBurst that has been completed

        // loop will continue if the first process that will be put in the Ready Queue arrives at a time later than 0
        while (jobs.get(job).getArrivalTime() != systemCount) {
            //System.out.println("<system time " + systemCount + "> idle");
            idleCount++;
            systemCount++;
        }

        super.addProcess(jobs.get(job)); //add the first process that arrives to the Ready Queue
        job++; //increment to next job that will be added to the RQ
        Process currProcess;
        currProcess = super.getProcess(0);

        //while loop that will continue until all processes have been completed
        while (true) {

            /* this loop will check if 'there are still processes in the job pool that still need to arrive to be placed
            in the ReadyQueue' AND 'the Ready Queue is empty'.*/
            while ((job != totalProcesses) && super.readyQueueEmpty()) {
                if (jobs.get(job).getArrivalTime() == systemCount) {
                    super.addProcess(jobs.get(job));
                    currProcess = super.getProcess(0);
                    job++;
                    break;
                } else {
                    //if no process arrives, cpu is idle
                    //System.out.println("<system time   " + systemCount + "> idle");
                    idleCount++;
                    systemCount++;
                }
            }

            // while loop to check if multiple processes have the 'same arrival time'
            while ((job != totalProcesses) && (jobs.get(job).getArrivalTime() == systemCount)) {
                super.addProcess(jobs.get(job));
                job++;
            }

            //if the original CPU Burst is equal to the temporary cpu burst counter, the process is complete
            if (cpuBurstCounter == currProcess.getCPUBurst()) {
                currProcess.setCompletionTime(systemCount); //set completion time
                // https://www.youtube.com/watch?v=-EIyOVX2lsA
                // TurnAround time = completion - arrival = wait time + CPUBurst
                // final formula: wait time = (completion - (arrival + CPUBurst)
                currProcess.setWaitTime(currProcess.getCompletionTime() - (currProcess.getArrivalTime() + currProcess.getCPUBurst()));
                //System.out.println("system time: " + systemCount + ", process finished: " + currProcess.getPID());

                super.removeProcess(0); //remove the process from the RQ
                cpuBurstCounter = 0; //set the cpu burst counter back to 0 for next process

                //check if RQ is empty and that there are no more jobs left to run
                if (super.readyQueueEmpty() && (job == totalProcesses)) {
                    break;
                }

                //if the RQ is not empty, get the next process at the head of the RQ
                else if (!super.readyQueueEmpty()) {
                    currProcess = super.getProcess(0);
                }

            }
            //if the process is not complete, it is currently running
            else if (cpuBurstCounter != currProcess.getCPUBurst()) {
                System.out.println("<system time   " + systemCount + "> process    " + currProcess.getPID() + " is running");
                cpuBurstCounter++;
                systemCount++;
            }
        }

    }

    //calculate average Wait Time
    private double averageWaitTime(ArrayList<Process> jobs){
        int sum=0;
        double avgWT = 0.0;
        for(int i=0; i<jobs.size(); i++){
            sum = sum + jobs.get(i).getWaitTime();
        }
        avgWT = (double)sum/jobs.size();
        return avgWT;
    }

    public void runFCFS(ArrayList<Process> jobs){
        double avgWT;
        System.out.println();
        System.out.println("Scheduling Algorithm 1: First Come First Serve");
        firstComeFirstServe(jobs); //run algorithm for fcfs
        //calculate average waiting time and turn around time
        avgWT = averageWaitTime(jobs);
        System.out.print("Average waiting time: ");
        System.out.printf("%.2f", avgWT);
        System.out.println();
    }

}