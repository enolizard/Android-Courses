import java.util.concurrent.CountDownLatch;

public class Locks {
    private static class Generator {
        private final CountDownLatch latch;

        public Generator(CountDownLatch latch) {
            this.latch = latch;
        }

        private void generate() {
            System.out.println("generated");
            latch.countDown();
        }

        private void stop() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("stopped");
        }
    }

    public static void main(String[] args) {
        latchDemo();
    }

    private static void latchDemo() {
        int count = 6;
        Generator generator = new Generator(new CountDownLatch(count));

        Thread t1 = new Thread(() -> generator.stop());

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                generator.generate();
            }
        });

        t2.start();
        t1.start();

    }
}
