/*
  A random number generator isolated to the current thread.
  Like the global Random generator used by the Math class, a ThreadLocalRandom is initialized
  with an internally generated seed that may not otherwise be modified.
  When applicable, use of ThreadLocalRandom rather than shared Random objects in concurrent programs will
  typically encounter much less overhead and contention.
  Use of ThreadLocalRandom is particularly appropriate when multiple tasks use random numbers in parallel in thread pools.
 */

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ALL")
public class ProcessGenerator {

    // fields
    private final double minLoad;
    private final double maxLoad;
    private final int minTime;
    private final int maxTime;

    // constructor using these fields
    public ProcessGenerator(double minLoad, double maxLoad, int minTime, int maxTime) {
        this.minLoad = minLoad;
        this.maxLoad = maxLoad;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    // generate a process
    public Process[] generate(int n) {
        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++)
            processes[i] = new Process(randomLoad(), randomTime());
        return processes;
    }

    // time and load generation
    public int randomTime() {
        return ThreadLocalRandom.current().nextInt(minTime, maxTime + 1);
    }
    public double randomLoad() {
        return ThreadLocalRandom.current().nextDouble(minLoad, maxLoad);
    }

}