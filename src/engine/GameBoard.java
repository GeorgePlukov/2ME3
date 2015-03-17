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
			if((mousePos.getY() > 64 && mousePos.getY() < 7 * 64) && (mousePos.getX() > x*64 + 64 && mousePos.getX() < x*64 + 128) && (Mouse.next() && !Mouse.getEventButtonState() && Mouse.getEventButton() == 0))
			{
				if(isRed)
				{
					if(Logic.addChip(chips, x, ChipColor.Color.RED))
						isRed = false;
					
				}
				else
				{
					if(Logic.addChip(chips, x, ChipColor.Color.BLUE))
						isRed = true;
				}
			}
		}
		
		if(Logic.checkWin(chips) == Color.RED)
		{
			System.out.println("RED WIN");
		}
		
		if(Logic.checkWin(chips) == Color.BLUE)
		{
			System.out.println("BLUE WIN");
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
