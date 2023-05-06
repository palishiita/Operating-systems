package Simulation;

import FrameAllocationAlgorithms.*;
import PrincipleOfLocality.Generator;
import PrincipleOfLocality.LocalityPrinciple;

import java.util.Scanner;

public class Main {

    //DEFAULT: process = 10; pages = 10000; min = 30; max =300; radius = 10; frames = 100
    public static int Processes = 10;
    public static int Pages = 10000;
    public static int PageFrames = 100; // manipulating numb_Of_frames affects result, more frames = proportional will be more effective
    public static final int MINIMUM_NUMBER_OF_REFERENCES = 30; //no effects
    public static final int MAXIMUM_NUMBER_OF_REFERENCES = 300; // we can observe more differences between results
    public static final int RADIUS_OF_LOCALITY = 10;

    public static void main(String[] args) {

        Generator generator = new Generator(Processes, Pages, MINIMUM_NUMBER_OF_REFERENCES,
                MAXIMUM_NUMBER_OF_REFERENCES, RADIUS_OF_LOCALITY);

        int[][] processes = generator.generateProcesses(); // page reference request generator

        System.out.println("=".repeat(52) + "\n" + " " + "Comparing Page Faults " + "\n" + "=".repeat(52));
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("-".repeat(30) + "\n" + " " + "Frame Allocation Algorithms " + "\n" + "-".repeat(30));
        System.out.println("Input number of process:");
        Processes = sc.nextInt();
        System.out.println("Size of Generated Page Reference Sequence / requests:");
        Pages = sc.nextInt();
        System.out.println("Input Page-Frame:");
        PageFrames = sc.nextInt();
        System.out.println();

        AllocatingFramesClass[] algorithms = new AllocatingFramesClass[] {
                // TODO equal
                new Equal_LRU(LocalityPrinciple.cloneArray(processes), PageFrames),
                //new Equal_ALRU(LocalityPrinciple.cloneArray(processes), PageFrames),
                //new Equal_FIFO(LocalityPrinciple.cloneArray(processes), PageFrames),
                //new Proportional_OPT(LocalityPrinciple.cloneArray(processes), numberOfFrames),
                //new Proportional_RAND(LocalityPrinciple.cloneArray(processes), numberOfFrames),
                // TODO proportional
                new Proportional_LRU(LocalityPrinciple.cloneArray(processes), PageFrames),
                //new Proportional_ALRU(LocalityPrinciple.cloneArray(processes), numberOfFrames),
                new Proportional_FIFO(LocalityPrinciple.cloneArray(processes), PageFrames),
                //new Proportional_OPT(LocalityPrinciple.cloneArray(processes), numberOfFrames),
                //new Proportional_RAND(LocalityPrinciple.cloneArray(processes), numberOfFrames),
                // TODO page fault frequency
                new PageFaultFrequency_LRU(LocalityPrinciple.cloneArray(processes), PageFrames),
                // TODO working set
                new WorkingSet_LRU(LocalityPrinciple.cloneArray(processes), PageFrames),
                //new WorkingSet_ALRU(LocalityPrinciple.cloneArray(processes), numberOfFrames),
                //new WorkingSet_FIFO(LocalityPrinciple.cloneArray(processes), numberOfFrames),
                //new WorkingSet_OPT(LocalityPrinciple.cloneArray(processes), numberOfFrames),
                //new WorkingSet_RAND(LocalityPrinciple.cloneArray(processes), numberOfFrames),
        };

        System.out.println("_".repeat(48) + "\n" + " " + "Results for all page replace + frame allocate" + "\n" + "_".repeat(48));
        for (AllocatingFramesClass algo : algorithms) {
            System.out.println(algo.getClass().getSimpleName() + " - " + algo.doAlgorithm());
        }

    }
}
