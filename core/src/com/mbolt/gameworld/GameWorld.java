package com.mbolt.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mbolt.fbhelpers.AssetLoader;
import com.mbolt.gameobjects.Bird;
import com.mbolt.gameobjects.ScrollHandler;


/**
 * Created by Abhilash on 21-02-2016.
 */

public class GameWorld {

    public int midPointY;
    private int score =0;
    private Rectangle ground;
    private Bird bird;
    private ScrollHandler scroller;
    private float runTime = 0;
    private GameRenderer gameRenderer;

    public void setRenderer(GameRenderer gamerenderer) {
        this.gameRenderer = gamerenderer;
    }

    public enum GameState {

       MENU, READY, RUNNING, GAMEOVER, HIGHSCORE

    }
    private GameState currentState;

    public GameWorld(int midPointY){

        currentState = GameState.MENU;
        this.midPointY = midPointY;
        // Initialize bird here
        bird = new Bird(33, midPointY- 5, 17, 12);
        scroller = new ScrollHandler(this,midPointY + 66);

        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void updateRunning(float delta) {
        if(delta > .15f){
            delta = .15f;
        }
        bird.update(delta);
        scroller.update(delta);

        if(scroller.collides(bird) && bird.isAlive()){
            // Clean up on game over
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
            gameRenderer.prepareTransition(255, 255, 255, .3f);
            AssetLoader.fall.play();
        }
        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            if(bird.isAlive()){
                AssetLoader.dead.play();

                gameRenderer.prepareTransition(255, 255, 255, .3f);
                bird.die();
            }

            scroller.stop();
            bird.decelerate();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }
    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    public void updateReady(float delta){
        bird.updateReady(runTime);
        scroller.updateReady(delta);
    }

    public Bird getBird() {
        return bird;
    }

    public int getMidPointY() {
        return midPointY;
    }

    public ScrollHandler getScroller(){
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment){
        score += increment;
    }

    public void ready() {
        currentState = GameState.READY;
        gameRenderer.prepareTransition(0, 0, 0, 1f);
    }

    public boolean isReady(){
        return (currentState == GameState.READY);
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart(){
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        ready();
    }

    public boolean isGameOver(){
        return currentState == GameState.GAMEOVER;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

}
