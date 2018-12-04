package world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.badlogic.gdx.graphics.Texture;

import spidermon.Settings;

public class WorldBuilder {
	
	private static TileMap builderMap;
	static Texture tree = new Texture ("sprites/Dark_Tree.png");
	
	
	public static void setMap (TileMap tileMap)throws Exception {
		  builderMap = tileMap;
		
		  File file = new File("WorldText.txt"); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String st; 
		  while ((st = br.readLine()) != null) { 
		    //System.out.println(st);
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
		
		
//		builderMap.getTile(10,10).setObject(true, tree, 2, 2, Settings.TREE_WIDTH, Settings.TREE_HEIGHT);
//		builderMap.getTile(10, 11).setWalkable(false);
//		builderMap.getTile(11, 11).setWalkable(false);
//		
//		builderMap.getTile(10,5).setObject(false, tree, 2, 2, Settings.TREE_WIDTH, Settings.TREE_HEIGHT);
//		builderMap.getTile(10, 6).setWalkable(false);
//		builderMap.getTile(11, 5).setWalkable(false);
//		builderMap.getTile(11, 6).setWalkable(false);
//		
//		builderMap.getTile(9, 7).setRenderInFront(true);
//		builderMap.getTile(10, 7).setRenderInFront(true);
//		builderMap.getTile(11, 7).setRenderInFront(true);
//		builderMap.getTile(12, 7).setRenderInFront(true);
		
		
	
	}
	
}
