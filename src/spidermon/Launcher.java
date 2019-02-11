package spidermon;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Launcher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//creates configuration for the screen
		config.height = 600;
		config.width = 900;
		config.vSyncEnabled = true;
		config.title = "Spidermon Game";
		config.resizable = false;
		
		new LwjglApplication(new Spidermon(), config);

	}
}