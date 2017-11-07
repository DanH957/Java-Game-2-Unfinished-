package com.Game.Model;

public class TileMap {
	
	private int width, height;
	private Tile[][] tiles;

	public TileMap(int width, int height) {
		this.width = width;
		this.height = height;
		
		tiles = new Tile[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(Math.random() > 0.5d) {
					tiles[x][y] = new Tile(TERRAIN.GRASS1);
				}else {
					tiles[x][y] = new Tile(TERRAIN.GRASS2);
				}
				
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public int getwidth() {
		return width;
	}
	
	public int getheight() {
		return height;
	}
}
