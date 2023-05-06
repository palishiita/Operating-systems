package PrincipleOfLocality;
import java.util.Arrays;
import java.util.Random;

public class LocalityPrinciple {
    public static int[][] cloneArray(int[][] array) {
        return Arrays.stream(array).map(int[]::clone).toArray(int[][]::new); // copy array
    }
    public static int randomize(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}