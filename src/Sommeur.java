import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Sommeur implements Runnable {
    private int[] array;
    private int start, end;
    private int sum = 0;

    public Sommeur(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
    }

    public int getSomme() {
        return sum;
    }

    public static void main(String[] args) {
        int[] array = new int[20000];

        // Initialize the array with some values
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3000);
        Sommeur[] sommeurs = new Sommeur[3000];
        int partSize = array.length / 3000;

        // Submit tasks to the thread pool
        for (int i = 0; i < 3000; i++) {
            int start = i * partSize;
            int end = (i == 3000 - 1) ? array.length : (i + 1) * partSize;
            sommeurs[i] = new Sommeur(array, start, end);
            executorService.submit(sommeurs[i]);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Calculate the total sum
        int totalSum = 0;
        for (Sommeur sommeur : sommeurs) {
            totalSum += sommeur.getSomme();
        }

        // Display the total sum
        System.out.println("Total sum of the array: " + totalSum);
    }
}
