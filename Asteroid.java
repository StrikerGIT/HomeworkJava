package com.star.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class Asteroid {

    private StarGame game;
    private Texture textureAsteroid;
    private Vector2 position;
    private Vector2 move;
    private float angle;
    public Asteroid(StarGame game) {
        this.game = game;
        this.textureAsteroid = new Texture("asteroid.png");
        this.position = new Vector2((ScreenManager.SCREEN_WIDTH + 200), MathUtils.random(-200, ScreenManager.SCREEN_HEIGHT + 200));
        this.move = new Vector2(-90.0f, -270.0f);
        this.angle =  MathUtils.random(120.0f,220.0f);
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureAsteroid, position.x - 128, position.y - 128, 128, 128, 256, 256, 1, 1, 0, 0, 0, 256, 256, false, false);
    }

    public void update(float dt) {
            position.x -= (float) Math.cos(Math.toRadians(angle)) * move.x * dt;
            position.y -= (float) Math.sin(Math.toRadians(angle)) * move.y * dt;
        if (position.x < -100.0f) {
            position.x = ScreenManager.SCREEN_WIDTH +  100;
        }
        if (position.y < -100.0f) {
            position.y = ScreenManager.SCREEN_HEIGHT + 100;
        }
        else if (position.y > ScreenManager.SCREEN_HEIGHT + 100.0f) {
            position.y = 100.0f;
        }
    }

}
