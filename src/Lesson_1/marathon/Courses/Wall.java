package Lesson_1.marathon.Courses;

import Lesson_1.marathon.Competitors.Competitor;

class Wall extends Obstacle {
    int height;

    Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}