package controller;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

import spidermon.util.AnimationSet;
import world.DIRECTION;
import world.TileMap;

public class Player {
	//Create Tile Map to create the world
	private TileMap map;
	
	int x;
	int y;
	DIRECTION facing;
	
	//variables for motion tweening
	private float worldX;
	private float worldY;
	
	int initX;
	int initY;
	
	int finX;
	int finY;

	float animationTimer;
	float anim_time = 0.5f;
	
	//optional - for running
	boolean running = false;
	
	private float walkTimer;
	boolean moveRequestThisFrame;
	
	private PLAYER_STATE playerState;
	
	private AnimationSet animationsWalking;
	private AnimationSet animationsRunning;

	
	public Player (TileMap tileMap, int x, int y, AnimationSet animationsWalking, AnimationSet animationsRunning) {
		this.map = tileMap;
		this.x = x;
		this.y = y;
		
		this.worldX = x;
		this.worldY = y;
		
		this.animationsWalking = animationsWalking;
		this.animationsRunning = animationsRunning;
		
		map.getTile(x, y).setPlayer(this);
		
		this.playerState = PLAYER_STATE.STANDING;
		
		this.facing = DIRECTION.SOUTH;
	}
	
	public enum PLAYER_STATE {
		WALKING,
		STANDING;
	}
	
	public void update(float delta) {
		if (playerState == PLAYER_STATE.WALKING) {
			
			//Interpolation for smooth motion tweening
			animationTimer += delta;
			walkTimer += delta;
			
			worldX = Interpolation.linear.apply(initX, finX, animationTimer/anim_time);
			worldY = Interpolation.linear.apply(initY, finY, animationTimer/anim_time);
			
			if (animationTimer > anim_time) {
				float remainingTime = animationTimer - anim_time;
				walkTimer -= remainingTime;
				finishMove();
				
				if(moveRequestThisFrame) {
					move(facing);
				} 
				else {
					walkTimer = 0f;
				}
			}	
		}
		moveRequestThisFrame = false;
	}
	
	public boolean move (DIRECTION dir) {
		//Cannot start a new move if the old one is still in process
		if (playerState == PLAYER_STATE.WALKING) {
			if (facing == dir) {
				moveRequestThisFrame = true;
			}
			return false;
		}
		
		if (x + dir.getDX() >= map.getWidth() || x + dir.getDX() < 0 || y + dir.getDY() >= map.getHeight() || y + dir.getDY() < 0) {
			return false;
		}
		
		//Checks to see if the next tile is occupied by another player
		if (map.getTile(x + dir.getDX(), y + dir.getDY()).getPlayer() != null) {
			return false;
		}
		if (map.getTile(x + dir.getDX(), y + dir.getDY()).HasObject()) {
			if (!map.getTile(x + dir.getDX(), y + dir.getDY()).getObject().isWalkable()) {
				return false;
			}
		}
		if (!map.getTile(x + dir.getDX(), y + dir.getDY()).isWalkable()) {
			return false;
		}
		
		
		
		initMove(dir);
		
		
		map.getTile(x, y).setPlayer(null);
		x += dir.getDX();
		y += dir.getDY();
		map.getTile(x, y).setPlayer(this);
		return true;
		
	}
	
	public float getWorldX() {
		return worldX;
	}

	public float getWorldY() {
		return worldY;
	}

	public void initMove(DIRECTION dir) {
		this.facing = dir;
		
		this.initX = x;
		this.initY = y;
		
		this.finX = x + dir.getDX();
		this.finY = y + dir.getDY();
		
		this.worldX = x;
		this.worldY = y;
		
		animationTimer = 0f;
		playerState = PLAYER_STATE.WALKING;
		
	}
	
	public void finishMove() {
		//Returns player state to standing
		playerState = PLAYER_STATE.STANDING;
		this.worldX = finX;
		this.worldY = finY;
		this.initX = 0;
		this.initY = 0;
		this.finX = 0;
		this.finY = 0;
	}

	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}

	//optional - for running
	public void setAnim_time(float anim_time) {
		this.anim_time = anim_time;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public TextureRegion getSprite() {
		if (playerState == PLAYER_STATE.WALKING) {
			if (running == false) {
				return animationsWalking.getWalking(facing).getKeyFrame(walkTimer);
			}
			else if (running) {
				//optional - for running
				return animationsRunning.getWalking(facing).getKeyFrame(walkTimer);
			}
		}
		else 
		if (playerState == PLAYER_STATE.STANDING) {
			return animationsWalking.getStanding(facing);
		}
		return animationsWalking.getStanding(DIRECTION.SOUTH);
	}
}
