package engine;

import engine.ChipColor.Color;



public class Chip 
{
	//Declare Variables & Objects
	private int x, y;		//X & Y Coordinates
	private Color color;
	
	public Chip(int x, int y, int sx, int sy, Color blue)
	{
		this.x = x;
		this.y = y;
		this.color = blue;
	}

	
	public Color getColor()
	{
		return color;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
