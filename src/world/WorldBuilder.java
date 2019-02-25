package world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.badlogic.gdx.graphics.Texture;

import spidermon.Settings;


public class WorldBuilder {

private static TileMap builderMap;
//initializes and declares textures from the sprites folder
static Texture tree = new Texture ("sprites/Dark_Tree.png");
static Texture house = new Texture ("sprites/house_building.png");
static Texture gobby = new Texture ("sprites/gobby.png");
static Texture venom = new Texture ("sprites/venom.png");
static Texture mysterio = new Texture ("sprites/mysterio.png");
static Texture danush = new Texture ("sprites/Danush.png");
static Texture building = new Texture ("sprites/classic_building.png");
static Texture tallGrass = new Texture("sprites/tall_grass.png");


	public static void setMap (TileMap tileMap)throws Exception {
		builderMap = tileMap;
	
		//reads a text file to easily construct and modify the map
		File file = new File("WorldText.txt"); 
	
		BufferedReader br = new BufferedReader(new FileReader(file)); 
	
		//reads each line of text and parses it into three variables: object name, x coordinate, y coordinate
		//x and y coordinates are for the bottom left corner of the object
		//this information is passed to the build method
		String st; 
		while ((st = br.readLine()) != null) { 
			String str[] = st.split(" ", 3);
			int xC = Integer.valueOf(str[1]);
			int yC = Integer.valueOf(str[2]);
			String type = str[0];
	
			build(xC, yC, type);
		}
	}

	
	//the build method takes in the object name and coordinates
	//Coordinates control where the object is rendered
	//The name control what type of object is rendered (tree, building, enemy, etc.)
	//based on the coordinates, certain tiles are selected to be walkable or not, creating colliders
	//other tiles have the setRenderInFront set to true for a 3 dimensional effect
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
			builderMap.getTile(x, y).setObject(true, house, 4, 4, 100, 110);			
			builderMap.getTile(x, y).setWalkable(false);
			builderMap.getTile(x+1, y).setWalkable(false);
			builderMap.getTile(x+2, y).setWalkable(false);
			builderMap.getTile(x+3, y).setWalkable(false);
			builderMap.getTile(x, y+1).setWalkable(false);
			builderMap.getTile(x, y+2).setWalkable(false);
			builderMap.getTile(x, y+3).setWalkable(false);	
			builderMap.getTile(x+3, y+1).setWalkable(false);
			builderMap.getTile(x+3, y+2).setWalkable(false);
			builderMap.getTile(x+3, y+3).setWalkable(false);
			builderMap.getTile(x+1, y+3).setWalkable(false);
			builderMap.getTile(x+2, y+3).setWalkable(false);
			builderMap.getTile(x, y+4).setRenderInFront(true);
			builderMap.getTile(x+1, y+4).setRenderInFront(true);
			builderMap.getTile(x+2, y+4).setRenderInFront(true);
			builderMap.getTile(x+3, y+4).setRenderInFront(true);
			builderMap.getTile(x-1, y+4).setRenderInFront(true);
			builderMap.getTile(x+4, y+4).setRenderInFront(true);
			builderMap.getTile(x, y+5).setRenderInFront(true);
			builderMap.getTile(x+1, y+5).setRenderInFront(true);
			builderMap.getTile(x+2, y+5).setRenderInFront(true);
			builderMap.getTile(x+3, y+5).setRenderInFront(true);
			builderMap.getTile(x-1, y+5).setRenderInFront(true);
			builderMap.getTile(x+4, y+5).setRenderInFront(true);
		}
		
		if (type.equals("newBuilding")) {
			builderMap.getTile(x, y).setObject(true, building, 4, 4, 100, 110);
			builderMap.getTile(x, y).setWalkable(false);
			builderMap.getTile(x+1, y).setWalkable(false);
			builderMap.getTile(x+2, y).setWalkable(false);
			builderMap.getTile(x+3, y).setWalkable(false);
			builderMap.getTile(x, y+1).setWalkable(false);
			builderMap.getTile(x, y+2).setWalkable(false);
			builderMap.getTile(x, y+3).setWalkable(false);	
			builderMap.getTile(x+3, y+1).setWalkable(false);
			builderMap.getTile(x+3, y+2).setWalkable(false);
			builderMap.getTile(x+3, y+3).setWalkable(false);
			builderMap.getTile(x+1, y+3).setWalkable(false);
			builderMap.getTile(x+2, y+3).setWalkable(false);
			builderMap.getTile(x, y+4).setRenderInFront(true);
			builderMap.getTile(x+1, y+4).setRenderInFront(true);
			builderMap.getTile(x+2, y+4).setRenderInFront(true);
			builderMap.getTile(x+3, y+4).setRenderInFront(true);
			builderMap.getTile(x-1, y+4).setRenderInFront(true);
			builderMap.getTile(x+4, y+4).setRenderInFront(true);
			builderMap.getTile(x, y+5).setRenderInFront(true);
			builderMap.getTile(x+1, y+5).setRenderInFront(true);
			builderMap.getTile(x+2, y+5).setRenderInFront(true);
			builderMap.getTile(x+3, y+5).setRenderInFront(true);
			builderMap.getTile(x-1, y+5).setRenderInFront(true);
			builderMap.getTile(x+4, y+5).setRenderInFront(true);
		}


		//The enemies have colliders and 3-D effects
		//The previous tile and object tile have additional parameters to control player interaction
		//with enemies in the game (fightTile and EnemyType)
		if (type.equals("newGobby")) {
			builderMap.getTile(x, y).setObject(false, gobby, 2, 3, 25, 30);
			builderMap.getTile(x, y+1).setRenderInFront(true);
			builderMap.getTile(x-1, y+1).setRenderInFront(true);
			builderMap.getTile(x+1, y+1).setRenderInFront(true);
			
			builderMap.getTile(x, y - 1).setFightTile(true);
			builderMap.getTile(x, y).setEnemyType("Gobby");
		}
		
		if (type.equals("newVenom")) {
			builderMap.getTile(x, y).setObject(false, venom, 2, 3, 30, 30);
			builderMap.getTile(x, y+1).setRenderInFront(true);
			builderMap.getTile(x-1, y+1).setRenderInFront(true);
			builderMap.getTile(x+1, y+1).setRenderInFront(true);
			
			builderMap.getTile(x, y - 1).setFightTile(true);
			builderMap.getTile(x, y).setEnemyType("Venom");
		}
		
		if (type.equals("newMysterio")) {
			builderMap.getTile(x, y).setObject(false, mysterio, 2, 3, 28, 30);
			builderMap.getTile(x, y+1).setRenderInFront(true);
			builderMap.getTile(x-1, y+1).setRenderInFront(true);
			builderMap.getTile(x+1, y+1).setRenderInFront(true);
			
			builderMap.getTile(x, y - 1).setFightTile(true);
			builderMap.getTile(x, y).setEnemyType("Mysterio");
		}
		
		if (type.equals("newDanush")) {
			builderMap.getTile(x, y).setObject(false, danush, 2, 3, 20, 25);
			builderMap.getTile(x, y+1).setRenderInFront(true);
			builderMap.getTile(x-1, y+1).setRenderInFront(true);
			builderMap.getTile(x+1, y+1).setRenderInFront(true);
			
			builderMap.getTile(x, y - 1).setFightTile(true);
			builderMap.getTile(x, y).setEnemyType("Danush");
		}
				
	}
}