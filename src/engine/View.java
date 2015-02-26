package engine;

public class View {
	private static SpriteSheet sheet = new SpriteSheet("res/Chips.png", 64, 64);	//Sprite Sheet with GameBoard and Chip Tiles

	//Render The Game Board With Tiles
	public static void render(GameBoard game)
	{

		//Draws Chip Options
		sheet.draw(0, 0, 0, 0);
		sheet.draw(64*8, 0, 1, 0);
		
		//Draw Outline for Selected Chip (Green)
		if(game.isRedActive())
			sheet.draw(64*8, 0, 0, 1);

		else
			sheet.draw(0, 0, 0, 1);

		
		Chip [][] chips = game.getChips();
		
		//Draw Chips in Chip Matrix
		for(int i = 0; i < chips.length; i++)
		{
			for(int j = 0; j < chips[0].length; j++)
			{
				if(chips[i][j] != null)
					drawChip(chips[i][j]);
			}
		}
		
		//Draws Validity to Screen
		if(Logic.checkValidity(game.getChips(), game.getErrors()))
		{
			sheet.draw(256, 0, 0, 2);
		}
		else 
			sheet.draw(256, 0, 0, 3);
		
		//Draws Errors
		
		
		//Draws Board
		for(int i =  1; i <= chips.length; i++)
		{
			for(int j = 1; j <= chips[0].length; j++)
			{
				sheet.draw(i*64, j*64, 2, 0);
				sheet.draw(i*64, j*64, 2, 1);
				if(game.getErrors()[i-1][j-1])
					sheet.draw(i*64, j*64, 1, 1);
			}
		}
	}
	
	private static void drawChip(Chip chip)
	{
		//Draw Appropriate Color Chip
		switch(chip.getColor()){
		case BLUE:
			sheet.draw(chip.getX(), chip.getY(), 0, 0);
			break;
		case RED:
			sheet.draw(chip.getX(), chip.getY(), 1, 0);
			break;
		}

	}
}
