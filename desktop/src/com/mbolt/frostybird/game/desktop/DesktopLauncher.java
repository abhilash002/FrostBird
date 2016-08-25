package com.mbolt.frostybird.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mbolt.frostybird.game.FrostyBirdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "FrostyBird";
		cfg.width = 1080 / 3;
		cfg.height = 1920 / 3;
		new LwjglApplication(new FrostyBirdGame(), cfg);
	}
}
