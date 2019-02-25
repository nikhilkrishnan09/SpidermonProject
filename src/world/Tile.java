package world;

import com.badlogic.gdx.graphics.Texture;

import controller.Player;

public class Tile {
	
	//Tile object constructor
	//A tile is a single walkable space on the map
	//The tile has several properties that impact player interaction 
	//The tile object works in tandem with the WorldObject to control player interaction
	Player player;
	int type;
	boolean hasObject;
	boolean walkable = true;
	WorldObject object;
	
	//renderInFront variable allows for 3-Dimensional effect depending on if Spiderman is obscured by 
	//the object or renders in front and blocks part of the object
	boolean renderInFront = false;
	boolean fightTile = false;
	String enemyType;

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
	
	public void setHasObject(boolean hasObject) {
		this.hasObject = hasObject;
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

	public boolean isFightTile() {
		return fightTile;
	}


	public void setFightTile(boolean fightTile) {
		this.fightTile = fightTile;
	}
	
	public String getEnemyType() {
		return enemyType;
	}


	public void setEnemyType(String enemyType) {
		this.enemyType = enemyType;
	}

	//a tile may have an object, declared here
	public void setObject (boolean walkable, Texture texture, int widthTiles, int heightTiles, int width, int height) {
		object = new WorldObject(walkable, texture, widthTiles, heightTiles, width, height);
		hasObject = true;
	}
	
}
