package world;

public class TileMap {

	private int width;
	private int height;
	Tile[][] tiles;
	
	public TileMap (int width, int height) {
		this.width = width;
		this.height = height;
		
		tiles = new Tile[width][height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = new Tile(TERRAIN.DARK_GRASS);
			}
		}
	}
	
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
