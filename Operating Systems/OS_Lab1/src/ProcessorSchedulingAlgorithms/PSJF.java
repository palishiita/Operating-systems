package ProcessorSchedulingAlgorithms;

import java.util.ArrayList;

public class PSJF extends Scheduler{

    private int systemCount;
    private int idleCount;

    public PSJF(){
        super(); //class parent class Scheduler
        systemCount = 0; //set time in system to 0
        idleCount = 0; //counter for time no process is allocated the cpu
    }

    private void P_shortestJobFirst(ArrayList<Process> jobs){
        int totalProcesses = jobs.size(); //will store the number of distinct processes that will be allocated the cpu
        int job=0; //first job to arrive
        Process currProcess;
        //loop will continue if the first process that will be put in the Ready Queue arrives at a time later than 0
        while(jobs.get(job).getArrivalTime() != systemCount){
            //System.out.println("<system time   " + systemCount + "> idle");
            idleCount++;
            systemCount++;
        }
        super.addProcess(jobs.get(job)); //add the job to the Ready Queue
        job++; //increment to next job that will arrive
        currProcess = super.getProcess(0); //set current process to the first process in the ReadyQueue
        Process shortestProcess;
        int shortestProcessIndex = 0;
        while(true){
            //this loop will check if the Ready Queue is empty BUT there are still processes
            //in the job pool that still need to arrive to be placed in the ReadyQueue
            while((job != totalProcesses) && super.readyQueueEmpty()){
                if(jobs.get(job).getArrivalTime() == systemCount){
                    super.addProcess(jobs.get(job));
                    currProcess = super.getProcess(0);
                    job++;
                    break;
                }
                else{
                    //if no process arrives, cpu is in idle
                    //System.out.println("<system time   " + systemCount + "> idle");
                    idleCount++;
                    systemCount++;
                }
            }
            // while loop to check if multiple processes have the same arrival time
            while((job != totalProcesses) && (jobs.get(job).getArrivalTime() == systemCount)){
                super.addProcess(jobs.get(job));
                job++;
            }
            //check if RQ is empty
            if(!super.readyQueueEmpty()){
                shortestProcess = currProcess; //set the shortest process as the current process
                //if the RQ is of size 1, the shortest process will be at the head of the RQ
                if(super.readyQueueSize() == 1){
                    shortestProcessIndex = 0;
                }
                else{
                    //if the RQ size is >1, loop through all the processes currently in the RQ and find the process
                    //with the smallest CPU Burst. Set that process as the shortest process and set the response time for that process
                    for(int i=0; i<super.readyQueueSize(); i++){
                        if(super.getProcess(i).getCPUBurstLeft() < shortestProcess.getCPUBurstLeft()){
                            shortestProcess = super.getProcess(i);
                            //find the position in the RQ of the shortest process
                            shortestProcessIndex = super.getShortestProcessIndex(shortestProcess);
                        }
                    }//end for loop
                }//end else

                //if the current process still have CPU Burst to complete, the process s running
                if(shortestProcess.getCPUBurstLeft() != 0){
                    //System.out.println("<system time   " + systemCount + "> process    " + shortestProcess.getPID() +" is running" );
                    systemCount++;
                    shortestProcess.setCPUBurstLeft(shortestProcess.getCPUBurstLeft()-1);
                }

                //if the cpuBurst reaches 0, the process is complete
                if(shortestProcess.getCPUBurstLeft() == 0){
                    shortestProcess.setCompletionTime(systemCount); //set completion time
                    shortestProcess.setTurnaroundTime(shortestProcess.getCompletionTime() - shortestProcess.getArrivalTime()); //set turnaround time
                    shortestProcess.setWaitTime(shortestProcess.getCompletionTime() - (shortestProcess.getArrivalTime() + shortestProcess.getCPUBurst())); //set wait time
                    //System.out.println("<system time   " + systemCount + "> process    " + shortestProcess.getPID() +" is finished....." );
                    super.removeProcess(shortestProcessIndex); //remove the process with respect to its position in the RQ
                    //if the RQ is not empty, set the current process as the process at the head until  min comparison is ran again
                    if(!super.readyQueueEmpty()){
                        currProcess = super.getProcess(0);
                        shortestProcess = currProcess;

                    }
                }
            }
            //if the RQ is empty and all jobs are complete, break from outer while loop
            if(super.readyQueueEmpty() && job == totalProcesses) break;
        } //end while loop
    } //end srtf method

    //calculate average Wait Time
    private double averageWaitTime(ArrayList<Process> jobs){
        int sum=0;
        double avgWT = 0.0;
        for(int i=0; i<jobs.size(); i++){
            sum = sum + jobs.get(i).getWaitTime();
        }
        avgWT = (double)sum / jobs.size();
        return avgWT;
    }


    public void runPSJF(ArrayList<Process> jobs){
        double avgWT;
        System.out.println();
        System.out.println("Scheduling Algorithm: Shortest Remaining Time First");
        P_shortestJobFirst(jobs); //runs the sjf algorithm
        //calculate averages
        avgWT = averageWaitTime(jobs);
        //System.out.println("<system time   " + systemCount + "> All processes finished...");
        System.out.print("Average waiting time: ");
        System.out.printf("%.2f", avgWT);
        System.out.println();
    }
}