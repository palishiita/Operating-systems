import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.range;

public class ScheduleDistributedSystem {

    // N
    static int NUMBER_OF_PROCESSORS = 75;
    // p - CPU load MAX threshold x
    static double MAX_PROCESSOR_LOAD = 0.8; // 80% cpu
    // r - CPU load MIN processor threshold x
    static double MIN_PROCESSOR_LOAD = 0.3; // 30% cpu
    // z - maximum number of queries that are brought about depending on current load of processor (algo 1)
    static int MAX_LOAD_QUERY = 15;
    private static final int NUMBER_OF_PROCESSES = 1000;
    // process generation
    private static final double PROCESS_MIN_LOAD = 0.3;
    private static final double PROCESS_MAX_LOAD = 0.5;
    private static final int PROCESS_MIN_TIME = 50;
    private static final int PROCESS_MAX_TIME = 100;


    public static void main(String[] args) {

        System.out.println();
        System.out.println("---------------The allocation of processors in distributed systems---------------\n");
        Scanner scan = new Scanner(System.in);

        System.out.println("Input N:");
        NUMBER_OF_PROCESSORS = scan.nextInt();
        System.out.println();

        System.out.println("Input p:");
        MAX_PROCESSOR_LOAD = scan.nextFloat();
        System.out.println();

        System.out.println("Input r:");
        MIN_PROCESSOR_LOAD = scan.nextFloat();
        System.out.println();

        // when current load has not met the condition or threshold a migration request is create
        // if it exceeds max migration then it executes in x (only algo A),
        System.out.println("Input z:");
        MAX_LOAD_QUERY = scan.nextInt();
        System.out.println();

        System.out.println("Input number of Process / tasks:");
        NUMBER_OF_PROCESSORS = scan.nextInt();
        System.out.println();

        // generating processors
        List<Processor> processors = range(0, NUMBER_OF_PROCESSORS)
                .mapToObj(i -> new Processor(MIN_PROCESSOR_LOAD, MAX_PROCESSOR_LOAD))
                .collect(toList());

        // process generation
        Process[] processes = new ProcessGenerator(
                PROCESS_MIN_LOAD,
                PROCESS_MAX_LOAD,
                PROCESS_MIN_TIME,
                PROCESS_MAX_TIME
        ).generate(NUMBER_OF_PROCESSES);

        // create an array with managers
        Algorithm[] managers = new Algorithm[] {
                new Algorithm_Random(ProcessorProcessManager.processorsList(processors), MAX_LOAD_QUERY),
                new Algorithm_Maximum(ProcessorProcessManager.processorsList(processors)),
                new Algorithm_Minimum(ProcessorProcessManager.processorsList(processors)),
        };

        for (Algorithm manager : managers) {
            System.out.println(manager.process(ProcessorProcessManager.processesQueue(processes)));
        }

    }

    /*
    // migration is most in case of algorithm A because we don't start executing from processor x from the start
    therefore incrementing migration (we are migrating even more therefore waisting time!)
    moving a task from one processor to other query
    load: ask processor for cpu!!
     */

    /** algo 1
     ** first processor selection happens, then we check the currentLoad < p **

     * Processor x randomly selects processor y (takes time!!)
     * If current load of processor y is less than the threshold p,  [currentLoad < p] the process is sent there.
     * If not, we draw and ask the next one, trying at most z times (max migration queries).
     * If all the drawn processors have current load above p [currentLoad], the process executes on processor x.
     **/

    /**
     * algo 2
     ** first we check the currentLoad < p then, processor selection happens **

     * If the load on processor x exceeds maximum threshold p [currentLoad > p],
     * the process is sent to a randomly selected processor y with currentLoad less than p [currentLoad < p]
     * if the drawn y has currentLoad > p, new processor is drawn and repeated until successful
     * If it does not succeed - the process executes on x.
     **/

    /**
     ** algo 3
     * first we check the currentLoad < r then, processor selection happens **.
     *
     * if a processor is currently working below some min threshold (% of the processor's load),
     * then it sends "information to all other processors" that it has free space -> increment load query .
     * Those processors then can send some of their workload to that processor.
     **/
    /*
    Load balancing tries to ensure that every processor in the system does almost the same amount of work at any
  time. Thus, the load of each of the nodes will be equal in the system at any point of time.
     */

}