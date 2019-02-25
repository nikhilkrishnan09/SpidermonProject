package world;

import java.util.Random;

public class TileMap {

	private int width;
	private int height;
	
	//2D array of tiles for creating a tile map
	//Size of this array is controlled in Settings
	
	Tile[][] tiles;
	Random random;
	
	public TileMap (int width, int height) {
		this.width = width;
		this.height = height;
		
		//Randomly determines which type of grass will appear
		random = new Random();
		
		tiles = new Tile[width][height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (random.nextInt(10) + 1 < 8) {
					tiles[x][y] = new Tile(1);
				}
				else {
					tiles[x][y] = new Tile(0);
				}
				
				
			}
		}
	}
	
	//Each tile of the map can be accessed individually with x and y coordinates
	//Based on the players position, this controls spidermons interaction with the world
	//From the tile class, world objects can be accessed, controlling player interaction as well
	public Tile getTile (int x, int y) {
		return tiles[x][y];
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}
	
}
