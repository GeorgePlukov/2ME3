package engine;

import engine.ChipColor.Color;

public class MainMenu extends GameBoard
{
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
		
		
		
		//Draw Win
		
		if(Logic.checkWin(game.getChips()) == Color.BLUE)
			sheet.draw(256, 0, 0, 2);
		else if(Logic.checkWin(game.getChips()) == Color.RED)
			sheet.draw(256, 0, 0, 3);
		else if(Logic.isTie(chips))
			sheet.draw(256, 0, 1, 3);
		else
			sheet.draw(256,  0, 1, 2);
			
		
		
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
}