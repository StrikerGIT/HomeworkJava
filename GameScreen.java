package com.star.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.star.app.game.GameController;
import com.star.app.game.WorldRenderer;
import com.star.app.screen.utils.Assets;

import java.sql.Statement;

public class GameScreen extends AbstractScreen {
    private GameController gameController;
    private WorldRenderer worldRenderer;
    private Stage stage;
    private BitmapFont font24;
    private State statement;

    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        Assets.getInstance().loadAssets(ScreenManager.ScreenType.GAME);
        this.gameController = new GameController();
        this.worldRenderer = new WorldRenderer(gameController, batch);
        this.stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        this.font24 = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");
        this.statement = State.RUN;
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        textButtonStyle.font = font24;
        skin.add("simpleSkin", textButtonStyle);

        Button btnPause = new TextButton("Pause", textButtonStyle);
        Button btnMenu = new TextButton("Menu", textButtonStyle);
        btnPause.setPosition(1000, 700);
        btnMenu.setPosition(1000, 600);

        btnPause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (statement == State.RUN) {
                    ScreenManager.getInstance().getGameScreen().pause();
                }
                else if (statement == State.PAUSE) {
                    ScreenManager.getInstance().getGameScreen().resume();
                }
            }});

        btnMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
            }
        });

        stage.addActor(btnPause);
        stage.addActor(btnMenu);
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        if (statement == State.RUN) {
            if (gameController.getHero().getHp() <= 0) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAMEOVER);
            }
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            gameController.update(delta);
            worldRenderer.render();
            stage.act(delta);
        }
    }

    @Override
    public void pause() {
        statement = State.PAUSE;
    }

    @Override
    public void resume() {
        statement = State.RUN;
    }

    @Override
    public void dispose() {
        gameController.dispose();
    }
}
