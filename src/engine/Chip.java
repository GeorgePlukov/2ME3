package engine;

public class Chip 
{
	//Declare Variables & Objects
	private int x, y, sx, sy;
	private char color;
	private SpriteSheet s;
	
	public Chip(int x, int y, int sx, int sy, char color, SpriteSheet s)
	{
		this.x = x;
		this.y = y;
		this.sx = sx; 
		this.sy = sy;
		this.color = color;
		this.s = s;
	}
	
	public void render()
	{
		//Draw Appropriate Color Chip
		if(color == 'b')
		{
			s.draw(x, y, 0, 0, true);
		}
		else if(color == 'r')
		{
			s.draw(x, y, 1, 0, true);
		}
	}
}
