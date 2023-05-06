import java.util.List;
import java.util.Queue;

public class Algorithm_Random extends Algorithm {

    /**
     ** first processor selection happens, then we check the currentLoad < p **

     * Processor x randomly selects processor y
     * If current load of processor y is less than the threshold p,  [currentLoad < p] the process is sent there.
     * If not, we draw and ask the next one, trying at most z times (max migration queries).
     * If all the drawn processors have current load above p [currentLoad], the process executes on processor x.
    **/

    // maximum amount of time the process migrates to different processor
    private final int maxLoadRequests;

    public Algorithm_Random(List<Processor> processors, int maxLoadQueries) {
        super(processors);
        //
        this.maxLoadRequests = maxLoadQueries;
    }

    @Override
    public ToStringMethod process(Queue<Process> processes) {

        int maxLoadRequest = 0;
        int migrations = 0;

        while (!processes.isEmpty()) {  // if there are processes
            // take the process from the top of the queue and remove it from the queue
            Process process = processes.poll();
            // we randomize the processor
            Processor processor = randomProcessor();
            if (process.isEmpty()) {
                // we are processing "the process" if the process at the top of the queue is empty
                processAll();
            } else {
                // check whether the process was handled or not
                boolean processHandled = false;

                // the for loop is executed as long as 'i' is
                // less than the maximum number of migrations
                // and the process was not handled
                for (int i = 0; i < maxLoadRequests && !processHandled; i++) {
                    ++maxLoadRequest; // keep incrementing
                    // we randomize a different processor (this is the next processor)
                    Processor nextProcessor = randomProcessor();
                    // currentLoad < maxLoad
                    // ask the processor if it can handle the next process (IT'S LOAD) threshold p
                    if (nextProcessor.canHandleNextProcess()) {
                        // assign it a process
                        nextProcessor.addProcess(process);
                        // the process is handled
                        processHandled = true;
                        // we increase the number of migrations
                        ++migrations;
                    }
                }

                // if we have randomized enough times, we will load the primary processor
                if (!processHandled)
                    processor.addProcess(process);
            }

            // we start processing the processes
            processAll();
        }


        // output that is expected in main!
        return new ToStringMethod(
                "Algorithm 1",
                averageCPULoad(),
                averageDeviation(),
                maxLoadRequest,
                migrations);
    }


}

