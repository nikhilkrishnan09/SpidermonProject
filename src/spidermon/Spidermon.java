package spidermon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import spidermon.mainscreen.MainGameScreen;

public class Spidermon extends Game{
	
	private MainGameScreen screen;

	@Override
	public void create() {
		screen = new MainGameScreen(this);
		
		this.setScreen(screen);
	}
	
	public void render () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}


}
