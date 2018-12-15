package world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.badlogic.gdx.graphics.Texture;

import spidermon.Settings;

public class WorldBuilder {
	
	private static TileMap builderMap;
	static Texture newTree = new Texture ("sprites/Dark_Tree.png");
	
	
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
			builderMap.getTile(x, y).setObject(true, newTree, 2, 3, Settings.TREE_WIDTH, Settings.TREE_HEIGHT);
			builderMap.getTile(x, y+1).setWalkable(false);
			builderMap.getTile(x, y+2).setWalkable(false);
			builderMap.getTile(x+1, y+1).setWalkable(false);
			builderMap.getTile(x+1, y+2).setWalkable(false);
			builderMap.getTile(x, y+3).setRenderInFront(true);
			builderMap.getTile(x+1, y+3).setRenderInFront(true);
			builderMap.getTile(x-1, y+3).setRenderInFront(true);
			builderMap.getTile(x+2, y+3).setRenderInFront(true);
		}
		
		

		
	
	}
	
}
