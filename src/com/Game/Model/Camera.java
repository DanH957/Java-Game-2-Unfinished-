package com.Game.Model;

public class Camera {

	private float CameraX = 0f;
	private float CameraY = 0f;
	
	public void update(float newCamX, float newCamY) {
		this.CameraX = newCamX;
		this.CameraY = newCamY;
		
	}

	public float getCameraX() {
		return CameraX;
	}


	public float getCameraY() {
		return CameraY;
	}

	
}
