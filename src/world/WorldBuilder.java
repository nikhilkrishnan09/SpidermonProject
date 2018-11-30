package world;

import com.badlogic.gdx.graphics.Texture;

import spidermon.Settings;

public class WorldBuilder {
	
	private static TileMap builderMap;
	static Texture tree = new Texture ("sprites/Tree1.png");
	static Texture newTree = new Texture ("sprites/Tree2.png");
	
	public static void setMap (TileMap tileMap) {
		builderMap = tileMap;
		build();
	}
	
	public static void build() {
		builderMap.getTile(5, 5).setObject(true, newTree, 2, 3, 50, 85);
		builderMap.getTile(5, 6).setWalkable(false);
		builderMap.getTile(5, 7).setWalkable(false);
		builderMap.getTile(6, 6).setWalkable(false);
		builderMap.getTile(6, 7).setWalkable(false);
		builderMap.getTile(5, 8).setRenderInFront(true);
		builderMap.getTile(6, 8).setRenderInFront(true);
		builderMap.getTile(4, 8).setRenderInFront(true);
		builderMap.getTile(7, 8).setRenderInFront(true);
		
		
		
		builderMap.getTile(10,10).setObject(true, tree, 2, 2, Settings.TREE_WIDTH, Settings.TREE_HEIGHT);
		builderMap.getTile(10, 11).setWalkable(false);
		builderMap.getTile(11, 11).setWalkable(false);
		
		builderMap.getTile(10,5).setObject(false, tree, 2, 2, Settings.TREE_WIDTH, Settings.TREE_HEIGHT);
		builderMap.getTile(10, 6).setWalkable(false);
		builderMap.getTile(11, 5).setWalkable(false);
		builderMap.getTile(11, 6).setWalkable(false);
		
		builderMap.getTile(9, 7).setRenderInFront(true);
		builderMap.getTile(10, 7).setRenderInFront(true);
		builderMap.getTile(11, 7).setRenderInFront(true);
		builderMap.getTile(12, 7).setRenderInFront(true);
		
		
	
	}
	
}
