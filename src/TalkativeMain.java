class Talkative implements Runnable {
    private final int id;

    public Talkative(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread " + id + " - Message " + i);
        }
    }
}

public class TalkativeMain {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Talkative(i));
            thread.start();
        }
    }
}
