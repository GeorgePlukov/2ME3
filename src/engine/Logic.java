package engine;

import engine.ChipColor.Color;

abstract class Logic {
	
	
	public static boolean addChip(Chip [][] chips, int column, Color c)
	{
		
		for(int y = 0; y < chips.length-1; y++)
		{
			if(chips[column][y] == null)
			{
				chips[column][y] = new Chip(column * 64 + 64, y * 64 + 64, c);
				return true;
				
			}
		}
		
		return false;
		
	}
	
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
	
	public static Color check(Chip [][] chips, int x, int y)
	{
		boolean win = false;
		
		win = linearMatch(chips, x, y, 1, 0)
		|| linearMatch(chips, x, y, -1, 0)
		|| linearMatch(chips, x, y, 0, 1)
		|| linearMatch(chips, x, y, 0, -1)
		|| linearMatch(chips, x, y, 1, 1)
		|| linearMatch(chips, x, y, 1, -1)
		|| linearMatch(chips, x, y, -1, 1)
		|| linearMatch(chips, x, y, -1, -1);
		
		
		
		if(win)
			return chips[x][y].getColor();
		return null;
	}
	
	public static boolean linearMatch(Chip [][] chips, int x, int y, int stepX, int stepY)
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
	
	public static boolean isTie(Chip [][] chips)
	{
		for(int i = 0; i < chips.length; i++)
			for(int j = 0; j < chips[0].length; j++)
				if(chips[i][j] == null)
					return false;
		return true;
	}
	
}
