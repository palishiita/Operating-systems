import java.util.List;
import java.util.Queue;

public class Algorithm_Minimum extends Algorithm_Maximum {

    /**
     ** first we check the currentLoad < r then, processor selection happens **.

     * As in (2), except those processors with load less than the minimum threshold r,
     * randomly selects processors
     * if the load of the query is greater than p, the query takes over some of its tasks (assume which one)
     *
     * more
     * if a processor is currently working below some min threshold (% of the processor's load),
     * then it sends information to all other processors that it has free space.
     * Those processors then can send some of their workload to that processor.
    **/

    // the assumed threshold for queries about the process exchange
    private final int MAX_COUNT_OF_PROCESS_EXCHANGES = 15;

    public Algorithm_Minimum(List<Processor> processors) {
        super(processors);
    }

    @Override
    public ToStringMethod process(Queue<Process> processes) {
        int migrationRequest = 0;
        int migrations = 0;
        int requestAboutProcessExchange;

        while (!processes.isEmpty()) {

            // take the process from the top
            Process process = processes.poll();
            // randomize processor
            Processor processor = randomProcessor();
            ProcessResponse response = findProcessorAndProcess(process, processor);
            migrationRequest += response.migrationRequests;
            migrations += response.migrations;
            requestAboutProcessExchange = 0;

            // we check every processor
            for (Processor selectedProcessor : processors) {
                // if the process load is less than the minimum
                if (selectedProcessor.isBelowMinimumLoad()) {
                    while (selectedProcessor.canHandleNextProcess()
                            && requestAboutProcessExchange++ < MAX_COUNT_OF_PROCESS_EXCHANGES) {
                        // as long as the selected processor can handle the next process,
                        // and we have not exceeded the number of requests for the exchange of processes
                        Processor selectedToGiveBack = randomProcessor();
                        ++migrationRequest;

                        while (!selectedToGiveBack.canHandleNextProcess()) {
                            ++migrations;
                            // we replace the processors
                            selectedProcessor.addProcess(selectedToGiveBack.removeProcess());
                        }
                    }
                }
            }

            processAll();
        }

        // output that is expected in main!
        return new ToStringMethod("Algorithm 3",
                averageCPULoad(),
                averageDeviation(),
                migrationRequest,
                migrations);

    }
}