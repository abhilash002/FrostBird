package com.mbolt.gameobjects;

/**
 * Created by Abhilash on 23-02-2016.
 */
public class Grass extends Scrollable {
        // When Pipe's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        // Initialize a Random object for Random number generation
    }

    public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }


}
