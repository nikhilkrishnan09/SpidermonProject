package controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import spidermon.Settings;
import world.DIRECTION;

public class PlayerController extends InputAdapter {
	
	private Player player;
	
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	
	//optional - for running
	boolean run;
	
	public PlayerController (Player play) {
		this.player = play;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		//Checks for keyboard input, changes the coordinates of the player accordingly
		
		if (keycode == Keys.UP) {
			up  = true;
		}
		else if (keycode == Keys.DOWN) {
			down  = true;
		}
		else if (keycode == Keys.LEFT) {
			left  = true;
		}
		else if (keycode == Keys.RIGHT) {
			right  = true;
		}
		else if (keycode == Keys.SPACE) {
			run = true;
		}	
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.UP) {
			up  = false;
		}
		else if (keycode == Keys.DOWN) {
			down  = false;
		}
		else if (keycode == Keys.LEFT) {
			left  = false;
		}
		else if (keycode == Keys.RIGHT) {
			right  = false;
		}	
		else if (keycode == Keys.SPACE) {
			run = false;
		}
			
		return false;
	}
	
	public void update (float delta) {
		//optional - for running
		if (up && run) {
			player.setAnim_time(0.15f);
			player.setRunning(true);
			player.move(DIRECTION.NORTH);
			return;
		}
		if (down && run) {
			player.setAnim_time(0.15f);
			player.setRunning(true);
			player.move(DIRECTION.SOUTH);
			return;
		}
		if (left && run) {
			player.setAnim_time(0.15f);
			player.setRunning(true);
			player.move(DIRECTION.WEST);
			return;
		}
		if (right && run) {
			player.setAnim_time(0.15f);
			player.setRunning(true);
			player.move(DIRECTION.EAST);
			return;
		}
		/*
		 * 
		 * 
		 * 
		 */
		if (up) {
			player.setAnim_time(0.4f);
			player.setRunning(false);
			player.move(DIRECTION.NORTH);
			return;
		}
		if (down) {
			player.setAnim_time(0.4f);
			player.setRunning(false);
			player.move(DIRECTION.SOUTH);
			return;
		}
		if (left) {
			player.setAnim_time(0.4f);
			player.setRunning(false);
			player.move(DIRECTION.WEST);
			return;
		}
		if (right) {
			player.setAnim_time(0.4f);
			player.setRunning(false);
			player.move(DIRECTION.EAST);
			return;
		}
	}
	
	
	
}
