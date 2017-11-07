package com.Game.Model;


public class Tile {

	private TERRAIN terrain;
	private WorldObject object;
	private Actor actor;
	
	private boolean walkable = true;

	public Tile(TERRAIN terrain) {
		this.terrain = terrain;
	}
	
	public Tile(TERRAIN terrain, boolean walkable) {
		this.terrain = terrain;
		this.walkable = walkable;
	}
	
	public TERRAIN getTerrain() {
		return terrain;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public WorldObject getObject() {
		return object;
	}
	
	public void setObject(WorldObject object) {
		this.object = object;
	}
	
	public boolean walkable() {
		return walkable;
	}

	
}
