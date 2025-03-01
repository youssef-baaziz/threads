import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Talkative implements Runnable {
    private int number;

    public Talkative(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println((i+1) + " - Thread " + Thread.currentThread().getId() + ": " + number);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Create 10 Talkative tasks
        for (int i = 0; i < 10; i++) {
            Talkative talkative = new Talkative(i + 1);
            executorService.submit(talkative);
        }

        executorService.shutdown();
    }
}
