import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@SuppressWarnings("ALL")
public class Processor {

    private final double minLoad;
    private final double maxLoad;
    private List<Process> processes;
    private double currentLoad;
    private int time;
    private Map<Integer, Double> loadInTime;

    public Processor(double minLoad, double maxLoad) {
        this.minLoad = minLoad;
        this.maxLoad = maxLoad;
        this.processes = new ArrayList<>();
        this.loadInTime = new HashMap<>();
    }

    public Processor(Processor processor) {
        this(processor.minLoad, processor.maxLoad);
    }

    public void addProcess(Process process) {
        processes.add(process);
        currentLoad = currentLoad + process.getProcessorLoad();
    }

    // important!!! we are checking the load
    // load queries depends on these conditions!
    public boolean canHandleNextProcess() {
        return currentLoad < maxLoad; // load < p
    }
    public boolean isBelowMinimumLoad() {
        return currentLoad <= minLoad; // load < r
    }

    // put every process
    public void process() {
        loadInTime.put(++time, currentLoad);
        processes.forEach(Process::reduceTime);
        cleanEmptyProcesses();
    }


    public double averageLoad() {
        // total load for each unit of time
        double totalLoad = loadInTime.values().stream().reduce(Double::sum)
                .orElseThrow(IllegalStateException::new);
        // divide by the number of measurements
        return totalLoad / loadInTime.size();
    }

    public double averageDeviation() {
        // average load
        final double mean = averageLoad();
        // load over time
        double deviation = loadInTime.values().stream()
                // for each load value we take its distance from the average load and square it
                .mapToDouble(load -> pow(load - mean, 2))
                // sum it up
                .sum();
        // and divide by the number of measurements
        double averageDeviation = deviation / loadInTime.size();
        // we return the square root of this
        return sqrt(averageDeviation);
    }

    // removing failed process!! it exceeds 100% CPU
    private void cleanEmptyProcesses() {
        for(Iterator<Process> iterator = processes.iterator(); iterator.hasNext();) {
            Process process = iterator.next();
            if (process.isFinished()) {
                currentLoad -= process.getProcessorLoad();
                iterator.remove();
            }
        }
    }
    public Process removeProcess() {
        if(processes.size() > 0) {
            // sort by load
            processes.sort(Comparator.comparingDouble(Process::getProcessorLoad));
            this.currentLoad=this.currentLoad-processes.get(0).getProcessorLoad();
            // remove the first
            return processes.remove(0);
        }
        else
            return Process.EMPTY;
    }
}