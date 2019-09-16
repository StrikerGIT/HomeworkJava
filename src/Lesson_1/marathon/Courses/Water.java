package Lesson_1.marathon.Courses;

import Lesson_1.marathon.Competitors.Competitor;

class Water extends Obstacle {
    int length;

    Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}