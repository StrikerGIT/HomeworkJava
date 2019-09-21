package Lesson_2;

public enum DaysOfWeek {
    MONDAY ("Понедельник", 40), TUESDAY("Вторник", 32), WEDNSDAY("Среда", 24), THURSDAY("Четверг", 16), FRIDAY("Пятничко", 8),
    SATURDAY("Суббота", 0), SUNDAY("Воскресенье", 0);

    public String rus;
    public int hours;

    DaysOfWeek(String rus, int hours) {
        this.rus = rus;
        this.hours = hours;
    }

    public String getRus() {
        return rus;
    }

    public int getHours() {
        return hours;
    }

}




