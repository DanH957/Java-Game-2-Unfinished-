package com.Game.Controller;


import com.Game.Model.Actor;
import com.Game.Model.DIRECTION;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class PlayerController extends InputAdapter{
	

	
	private Actor player;
	
	private boolean[] buttonPress;
	private float[] buttonTimer;
	
	private  float WALK_REFACE_THRESHOLD = 0.07f;
	
	public PlayerController(Actor p) {
		this.player = p;
		buttonPress = new boolean[DIRECTION.values().length];
		buttonPress[DIRECTION.NORTH.ordinal()] = false;
		buttonPress[DIRECTION.SOUTH.ordinal()] = false;
		buttonPress[DIRECTION.EAST.ordinal()] = false;
		buttonPress[DIRECTION.WEST.ordinal()] = false;
		buttonTimer = new float[DIRECTION.values().length];
		buttonTimer[DIRECTION.NORTH.ordinal()] = 0f;
		buttonTimer[DIRECTION.SOUTH.ordinal()] = 0f;
		buttonTimer[DIRECTION.EAST.ordinal()] = 0f;
		buttonTimer[DIRECTION.WEST.ordinal()] = 0f;
	}

	public boolean keyDown(int keycode) {
		if (keycode == Keys.UP) {
			buttonPress[DIRECTION.NORTH.ordinal()] = true;
		}
		if (keycode == Keys.DOWN) {
			buttonPress[DIRECTION.SOUTH.ordinal()] = true;
		}
		if (keycode == Keys.LEFT) {
			buttonPress[DIRECTION.WEST.ordinal()] = true;
}
		if (keycode == Keys.RIGHT) {
			buttonPress[DIRECTION.EAST.ordinal()] = true;
}
		return false;
		}

		
		public boolean keyUp(int keycode) {
			if (keycode == Keys.UP) {
				releaseDirection(DIRECTION.NORTH);
			}
			if (keycode == Keys.DOWN) {
				releaseDirection(DIRECTION.SOUTH);
			}
			if (keycode == Keys.LEFT) {
				releaseDirection(DIRECTION.WEST);
	}
			if (keycode == Keys.RIGHT) {
				releaseDirection(DIRECTION.EAST);
	}  
		return false;
		
	}
		
		public void update(float delta) {
			if (buttonPress[DIRECTION.NORTH.ordinal()]) {
				updateDirection(DIRECTION.NORTH, delta);
				return;
			}
			if (buttonPress[DIRECTION.SOUTH.ordinal()]) {
				updateDirection(DIRECTION.SOUTH, delta);
				return;
			}
			if (buttonPress[DIRECTION.WEST.ordinal()]) {
				updateDirection(DIRECTION.WEST, delta);
				return;
			}
			if (buttonPress[DIRECTION.EAST.ordinal()]) {
				updateDirection(DIRECTION.EAST, delta);
				return;
			}
		}
		
		public void updateDirection(DIRECTION dir, float delta) {
			buttonTimer[dir.ordinal()] += delta;
			considerMove(dir);
		}
		
		public void releaseDirection(DIRECTION dir) {
			buttonPress[dir.ordinal()] = false;
			considerReface(dir);
			buttonTimer[dir.ordinal()] = 0f;
			
		}
		
		public void considerMove(DIRECTION dir) {
			if(buttonTimer[dir.ordinal()] > WALK_REFACE_THRESHOLD) {
				player.move(dir);
			}
		}
		
		public void considerReface(DIRECTION dir) {
			if(buttonTimer[dir.ordinal()] < WALK_REFACE_THRESHOLD) {
				player.reface(dir);
			}
		}
}
