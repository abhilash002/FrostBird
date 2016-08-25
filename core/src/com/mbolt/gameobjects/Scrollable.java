package com.mbolt.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Abhilash on 23-02-2016.
 */
public class Scrollable {
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrollLeft;

    public Scrollable(float x,float y, int width, int height,float scrollSpeed){
        position = new Vector2(x,y);
        velocity = new Vector2(scrollSpeed,0);
        this.width = width;
        this.height = height;
        isScrollLeft = false;
    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));

        // If the Scrollable object is no longer visible:
        if(position.x + width <0){
            isScrollLeft = true;
        }
    }
    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newX){
        position.x = newX;
        isScrollLeft = false;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getTailX(){
        return position.x + width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void stop() {
        velocity.x =0;
    }

    public boolean isScrolledLeft() {
        return isScrollLeft;
    }
}
