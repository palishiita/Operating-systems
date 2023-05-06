package PrincipleOfLocality;

@SuppressWarnings("ALL")
public class Generator {
    private final int processesCount;
    private final int pagesCount;
    private final int minimumNumberOfReferences;
    private final int maximumNumberOfReferences;
    private final int radiusOfLocality;

    public Generator(int processesCount, int pagesCount, int min, int max, int radiusOfLocality) {
        this.processesCount = processesCount;
        this.pagesCount = pagesCount;
        this.minimumNumberOfReferences = min;
        this.maximumNumberOfReferences = max;
        this.radiusOfLocality = radiusOfLocality;
    }

    public int[][] generateProcesses() {
        // first dimension are processes
        int[][] processes_array = new int[processesCount][];
        // second dimension are references to pages for process
        for (int j = 0; j < processes_array.length; j++) {
            //number of references is random from setting min and max number of references
            int howManyReferences = LocalityPrinciple.randomize(minimumNumberOfReferences, maximumNumberOfReferences);
            //creating references
            int[] referencesArray = new int[howManyReferences];
            //we have to randomize references for first elem in array (it is because we have i-1 in loop)
            referencesArray[0] = LocalityPrinciple.randomize(pagesCount / processesCount * j,
                    pagesCount / processesCount * (j + 1));
            // RULE OF LOCALITY: references of process are in specified range
            // position +- radius; but we can't have ArrayIndexOfOutBoundException
            for (int i = 1; i < howManyReferences; i++) {
                int min = Math.max(referencesArray[i - 1] - radiusOfLocality, 0);
                int max = Math.min(referencesArray[i - 1] + radiusOfLocality, pagesCount);
                referencesArray[i] = LocalityPrinciple.randomize(min, max);
            }
            //assign references to process
            processes_array[j] = referencesArray;
        }
        return processes_array;
    }

}

