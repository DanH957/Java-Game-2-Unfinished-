package com.Game.Main;

import com.Game.MainScreen.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainGame extends Game {
	
	private GameScreen screen;
	
	private AssetManager assetmanager;

	@Override
	public void create() {
		assetmanager = new AssetManager();
		assetmanager.load("res/TileUnpacked/indoor_tiles.png", Texture.class);
		assetmanager.load("res/TileUnpacked/Grass1.png", Texture.class);
		assetmanager.load("res/TileUnpacked/Grass2.png", Texture.class);
		assetmanager.load("res/packed/textures.atlas", TextureAtlas.class);
		assetmanager.load("res/TilesPacked/Tiletextures.atlas", TextureAtlas.class);
		assetmanager.finishLoading();
		
		screen = new GameScreen(this);
		
		this.setScreen(screen);
	}
	
	public void render() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
	
	public AssetManager getAssetManager() {
		return assetmanager;
	}

}
