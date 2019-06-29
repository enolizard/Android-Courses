public class MonitorWork {
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        System.out.println("start main thread");
        new Thread(() -> {
            System.out.println("start first thread");
            sendData();
        }).start();

        new Thread(() -> {
            System.out.println("start second thread");
            prepareData();
        }).start();
        System.out.println("finish main thread");
    }

    private static void sendData() {
        synchronized (monitor) {
            System.out.println("waiting data...");
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sending data...");
        }
    }

    private static void prepareData() {
        synchronized (monitor) {
            System.out.println("data prepared");
            monitor.notify();
        }
    }
}
