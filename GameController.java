package com.star.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.star.app.screen.ScreenManager;
import com.star.app.screen.utils.Assets;

import static java.lang.Math.*;

public class GameController {
    public static final int SPACE_WIDTH = 9600;
    public static final int SPACE_HEIGHT = 5400;

    private Music music;
    private int level;
    private Background background;
    private AsteroidController asteroidController;
    private BulletController bulletController;
    private ParticleController particleController;
    private PowerUpsController powerUpsController;
    private BotController botController;
    private Hero hero;
    private Vector2 tmpVec;
    private Stage stage;

    private float msgTimer;
    private String msg;

    public float getMsgTimer() {
        return msgTimer;
    }

    public String getMsg() {
        return msg;
    }

    public Stage getStage() {
        return stage;
    }

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public Background getBackground() {
        return background;
    }

    public PowerUpsController getPowerUpsController() {
        return powerUpsController;
    }

    public ParticleController getParticleController() {
        return particleController;
    }

    public BotController getBotController() {
        return botController;
    }

    public Hero getHero() {
        return hero;
    }

    public int getLevel() {
        return level;
    }

    public GameController(SpriteBatch batch) {
        this.background = new Background(this);
        this.hero = new Hero(this, "PLAYER1");
        this.asteroidController = new AsteroidController(this);
        this.bulletController = new BulletController(this);
        this.particleController = new ParticleController();
        this.powerUpsController = new PowerUpsController(this);
        this.botController = new BotController(this);
        this.tmpVec = new Vector2(0.0f, 0.0f);
        this.stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        this.stage.addActor(hero.getShop());
        this.level = 1;
        Gdx.input.setInputProcessor(stage);
        generateTwoBigAsteroids();
        generateBots();
        this.msg = "Level 1";
        this.msgTimer = 3.0f;
        this.music = Assets.getInstance().getAssetManager().get("audio/Music.mp3");
        this.music.setLooping(true);
        this.music.play();
    }

    public void generateTwoBigAsteroids() {
        for (int i = 0; i < 200; i++) {
            this.asteroidController.setup(MathUtils.random(0, GameController.SPACE_WIDTH), MathUtils.random(0, GameController.SPACE_HEIGHT),
                    MathUtils.random(-150.0f, 150.0f), MathUtils.random(-150.0f, 150.0f), 0.6f);
        }
    }

    public void generateBots() {
        for (int i = 0; i < 5 * level; i++) {
            this.botController.setup(MathUtils.random(0, GameController.SPACE_WIDTH), MathUtils.random(0, GameController.SPACE_HEIGHT),
                    MathUtils.random(-150.0f, 150.0f), MathUtils.random(-150.0f, 150.0f), 1f);
        }
    }

    public void update(float dt) {
        msgTimer -= dt;
        background.update(dt);
        hero.update(dt);
        asteroidController.update(dt);
        bulletController.update(dt);
        particleController.update(dt);
        powerUpsController.update(dt);
        botController.update(dt);
        botUpdateDirection();
        checkCollisions();
        if (!hero.isAlive()) {
            ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAMEOVER, hero);
        }
        if (asteroidController.getActiveList().size() == 0) {
            level++;
            generateTwoBigAsteroids();
            generateBots();
        }
        stage.act(dt);
    }

    public void botUpdateDirection()   {
        for (int k = 0; k < botController.getActiveList().size(); k++) {
            Bot bot = botController.getActiveList().get(k);
            float vx = (hero.getPosition().x - bot.getPosition().x) / 3;
            float vy = (hero.getPosition().y - bot.getPosition().y) / 3;
            if (vx < 100f){vx += signum(vx) * 200f;}
            if (vy < 100f){vy += signum(vy) * 200f;}
            bot.getVelocity().set(vx,vy);
        }
    }


    public void hit(Hero h, Asteroid a) {
        // h - 1
        // a - 2
        float v1 = h.getVelocity().len();
        float v2 = a.getVelocity().len();

        float m1 = 0.1f;
        float m2 = a.getScale();

        float th1 = h.getVelocity().angleRad();
        float th2 = a.getVelocity().angleRad();

        float phi1 = tmpVec.set(a.getPosition()).sub(h.getPosition()).angleRad();
        float phi2 = tmpVec.set(h.getPosition()).sub(a.getPosition()).angleRad();

        float v1xN = (float) (((v1 * cos(th1 - phi1) * (m1 - m2) + 2 * m2 * v2 * cos(th2 - phi1)) / (m1 + m2)) * cos(phi1) + v1 * sin(th1 - phi1) * cos(phi1 + PI / 2.0f));
        float v1yN = (float) (((v1 * cos(th1 - phi1) * (m1 - m2) + 2 * m2 * v2 * cos(th2 - phi1)) / (m1 + m2)) * sin(phi1) + v1 * sin(th1 - phi1) * sin(phi1 + PI / 2.0f));

        float v2xN = (float) (((v2 * cos(th2 - phi2) * (m2 - m1) + 2 * m1 * v1 * cos(th1 - phi2)) / (m2 + m1)) * cos(phi2) + v2 * sin(th2 - phi2) * cos(phi2 + PI / 2.0f));
        float v2yN = (float) (((v2 * cos(th2 - phi2) * (m2 - m1) + 2 * m1 * v1 * cos(th1 - phi2)) / (m2 + m1)) * sin(phi2) + v2 * sin(th2 - phi2) * sin(phi2 + PI / 2.0f));

        h.getVelocity().set(v1xN, v1yN);
        a.getVelocity().set(v2xN, v2yN);
    }

    public void hitBot(Hero h, Bot a) {
        // h - 1
        // a - 2
        float v1 = h.getVelocity().len();
        float v2 = a.getVelocity().len();

        float m1 = 0.1f;
        float m2 = a.getScale();

        float th1 = h.getVelocity().angleRad();
        float th2 = a.getVelocity().angleRad();

        float phi1 = tmpVec.set(a.getPosition()).sub(h.getPosition()).angleRad();
        float phi2 = tmpVec.set(h.getPosition()).sub(a.getPosition()).angleRad();

        float v1xN = (float) (((v1 * cos(th1 - phi1) * (m1 - m2) + 2 * m2 * v2 * cos(th2 - phi1)) / (m1 + m2)) * cos(phi1) + v1 * sin(th1 - phi1) * cos(phi1 + PI / 2.0f));
        float v1yN = (float) (((v1 * cos(th1 - phi1) * (m1 - m2) + 2 * m2 * v2 * cos(th2 - phi1)) / (m1 + m2)) * sin(phi1) + v1 * sin(th1 - phi1) * sin(phi1 + PI / 2.0f));

        float v2xN = (float) (((v2 * cos(th2 - phi2) * (m2 - m1) + 2 * m1 * v1 * cos(th1 - phi2)) / (m2 + m1)) * cos(phi2) + v2 * sin(th2 - phi2) * cos(phi2 + PI / 2.0f));
        float v2yN = (float) (((v2 * cos(th2 - phi2) * (m2 - m1) + 2 * m1 * v1 * cos(th1 - phi2)) / (m2 + m1)) * sin(phi2) + v2 * sin(th2 - phi2) * sin(phi2 + PI / 2.0f));

        h.getVelocity().set(v1xN, v1yN);
        a.getVelocity().set(v2xN, v2yN);
    }

    public void checkCollisions() {
        for (int i = 0; i < asteroidController.getActiveList().size(); i++) {
            Asteroid a = asteroidController.getActiveList().get(i);
            if (a.getHitArea().overlaps(hero.getHitArea())) {
                float dst = a.getPosition().dst(hero.getPosition());
                float halfOverLen = (a.getHitArea().radius + hero.getHitArea().radius - dst) / 2.0f;
                tmpVec.set(hero.getPosition()).sub(a.getPosition()).nor();
                hero.getPosition().mulAdd(tmpVec, halfOverLen);
                a.getPosition().mulAdd(tmpVec, -halfOverLen);
                hit(hero, a);
                if (a.takeDamage(2)) {
                    hero.addScore(a.getHpMax() * 10);
                }
                hero.takeDamage(2);
            }
        }
        for (int i = 0; i < botController.getActiveList().size(); i++) {
            Bot bot = botController.getActiveList().get(i);
            if (bot.getHitArea().overlaps(hero.getHitArea())) {
                float dst = bot.getPosition().dst(hero.getPosition());
                float halfOverLen = (bot.getHitArea().radius + hero.getHitArea().radius - dst) / 2.0f;
                tmpVec.set(hero.getPosition()).sub(bot.getPosition()).nor();
                hero.getPosition().mulAdd(tmpVec, halfOverLen);
                bot.getPosition().mulAdd(tmpVec, -halfOverLen);
                hitBot(hero, bot);
                if (bot.takeDamage(2)) {
                    hero.addScore(bot.getHpMax() * 100);
                }
                hero.takeDamage(2);
            }
        }

        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int j = 0; j < asteroidController.getActiveList().size(); j++) {
                Asteroid a = asteroidController.getActiveList().get(j);

                if (a.getHitArea().contains(b.getPosition())) {

                    particleController.setup(
                            b.getPosition().x + MathUtils.random(-4, 4), b.getPosition().y + MathUtils.random(-4, 4),
                            b.getVelocity().x * -0.3f + MathUtils.random(-30, 30), b.getVelocity().y * -0.3f + MathUtils.random(-30, 30),
                            0.2f,
                            2.2f, 1.7f,
                            1.0f, 1.0f, 1.0f, 1.0f,
                            0.0f, 0.0f, 1.0f, 0.0f
                    );

                    b.deactivate();
                    if (a.takeDamage(1)) {
                        hero.addScore(a.getHpMax() * 100);
                        for (int k = 0; k < 3; k++) {
                            powerUpsController.setup(a.getPosition().x, a.getPosition().y, a.getScale() / 4.0f);
                        }
                    }
                    break;
                }
            }
            for (int k = 0; k < botController.getActiveList().size(); k++) {
                Bot bot = botController.getActiveList().get(k);

                if (bot.getHitArea().contains(b.getPosition())) {
                    b.deactivate();
                    if (bot.takeDamage(1)) {
                        hero.addScore(bot.getHpMax() * 1000);
                        for (int l = 0; l < 3; l++) {
                            powerUpsController.setup(bot.getPosition().x, bot.getPosition().y, bot.getScale());
                        }
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < powerUpsController.getActiveList().size(); i++) {
            PowerUp p = powerUpsController.getActiveList().get(i);
            if (hero.getHitArea().contains(p.getPosition())) {
                hero.consume(p);
                particleController.getEffectBuilder().takePowerUpEffect(p.getPosition().x, p.getPosition().y, p.getType().index);
                p.deactivate();
            }
        }
    }

    public void dispose() {
        background.dispose();
    }
}