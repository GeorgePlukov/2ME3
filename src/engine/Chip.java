package engine;

enum ChipColor{
	RED, BLUE
}

public class Chip 
{
	//Declare Variables & Objects
	private int x, y;		//X & Y Coordinates
	private ChipColor color;
	
	public Chip(int x, int y, int sx, int sy, ChipColor color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}

	
	public ChipColor getColor()
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
