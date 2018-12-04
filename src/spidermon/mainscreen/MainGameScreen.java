package spidermon.mainscreen;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import controller.Player;
import controller.PlayerController;
import spidermon.Settings;
import spidermon.Spidermon;
import spidermon.util.AnimationSet;
import world.Camera;
import world.TileMap;
import world.WorldBuilder;

public class MainGameScreen extends AbstractScreen {
	//Initializes spriteBatch to render textures, and textures that are rendered
	SpriteBatch spriteBatch;
	Texture standSouth;
	Texture darkGrass;
	Texture fuzzyGrass;
	
	//Objects to model and control the player
	Player player;
	PlayerController playerController;
	
	Camera camera;
	
	//Time map to create world and terrain
	TileMap gameTileMap;
	
	public MainGameScreen(Spidermon app) throws Exception {
		super(app);
		
		//standSouth = new Texture("sprites/South_Stand.png");
		darkGrass = new Texture("sprites/grass1.png");
		fuzzyGrass = new Texture("sprites/grass2.png");
		
		spriteBatch = new SpriteBatch();
		gameTileMap = new TileMap(Settings.WIDTH_TILES, Settings.HEIGHT_TILES);
		
		TextureAtlas atlas = app.getAssetManager().get("sprites/packed/textures.atlas", TextureAtlas.class);
		
		AnimationSet animationsWalking = new AnimationSet(
				new Animation(0.5f/2f, atlas.findRegions("North_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.5f/2f, atlas.findRegions("South_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.5f/2f, atlas.findRegions("East_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.5f/2f, atlas.findRegions("West_Walking"), PlayMode.LOOP_PINGPONG),
				atlas.findRegion("North_Stand"),
				atlas.findRegion("South_Stand"),
				atlas.findRegion("East_Stand"),
				atlas.findRegion("West_Stand")
				);
		
		//optional - for running
		AnimationSet animationsRunning = new AnimationSet(
				new Animation(0.2f/2f, atlas.findRegions("North_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.2f/2f, atlas.findRegions("South_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.2f/2f, atlas.findRegions("East_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.2f/2f, atlas.findRegions("West_Walking"), PlayMode.LOOP_PINGPONG)
				);
		
		player = new Player (gameTileMap, 9, 6, animationsWalking, animationsRunning);
		playerController = new PlayerController(player);
		
		WorldBuilder.setMap(gameTileMap);
		
		camera = new Camera();
		//init();
	}

//	public void init() {
//		
//		float worldX = Gdx.graphics.getWidth()/2 - camera.getCameraX() * Settings.SCALE_TILE;
//		float worldY = Gdx.graphics.getHeight()/2 - camera.getCameraY() * Settings.SCALE_TILE;
//		
//		spriteBatch.begin();
//		
//		//renders the appropriate type of grass
//		for (int x = 0; x < gameTileMap.getWidth(); x++) {
//			for (int y = 0; y < gameTileMap.getHeight(); y++) {
//				if (gameTileMap.getTile(x, y).getTileType() == 1) {
//					spriteBatch.draw(darkGrass, worldX + x * Settings.SCALE_TILE, worldY + y * Settings.SCALE_TILE, Settings.SCALE_TILE, Settings.SCALE_TILE);
//				}
//				else {
//					spriteBatch.draw(fuzzyGrass, worldX + x * Settings.SCALE_TILE, worldY + y * Settings.SCALE_TILE, Settings.SCALE_TILE, Settings.SCALE_TILE);
//				}
//			}
//		}
//		
//		spriteBatch.end();
//	}
	
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
		playerController.update(delta);
		
		player.update(delta);
		camera.update(player.getWorldX() + 0.5f, player.getWorldY() + 0.5f);
		
		spriteBatch.begin();
		
		//world coordinates for camera
		float worldX = Gdx.graphics.getWidth()/2 - camera.getCameraX() * Settings.SCALE_TILE;
		float worldY = Gdx.graphics.getHeight()/2 - camera.getCameraY() * Settings.SCALE_TILE;
		for (int x = 0; x < gameTileMap.getWidth(); x++) {
			for (int y = 0; y < gameTileMap.getHeight(); y++) {
				if (gameTileMap.getTile(x, y).getTileType() == 1) {
					spriteBatch.draw(darkGrass, worldX + x * Settings.SCALE_TILE, worldY + y * Settings.SCALE_TILE, Settings.SCALE_TILE, Settings.SCALE_TILE);
				}
				else {
					spriteBatch.draw(fuzzyGrass, worldX + x * Settings.SCALE_TILE, worldY + y * Settings.SCALE_TILE, Settings.SCALE_TILE, Settings.SCALE_TILE);
				}
			}
		}		
		

		if (gameTileMap.getTile(player.getX(), player.getY()).isRenderInFront()) {
			
			spriteBatch.draw(player.getSprite(), worldX + player.getWorldX() * Settings.SCALE_TILE, worldY + player.getWorldY() * Settings.SCALE_TILE, 25 * Settings.SCALE, 30 * Settings.SCALE);

			for (int x = 0; x < gameTileMap.getWidth(); x++) {
				for (int y = 0; y < gameTileMap.getHeight(); y++) {
					if (gameTileMap.getTile(x, y).HasObject() && gameTileMap.getTile(x,y).getObject() != null) {
						spriteBatch.draw(gameTileMap.getTile(x, y).getObject().getTexture(), worldX + x * Settings.SCALE_TILE, worldY + y * Settings.SCALE_TILE, gameTileMap.getTile(x, y).getObject().getWidth() * Settings.SCALE, gameTileMap.getTile(x, y).getObject().getHeight() * Settings.SCALE);
					}
				}
			}		
		}
		else {
			for (int x = 0; x < gameTileMap.getWidth(); x++) {
				for (int y = 0; y < gameTileMap.getHeight(); y++) {
					if (gameTileMap.getTile(x, y).HasObject() && gameTileMap.getTile(x,y).getObject() != null) {
						spriteBatch.draw(gameTileMap.getTile(x, y).getObject().getTexture(), worldX + x * Settings.SCALE_TILE, worldY + y * Settings.SCALE_TILE, gameTileMap.getTile(x, y).getObject().getWidth() * Settings.SCALE, gameTileMap.getTile(x, y).getObject().getHeight() * Settings.SCALE);
					}
				}
			}		
			
			spriteBatch.draw(player.getSprite(), worldX + player.getWorldX() * Settings.SCALE_TILE, worldY + player.getWorldY() * Settings.SCALE_TILE, 25 * Settings.SCALE, 30 * Settings.SCALE);

		}
		

		
		
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
