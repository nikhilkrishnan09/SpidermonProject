package spidermon.util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

//this class packs all of the different sprites used in the frame-by-frame animation
//into an atlas that can be read by the game

public class TexturePackerTool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TexturePacker.process("sprites/unpacked/", "sprites/packed/", "textures");
	}

}
