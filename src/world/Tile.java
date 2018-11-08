package world;

import controller.Player;

public class Tile {
	
	TERRAIN terrain;
	
	private Player player;
	
	
	public Tile (TERRAIN terrain) {
		this.terrain = terrain;
	}



	public TERRAIN getTerrain() {
		
		return terrain;
	}



	public Player getPlayer() {
		return player;
	}



	public void setPlayer(Player actor) {
		this.player = actor;
	}
	
	
	
}
