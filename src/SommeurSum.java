import java.util.concurrent.*;

class Sommeur implements Callable<Integer> {
    private final int[] array;
    private final int start, end;

    public Sommeur(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }
}

public class SommeurSum {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Filling the array with numbers 1 to 10000
        }

        int numThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        Future<Integer>[] futures = new Future[numThreads];

        int chunkSize = array.length / numThreads;
        int totalSum = 0;

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;
            futures[i] = executor.submit(new Sommeur(array, start, end));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        for (Future<Integer> future : futures) {
            totalSum += future.get();
        }

        System.out.println("Total Sum: " + totalSum);
    }
}
