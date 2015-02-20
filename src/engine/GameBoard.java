package engine;

import java.io.IOException;

import org.lwjgl.input.Mouse;

public class GameBoard 
{
	
	//Declare Variables & Objects
	char selected;
	boolean valid; 
	SpriteSheet sheet;
	Chip [][] chips;

	
	public GameBoard()
	{
		try 
		{
			sheet = new SpriteSheet("res/Chips.png", 64, 64);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		chips = new Chip [8][8];
	}
	
	public void getInput()
	{
		//Highlight Selected
		if(Mouse.getX() > 0 && Mouse.getY() > 0 && Mouse.getX() < 64 && Mouse.getY() < 64 && Mouse.isButtonDown(0))
		{
			selected = 'b';
		}
		else if(Mouse.getX() > 64*8 && Mouse.getY() > 0 && Mouse.getX() < 64*8+64 && Mouse.getY() < 64 && Mouse.isButtonDown(0))
		{
			selected = 'r';
		}
		
		//Get Mouse Input for Game Board Tiles
		for (int i = 64; i < 512; i+=64)
		{
			for(int j = 64; j < 512; j+=64)
			{
				if(Mouse.getX() > i && Mouse.getY() > j && Mouse.getX() < i + 64 && Mouse.getY() < j + 64 && Mouse.isButtonDown(0))
				{
					if(selected == 'b')
					{
						chips[(i/64)-1][(j/64)-1] = new Chip(i, j, 64, 64, 'b', sheet);
					}
					else if(selected == 'r')
					{
						chips[(i/64)-1][(j/64)-1] = new Chip(i, j, 64, 64, 'r', sheet);
					}
				}
			}
		}
		
	}
	
	public void update()
	{
		System.out.println(checkValid());
	}
	
	//Render The Game Board With Tiles
	public void render()
	{
		//Draws Board
		for(int i =  1; i < 8; i++)
		{
			for(int j = 1; j < 8; j++)
			{
				sheet.draw(i*64, j*64, 2, 0, true);
				sheet.draw(i*64, j*64, 2, 1, true);
			}
		}
		
		//Draws Chip Options
		sheet.draw(0, 0, 0, 0, true);
		sheet.draw(64*8, 0, 1, 0, true);
		
		//Draw Outline for Selected Chip
		if(selected == 'b')
		{
			sheet.draw(0, 0, 0, 1, true);
		}
		if(selected == 'r')
		{
			sheet.draw(64*8, 0, 0, 1, true);
		}
		
		//Draw Chips in Chip Matrix
		for(int i = 0; i < chips.length; i++)
		{
			for(int j = 0; j < chips.length; j++)
			{
				if(chips[i][j] != null)
					chips[i][j].render();
			}
		}
	}
	
	
	public boolean checkValid()
	{
		return checkGravity() && checkChipNums();
	}
	
	private boolean checkGravity()
	{
		for(int y = chips.length-1; y > 1; y--)
		{
			for(int x = 0; x < chips.length; x++)
			{
				if(chips[x][y] != null && chips[x][y-1] == null)
					return false;
				
			}
		}
		
		return true;
	}
	
	private boolean checkChipNums()
	{
		int blueCount = 0, redCount = 0;
		for(int i = 0; i < chips.length; i++)
		{
			for(int j = 0; j < chips.length; j++)
			{
				if(chips[i][j] != null)
				{
					if(chips[i][j].getColor() == 'b')
						blueCount++;
					else if(chips[i][j].getColor() == 'r')
						redCount++;
				}
			}
		}
		if(blueCount == (redCount - 1) || redCount == (blueCount - 1))
			return true;
		if((blueCount == 1 && redCount == 0) || (redCount == 1 && blueCount == 0) || (redCount == 0 && blueCount == 0))
			return true;
		return false;
	}
}