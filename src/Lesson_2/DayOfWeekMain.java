package Lesson_2;

public class DayOfWeekMain {

    public static void main(final String[] args) {
        for (DaysOfWeek d: DaysOfWeek.values()) {
            getWorkingHours(d);
        }
    }
    public static void getWorkingHours (DaysOfWeek day){
        System.out.println("Сейчас " + day.getRus() + ". Количество часов, которые нужно отработать на этой неделе - " + day.getHours() + "!");
    }
}
