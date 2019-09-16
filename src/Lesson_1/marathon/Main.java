package Lesson_1.marathon;

import Lesson_1.marathon.Competitors.Team;
import Lesson_1.marathon.Courses.Course;

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Олимпийцы"); // Создаем команду
        team.InfoTeam();

        Course course = new Course("Полоса препятствий № 1"); // Создаем полосу препятствий
        course.start(team);

        team.InfoResult();
    }
}
