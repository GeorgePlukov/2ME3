package engine;

public class Chip 
{
	//Declare Variables & Objects
	private int x, y;		//X & Y Coordinates
	private char color;		//Character 'b' or 'r' for Blue or Red Chip. 
	private SpriteSheet s;	//SpriteSheet for Image.
	
	public Chip(int x, int y, int sx, int sy, char color, SpriteSheet s)
	{
		this.x = x;
		this.y = y;
		this.color = color;
		this.s = s;
	}
	
	public void render()
	{
		//Draw Appropriate Color Chip
		
		//Draws Blue Chip
		if(color == 'b')
		{
			s.draw(x, y, 0, 0);
		}
		//Draws Red Chip
		else if(color == 'r')
		{
			s.draw(x, y, 1, 0);
		}
	}
	
	public char getColor()
	{
		return color;
	}
}
