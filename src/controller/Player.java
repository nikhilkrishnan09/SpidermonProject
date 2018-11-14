package controller;

import com.badlogic.gdx.math.Interpolation;

import world.TileMap;

public class Player {
	//Create Tile Map to create the world
	private TileMap map;
	
	int x;
	int y;
	
	//variables for motion tweening
	private float worldX;
	private float worldY;
	
	int initX;
	int initY;
	
	int finX;
	int finY;
	
	float animationTimer;
	float ANIM_TIME = 0.25f;
	
	private PLAYER_STATE playerState;
	
	public Player (TileMap tileMap, int x, int y) {
		this.map = tileMap;
		this.x = x;
		this.y = y;
		
		this.worldX = x;
		this.worldY = y;
		
		map.getTile(x, y).setPlayer(this);
		
		this.playerState = PLAYER_STATE.STANDING;
	}
	
	public enum PLAYER_STATE {
		WALKING,
		STANDING;
	}
	
	public void update(float delta) {
		if (playerState == PLAYER_STATE.WALKING) {
			
			//Interpolation for smooth motion tweening
			animationTimer += delta;
			worldX = Interpolation.sine.apply(initX, finX, animationTimer/ANIM_TIME);
			worldY = Interpolation.sine.apply(initY, finY, animationTimer/ANIM_TIME);
			
			if (animationTimer > ANIM_TIME) {
				finishMove();
			}
		}
	}
	
	public boolean move (int dx, int dy) {
		//Cannot start a new move if the old one is still in process
		if (playerState != PLAYER_STATE.STANDING) {
			return false;
		}
		
		if (x+dx >= map.getWidth() || x+dx < 0 || y+dy >= map.getHeight() || y+dy < 0) {
			return false;
		}
		
		//Checks to see if the next tile is occupied by another player
		if (map.getTile(x + dx, y + dy).getPlayer() != null) {
			return false;
		}
		initMove(x, y, dx, dy);
		
		
		map.getTile(x, y).setPlayer(null);
		x += dx;
		y += dy;
		map.getTile(x, y).setPlayer(this);
		return true;
		
	}
	
	public float getWorldX() {
		return worldX;
	}

	public float getWorldY() {
		return worldY;
	}

	public void initMove(int oldX, int oldY, int dx, int dy) {
		this.initX = oldX;
		this.initY = oldY;
		
		this.finX = oldX + dx;
		this.finY = oldY + dy;
		
		this.worldX = oldX;
		this.worldY = oldY;
		
		animationTimer = 0f;
		playerState = PLAYER_STATE.WALKING;
		
	}
	
	public void finishMove() {
		//Returns player state to standing
		playerState = PLAYER_STATE.STANDING;
	}

	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}
}
