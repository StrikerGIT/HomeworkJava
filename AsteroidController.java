package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.star.app.game.helpers.ObjectPool;
import com.star.app.screen.ScreenManager;

public class AsteroidController extends ObjectPool<Asteroid> {
    private Texture asteroidTexture;
    private GameController gc;
    private int countAsteroids;

    @Override
    protected Asteroid newObject() {
        return new Asteroid();
    }

    public AsteroidController(GameController gc) {
        this.asteroidTexture = new Texture("asteroid.png");
        this.countAsteroids = 10;
        createAsteroids(countAsteroids);
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Asteroid a = activeList.get(i);
            batch.draw(asteroidTexture, a.getPosition().x - 128, a.getPosition().y - 128, 128, 128, 256, 256, 1, 1, 0, 0, 0, 256, 256, false, false);
        }
    }

    public void createAsteroids(int count){
        for (int i = 0; i < count; i++) {
            setup((ScreenManager.SCREEN_WIDTH + 200), MathUtils.random(-200, ScreenManager.SCREEN_HEIGHT + 200), MathUtils.random(200, 800), MathUtils.random(-600, 800));
        }
    }

    public void setup(float x, float y, float vx, float vy) {
        getActiveElement().activate(x, y, vx, vy);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
        if(activeList.size() == 0) {
            createAsteroids(countAsteroids);
        }
    }
}
