package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;
import com.star.app.screen.utils.Assets;

public class Bot implements Poolable{

    private GameController gc;
    private TextureRegion texture;
    private Vector2 position;
    private Vector2 velocity;
    private int hpMax;
    private int hp;
    private float scale;
    private boolean active;
    private Circle hitArea;

    private final float BASE_SIZE = 64.0f;
    private final float BASE_RADIUS = BASE_SIZE / 2.0f;

    public int getHpMax() {
        return hpMax;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Circle getHitArea() {
        return hitArea;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getScale() {
        return scale;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public Bot(GameController gc) {
        this.gc = gc;
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.hitArea = new Circle(0, 0, 0);
        this.active = false;
        this.texture = Assets.getInstance().getAtlas().findRegion("ship");
    }

    public boolean takeDamage(int amount) {
        hp -= amount;
        if (hp <= 0) {
            deactivate();
            return true;
        }
        return false;
    }

    public void activate(float x, float y, float vx, float vy, float scale) {
        this.position.set(x, y);
        this.velocity.set(vx, vy);
        if (this.velocity.len() < 50.0f) {
            this.velocity.nor().scl(50.0f);
        }
        this.hpMax = (int) ((10 + gc.getLevel() * 4) * scale);
        this.hp = this.hpMax;
        this.hitArea.setPosition(position);
        this.active = true;
        this.scale = scale;
        this.hitArea.setRadius(BASE_RADIUS * scale * 0.9f);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 32, position.y - 32, 32, 32, 64, 64, scale, scale,0);
        if(position.x > GameController.SPACE_WIDTH - ScreenManager.HALF_SCREEN_WIDTH) {
            batch.draw(texture, position.x - 32 - GameController.SPACE_WIDTH, position.y - 32, 32, 32, 64, 64, scale, scale,0);
        }
        if(position.x < ScreenManager.HALF_SCREEN_WIDTH) {
            batch.draw(texture, position.x - 32 + GameController.SPACE_WIDTH, position.y - 32, 32, 32, 64, 64, scale, scale,0);
        }
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < -BASE_RADIUS * scale) {
            position.x = GameController.SPACE_WIDTH + BASE_RADIUS * scale;
        }
        if (position.x > GameController.SPACE_WIDTH + BASE_RADIUS * scale) {
            position.x = -BASE_RADIUS * scale;
        }
        if (position.y < -BASE_RADIUS * scale) {
            position.y = GameController.SPACE_HEIGHT + BASE_RADIUS * scale;
        }
        if (position.y > GameController.SPACE_HEIGHT + BASE_RADIUS * scale) {
            position.y = -BASE_RADIUS * scale;
        }
        hitArea.setPosition(position);
    }
}
