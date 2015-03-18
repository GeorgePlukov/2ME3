package engine;

import engine.ChipColor.Color;

abstract class Logic {
	

	//Checks who has won tha game by checking for connect 4 at each chip. 
	public static Color checkWin(Chip [][] chips)
	{
		for(int i = 0; i < chips.length; i++)
			for(int j = 0; j < chips[0].length; j++)
			{
				Color c = check(chips, i, j);
				if(c != null)
					return c;
			}
		return null;
	}
	
	//Checks If Chip Is Connected
	private static Color check(Chip [][] chips, int x, int y)
	{
		boolean win = false;
		
		win = linearMatch(chips, x, y, 1, 0) //Check Horizontal
		|| linearMatch(chips, x, y, 0, 1)	//Check Vertical
		|| linearMatch(chips, x, y, 1, 1)	//Check Diagonal Up
		|| linearMatch(chips, x, y, 1, -1);	//Check Diagonal Down
		
		
		
		if(win)
			return chips[x][y].getColor();
		return null;
	}
	
	//Checks if 4 car connected given a certain step on X and Y where stepX and stepY must be -1, 0, 1.
	private static boolean linearMatch(Chip [][] chips, int x, int y, int stepX, int stepY)
	{
		
		try
		{
			Color val = chips[x][y].getColor();
			for (int i = 1; i < 4; ++i)
				if (chips[x + i * stepX][y + i * stepY].getColor() != val)
					return false;
		}
		catch(Exception e)
		{
			return false;
		}
		return true;

	}
	
	//Checks if the game is a tie (game board is full)
	public static boolean isTie(Chip [][] chips)
	{
		for(int i = 0; i < chips.length; i++)
			for(int j = 0; j < chips[0].length; j++)
				if(chips[i][j] == null)
					return false;
		return true;
	}
	
}
