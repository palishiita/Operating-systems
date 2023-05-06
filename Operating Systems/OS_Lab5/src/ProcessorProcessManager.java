import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProcessorProcessManager {

    // Processor creating
    public static List<Processor> processorsList(List<Processor> processors) {
        List<Processor> processorList = new ArrayList<>(processors.size());
        for (Processor processor : processors) {
            processorList.add(new Processor(processor));
        }
        return processorList;
    }

    // Process creating
    public static Queue<Process> processesQueue(Process[] processes) {
        Queue<Process> processQueue = new LinkedList<>();
        for (Process process : processes)
            processQueue.add(new Process(process));
        return processQueue;
    }

}
