package spidermon;

public class Settings {
	//Settings - most hardcoded dimensions here

	public static int TILE = 25;

	public static int SCALE = 2;
	public static int SCALE_TILE = SCALE * TILE;

	//change the size of the map by changing the 4 digit number
	public static int HEIGHT_TILES = 2000/SCALE_TILE;
	public static int WIDTH_TILES = 2000/SCALE_TILE;
	
	public static int TREE_WIDTH = 50;
	public static int TREE_HEIGHT = 82;
	
	public static float TEXT_SPEED = 0.05f;
	
}
