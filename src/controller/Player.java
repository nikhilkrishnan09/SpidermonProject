package controller;

import world.TileMap;

public class Player {
	
	private TileMap map;
	
	int x;
	int y;
	
	public Player (TileMap tileMap, int x, int y) {
		this.map = tileMap;
		this.x = x;
		this.y = y;
		map.getTile(x, y).setPlayer(this);
	}
	
	public boolean move (int dx, int dy) {
		
		if (x+dx >= map.getWidth() || x+dx < 0 || y+dy >= map.getHeight() || y+dy < 0) {
			return false;
		}
		
		//Checks to see if the next tile is occupied by another player
		if (map.getTile(x + dx, y + dy).getPlayer() != null) {
			return false;
		}
		
		map.getTile(x, y).setPlayer(null);
		x += dx;
		y += dy;
		map.getTile(x, y).setPlayer(this);
		return true;
		
	}
	
	
	
	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}
}
