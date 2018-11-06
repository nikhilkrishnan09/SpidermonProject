package spidermon;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//meaningful comment
		config.height = 600;
		config.width = 900;
		config.vSyncEnabled = true;
		config.title = "Spidermon Game";
		config.resizable = false;
		
		new LwjglApplication(new Spidermon(), config);

	}
}
