package com.Game.MainScreen;

import com.Game.Main.MainGame;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen{

	
	private MainGame app;
	
	public AbstractScreen(MainGame app) {
		this.app = app;
	}
	
	@Override
	public abstract void dispose();
		

	@Override
	public abstract void hide();

	@Override
	public abstract void pause();

	@Override
	public abstract void render(float delta);

	@Override
	public abstract void resize(int width, int height);

	@Override
	public abstract void resume();

	@Override
	public abstract void show();

	public MainGame getApp() {
		return app;
	}

	

}
