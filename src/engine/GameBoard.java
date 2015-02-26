package engine;

import org.newdawn.slick.geom.Point;

public class GameBoard 
{
	
	//Declare Variables & Objects
	private boolean isRed = false;
	
	private Chip [][] chips;	//Array to Hold Chips in GameBoard
	private boolean [][] errors;	//Array Holding Error Pointers in Grid

	
	public GameBoard()
	{
		//Initialize 7x6 GameBoard
		chips = new Chip [7][6];
		errors = new boolean [7][6];
		
		//Start GameBoard with Random Chip Selected. 
		if(Math.random() > 0.5)
			isRed = true;
	}
	

	public void update()
	{
		// Check to see whether or not we are switching colors.
		isRed = MouseInput.isRedActive(isRed);
		
		// Get the current mouse position.
		Point mousePos = MouseInput.getMousePosition();
	
		//Get Mouse Input for Game Board Tiles
		for (int i = 64; i < chips.length*64 + 64; i+=64)
			for(int j = 64; j < chips[0].length*64 + 64; j+=64)
				if(mousePos.getX() > i && mousePos.getY() > j && mousePos.getX() < i + 64 && mousePos.getY() < j + 64 && MouseInput.isButtonDown(0) && chips[(i/64)-1][(j/64)-1] == null)
				{
					if(isRed)
					{
						chips[(i/64)-1][(j/64)-1] = new Chip(i, j,  ChipColor.Color.RED);
					}
					else 
					{
						chips[(i/64)-1][(j/64)-1] = new Chip(i, j, ChipColor.Color.BLUE);
					}
				}
		
	}


	public boolean isRedActive() {
		return isRed;
	}

	public Chip[][] getChips() {
		return chips;
	}
	
	public boolean [][] getErrors()
	{
		return errors;
	}
}
