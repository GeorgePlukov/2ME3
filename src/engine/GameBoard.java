package engine;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Point;

import engine.ChipColor.Color;

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
		// Get the current mouse position.
		Point mousePos = MouseInput.getMousePosition();
		
		for(int x = 0; x < chips.length; x++)
		{
			if((mousePos.getY() > 64 && mousePos.getY() < 7 * 64) && (mousePos.getX() > x*64 + 64 && mousePos.getX() < x*64 + 128) && MouseInput.isClicked())
			{
				if(isRed)
				{
					if(addChip(chips, x, ChipColor.Color.RED))
						isRed = false;
					
				}
				else
				{
					if(addChip(chips, x, ChipColor.Color.BLUE))
						isRed = true;
				}
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
	
	private boolean addChip(Chip [][] chips, int column, Color c)
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
}
