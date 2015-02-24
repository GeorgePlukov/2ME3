package engine;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Point;

public class GameBoard 
{
	
	//Declare Variables & Objects
	private boolean isRedActive = false;
	private SpriteSheet sheet;	//Sprite Sheet with GameBoard and Chip Tiles
	private Chip [][] chips;	//Array to Hold Chips in GameBoard

	
	public GameBoard()
	{
		try 
		{
			sheet = new SpriteSheet("res/Chips.png", 64, 64);
		} 
		catch (IOException e) 
		{
			System.out.println("Sprite Sheet failure");
			e.printStackTrace();
		}
		
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
		for (int i = 64; i < chips.length*64 + 64; i+=64){
			for(int j = 64; j < chips[0].length*64 + 64; j+=64){
				if(mousePos.getX() > i && mousePos.getY() > j && mousePos.getX() < i + 64 && mousePos.getY() < j + 64 && MouseInput.isButtonDown(0)){
					if(isRedActive)
						chips[(i/64)-1][(j/64)-1] = new Chip(i, j, 64, 64, 'b', sheet);
					else 
						chips[(i/64)-1][(j/64)-1] = new Chip(i, j, 64, 64, 'r', sheet);
				}
			}
		}
	}
	
	//Render The Game Board With Tiles
	public void render()
	{
		
		//Draws Chip Options
		sheet.draw(0, 0, 0, 0);
		sheet.draw(64*8, 0, 1, 0);
		
		//Draw Outline for Selected Chip (Green)
		if(isRedActive)
			sheet.draw(0, 0, 0, 1);
		else
			sheet.draw(64*8, 0, 0, 1);
		
		
		//Draw Chips in Chip Matrix
		for(int i = 0; i < chips.length; i++)
		{
			for(int j = 0; j < chips[0].length; j++)
			{
				if(chips[i][j] != null)
					chips[i][j].render();
			}
		}
		
		//Draws Validity to Screen
		if(checkValidity())
		{
			sheet.draw(256, 0, 0, 2);
		}
		else 
			sheet.draw(256, 0, 0, 3);
		
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
	
	
	public boolean checkValidity(){
		return Logic.checkGravity(chips) && Logic.checkChipNums(chips);
	}
	

	

}
