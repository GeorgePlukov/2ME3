package engine;

import org.newdawn.slick.geom.Point;

public class GameBoard 
{
	
	//Declare Variables & Objects
	private boolean isRedActive = false;
	
	private Chip [][] chips;	//Array to Hold Chips in GameBoard

	
	public GameBoard()
	{
		//Initialize 7x6 GameBoard
		chips = new Chip [7][6];
		
		//Start GameBoard with Random Chip Selected. 
		if(Math.random() > 0.5)
			isRedActive = true;

	}
	

	public void update()
	{
		// Check to see whether or not we are swiching colors.
		isRedActive = MouseInput.isRedActive(isRedActive);
		
		// Get the current mouse position.
		Point mousePos = MouseInput.getMousePosition();
	
		//Get Mouse Input for Game Board Tiles
		for (int i = 64; i < chips.length*64 + 64; i+=64)
			for(int j = 64; j < chips[0].length*64 + 64; j+=64)
				if(mousePos.getX() > i && mousePos.getY() > j && mousePos.getX() < i + 64 && mousePos.getY() < j + 64 && MouseInput.isButtonDown(0))
					if(isRedActive)
						chips[(i/64)-1][(j/64)-1] = new Chip(i, j, 64, 64, ChipColor.RED);
					else 
						chips[(i/64)-1][(j/64)-1] = new Chip(i, j, 64, 64, ChipColor.BLUE);
		
	}


	public boolean isRedActive() {
		return isRedActive;
	}

	public Chip[][] getChips() {
		return chips;
	}
}
