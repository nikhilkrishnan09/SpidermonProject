package world;

import com.badlogic.gdx.graphics.Texture;

import controller.Player;

public class Tile {
	
	//Tile object constructor
	Player player;
	int type;
	boolean hasObject;
	boolean walkable = true;
	WorldObject object;
	boolean renderInFront = false;


	public Tile (int t) {
		type = t;
	}


	public int getTileType() {
		return type;
	}
 	

	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player actor) {
		this.player = actor;
	}
	
	public boolean HasObject() {
		return hasObject;
	}
	
	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public WorldObject getObject() {
		return object;
	}

	
	public boolean isRenderInFront() {
		return renderInFront;
	}


	public void setRenderInFront(boolean renderInFront) {
		this.renderInFront = renderInFront;
	}


	public void setObject (boolean walkable, Texture texture, int widthTiles, int heightTiles, int width, int height) {
		object = new WorldObject(walkable, texture, widthTiles, heightTiles, width, height);
		hasObject = true;
	}
	
}
