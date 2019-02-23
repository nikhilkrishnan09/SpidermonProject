package spidermon.mainscreen;


import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;

import battle.Enemy;
import battle.SpidermanBattler;
import controller.Player;
import controller.PlayerController;
import spidermon.Settings;
import spidermon.Spidermon;
import spidermon.util.AnimationSet;
import world.Camera;
import world.TileMap;
import world.WorldBuilder;

public class MainGameScreen extends AbstractScreen {
	//Initializes spriteBatch to render textures
	SpriteBatch spriteBatch;
	Texture darkGrass;
	Texture fuzzyGrass;
	Texture borderTree;
	Texture textBox;
	Texture spidermanBattle;
	Texture goblinBattle;
	Texture venomBattle;
	Texture mysterioBattle;
	Texture healthBarFrame;
	Texture healthBar;
	
	//Objects to model and control the player
	Player player;
	PlayerController playerController;
	
	Camera camera;
	
	//Time map to create world and terrain
	TileMap gameTileMap;
	int borderWidth;
	int borderHeight;
	
	//Text
	private BitmapFont font;
	private BitmapFont battleOptions;
	
	//Scrolling text animation
	public static float animTime;
	public static boolean animatingText;
	int charactersToDisplay;
	float waitTime;
	float totalAnimationTime;
	String text;
	String actuallyDisplayedText;
	
	Enemy enemy;
	SpidermanBattler spidermanBattler;
	boolean battleTurn1 = false;
	boolean battleTurn2 = false;
	boolean battleTurn3 = false;
	boolean enemyTurn = false;
	boolean enemySelectingMove = false;
	
	public MainGameScreen(Spidermon app) throws Exception {
		super(app);
		
		//Initializes variables and objects
		darkGrass = new Texture("sprites/grass1.png");
		fuzzyGrass = new Texture("sprites/grass2.png");
		borderTree = new Texture("sprites/Dark_Tree.png");
		textBox = new Texture("sprites/Text_box.png");
		spidermanBattle = new Texture ("sprites/spiderman_battle_sprite.png");
		goblinBattle = new Texture ("sprites/green_goblin_battle_sprite.png");
		venomBattle = new Texture ("sprites/venom_battle_sprite.png");
		mysterioBattle = new Texture ("sprites/mysterio_battle_sprite.png");
		healthBarFrame = new Texture ("sprites/health_bar_frame.png");
		healthBar = new Texture ("sprites/health_bar.png");
		
		spriteBatch = new SpriteBatch();
		gameTileMap = new TileMap(Settings.WIDTH_TILES, Settings.HEIGHT_TILES);
		
		TextureAtlas atlas = app.getAssetManager().get("sprites/packed/textures.atlas", TextureAtlas.class);
		
		//Walking and standing animations
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
		
		//Running animations
		AnimationSet animationsRunning = new AnimationSet(
				new Animation(0.2f/2f, atlas.findRegions("North_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.2f/2f, atlas.findRegions("South_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.2f/2f, atlas.findRegions("East_Walking"), PlayMode.LOOP_PINGPONG),
				new Animation(0.2f/2f, atlas.findRegions("West_Walking"), PlayMode.LOOP_PINGPONG)
				);
		
		//Initialization
		player = new Player (gameTileMap, 9, 6, animationsWalking, animationsRunning);
		playerController = new PlayerController(player);
		
		WorldBuilder.setMap(gameTileMap);
		
		camera = new Camera();		
		
		borderWidth = gameTileMap.getWidth() + 9;
		borderHeight = gameTileMap.getHeight() + 6;
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		battleOptions = new BitmapFont();
		battleOptions.setColor(Color.WHITE);
	

		animTime = 0;
		animatingText = true;
		waitTime = 0;
	}
	

	@Override
	public void dispose() {
		spriteBatch.dispose();
		
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
		float worldX = Gdx.graphics.getWidth()/2 - camera.getCameraX() * Settings.SCALE_TILE;
		float worldY = Gdx.graphics.getHeight()/2 - camera.getCameraY() * Settings.SCALE_TILE;
		
		spriteBatch.begin();
		
		//Does not render world when player is in battle
		if (!player.isInBattle()) {
			//Surrounding grass
			for (int i = -9; i < borderWidth; i++) {
				for (int j = Settings.HEIGHT_TILES; j < borderHeight; j++) {
						spriteBatch.draw(darkGrass, worldX + i * Settings.SCALE_TILE, worldY + j * Settings.SCALE_TILE, Settings.SCALE_TILE, Settings.SCALE_TILE);
				}
				for (int j = -1; j > -7; j--) {				
						spriteBatch.draw(darkGrass, worldX + i * Settings.SCALE_TILE, worldY + j  * Settings.SCALE_TILE, Settings.SCALE_TILE, Settings.SCALE_TILE);
				}
			}
			
			for (int i = 0; i < Settings.HEIGHT_TILES; i++) {
				for (int j = -9; j < 1; j++) {				
						spriteBatch.draw(darkGrass, worldX + j * Settings.SCALE_TILE, worldY + i  * Settings.SCALE_TILE, Settings.SCALE_TILE, Settings.SCALE_TILE);
				}			
				for (int j = Settings.WIDTH_TILES; j < Settings.WIDTH_TILES + 9; j++) {				
						spriteBatch.draw(darkGrass, worldX + j * Settings.SCALE_TILE, worldY + i  * Settings.SCALE_TILE, Settings.SCALE_TILE, Settings.SCALE_TILE);
				}			
			}
			
			//Grass on walkable area
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
			
			//Surrounding forest - top
			for (int i = -9; i < borderWidth; i += 2) {
				for (int j = Settings.HEIGHT_TILES + 6; j >= Settings.HEIGHT_TILES; j -= 2) {
					spriteBatch.draw(borderTree, worldX + i * Settings.SCALE_TILE, worldY + (j - 1) * Settings.SCALE_TILE, Settings.TREE_WIDTH * Settings.SCALE, Settings.TREE_HEIGHT * Settings.SCALE);
				}
			}
		
			//Surrounding forest - sides
			for (int i = Settings.HEIGHT_TILES - 3; i > -2; i -= 2) {
				for (int j = -10; j < 0; j+= 2) {				
						spriteBatch.draw(borderTree, worldX + j * Settings.SCALE_TILE, worldY + i  * Settings.SCALE_TILE, Settings.TREE_WIDTH * Settings.SCALE, Settings.TREE_HEIGHT * Settings.SCALE);
				}			
				
				for (int j = Settings.WIDTH_TILES; j < Settings.WIDTH_TILES + 10; j += 2) {
					spriteBatch.draw(borderTree, worldX + j * Settings.SCALE_TILE, worldY + i  * Settings.SCALE_TILE, Settings.TREE_WIDTH * Settings.SCALE, Settings.TREE_HEIGHT * Settings.SCALE);
				}
					
			}
			
			//Renders player and objects player interacts with
			//Controls which objects are rendered first for 3D effect
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
			
			//Surrounding forest - bottom
			for (int i = -9; i < borderWidth; i += 2) {

				for (int j = -3; j > -9; j -= 2) {
					spriteBatch.draw(borderTree, worldX + i * Settings.SCALE_TILE, worldY + (j) * Settings.SCALE_TILE, Settings.TREE_WIDTH * Settings.SCALE, Settings.TREE_HEIGHT * Settings.SCALE);
				}
			}
		}
		
		//Introduction text
		if (player.isIntroText()) {
			spriteBatch.draw(textBox, 275, 5, 350, 90);
			text = "Welcome to the world of Spidermon!\nYour objective is to find and defeat all enemies.\nPress ENTER to start your journey...";
			totalAnimationTime = Settings.TEXT_SPEED * text.length();
			
			//Creates scrolling text effect, using frame rate (delta) as a delay between characters
			if (animatingText) {
				animTime += delta;
				charactersToDisplay = (int)((animTime/totalAnimationTime) * text.length());
				actuallyDisplayedText = "";
				for (int i = 0; i < charactersToDisplay; i++) {
					actuallyDisplayedText += text.charAt(i);
				}
				font.draw(spriteBatch, actuallyDisplayedText, 300, 75);
				if (actuallyDisplayedText.equals(text)) {
					animatingText = false;
				}
			}
			else {
				font.draw(spriteBatch, text, 300, 75);

			}
		}	
		
		//Pre-battle text
		if (player.isInRegularBattleCutScene()) {
			spriteBatch.draw(textBox, 275, 5, 350, 90);
			text = "You have encountered an enemy!\nDefeat this enemy in battle to progress!\nBattle... Start!";

			totalAnimationTime = Settings.TEXT_SPEED * text.length();

			if (animatingText) {
				animTime += delta;
				charactersToDisplay = (int)((animTime/totalAnimationTime) * text.length());
				actuallyDisplayedText = "";
				for (int i = 0; i < charactersToDisplay; i++) {
					actuallyDisplayedText += text.charAt(i);
				}
				font.draw(spriteBatch, actuallyDisplayedText, 300, 75);
				if (actuallyDisplayedText.equals(text)) {
					animatingText = false;
				}
				waitTime = 0;
			}
			else {
				font.draw(spriteBatch, text, 300, 75);
				
				waitTime += delta;
				if (waitTime > 2f) {
					player.setInRegularBattleCutScene(false);
					
					if (gameTileMap.getTile(player.getX(), player.getY()+1).getEnemyType().equals("Gobby")) {
						
						enemy = new Enemy (100, 20, "Pumpkin Bomb", "Punch", 40, 15);
					}
					if (gameTileMap.getTile(player.getX(), player.getY()+1).getEnemyType().equals("Venom")) {
						
						enemy = new Enemy (100, 20, "Venom Slash", "Bite", 40, 15);
					}
					if (gameTileMap.getTile(player.getX(), player.getY()+1).getEnemyType().equals("Mysterio")) {
						
						enemy = new Enemy (100, 20, "Phobia", "Smoke Bomb", 40, 15);
					}
					spidermanBattler = new SpidermanBattler();
//					spidermanBattler.resetHealth();
					battleTurn1 = true;
					battleTurn2 = false;
					battleTurn3 = false;
					enemyTurn = false;
					player.setInBattle(true);
					animatingText = true;
					animTime = 0;
					waitTime = 0;
					text = "Spidermon, select your move.";
				}
			}
		}

//		Texture healthBarFrame;
//		Texture healthBar;
		
		if (player.isInBattle()) {
			spriteBatch.draw(spidermanBattle, 150, 300, 84, 180);
			if (gameTileMap.getTile(player.getX(), player.getY()+1).getEnemyType().equals("Gobby")) {
				spriteBatch.draw(goblinBattle, 600, 300, 162, 168);
			}
			if (gameTileMap.getTile(player.getX(), player.getY()+1).getEnemyType().equals("Venom")) {
				spriteBatch.draw(venomBattle, 600, 300, 162, 168);
			}
			if (gameTileMap.getTile(player.getX(), player.getY()+1).getEnemyType().equals("Mysterio")) {
				spriteBatch.draw(mysterioBattle, 600, 250, 162, 220);
			}
			spriteBatch.draw(textBox, 275, 5, 350, 90);
			spriteBatch.draw(healthBarFrame, 90, 500, 250, 50);
			spriteBatch.draw(healthBarFrame, 560, 500, 250, 50);
						
			if (enemy.getOldHealth() > 0 && spidermanBattler.getOldHealth() > 0) {
				
				if (battleTurn1) {
					battleOptions.draw(spriteBatch, "1: Attack", 175, 250);
					battleOptions.draw(spriteBatch, "2: Heal", 175, 200);
					battleOptions.draw(spriteBatch, "3: Defend", 175, 150);
					
					spidermanBattler.setProtect(false);
					
					text = "Spidermon, select your move.";
					totalAnimationTime = Settings.TEXT_SPEED * text.length();
					double healthWidth = ((double)spidermanBattler.getHealth()/spidermanBattler.getMaxHealth())*240;
					spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);

					double enemyHealthWidth = ((double) enemy.getHealth()/enemy.getMaxHealth())*240;
					spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);

					
					if (animatingText) {
						animTime += delta;
						charactersToDisplay = (int)((animTime/totalAnimationTime) * text.length());
						actuallyDisplayedText = "";
						for (int i = 0; i < charactersToDisplay; i++) {
							actuallyDisplayedText += text.charAt(i);
						}
						font.draw(spriteBatch, actuallyDisplayedText, 300, 75);
						if (actuallyDisplayedText.equals(text)) {
							animatingText = false;
							player.setBattleMoveInput(true);
						}
					}
					else {
						font.draw(spriteBatch, text, 300, 75);
						if (!player.isBattleMoveInput()) {
							spidermanBattler.setMove(player.getBattleMove());
							battleTurn1 = false;
							battleTurn2 = true;
							animTime = 0;
							animatingText = true;
							waitTime = 0;
						}
					}
				}	
				
				if (battleTurn2) {
					text = spidermanBattler.getBattleText();
					totalAnimationTime = Settings.TEXT_SPEED * text.length();		
					double enemyHealthWidth = ((double) enemy.getHealth()/enemy.getMaxHealth())*240;
					spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
					
					
					
					if (animatingText) {
						double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
						spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
						animTime += delta;
						charactersToDisplay = (int)((animTime/totalAnimationTime) * text.length());
						actuallyDisplayedText = "";
						for (int i = 0; i < charactersToDisplay; i++) {
							actuallyDisplayedText += text.charAt(i);
						}
						font.draw(spriteBatch, actuallyDisplayedText, 300, 75);
						if (actuallyDisplayedText.equals(text)) {
							animatingText = false;
							if ((int)spidermanBattler.getOldHealth() != spidermanBattler.getHealth()) {
								spidermanBattler.setHealthChange(true);
							}
							if (spidermanBattler.getMove() == 1) {
								player.setBattleMoveInput(true);
								spidermanBattler.setAttacking(true);
							}
						}
					}
					else {
						font.draw(spriteBatch, text, 300, 75);
				

						if (spidermanBattler.getMove() == 1) {
							double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
							spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
							
							battleOptions.draw(spriteBatch, "1: Eviscerate", 175, 250);
							battleOptions.draw(spriteBatch, "2: Web Thwip", 175, 200);
							battleOptions.draw(spriteBatch, "3: Attack Boost", 175, 150);
							
							if (!player.isBattleMoveInput()) {
								spidermanBattler.setMove(player.getBattleMove());
								battleTurn2 = false;
								battleTurn3 = true;
								animTime = 0;
								animatingText = true;
								waitTime = 0;
							}
						}
						else if (spidermanBattler.isHealthChange()) {
							if (spidermanBattler.getOldHealth() < spidermanBattler.getHealth()) {
								spidermanBattler.setOldHealth(0.2f);
								double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
								spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
							}
							if ((int)spidermanBattler.getOldHealth() == spidermanBattler.getHealth()) {
								
								double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
								spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
								waitTime += delta;
								
								if (waitTime > 1f) {
									spidermanBattler.setHealthChange(false);
									battleTurn2 = false;
									enemyTurn = true;
									animTime = 0;
									animatingText = true;
									waitTime = 0;
									enemySelectingMove = true;
								}
								
							}
							
						}
						else if (spidermanBattler.getMove() == 3 || !spidermanBattler.isHealthChange()) {
							
					
							double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
							spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
							waitTime += delta;
							
							if (waitTime > 1f) {
								battleTurn2 = false;
								enemyTurn = true;
								animTime = 0;
								animatingText = true;
								waitTime = 0;
								enemySelectingMove = true;
							}
							
							
						}			
					}	
				}
				
				if (battleTurn3) {
					text = spidermanBattler.getBattleText();
					totalAnimationTime = Settings.TEXT_SPEED * text.length();		
					
					if (animatingText) {
						double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
						spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
						double enemyHealthWidth = ((double) enemy.getOldHealth()/enemy.getMaxHealth())*240;
						spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
						
						animTime += delta;
						charactersToDisplay = (int)((animTime/totalAnimationTime) * text.length());
						actuallyDisplayedText = "";
						
						for (int i = 0; i < charactersToDisplay; i++) {
							actuallyDisplayedText += text.charAt(i);
						}
						font.draw(spriteBatch, actuallyDisplayedText, 300, 75);
						if (actuallyDisplayedText.equals(text)) {
							animatingText = false;
							
							if (spidermanBattler.getOldHealth() != spidermanBattler.getHealth()) {
								spidermanBattler.setHealthChange(true);
							}
							if (spidermanBattler.AttackWorked()) {
								enemy.takeDamage(spidermanBattler.getDamage());
							}
						}
					}
					else {
						font.draw(spriteBatch, text, 300, 75);
				

						if (spidermanBattler.AttackWorked()) {
							double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
							spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
							
							if (enemy.getHealth() < enemy.getOldHealth()) {
								enemy.setOldHealth(-0.4f);
								double enemyHealthWidth = ((double) enemy.getOldHealth()/enemy.getMaxHealth())*240;
								spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
							}
							
							if ((int)enemy.getOldHealth() == enemy.getHealth()) {
								double enemyHealthWidth = ((double) enemy.getOldHealth()/enemy.getMaxHealth())*240;
								spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
								
									battleTurn3 = false;
									enemyTurn = true;
									animTime = 0;
									animatingText = true;
									waitTime = 0;
									spidermanBattler.setAttacking(false);
									enemySelectingMove = true;
								
							}
							
						
						}
						else if (spidermanBattler.isHealthChange()) {
							double enemyHealthWidth = ((double) enemy.getOldHealth()/enemy.getMaxHealth())*240;
							spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
							
							if (spidermanBattler.getOldHealth() > spidermanBattler.getHealth()) {
								spidermanBattler.setOldHealth(-0.4f);
								double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
								spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
							}
							
							if ((int)spidermanBattler.getOldHealth() == spidermanBattler.getHealth()) {
								double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
								spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
								spidermanBattler.setHealthChange(false);
								
									battleTurn3 = false;
									enemyTurn = true;
									animTime = 0;
									animatingText = true;
									enemySelectingMove = true;
									spidermanBattler.setAttackWorked(true);
									spidermanBattler.setAttacking(false);
									waitTime = 0;
								
							}
						}		
						else {
							battleTurn3 = false;
							enemyTurn = true;
							animTime = 0;
							animatingText = true;
							enemySelectingMove = true;
							spidermanBattler.setAttackWorked(true);
							spidermanBattler.setAttacking(false);
							waitTime = 0;
						}
					}
					
					
					
					
				}
				if (enemyTurn) {
					
					
					
					if (enemySelectingMove) {
						double random = Math.random();
//						
//						if (enemy.getHealth() == 100) {
//							if (random >= 0.5d) {
//								enemy.setMove(1, spidermanBattler.isProtect());
//							}
//							else {
//								enemy.setMove(2, spidermanBattler.isProtect());
//							}
//						}
//						else {
//							if (random < 0.4d)
//							{
//								enemy.setMove(3, spidermanBattler.isProtect());
//							
//							}
//							if (random >= 0.4d && random < 0.7d) {
//								enemy.setMove(1, spidermanBattler.isProtect());
//							}
//							if (random >= 0.7d) {
//								enemy.setMove(2, spidermanBattler.isProtect());
//							}
//						}
						if (random >= 0.9d) {
							enemy.setMove(1, spidermanBattler.isProtect());
						}
						else {
							enemy.setMove(2, spidermanBattler.isProtect());
						}
						
						enemySelectingMove = false;
					}
					
					
					
					String enemyText = enemy.getBattleText();
					

					
					totalAnimationTime = Settings.TEXT_SPEED * enemyText.length();		
				
					if (animatingText) {
						
						double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
						spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
						
						double enemyHealthWidth = ((double) enemy.getOldHealth()/enemy.getMaxHealth())*240;
						spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
						
						animTime += delta;
						charactersToDisplay = (int)((animTime/totalAnimationTime) * enemyText.length());
						actuallyDisplayedText = "";
						
						for (int i = 0; i < charactersToDisplay; i++) {
							actuallyDisplayedText += enemyText.charAt(i);
						}
						font.draw(spriteBatch, actuallyDisplayedText, 300, 75);
						
						
						
						
						if (actuallyDisplayedText.equals(enemyText)) {
							animatingText = false;
							
							if (enemy.didAttackWork()) {
								spidermanBattler.takeDamage(enemy.getDamage());
							}
						
							if (spidermanBattler.getOldHealth() != spidermanBattler.getHealth()) {
								spidermanBattler.setHealthChange(true);
							}
							
//							if (enemy.getMove() == 3) {
//								enemy.Heal();
//							}
						}
					}
					
					
					else {
						
						if (spidermanBattler.isHealthChange()) {
							double enemyHealthWidth = ((double) enemy.getOldHealth()/enemy.getMaxHealth())*240;
							spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
							
							if (spidermanBattler.getOldHealth() > spidermanBattler.getHealth()) {
								spidermanBattler.setOldHealth(-0.2f);
								double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
								spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
							}
							if ((int)spidermanBattler.getOldHealth() == spidermanBattler.getHealth()) {
								double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
								spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
								spidermanBattler.setHealthChange(false);
								
								enemyTurn = false;
								battleTurn1 = true;
								animTime = 0;
								animatingText = true;
								waitTime = 0;
								
							}						
						}
						else if (enemy.getOldHealth() != enemy.getHealth()) {
							double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
							spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
							
							if (enemy.getHealth() > enemy.getOldHealth()) {
								enemy.setOldHealth(0.2f);
								double enemyHealthWidth = ((double) enemy.getOldHealth()/enemy.getMaxHealth())*240;
								spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
							}
							
							if ((int)enemy.getOldHealth() == enemy.getHealth()) {
								double enemyHealthWidth = ((double) enemy.getOldHealth()/enemy.getMaxHealth())*240;
								spriteBatch.draw(healthBar, 565, 507, (int) enemyHealthWidth, 36);
								
								enemyTurn = false;
								battleTurn1 = true;
								animTime = 0;
								animatingText = true;
								waitTime = 0;
								
							}
						}
						else {
							enemyTurn = false;
							battleTurn1 = true;
							animTime = 0;
							animatingText = true;
							waitTime = 0;
							
						}						
					}					
				}
			}
			if ((int)enemy.getOldHealth() <= 0) {
				battleTurn3 = false;
				spriteBatch.draw(textBox, 275, 5, 350, 90);
				text = "You won the battle! Keep exploring and \nsearching for enemies throughout \nthe world..................";
				totalAnimationTime = Settings.TEXT_SPEED * text.length();
				animatingText = true;
				
				//Creates scrolling text effect, using frame rate (delta) as a delay between characters
				if (animatingText) {
					animTime += delta;
					charactersToDisplay = (int)((animTime/totalAnimationTime) * text.length());
					actuallyDisplayedText = "";
					double healthWidth = (spidermanBattler.getOldHealth()/spidermanBattler.getMaxHealth())*240;				
					spriteBatch.draw(healthBar, 95, 507, (int) healthWidth, 36);
					
					for (int i = 0; i < charactersToDisplay; i++) {
						actuallyDisplayedText += text.charAt(i);
					}
					font.draw(spriteBatch, actuallyDisplayedText, 300, 75);
					if (actuallyDisplayedText.equals(text)) {
						animatingText = false;
						player.setInBattle(false);
						gameTileMap.getTile(player.getX(), player.getY()).setFightTile(false);
						gameTileMap.getTile(player.getX(), player.getY()+1).setHasObject(false);
					}
					
				}
					
			}
			if ((int)spidermanBattler.getOldHealth() == 0) {
				player.setInBattle(false);
				
			}
		
			
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
