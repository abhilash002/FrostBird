package com.mbolt.frostybird.game;

import com.badlogic.gdx.Game;
import com.mbolt.fbhelpers.AssetLoader;
import com.mbolt.screens.SplashScreen;

public class FrostyBirdGame extends Game {
	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.dispose();
		AssetLoader.dispose();
	}
}
