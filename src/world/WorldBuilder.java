package world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.badlogic.gdx.graphics.Texture;

import spidermon.Settings;


public class WorldBuilder {

private static TileMap builderMap;
static Texture tree = new Texture ("sprites/Dark_Tree.png");
static Texture house = new Texture ("sprites/house_building.png");
static Texture gobby = new Texture ("sprites/gobby.png");
static Texture building = new Texture ("sprites/building_2.png");


	public static void setMap (TileMap tileMap)throws Exception {
		builderMap = tileMap;
	
		File file = new File("WorldText.txt"); 
	
		BufferedReader br = new BufferedReader(new FileReader(file)); 
	
		String st; 
		while ((st = br.readLine()) != null) { 
			String str[] = st.split(" ", 3);
			int xC = Integer.valueOf(str[1]);
			int yC = Integer.valueOf(str[2]);
			String type = str[0];
	
			build(xC, yC, type);
		}
	}

	public static void build(int x, int y, String type) {
	
		if (type.equals("newTree")) {
			builderMap.getTile(x, y).setObject(true, tree, 2, 3, 50, 82);
			builderMap.getTile(x, y+1).setWalkable(false);
			builderMap.getTile(x, y+2).setWalkable(false);
			builderMap.getTile(x+1, y+1).setWalkable(false);
			builderMap.getTile(x+1, y+2).setWalkable(false);
			builderMap.getTile(x, y+3).setRenderInFront(true);
			builderMap.getTile(x+1, y+3).setRenderInFront(true);
			builderMap.getTile(x-1, y+3).setRenderInFront(true);
			builderMap.getTile(x+2, y+3).setRenderInFront(true);
		}
	
		if (type.equals("newHouse")) {
			builderMap.getTile(x, y).setObject(true, house, 3, 3, 75, 92);
			builderMap.getTile(x, y+1).setWalkable(false);
			builderMap.getTile(x, y+2).setWalkable(false);
			builderMap.getTile(x+1, y+1).setWalkable(false);
			builderMap.getTile(x+1, y+2).setWalkable(false);
			builderMap.getTile(x+2, y+1).setWalkable(false);
			builderMap.getTile(x+2, y+2).setWalkable(false);
			builderMap.getTile(x, y+3).setRenderInFront(true);
			builderMap.getTile(x+1, y+3).setRenderInFront(true);
			builderMap.getTile(x-1, y+3).setRenderInFront(true);
			builderMap.getTile(x+2, y+3).setRenderInFront(true);
		}
		
		if (type.equals("newBuilding2")) {
			builderMap.getTile(x, y).setObject(true, building, 3, 3, 75, 92);
			builderMap.getTile(x, y+1).setWalkable(false);
			builderMap.getTile(x, y+2).setWalkable(false);
			builderMap.getTile(x+1, y+1).setWalkable(false);
			builderMap.getTile(x+1, y+2).setWalkable(false);
			builderMap.getTile(x+2, y+1).setWalkable(false);
			builderMap.getTile(x+2, y+2).setWalkable(false);
			builderMap.getTile(x, y+3).setRenderInFront(true);
			builderMap.getTile(x+1, y+3).setRenderInFront(true);
			builderMap.getTile(x-1, y+3).setRenderInFront(true);
			builderMap.getTile(x+2, y+3).setRenderInFront(true);
			builderMap.getTile(x+3, y+3).setRenderInFront(true);
		}


		if (type.equals("newGobby")) {
			builderMap.getTile(x, y).setObject(false, gobby, 2, 3, 25, 30);
			builderMap.getTile(x, y+1).setRenderInFront(true);
			builderMap.getTile(x-1, y+1).setRenderInFront(true);
			builderMap.getTile(x+1, y+1).setRenderInFront(true);
		}
	}
}