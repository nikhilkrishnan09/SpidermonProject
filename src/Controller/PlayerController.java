package Controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import spidermon.Settings;

public class PlayerController extends InputAdapter {
	
	private Player player;
	
	public PlayerController (Player play) {
		this.player = play;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		
		if (keycode == Keys.UP) {
			if (player.getY() == Settings.HEIGHT_TILES - 1) {
				return false;
			}
			
			player.move(0, 1);
			
		}
		else if (keycode == Keys.DOWN) {
			if (player.getY() == 0) {
				return false;
			}
			player.move(0, -1);
		}
		else if (keycode == Keys.LEFT) {
			if (player.getX() == 0) {
				return false;
			}
			
			player.move(-1, 0);
		}
		else if (keycode == Keys.RIGHT) {
			if (player.getX() == Settings.WIDTH_TILES - 1) {
				return false;
			}
			player.move(1, 0);
		}
		
		
		return false;
	}
	
}
