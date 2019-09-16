package Lesson_1.marathon.Courses;

import Lesson_1.marathon.Competitors.Competitor;


class Cross extends Obstacle {
    int length;

    Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}