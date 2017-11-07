package com.Game.Util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerTool {

	public static void main(String[] args){
		TexturePacker.process(
				"res/TileUnpacked",
				"res/TilesPacked",
				"Tiletextures");
		
	}
}
