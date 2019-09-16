package Lesson_1.marathon.Competitors;

public interface Competitor {
    void run(int dist);
    void swim(int dist);
    void jump(int height);
    boolean isOnDistance();
    void infoName();
    void infoResult();
}