package com.star.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;


public class Asteroid implements Poolable {

    private Vector2 position;
    private Vector2 move;
    private float angle;
    private boolean active;


    public Asteroid() {
        this.angle = MathUtils.random(30,90.0f);
        this.position = new Vector2(0, 0);
        this.move = new Vector2(0, 0);
        this.active = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void activate(float x, float y, float vx, float vy) {
        position.set(x, y);
        move.set(vx, vy);
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public void update(float dt) {
        if (active) {
            position.x -= (float) Math.cos(Math.toRadians(angle)) * move.x * dt;
            position.y -= (float) Math.sin(Math.toRadians(angle)) * move.y * dt;
            if ((position.x < -100.0f)||(position.x > 1500.0f)) {
                position.x = ScreenManager.SCREEN_WIDTH + 100;
            }
            if (position.y < -100.0f) {
                position.y = ScreenManager.SCREEN_HEIGHT + 100;
            } else if (position.y > ScreenManager.SCREEN_HEIGHT + 100.0f) {
                position.y = 100.0f;
            }
        }
    }

}
