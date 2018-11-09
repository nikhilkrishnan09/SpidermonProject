package spidermon.mainscreen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.Player;
import controller.PlayerController;
import spidermon.Settings;
import spidermon.Spidermon;
import world.TileMap;

public class MainGameScreen extends AbstractScreen {
	
	SpriteBatch spriteBatch;
	Texture standSouth;
	Texture darkGrass;
	Texture fuzzyGrass;
	
	Player player;
	PlayerController playerController;
	
	
	
	
	TileMap gameTileMap;

	public MainGameScreen(Spidermon app) {
		super(app);
		
		standSouth = new Texture("sprites/South_Stand.png");
		darkGrass = new Texture("sprites/grass1.png");
		fuzzyGrass = new Texture("sprites/grass2.png");
		
		spriteBatch = new SpriteBatch();
		gameTileMap = new TileMap(Settings.WIDTH_TILES, Settings.HEIGHT_TILES);
		
		player = new Player (gameTileMap, 0, 0);
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
		
		for (int x = 0; x < gameTileMap.getWidth(); x++) {
			for (int y = 0; y < gameTileMap.getHeight(); y++) {
				if (gameTileMap.getTile(x, y).getTileType() == 1) {
					spriteBatch.draw(darkGrass, x * Settings.SCALE_TILE, y * Settings.SCALE_TILE, 50, 50);
				}
				else {
					spriteBatch.draw(fuzzyGrass, x * Settings.SCALE_TILE, y * Settings.SCALE_TILE, 50, 50);
				}
					
				
			}
		}
		
		spriteBatch.draw(standSouth, player.getX() * Settings.SCALE_TILE, player.getY() * Settings.SCALE_TILE, 50, 60);
		spriteBatch.end();
		
	}

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
