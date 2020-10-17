package DZ;

public class PingPong {

    static volatile char current = 1;


    Object playLock = new Object();

    public void ping() throws InterruptedException {
        synchronized (playLock) {
            while (current != 1) {
                playLock.wait();
            }
            System.out.println("ping");
            Thread.sleep(100);
            current = 2;
            playLock.notifyAll();
        }
    }

    public void pong() throws InterruptedException {
        synchronized (playLock) {
            while (current != 2) {
                playLock.wait();
            }
            System.out.println("pong");
            Thread.sleep(100);
            current = 1;
            playLock.notifyAll();
        }
    }

    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        Counter c = new Counter(0);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        pingPong.ping();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.inc();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        pingPong.pong();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.inc();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(c.getC());


    }

}
