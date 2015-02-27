package engine;

abstract class Logic {
	
	public static boolean checkValidity(Chip [][] chips, boolean [][] errors){
		return checkGravity(chips, errors) && checkChipNums(chips);
	}
	
	// Make sure that all the chips have one supporting them and added errors to the boolean array for any empty space below the tile. 
	private static boolean checkGravity(Chip[][] chips, boolean [][] errors){
		boolean gravity = true;
		resetErrors(errors);
		for(int y = chips[0].length-1; y > 0; y--)
		{
			for(int x = 0; x < chips.length; x++)
			{
				if(chips[x][y] != null && chips[x][y-1] == null)
				{
					gravity = false;
					
					for(int i = y-1; i >= 0 && chips[x][i] == null; i--)
					{
						errors[x][i] = true;
					}
				}
				
			}
		}
		
		return gravity;
	}
	private static boolean checkChipNums(Chip[][] chips)
	{
		int blueCount = 0, redCount = 0;
		for(int i = 0; i < chips.length; i++)
		{
			for(int j = 0; j < chips[0].length; j++)
			{
				if(chips[i][j] != null)
				{
					switch(chips[i][j].getColor()){
					case BLUE:
						blueCount++;
						break;
					case RED:
						redCount++;
						break;
					}
				}
			}
		}
		if(blueCount == (redCount - 1) || redCount == (blueCount - 1))
			return true;
		if((blueCount == 1 && redCount == 0) || (redCount == 1 && blueCount == 0) || (redCount == 0 && blueCount == 0) || (redCount == blueCount))
			return true;
		return false;
	}
	
	private static void resetErrors(boolean [][] errors)
	{
		for(int i = 0; i < errors.length; i++)
			for(int j = 0; j < errors[0].length; j++)
				errors[i][j] = false;
	}
	
}
