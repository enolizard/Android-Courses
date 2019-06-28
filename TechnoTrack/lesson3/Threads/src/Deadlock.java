public class Deadlock {
    private static class PingPong {
        synchronized void ping(PingPong pp) {
            System.out.println("ping");
            pp.pong(this);
        }

        synchronized void pong(PingPong pp) {
            System.out.println("pong");
            pp.ping(this);
        }
    }

    public static void main(String[] args) {
        PingPong pong = new PingPong();
        PingPong ping = new PingPong();

        Thread t1 = new Thread(() -> {
            pong.pong(ping);
        });

        Thread t2 = new Thread(() -> {
            ping.ping(pong);
        });

        t1.start();
        t2.start();
    }
}
