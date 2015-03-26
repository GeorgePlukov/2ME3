package engine;

import engine.ChipColor.Color;

public class View {
	private static SpriteSheet sheet = new SpriteSheet("res/Chips.png", 64, 64);	//Sprite Sheet with GameBoard and Chip Tiles

	//Render The Game Board With Tiles
	public static void render(GameBoard game)
	{

		//Draws Chip Options
		sheet.draw(0, 0, 0, 0);
		sheet.draw(64*8, 0, 1, 0);
		
		//Draw Single/Multiplayer Buttons
		sheet.draw(64*2, 0, 3, 3);
		sheet.draw(64*6, 0, 2, 3);
		
		//Draw Save Button
		sheet.draw(0, 64*8, 3, 0);
		
		//Draw Load Button
		sheet.draw(64*8, 64*8, 3, 1);
		
		//Draw Reset Button
		sheet.draw(64*4, 64*8, 3, 2);
		
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
		
		
		//Draw Who Wins
		if(Logic.checkWin(game.getChips()) == Color.BLUE)
			sheet.draw(256, 0, 0, 2);
		else if(Logic.checkWin(game.getChips()) == Color.RED)
			sheet.draw(256, 0, 0, 3);
		
		//Draw Is Tie
		else if(Logic.isTie(chips))
			sheet.draw(256, 0, 1, 3);
		
		//Draw Playing
		else
			sheet.draw(256,  0, 1, 2);
			
		
		
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
