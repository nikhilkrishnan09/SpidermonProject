package spidermon.mainscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Controller.Player;
import Controller.PlayerController;
import spidermon.Settings;
import spidermon.Spidermon;

public class MainGameScreen extends AbstractScreen {
	
	SpriteBatch spriteBatch;
	Texture standSouth;
	Player player;
	PlayerController playerController;

	public MainGameScreen(Spidermon app) {
		super(app);
		
		standSouth = new Texture("sprites/South_Stand.png");
		spriteBatch = new SpriteBatch();
		player = new Player (0, 0);
		playerController = new PlayerController(player);
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void render(float delta) {
		spriteBatch.begin();
		spriteBatch.draw(standSouth, player.getX() * Settings.SCALE_TILE, player.getY() * Settings.SCALE_TILE, 50, 60);
		spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(playerController);
	}

}
