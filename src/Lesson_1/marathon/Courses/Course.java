package Lesson_1.marathon.Courses;

import Lesson_1.marathon.Competitors.Team;
import Lesson_1.marathon.Competitors.Competitor;

public class Course {
    String name;
    Obstacle[] course = {new Cross(80), new Wall(2), new Wall(25), new Cross(120), new Water(50)};

    public Course(String courseName){
        name = courseName;
    }

    public void start(Team team){
        for (Competitor c : team.ArrayOfCompetitors) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }

    }


}
