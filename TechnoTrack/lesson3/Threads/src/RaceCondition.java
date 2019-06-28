public class RaceCondition {
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

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                inc.syncAct(finalI);
            }).start();
        }
    }
}
