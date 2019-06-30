public class ThreadJoin {
    private static class Incrementation {
        int count;
        private Object lock = new Object();

        void syncAct(int d) {
            synchronized (lock) {
                act(d);
            }
        }

        void act(int d) {
            for (int i = 0; i < 10; i++) {
                System.out.println(d + ": " + count++);
            }
        }
    }

    public static void main(String[] args) {
        Incrementation inc = new Incrementation();
        Thread t1 = new Thread(() -> {
            inc.act(1);
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inc.syncAct(2);
        });
        Thread t3 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inc.syncAct(3);
        });
        Thread t4 = new Thread(() -> {
            try {
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inc.syncAct(4);
        });
        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inc.syncAct(5);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
