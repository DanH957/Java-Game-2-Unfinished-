package com.Game.Model;

import com.Game.Util.AnimationSet;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

public class Actor implements YSortable {
	
	private TileMap map;
	private int x;
	private int y;
	private DIRECTION facing;
	private World world;
	
	private float worldX, worldY; //player location
	
	
	//state specific fields
	private int srcX, srcY;
	private int destX, destY;
	private float animTimer;
	private float ANIM_TIME = 0.3f;
	private float REFACE_TIME = 0.1f;
	
	private float WalkTimer;
	private boolean moveRequestThisFrame;
	
	private ACTOR_STATE state;		
	
	private AnimationSet animations;
	
	public Actor(World world, int x, int y, AnimationSet animations) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.worldX = x;
		this.worldY = y;
		this.animations = animations;
		this.state = ACTOR_STATE.STANDING;
		this.facing = DIRECTION.SOUTH;
	}
	
	public enum ACTOR_STATE{
		WALKING,
		STANDING,
		REFACING,
		;
	}
	
	public void update(float delta) {
		if (state == ACTOR_STATE.WALKING) {
			animTimer += delta;
			WalkTimer += delta;
			worldX = Interpolation.linear.apply(srcX, destX, animTimer/ANIM_TIME);
			worldY = Interpolation.linear.apply(srcY, destY, animTimer/ANIM_TIME);
			if (animTimer > ANIM_TIME) {
				float leftOverTime = animTimer - ANIM_TIME;
				finishMove();
				if(moveRequestThisFrame) {
					move(facing);
					animTimer += leftOverTime;
					worldX = Interpolation.linear.apply(srcX, destX, animTimer/ANIM_TIME);
					worldY = Interpolation.linear.apply(srcY, destY, animTimer/ANIM_TIME);
				}else {
					WalkTimer = 0f;
				}
			}
		}
		if(state == ACTOR_STATE.REFACING) {
			animTimer += delta;
			if(animTimer > REFACE_TIME) {
				state = ACTOR_STATE.STANDING;
			}
		}
		moveRequestThisFrame = false;
	}
	
	public void reface(DIRECTION dir) {
		if(state != ACTOR_STATE.STANDING) {
			return;
		}
		if(facing == dir) {
			return;
		}
		facing = dir;
		state = ACTOR_STATE.REFACING;
		animTimer = 0f;
	}
	
	public boolean move(DIRECTION dir) {
		if(state == ACTOR_STATE.WALKING) {
			if(facing == dir) {
				moveRequestThisFrame = true;
			}
			return false;
		}
		//edge of world test
		if(x+dir.getDx() >= world.getmap().getwidth() || x+dir.getDx() < 0 || y+dir.getDy()>= world.getmap().getheight() || y+dir.getDy() < 0) {
			reface(dir);
			return false;
		}
		
		if (!world.getmap().getTile(x+dir.getDx(), y+dir.getDy()).walkable()) {
			reface(dir);
			return false;
		}
		//actor test
		if(world.getmap().getTile(x+dir.getDx(), y+dir.getDy()).getActor() != null) {
			reface(dir);
			return false;
		}
		
		
		//is there an object there
		if (world.getmap().getTile(x+dir.getDx(), y+dir.getDy()).getObject() != null) {
			WorldObject o = world.getmap().getTile(x+dir.getDx(), y+dir.getDy()).getObject();
			if (!o.isWalkable()) {
				reface(dir);
				return false;
			}
			
			
		}
		initializeMove(dir);
		world.getmap().getTile(x, y).setActor(null);
		x += dir.getDx();
		y += dir.getDy();
		world.getmap().getTile(x, y).setActor(this);
		return true;
		}
	

	private void initializeMove(DIRECTION dir) {
		this.facing = dir;
		this.srcX = x;
		this.srcY = y;
		this.destX = x+dir.getDx();
		this.destY = y+dir.getDy();
		this.worldX = x;
		this.worldY =y;
		animTimer = 0f;
		state = ACTOR_STATE.WALKING;
		
	}
	
	private void finishMove() {
		state = ACTOR_STATE.STANDING;
		this.worldX = destX;
		this.worldY = destY;
		this.srcX = 0;
		this.srcY = 0;
		this.destX = 0;
		this.destY = 0;
	}
	
	
	
	public float GetWorldX() {
		return worldX;
	}

	public float GetWorldY() {
		return worldY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public TextureRegion getSprite() {
		if(state == ACTOR_STATE.WALKING) {
			return animations.GetWalking(facing).getKeyFrame(WalkTimer);
		}else if(state == ACTOR_STATE.STANDING) {
			return animations.getStanding(facing);		
		}else if(state == ACTOR_STATE.REFACING) {
			return animations.GetWalking(facing).getKeyFrames()[0];
		}
		return animations.getStanding(DIRECTION.SOUTH);
	}


	
	
	@Override
	public float GetSizeX() {
		// TODO Auto-generated method stub
		return 1f;
	}

	@Override
	public float GetSizeY() {
		// TODO Auto-generated method stub
		return 1.5f;
	}
}
