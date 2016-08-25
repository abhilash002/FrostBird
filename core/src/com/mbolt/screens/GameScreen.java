package com.mbolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mbolt.gameworld.GameRenderer;
import com.mbolt.gameworld.GameWorld;
import com.mbolt.fbhelpers.InputHandler;

/**
 * Created by Abhilash on 21-02-2016.
 */
public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private GameRenderer gamerenderer;
    private float runtime;

    public GameScreen(){

        float screenWidth =  Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = (screenHeight) / (screenWidth / gameWidth);

        int midPointY = (int)( gameHeight / 2 );

        gameWorld = new GameWorld(midPointY);
        Gdx.input.setInputProcessor(new InputHandler(gameWorld,screenWidth/gameWidth, screenHeight/gameHeight));
        gamerenderer = new GameRenderer(gameWorld, (int)gameHeight,midPointY);
        gameWorld.setRenderer(gamerenderer);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runtime += delta;
        gameWorld.update(delta);
        gamerenderer.render(delta, runtime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
