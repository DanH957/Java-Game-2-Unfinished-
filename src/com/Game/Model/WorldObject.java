package com.Game.Model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

public class WorldObject implements YSortable{

	private int x, y;
	
	private TextureRegion texture;
	private float SizeX, SizeY;
	
	private List<GridPoint2> tiles;
	private boolean walkable;
	
	private Animation animation;
	private float animationTimer;
	
	public WorldObject(int x, int y, TextureRegion texture, float SizeX, float SizeY, GridPoint2[] tiles) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.SizeX = SizeX;
		this.SizeY = SizeY;
		this.tiles = new ArrayList<GridPoint2>();
		for (GridPoint2 p : tiles) {
			this.tiles.add(p);
		}
		this.walkable = true;
		
	}
	
	public WorldObject(int x, int y, boolean walkable, TextureRegion texture, float SizeX, float SizeY, GridPoint2[]tiles) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.SizeX = SizeX;
		this.SizeY = SizeY;
		this.tiles = new ArrayList<GridPoint2>();
		for (GridPoint2 p : tiles) {
			this.tiles.add(p);
		}
		this.walkable = walkable;
		
	}
	
	public WorldObject(int x, int y, boolean walkable, Animation animation, float sizeX, float sizeY, GridPoint2[] tiles) {
		this.x = x;
		this.y = y;
		this.animation = animation;
		this.animationTimer = 0f;
		this.SizeX = sizeX;
		this.SizeY = sizeY;
		this.tiles = new ArrayList<GridPoint2>();
		for (GridPoint2 p : tiles) {
			this.tiles.add(p);
		}
		this.walkable = walkable;
	}
	
	public void update(float delta) {
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public List<GridPoint2> getTiles() {
		return tiles;
	}

	@Override
	public float GetWorldX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float GetWorldY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public TextureRegion getSprite() {
		if (texture != null) {
			return texture;
		} else {
			return animation.getKeyFrame(animationTimer);
		}
	}

	@Override
	public float GetSizeX() {
		// TODO Auto-generated method stub
		return SizeX;
	}

	@Override
	public float GetSizeY() {
		// TODO Auto-generated method stub
		return SizeY;
	}

	public boolean isWalkable() {
		// TODO Auto-generated method stub
		return walkable;
	}
	
	
}
