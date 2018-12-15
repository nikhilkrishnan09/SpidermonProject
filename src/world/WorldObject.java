package world;

import com.badlogic.gdx.graphics.Texture;

public class WorldObject {
	
	 boolean walkable;
	 Texture texture;
	 int width;
	 int height;
	 private int widthTiles;
	 private int heightTiles;
	 
	 

	public WorldObject(boolean walkable, Texture texture, int widthTiles, int heightTiles, int width, int height) {
		  this.walkable = walkable;
		  this.texture = texture;
		  this.widthTiles = widthTiles;
		  this.heightTiles = heightTiles;
		  this.width = width;
		  this.height = height;
	 }



	public boolean isWalkable() {
		return walkable;
	}



	public Texture getTexture() {
		return texture;
	}



	public int getWidth() {
		return width;
	}



	public int getHeight() {
		return height;
	}
	
	public int getWidthTiles() {
		return widthTiles;
	}



	public int getHeightTiles() {
		return heightTiles;
	}


}
