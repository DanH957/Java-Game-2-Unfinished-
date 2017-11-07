package com.Game.Util;

import java.util.HashMap;
import java.util.Map;

import com.Game.Model.DIRECTION;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationSet {
	
	private Map<DIRECTION, Animation> walking;
	private Map<DIRECTION, TextureRegion> standing;
	
	public AnimationSet(Animation WalkNorth,
			Animation WalkSouth, 
			Animation WalkEast, 
			Animation WalkWest, 
			TextureRegion StandNorth, 
			TextureRegion StandSouth, 
			TextureRegion StandEast, 
			TextureRegion StandWest) {
		walking = new HashMap<DIRECTION, Animation>();
		walking.put(DIRECTION.NORTH, WalkNorth);
		walking.put(DIRECTION.SOUTH, WalkSouth);
		walking.put(DIRECTION.EAST, WalkEast);
		walking.put(DIRECTION.WEST, WalkWest);
		standing = new HashMap<DIRECTION, TextureRegion>();
		standing.put(DIRECTION.NORTH, StandNorth);
		standing.put(DIRECTION.SOUTH, StandSouth);
		standing.put(DIRECTION.EAST, StandEast);
		standing.put(DIRECTION.WEST, StandWest);
	}

	public Animation GetWalking(DIRECTION dir) {
		return walking.get(dir);
	}
	
	public TextureRegion getStanding(DIRECTION dir) {
		return standing.get(dir);
	}
}
