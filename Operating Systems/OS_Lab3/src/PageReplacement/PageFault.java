package PageReplacement;

import Algorithms.*;
import Memory.VirtualMemory;
import Memory.Timer;

import java.util.ArrayList;
import java.util.Scanner;

public class PageFault {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("_".repeat(52) + "\n" + " " + "Page Replacement Algorithms " +  "\n" + "_".repeat(52));

        System.out.println("Message: pages > frames!!");

        System.out.println("\n" + "Size of Generated Page Reference Sequence:");
        int lengthOfString = sc.nextInt();

        System.out.println("Input Number of Pages in virtual memory (size):");
        int NumberOfPages = sc.nextInt();
        PageReferenceGenerator.max = NumberOfPages;

        System.out.println("Input Number of Frames in physical memory (size):");
        int NumberOfFrames = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        int FIFO = 0, OPT = 0, LRU = 0, ALRU = 0, RAND = 0;
        int n = 10;

        PageReferenceGenerator pageReferenceGenerator = new PageReferenceGenerator();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < lengthOfString; i++) {
                int value = pageReferenceGenerator.generatePage();
                list.add(value);
            }

            FIFO += simulation(new FirstInFirstOut(), NumberOfFrames, NumberOfPages, list, lengthOfString);
            OPT += simulationOPT(new OptimalPageReplacement(), NumberOfFrames, NumberOfPages, list, lengthOfString);
            LRU += simulation(new LeastRecentlyUsed(), NumberOfFrames, NumberOfPages, list, lengthOfString);
            ALRU += simulation(new ApproximatedLeastRecentlyUsed(), NumberOfFrames, NumberOfPages, list, lengthOfString);
            RAND += simulation(new RANDOM(), NumberOfFrames, NumberOfPages, list, lengthOfString);
        }
        System.out.println();

        System.out.println("page ref:" + list);

        System.out.println("_".repeat(52) + "\n" + " " + "Comparing Page Faults " + "\n" + "_".repeat(52));

        System.out.println();

        FIFO = FIFO / n;
        System.out.println("First In First Out: " + FIFO);
        OPT = OPT / n;
        System.out.println("Optimal Page Replacement: " + OPT);
        LRU = LRU / n;
        System.out.println("Least Recently Used: " + LRU);
        ALRU = ALRU / n;
        System.out.println("Approximately Least Recently Used: " + ALRU);
        RAND = RAND / n;
        System.out.println("Random: " + RAND);
    }

    static int simulation(PageReplacementAlgorithm pageReplacementAlgorithm, int frames, int pages,
                          ArrayList<Integer> addressList, int lengthOfString) {
        Timer.getInstance().resetTime();
        VirtualMemory virtualMemory = new VirtualMemory(pageReplacementAlgorithm, frames, pages);
        for (int i = 0; i < lengthOfString; i++)
            virtualMemory.addPageToPhysicalMemory(addressList.get(i));
        if (frames > pages) {
            return frames;
        } else {
            return (virtualMemory.getChangeOfPageCounter() + frames);
        }
    }

    static int simulationOPT(PageReplacementAlgorithm pageReplacementAlgorithm, int frames, int pages,
                             ArrayList<Integer> addressList, int lengthOfString) {
        Timer.getInstance().resetTime();
        VirtualMemory virtualMemory = new VirtualMemory(pageReplacementAlgorithm, frames, pages);
        for (int i = 0; i < lengthOfString; i++) {
            ArrayList<Integer> waitingTime = new ArrayList<>();
            for (int j = i; j < lengthOfString; j++) {
                waitingTime.add(addressList.get(j));
            }
            virtualMemory.addPageToPhysicalMemoryOPT(addressList.get(i), waitingTime);
        }
        if (frames > pages) {
            return frames;
        } else {
            return (virtualMemory.getChangeOfPageCounter() + frames);
        }
    }

}