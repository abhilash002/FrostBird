package com.mbolt.fbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Abhilash on 21-02-2016.
 */
public class AssetLoader {

    public static BitmapFont font, shadow,whiteFont;
    public static Texture texture, logoTexture ;
    public static TextureRegion bg, grass,ready, gameOver,highScore, scoreboard,
                        star, noStar, retry ;
    public static Sound dead, coin, flap, fall;
    public static Animation birdAnimation ;
    public static TextureRegion bird , birdUp, birdDown, logo, sound, noSound ,
            zbLogo, playButtonUp, playButtonDown;
    public static TextureRegion bar, skullUp, skullDown;
    public static Preferences prefs;

    public static void load(){

        prefs = Gdx.app.getPreferences("FrostyBird");
        // Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        logo = new TextureRegion(logoTexture, 0, 0, 471, 158);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        ready = new TextureRegion(texture,59, 83, 34, 7);
        ready.flip(false,true);

        retry = new TextureRegion(texture, 59, 110, 33, 7);
        retry.flip(false, true);

        gameOver = new TextureRegion(texture, 132, 206,100,14);
        gameOver.flip(false, true);

        scoreboard = new TextureRegion(texture, 362, 0, 150, 79);
        scoreboard.flip(false, true);

        star = new TextureRegion(texture, 344,0,16,14);
        noStar = new TextureRegion(texture, 344, 14, 16,14);
        star.flip(false,true);
        noStar.flip(false, true);

        highScore = new TextureRegion(texture, 59, 101, 48, 7);
        highScore.flip(false, true);

        zbLogo = new TextureRegion(texture, 0, 55, 135, 24 );
        zbLogo.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);
        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);
        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false , true);
        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false,true);

        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);

        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);

        whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
        whiteFont.getData().setScale(.1f, -.1f);

        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        sound = new TextureRegion(texture,226, 0, 15,15);
        noSound = new TextureRegion(texture, 241, 0, 15, 15);
        sound.flip(false,true);
        noSound.flip(false,true);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("FrostyBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }


    public static void dispose() {
        texture.dispose();

        dead.dispose();
        coin.dispose();
        flap.dispose();
        fall.dispose();

        font.dispose();
        whiteFont.dispose();

        shadow.dispose();
    }
}
