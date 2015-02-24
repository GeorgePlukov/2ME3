package engine;

abstract class Logic {
	
	// Make sure that all the chips have one supporting them
	public static boolean checkGravity(Chip[][] chips){
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
	public static boolean checkChipNums(Chip[][] chips)
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
