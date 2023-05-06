import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("ALL")
public class Algorithm_Maximum extends Algorithm {

    /**
     ** first we check the currentLoad < p then, processor selection happens **

     * If the load on processor x exceeds maximum threshold p [currentLoad > p],
     * the process is sent to a randomly selected processor y with currentLoad less than p [currentLoad < p]
     * if the drawn y has currentLoad > p, new processor is drawn and repeated until successful
     * If it does not succeed - the process executes on x.
    **/


    public Algorithm_Maximum(List<Processor> processors) {
        super(processors);
    }

    @Override
    public ToStringMethod process(Queue<Process> processes) {

        int migrationRequest = 0;
        int migrations = 0;

        while (!processes.isEmpty()) {
            // get the first process
            Process process = processes.poll();
            // get random processor
            Processor processor = randomProcessor();
            ProcessResponse response = findProcessorAndProcess(process, processor);
            // assign the number of requests for migration
            migrationRequest += response.migrationRequests;
            // and the actual number of migrations
            migrations += response.migrations;
            processAll();
        }

        // output that is expected in main!
        return new ToStringMethod("Algorithm 2",
                averageCPULoad(),
                averageDeviation(),
                migrationRequest,
                migrations);

    }

    protected ProcessResponse findProcessorAndProcess(Process process, Processor processor) {

        // for a given cpu and process
        int migrationRequest = 0;
        int migrations = 0;
        Processor selectedProcessor = processor;

        // if the process is empty, we don't have to worry -> we operate
        if (process.isEmpty()) {
            processAll();
        }

        // the case where the process can be handled
        else if (processor.canHandleNextProcess()) {
            processor.addProcess(process);
        }

        else {

            // all processors
            List<Processor> processorsToPeek = new ArrayList<>(processors);
            // the process is not handled
            boolean processHandled = false;

            // as long as the processors are available and the process is not handled
            while (!processorsToPeek.isEmpty() && !processHandled) {
                // random processor
                int index = random.nextInt(processorsToPeek.size());
                Processor newProcessor = processorsToPeek.get(index);
                ++migrationRequest;

                // if it can handle that great
                if (newProcessor.canHandleNextProcess()) {
                    ++migrations;
                    selectedProcessor = newProcessor;
                    newProcessor.addProcess(process);
                    processHandled = true;
                }

                // it can't, then we remove it from the list and continue randomizing
                else
                    processorsToPeek.remove(index);
            }
        }

        return new ProcessResponse(migrationRequest, migrations, selectedProcessor);
    }

    protected static class ProcessResponse {

        protected final int migrationRequests;
        protected final int migrations;
        protected final Processor processor;

        public ProcessResponse(int migrationRequests, int migrations, Processor processor) {
            this.migrationRequests = migrationRequests;
            this.migrations = migrations;
            this.processor = processor;
        }
    }

}
// 50
// 0.8
// 0.2
// 10
// 200