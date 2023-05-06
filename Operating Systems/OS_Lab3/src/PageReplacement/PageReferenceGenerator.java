package PageReplacement;

import java.util.Random;

// locality principle
public class PageReferenceGenerator {

    static int min = 1;
    static int max = 20;


    public int generatePage() {
        Random random = new Random();
        return random.nextInt(max) + min;
    }
}