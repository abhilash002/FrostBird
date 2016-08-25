package com.mbolt.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mbolt.fbhelpers.AssetLoader;

/**
 * Created by Abhilash on 21-02-2016.
 */
    public class Bird {

    private Circle boundingCircle;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private boolean isAlive;
    private float originalY;
    private float rotation;
    private int width , height;

    public Bird(float x,float y,int width,int height){
        this.width = width;
        this.height = height;
        this.originalY = y;
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
        isAlive = true;
    }

    public void update(float delta){
        velocity.add(acceleration.cpy().scl(delta));
        if(velocity.y > 200){
           velocity.y = 200;}

        // CEILING CHECK
        if (position.y < -13) {
            position.y = -13;
            velocity.y = 0;
        }
        position.add(velocity.cpy().scl(delta));

        boundingCircle.set(position.x + 9, position.y + 6,6.5f);

        if(velocity.y < 0 ){
            rotation -= 600 * delta;
            if(rotation < -20){
                rotation = -20;
            }
        }

        if(isFalling() || !isAlive){
            rotation += 480 * delta;
            if(rotation > 90){
                rotation = 90;
            }
        }
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void onClick() {
        if (isAlive) {
            AssetLoader.flap.play();
            velocity.y = -140;
        }
    }

    public void updateReady(float runTime) {
        position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public boolean isFalling(){
        return velocity.y > 110;
    }

    public boolean shouldntFlap(){
        return velocity.y > 70 || !isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
        velocity.y = 0;
    }


    public void decelerate() {
        // We want the bird to stop accelerating downwards once it is dead.
        acceleration.y = 0;
    }

    public void onRestart(int y){
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }
}
