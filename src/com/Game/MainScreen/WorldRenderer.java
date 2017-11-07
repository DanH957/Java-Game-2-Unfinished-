package com.Game.MainScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.Game.Main.Settings;
import com.Game.Model.Actor;
import com.Game.Model.Camera;
import com.Game.Model.TERRAIN;
import com.Game.Model.World;
import com.Game.Model.WorldObject;
import com.Game.Model.YSortable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldRenderer {

	private AssetManager assetManager;
	private World world;
	
	private Texture Grass1;
	private Texture Grass2;
	private Texture test;
	private TextureRegion Path;
	
	private List<Integer> renderedObjects = new ArrayList<Integer>();
	private List<YSortable> forRendering = new ArrayList<YSortable>();
	
	public WorldRenderer(AssetManager assetManager, World world) {
		this.assetManager = assetManager;
		this.world = world;
		Grass1 = assetManager.get("res/TileUnpacked/Grass1.png", Texture.class);
		Grass2 = assetManager.get("res/TileUnpacked/Grass2.png", Texture.class);
		test = assetManager.get("res/TileUnpacked/indoor_tiles.png", Texture.class);
	}
	
	
	public void render(SpriteBatch batch, Camera camera) {
		 float worldStartX = Gdx.graphics.getWidth()/2 - camera.getCameraX()*Settings.SCALED_TILE_SIZE;
	        float worldStartY = Gdx.graphics.getHeight()/2 - camera.getCameraY()*Settings.SCALED_TILE_SIZE;
	        
	        for(int x = 0; x < 20; x++) {
	        	for(int y = 0; y <20; y++) {
	        		Texture render;
	        		
	        		if(world.getmap().getTile(x, y).getTerrain() == TERRAIN.GRASS1) {
	        			render = Grass1;
	        		}else {
	        			render = Grass2;
	        		}
	        		batch.draw(render,
	        				worldStartX+x*Settings.SCALED_TILE_SIZE,
	        				worldStartY+y*Settings.SCALED_TILE_SIZE,
	        				Settings.SCALED_TILE_SIZE,
	        				Settings.SCALED_TILE_SIZE);
	        		
	        	}
	        }
	        
	        TextureAtlas atlas = assetManager.get("res/TilesPacked/Tiletextures.atlas", TextureAtlas.class);
	        Path = atlas.findRegion("Path");
	        
	        for(int y = 16; y < 25; y++) {
	        batch.draw(Path,
    				worldStartX+12*Settings.SCALED_TILE_SIZE,
    				worldStartY+y*Settings.SCALED_TILE_SIZE,
    				Settings.SCALED_TILE_SIZE,
    				Settings.SCALED_TILE_SIZE);
	        }
	       
	        
			
	        for(int x = 0; x < world.getmap().getwidth(); x++) {
	        	for(int y = 0; y < world.getmap().getheight(); y++) {
	        		if(world.getmap().getTile(x, y).getActor() != null) {
	        			Actor actor = world.getmap().getTile(x, y).getActor();
	        			forRendering.add(actor);
	        		}
	        		
	        		if(world.getmap().getTile(x, y).getObject() != null) {
	        			WorldObject object = world.getmap().getTile(x, y).getObject();
	        			if(renderedObjects.contains(object.hashCode())) {
	        				continue;
	        			}
	        			if (object.isWalkable()) {  		// if it's walkable, draw it right away
							batch.draw(object.getSprite(),	// chances are it's probably something on the ground
									worldStartX+object.GetWorldX()*Settings.SCALED_TILE_SIZE,
									worldStartY+object.GetWorldY()*Settings.SCALED_TILE_SIZE,
									Settings.SCALED_TILE_SIZE*object.GetSizeX(),
									Settings.SCALED_TILE_SIZE*object.GetSizeY());
							continue;
						} else {	// add it to the list of YSortables
							forRendering.add(object);
							renderedObjects.add(object.hashCode());
						}
	        		
	        	}
	        }
	        }
		
	        Collections.sort(forRendering, new WorldObjectYComparator());
	        Collections.reverse(forRendering);
	        
	        for (YSortable loc : forRendering) {
	        	batch.draw(loc.getSprite(),
	        			worldStartX+loc.GetWorldX()*Settings.SCALED_TILE_SIZE,
        				worldStartY+loc.GetWorldY()*Settings.SCALED_TILE_SIZE,
        				Settings.SCALED_TILE_SIZE*loc.GetSizeX(),
        				Settings.SCALED_TILE_SIZE*loc.GetSizeY());
	        			
	        			
	        }
	        renderedObjects.clear();
			forRendering.clear();
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
}
