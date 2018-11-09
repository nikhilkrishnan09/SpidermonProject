package spidermon.mainscreen;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.Player;
import controller.PlayerController;
import spidermon.Settings;
import spidermon.Spidermon;
import world.Camera;
import world.TileMap;

public class MainGameScreen extends AbstractScreen {
	
	SpriteBatch spriteBatch;
	Texture standSouth;
	Texture darkGrass;
	Texture fuzzyGrass;
	
	Player player;
	PlayerController playerController;
	
	Camera camera;
	
	
	TileMap gameTileMap;

	public MainGameScreen(Spidermon app) {
		super(app);
		
		standSouth = new Texture("sprites/South_Stand.png");
		darkGrass = new Texture("sprites/grass1.png");
		fuzzyGrass = new Texture("sprites/grass2.png");
		
		spriteBatch = new SpriteBatch();
		gameTileMap = new TileMap(Settings.WIDTH_TILES, Settings.HEIGHT_TILES);
		
		player = new Player (gameTileMap, 9, 6);
		playerController = new PlayerController(player);
		
		camera = new Camera();
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
		camera.update(player.getX() + 0.5f, player.getY() + 0.5f);
		
		spriteBatch.begin();
		
		float worldX = Gdx.graphics.getWidth()/2 - camera.getCameraX() * Settings.SCALE_TILE;
		float worldY = Gdx.graphics.getHeight()/2 - camera.getCameraY() * Settings.SCALE_TILE;
		
		for (int x = 0; x < gameTileMap.getWidth(); x++) {
			for (int y = 0; y < gameTileMap.getHeight(); y++) {
				if (gameTileMap.getTile(x, y).getTileType() == 1) {
					spriteBatch.draw(darkGrass, worldX + x * Settings.SCALE_TILE, worldY + y * Settings.SCALE_TILE, 50, 50);
				}
				else {
					spriteBatch.draw(fuzzyGrass, worldX + x * Settings.SCALE_TILE, worldY + y * Settings.SCALE_TILE, 50, 50);
				}
					
				
			}
		}
		
		
		spriteBatch.draw(standSouth, worldX + player.getX() * Settings.SCALE_TILE, worldY + player.getY() * Settings.SCALE_TILE, 50, 60);
		
		
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
