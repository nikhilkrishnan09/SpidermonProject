package spidermon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import spidermon.mainscreen.MainGameScreen;

public class Spidermon extends Game{
	//Creates the game screen
	private MainGameScreen screen;
	AssetManager assetManager;

	@Override
	public void create() {
		assetManager = new AssetManager();
		assetManager.load("sprites/packed/textures.atlas", TextureAtlas.class);
		assetManager.finishLoading();
		
		try {
			screen = new MainGameScreen(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setScreen(screen);
	}
	
	public void render () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
	
	public AssetManager getAssetManager() {
		return assetManager;
	}


}
