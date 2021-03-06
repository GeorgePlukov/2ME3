package engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import org.newdawn.slick.geom.Point;

import engine.ChipColor.Color;

public class GameBoard 
{
	
	//Declare Variables & Objects
	private boolean isRed = false;
	private boolean isAI = false;
	
	private Chip [][] chips;	//Array to Hold Chips in GameBoard

	
	public GameBoard()
	{
		//Initialize 7x6 GameBoard
		chips = new Chip [7][6];
		
		//Start GameBoard with Random Chip Selected. 
		if(Math.random() > 0.5)
			isRed = true;
	}
	

	public void update()
	{
		// Get the current mouse position.
		Point mousePos = MouseInput.getMousePosition();
		
		//Check Columns for Clicks
		
		if(isAI && !isRed && Logic.checkWin(chips) == null)
		{
			try 
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e) {}
			int colToMove = ArtificialIntelligence.getMove(chips);
			if (addChip(chips, colToMove, ChipColor.Color.BLUE))
				isRed = true;
		}
		
		else if(Logic.checkWin(chips) == null)
		{
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
		
		
		//Check Reset Click
		if((mousePos.getY() > 8 * 64 && mousePos.getY() < 9 * 64) && (mousePos.getX() > 4 * 64 && mousePos.getX() < 5 * 64) && MouseInput.isClicked())
		{
			chips = new Chip [7][6];
		}
		//Check Save Click
		if((mousePos.getY() > 8 * 64 && mousePos.getY() < 9 * 64) && (mousePos.getX() > 0 * 64 && mousePos.getX() < 1 * 64) && MouseInput.isClicked())
		{
			saveGame();
		}
		//Check Load Click
		if((mousePos.getY() > 8 * 64 && mousePos.getY() < 9 * 64) && (mousePos.getX() > 8 * 64 && mousePos.getX() < 9 * 64) && MouseInput.isClicked())
		{
			loadGame();
		}
		
		//Check Single Player Click
		if((mousePos.getY() > 0 * 64 && mousePos.getY() < 1 * 64) && (mousePos.getX() > 2 * 64 && mousePos.getX() < 3 * 64) && MouseInput.isClicked())
		{
			chips = new Chip [7][6];
			isAI = true;
		}
		//Check Multi-player Click
		if((mousePos.getY() > 0 * 64 && mousePos.getY() < 1 * 64) && (mousePos.getX() > 6 * 64 && mousePos.getX() < 7 * 64) && MouseInput.isClicked())
		{
			chips = new Chip [7][6];
			isAI = false;
		}
	}


	public boolean isRedActive() {
		return isRed;
	}

	public Chip[][] getChips() {
		return chips;
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
	
	private void saveGame()
	{
		try 
		{
			PrintWriter writer = new PrintWriter("save.txt");
			
			writer.print(isRed);
			writer.print("\n");
			
			for(int i = 0; i < chips.length; i++)
			{
				for(int j = 0; j < chips[0].length; j++)
				{
					if(chips[i][j] == null)
						writer.print("_");
					else if(chips[i][j].getColor() == Color.BLUE)
						writer.print("b");
					else if(chips[i][j].getColor() == Color.RED)
						writer.print("r");
					
				}
				writer.print("\n");
			}
					
			
			writer.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void loadGame()
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader("save.txt"));
			if(br.readLine().equals("false"))
				isRed = false;
			else
				isRed = true;
			
			for(int i = 0; i < chips.length; i++)
			{
				String [] tokens = br.readLine().split("(?!^)");
				
				for(int j = 0; j < chips[0].length; j++)
				{
					
						if(tokens[j].equals("b"))
							chips[i][j] = new Chip(i * 64 + 64, j * 64 + 64, Color.BLUE);
						else if(tokens[j].equals("r"))
							chips[i][j] = new Chip(i * 64 + 64, j * 64 + 64, Color.RED);
						else
							chips[i][j] = null;
					
				}
			}
		} 
		catch (Exception e) 
		{
			
		}
	}
}
