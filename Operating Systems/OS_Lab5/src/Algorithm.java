import java.util.List;
import java.util.Queue;
import java.util.Random;

import static java.util.Collections.unmodifiableList;

// all the methods that all algorithms must return result for

public abstract class Algorithm {

    // fields
    protected final List<Processor> processors;
    protected final Random random;
    // constructor
    public Algorithm(List<Processor> processors) {
        this.processors = unmodifiableList(processors);
        this.random = new Random();
    }

    public abstract ToStringMethod process(Queue<Process> processes);
    // standard deviation = sqrt(sum(load - fixedLoadInTime)/numOfProcessors)
    // sum of all averageDeviation / size of processor
    protected double averageDeviation() {
        return processors.stream().mapToDouble(Processor::averageDeviation).sum() / processors.size();
    }
    protected double averageCPULoad() {
        // sum
        return processors.stream().mapToDouble(Processor::averageLoad).sum() / processors.size();
    }
    // allocate processor (minLoad and maxLoad) to each process
    protected void processAll() {
        processors.forEach(Processor::process);
    }
    protected Processor randomProcessor() {
        return processors.get(random.nextInt(processors.size()));
    }

}