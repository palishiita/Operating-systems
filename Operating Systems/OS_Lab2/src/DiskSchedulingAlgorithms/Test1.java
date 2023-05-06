package DiskSchedulingAlgorithms;
import java.util.*;

// FCFS, SSTF, SCAN, C-SCAN algorithm

/*
•	Number of tracks that user inputs = the disk capacity
•	Number of requests (in this case the length of the request) that the user inputs = requestQueueSize
•	Using the requestQueueSize I generate array of random requests (using Random class) and store these values into the
    requestQueue.
•	Number of tracks that user inputs = the disk capacity
•	Number of requests (in this case the length of the request) that the user inputs = requestQueueSize
•	Using the requestQueueSize I generate array of random requests (using Random class) and store these values into the
    requestQueue
 */
public class Test1 {

    static int requestQueueSize;
    static int [] requestQueue;
    static int totalTracks; // disk size
    static int headPosition;

    static FirstComeFirstServe fcfs;
    static ShortestSeekTimeFirst sstf;
    static Scan scan;
    static CScan cScan;

    static Scanner in = new Scanner(System.in);

    static void header1() {
        System.out.println("_".repeat(35)+"\n"+" Disk Scheduling Algorithms " +"\n"+ "_".repeat(35));
    }
    static void header2() {
        header1();
        System.out.println("Total number of tracks:  " + totalTracks);
        System.out.println("Initial head position:  " + headPosition);
    }
    static void header3 () {
        header2();
        System.out.println("Length of the request queue:  " + requestQueueSize);
    }
    static void header4 () {
        header3();
        System.out.println("Request queue:  " + Arrays.toString(requestQueue));
    }
    static void header5 (int choice) {
        header4();
        if (choice == 0) {
            System.out.println("executed all!"+"\n");
        }
    }
    // Clears screen.
    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // input reading start
    // Reads total track number and initial head position, request queue.
    static void inputPhase1() {
        header1();
        while (true) {
            try {
                System.out.print("Enter the total number of tracks: ");
                totalTracks = Integer.parseInt(in.nextLine());
                if (totalTracks < 1) throw new InvalidTotalTracks();
                break;
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("enter an integer!");
            } catch (InvalidTotalTracks e) {
                System.out.println(e.getMessage());
            }
        }
        clearScreen();
        inputPhase2();
        DiskSchedulingAlgorithm.totalTracksMaxReq = totalTracks;
    }

    // Reads the head position.
    static void inputPhase2() {
        while (true) {
            try {
                System.out.print("Enter the initial head position: ");
                headPosition = Integer.parseInt(in.nextLine());
                if (headPosition < 0 || headPosition >= totalTracks) throw new InvalidHeadPosition();
                break;
            }catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("enter an integer!");
            } catch (InvalidHeadPosition e) {
                System.out.println(e.getMessage());
            }
        }
        DiskSchedulingAlgorithm.headPosition = headPosition;
        clearScreen();
    }

    // Reads the requestQueueSize.
    static void inputPhase3() {
        while (true) {
            try {
                System.out.print("Enter the length of the request queue: ");
                requestQueueSize = Integer.parseInt(in.nextLine());
                if (requestQueueSize < 1) throw new InvalidRequestQueueSize();
                break;
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("enter an integer!");
            } catch (InvalidRequestQueueSize e) {
                System.out.println(e.getMessage());
            }
        }
        clearScreen();
    }
    // reading input end


    // Reads the requestQueue.
    static void phase4() {
        requestQueue = new int[requestQueueSize];
        for (int i = 0; i < requestQueueSize; i++) {
            while (true) {
            // creating random queue of requests!
            // number of requests generated == input requestQueueSize && Max of randomly generated queue == totalTracks
                requestQueue[i] = new Random().nextInt(totalTracks);
                break;
            }
        }
        clearScreen();
    }

    // Reads choice.
    static int phase5() {
        int choice;
        System.out.println("\n"+"_".repeat(35)+ "Menu"+"_".repeat(35));
        System.out.println("1️⃣. Execute a disk scheduling algorithm \n2️⃣. Execute all");
        while (true){
            try {
                System.out.print("Enter your choice:  ");
                choice = Integer.parseInt(in.nextLine());
                if(choice != 1 && choice != 2) throw new Invalid1();
                break;
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("enter valid choice!");
            }catch (Invalid1 e) {
                System.out.println(e.getMessage());
            }
        }
        clearScreen();

        int choice1 = 0;
        if (choice == 1) {
            System.out.println(" Menu ");
            System.out.println("1️⃣. First Come First Serve. 2️⃣. Shortest Seek Time First.3️⃣. C-Scan. 4️⃣. Scan.");
            while (true) {
                try {
                    System.out.print("Enter your choice:  ");
                    choice1 = Integer.parseInt(in.nextLine());
                    if (choice1 < 0 || choice1 > 6) throw new Invalid2();
                    break;
                }catch (NumberFormatException | NoSuchElementException e) {
                    System.out.println("enter an integer!");
                }catch (Invalid2 e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        clearScreen();
        return choice1;
    }

    // Gives display options.
    static void phase6(int choice) {
        header5(choice);
        if (choice == 0) {
            ArrayList<Integer> totalSeeks = new ArrayList<>();
            totalSeeks.add(fcfs.totalSeekTime);
            totalSeeks.add(sstf.totalSeekTime);
            totalSeeks.add(scan.totalSeekTime);
            totalSeeks.add(cScan.totalSeekTime);

            Collections.sort(totalSeeks);

            boolean fcfsDone = false;
            boolean sstfDone = false;
            boolean scanDone = false;

            for (Integer i :
                    totalSeeks) {
                if ((i == fcfs.totalSeekTime) && !fcfsDone) {
                    fcfs.displayResults();
                    System.out.println();
                    fcfsDone = true;
                }else if ((i == sstf.totalSeekTime) && !sstfDone) {
                    sstf.displayResults();
                    System.out.println();
                    sstfDone = true;
                }else if ((i == scan.totalSeekTime) && !scanDone) {
                    scan.displayResults();
                    System.out.println();
                    scanDone = true;
                }else {
                    cScan.displayResults();
                    System.out.println();
                }
            }
            System.out.println();
        }else {

            if (choice == 1) {
                fcfs.displayResults();
                System.out.println();
                while (true){
                    try {
                        System.out.print("🟢 Enter 1️⃣ for detailed result or 2️⃣ to go to the menu:  ");
                        int response = Integer.parseInt(in.nextLine());
                        clearScreen();
                        if (response == 1) {
                            header5(choice);
                            fcfs.displayDetailedResults();
                        }else if (response == 2) {
                            int m = phase5();
                            phase6(m);
                        }
                        break;
                    } catch (NumberFormatException | NoSuchElementException e) {
                        System.out.println("Please enter an integer!");
                    }
                }

            }else if (choice == 2) {
                sstf.displayResults();
                System.out.println();
                while (true){
                    try {
                        System.out.print("Enter 1️⃣ for detailed result or 2️⃣ to go to the menu:  ");
                        int response = Integer.parseInt(in.nextLine());
                        clearScreen();
                        if (response == 1) {
                            header5(choice);
                            sstf.displayDetailedResults();
                        }else if (response == 2) {
                            int m = phase5();
                            phase6(m);
                        }
                        break;
                    } catch (NumberFormatException | NoSuchElementException e) {
                        System.out.println("enter an integer!");
                    }
                }

            } else if (choice == 3) {
                cScan.displayResults();
                System.out.println();
                while (true){
                    try {
                        System.out.print("Enter 1️⃣ for detailed result or 2️⃣ to go to the menu:  ");
                        int response = Integer.parseInt(in.nextLine());
                        clearScreen();
                        if (response == 1) {
                            header5(choice);
                            System.out.println("-".repeat(58));
                            cScan.displayDetailedResults();
                        }else if (response == 2) {
                            int m = phase5();
                            phase6(m);
                        }
                        break;
                    } catch (NumberFormatException | NoSuchElementException e) {
                        System.out.println("Please enter an integer!");
                    }
                }
            }else if (choice == 4) {
                scan.displayResults();
                System.out.println();
                while (true){
                    try {
                        System.out.print("Enter 1️⃣ for detailed result or 2️⃣ to go to the menu:  ");
                        int response = Integer.parseInt(in.nextLine());
                        clearScreen();
                        if (response == 1) {
                            header5(choice);
                            scan.displayDetailedResults();
                        }else if (response == 2) {
                            int m = phase5();
                            phase6(m);
                        }
                        break;
                    } catch (NumberFormatException | NoSuchElementException e) {
                        System.out.println("enter an integer!");
                    }
                }
            }

        }

        while (true){
            try {
                System.out.print("Enter 1️⃣ to continue, 2️⃣ for Change menu, or any other int to exit:  ");
                int n  =  Integer.parseInt(in.nextLine());
                clearScreen();
                if (n == 1) {
                    int m = phase5();
                    phase6(m);
                }
                else if (n == 2) phase7();
                else return;
                break;
            }catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("Please enter an integer.");
            }
        }
        clearScreen();

    }

    // Edit.
    static void phase7() {
        header4();
        System.out.println("-".repeat(10) + " Menu " + "-".repeat(10));
        System.out.println("1️⃣. Change total tracks.");
        System.out.println("2️⃣. Change initial head position.");
        System.out.println("3️⃣. Change request queue.");

        int choice;
        while (true){
            try {
                System.out.print("Enter your choice:  ");
                choice = Integer.parseInt(in.nextLine());
                if (choice != 1 && choice != 2 && choice != 3) throw new Invalid1();
                break;
            }catch (NumberFormatException | NoSuchElementException e) {
                System.out.println();
                System.out.println("Please enter an integer.");
            }catch (Invalid1 e) {
                System.out.println();
                System.out.println("Entered choice must be 1 or 2 or 3.");
            }
        }
        clearScreen();

        switch (choice) {
            case 1 -> {
                inputPhase1();
                inputPhase3();
                phase4();
                init();
            }
            case 2 -> {
                inputPhase2();
                init();
            }
            case 3 -> {
                inputPhase3();
                phase4();
                init();
            }
        }
        int n = phase5();
        phase6(n);
    } // phase 6 finish

    static void init() {
        fcfs = new FirstComeFirstServe(requestQueue);
        sstf = new ShortestSeekTimeFirst(requestQueue);
        scan = new Scan(requestQueue);
        cScan = new CScan(requestQueue);
    }

    public static void main(String[] args) {
        clearScreen();
        inputPhase1();
        inputPhase3();
        phase4();
        init();
        int choice = phase5();
        phase6(choice);
    }

}

// ALL EXCEPTIONS: checking validity of requestQueueSize, total tracks,
class InvalidRequestQueueSize extends Exception {
    public InvalidRequestQueueSize () {
        super("Size of requestQueue > 1.");
    }
}
class InvalidTotalTracks extends Exception {
    public InvalidTotalTracks () {
        super("totalTracks > 1.");
    }
}
class InvalidHeadPosition extends Exception {
    public InvalidHeadPosition() {
        super("initial head position >= 0 && < totalTracks.");
    }
}
class Invalid1 extends Exception {
    public Invalid1() {
        super(" enter 1 or 2.");
    }
}
class Invalid2 extends Exception {
    public Invalid2() {
        super("enter a number in the range 1 - 4.");
    }
}