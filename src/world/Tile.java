package world;

import controller.Player;

public class Tile {
	
	
	
	Player player;
	int type;
	
	
	public Tile (int t) {
		type = t;
	}


	public int getTileType() {
		return type;
	}
 	


	public Player getPlayer() {
		return player;
	}



	public void setPlayer(Player actor) {
		this.player = actor;
	}
	
	
	
}
