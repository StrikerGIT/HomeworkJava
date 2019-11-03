import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static AtomicInteger WINNER = new AtomicInteger(0);;
    public static CyclicBarrier CB;
    public static CountDownLatch CDL_READY ;
    public static CountDownLatch CDL_FINISH;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            CDL_READY.countDown();
            CB.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (WINNER.addAndGet(1) == 1){
            System.out.println("//////////////////////////////////////////////////////////////");
            System.out.println("В гонке побеждает  " + name);
            System.out.println("//////////////////////////////////////////////////////////////");
        }
        CDL_FINISH.countDown();
    }
}
