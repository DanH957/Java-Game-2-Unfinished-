package com.Game.MainScreen;

import com.Game.Controller.PlayerController;
import com.Game.Main.MainGame;
import com.Game.Main.Settings;
import com.Game.Model.Actor;
import com.Game.Model.Camera;
import com.Game.Model.TERRAIN;
import com.Game.Model.TileMap;
import com.Game.Model.World;
import com.Game.Model.WorldObject;
import com.Game.Util.AnimationSet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen extends AbstractScreen {
	
	private PlayerController controller;
	private Camera camera;
	private Actor player;
	private World world;
	private Animation flowerAnimation;
	

	private SpriteBatch batch;
	
	private WorldRenderer worldRenderer;
	

	
	public GameScreen(MainGame app) {
		super(app);
		
		batch = new SpriteBatch();
		
		TextureAtlas atlas = app.getAssetManager().get("res/packed/textures.atlas", TextureAtlas.class);
		
		TextureAtlas atlas1 = app.getAssetManager().get("res/TilesPacked/Tiletextures.atlas", TextureAtlas.class);
		flowerAnimation = new Animation(0.8f, atlas1.findRegions("flowers"), PlayMode.LOOP_PINGPONG);
		
		AnimationSet animations = new AnimationSet(
				new Animation(0.3f/2f, atlas.findRegions("Player_Walk_South"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("Player_Walk_North"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("Player_Walk_East"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("Player_Walk_West"), PlayMode.LOOP_PINGPONG),
				atlas.findRegion("Player_Stand_South"),
				atlas.findRegion("Player_Stand_North"),
				atlas.findRegion("Player_Stand_East"),
				atlas.findRegion("Player_Stand_West")
				
				);
		
		camera = new Camera();
		world = new World(50,50);
		player = new Actor(world, 4, 4, animations);
		world.addActor(player);
		
		
		addHouse(10,10);
		addFlowers(world,5,5);
		
		
		
		for (int xi = 0; xi < 20; xi++) {
			for (int yi = 0; yi < 20; yi++) {
				if (xi==0 || xi==18) {
					if (world.getmap().getTile(xi, yi).getObject() == null) {
						addTree(world,xi,yi);
					}
				}
				if (yi==0||yi==18 & xi != 11 & xi != 12) {
					if (world.getmap().getTile(xi, yi).getObject() == null) {
						addTree(world,xi,yi);
					}
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			int xr = MathUtils.random(19);
			int yr = MathUtils.random(19);
			if (world.getmap().getTile(xr, yr).getObject() == null) {
				addFlowers(world,xr,yr);
			}
		}
		
		controller = new PlayerController(player);
		worldRenderer = new WorldRenderer(getApp().getAssetManager(), world);
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render(float delta) {
		controller.update(delta);
		
		
		
        batch.begin();
        worldRenderer.render(batch, camera);
		batch.end();
		
		camera.update(player.GetWorldX()+0.5f, player.GetWorldY()+0.5f);
		world.update(delta);
		
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void resume() {
	
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(controller);
	}
	
	private void addHouse(int x, int y) {
		TextureAtlas atlas = getApp().getAssetManager().get("res/packed/textures.atlas", TextureAtlas.class);
		TextureRegion houseRegion = atlas.findRegion("small_house");
		GridPoint2[] gridArray = new GridPoint2[5*4-1];
		int index = 0;
		for (int loopX = 0; loopX < 5; loopX++) {
			for (int loopY = 0; loopY < 4; loopY++) {
				if (loopX==3&&loopY==0) {
					continue;
				}
				gridArray[index] = new GridPoint2(loopX, loopY);
				index++;
			}
		}
		WorldObject house = new WorldObject(x, y, false, houseRegion, 5f, 5f, gridArray);
		world.addWorldObject(house);

}
	private void addTree(World world, int x, int y) {
		TextureAtlas atlas = getApp().getAssetManager().get("res/TilesPacked/Tiletextures.atlas", TextureAtlas.class);
		TextureRegion treeRegion = atlas.findRegion("large_tree");
		GridPoint2[] gridArray = new GridPoint2[2*2];
		gridArray[0] = new GridPoint2(0,0);
		gridArray[1] = new GridPoint2(0,1);
		gridArray[2] = new GridPoint2(1,1);
		gridArray[3] = new GridPoint2(1,0);
		WorldObject tree = new WorldObject(x, y, false, treeRegion, 2f, 3f, gridArray);
		world.addWorldObject(tree);
	}
	
	private void addFlowers(World world, int x, int y) {
		GridPoint2[] gridArray = new GridPoint2[1];
		gridArray[0] = new GridPoint2(0,0);
		WorldObject flowers = new WorldObject(x, y, true, flowerAnimation, 1f, 1f, gridArray);
		world.addWorldObject(flowers);
	}
}
