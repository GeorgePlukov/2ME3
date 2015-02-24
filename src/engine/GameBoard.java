package engine;

import java.io.IOException;

import org.lwjgl.input.Mouse;

public class GameBoard 
{
	
	//Declare Variables & Objects
	char selected;		//Currently Selected Tile Color for Board
	SpriteSheet sheet;	//Sprite Sheet with GameBoard and Chip Tiles
	Chip [][] chips;	//Array to Hold Chips in GameBoard

	
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
		
		//Initialize 7x6 GameBoard
		chips = new Chip [7][6];
		
		
		//Start GameBoard with Random Chip Selected. 
		
		if(Math.random() > 0.5)
			selected = 'b';
		else
			selected = 'r';
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
		
		for (int i = 64; i < chips.length*64 + 64; i+=64)
		{
			for(int j = 64; j < chips[0].length*64 + 64; j+=64)
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
		
	}
	
	//Render The Game Board With Tiles
	public void render()
	{
		
		
		//Draws Chip Options
		sheet.draw(0, 0, 0, 0);
		sheet.draw(64*8, 0, 1, 0);
		
		//Draw Outline for Selected Chip (Green)
		if(selected == 'b')
		{
			sheet.draw(0, 0, 0, 1);
		}
		if(selected == 'r')
		{
			sheet.draw(64*8, 0, 0, 1);
		}
		
		//Draw Chips in Chip Matrix
		for(int i = 0; i < chips.length; i++)
		{
			for(int j = 0; j < chips[0].length; j++)
			{
				if(chips[i][j] != null)
					chips[i][j].render();
			}
		}
		
		//Draws Validity to Screen
		if(checkValid())
		{
			sheet.draw(256, 0, 0, 2);
		}
		else 
			sheet.draw(256, 0, 0, 3);
		
		//Draws Board
		for(int i =  1; i <= chips.length; i++)
		{
			for(int j = 1; j <= chips[0].length; j++)
			{
				sheet.draw(i*64, j*64, 2, 0);
				sheet.draw(i*64, j*64, 2, 1);
			}
		}
	}
	
	
	public boolean checkValid()
	{
		return checkGravity() && checkChipNums();
	}
	
	private boolean checkGravity()
	{
		for(int y = chips[0].length-1; y > 0; y--)
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
			for(int j = 0; j < chips[0].length; j++)
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
		if((blueCount == 1 && redCount == 0) || (redCount == 1 && blueCount == 0) || (redCount == 0 && blueCount == 0) || (redCount == blueCount))
			return true;
		return false;
	}
}
