package ProcessorSchedulingAlgorithms;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Process> jobs = new ArrayList<>(); //array to store processes read in from file
        Process process;
        try {
            FileInputStream FileStream = new FileInputStream("Test5.txt"); // pid, arrival, burst
            Scanner input = new Scanner(FileStream);
            String line;
            Scanner info;
            int pid, arrival, CPUBurst; // will store values PID, Arrival Time and CPUBurst for a Process read in from file
            while (input.hasNext()) {
                line = input.nextLine();
                info = new Scanner(line);
                pid = Integer.parseInt(info.next()); //PID
                arrival = Integer.parseInt(info.next()); //Arrival Time
                CPUBurst = Integer.parseInt(info.next()); //CPU Burst
                process = new Process(pid, arrival, CPUBurst); //create new Process object
                jobs.add(process); //add process to array
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
        // 380.94
        FCFS fcfs = new FCFS();
        fcfs.runFCFS(jobs);
        System.out.println();
        */

        /*
        // 1.51
        PSJF psjf = new PSJF();
        psjf.runPSJF(jobs);
        System.out.println();
         */

        /*
        // 1.51
        NSJF nsjf = new NSJF();
        nsjf.runNSJF(jobs);
        System.out.println();
         */


        // 380.94
        RR rr = new RR();
        rr.runRR(jobs, 65);
        System.out.println();

    }

}

