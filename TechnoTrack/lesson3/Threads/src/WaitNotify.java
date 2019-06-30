public class WaitNotify {
    private static class Producer implements Runnable {
        private final Message msg;

        private Producer(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            msg.send();
        }
    }

    private static class Consumer implements Runnable {
        private final Message msg;

        private Consumer(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            msg.receive();
        }
    }

    private static class Message {
        private boolean isSent = false;


        synchronized void send() {
            isSent = true;
            System.out.println("sent");
            notify();
        }

        synchronized void receive() {
            while (true) {
                try {
                    wait();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("received");
                isSent = false;
                notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Message msg = new Message();

        Thread producer = new Thread(new Producer(msg));
        Thread producer2 = new Thread(new Producer(msg));
        Thread producer3 = new Thread(new Producer(msg));
        Thread consumer = new Thread(new Consumer(msg));

        consumer.start();
        Thread.sleep(2000);
        producer.start();
        producer2.start();
//        producer2.join();
        producer3.start();
    }
}
