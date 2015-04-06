package engine;

import java.util.ArrayList;

import engine.ChipColor.Color;

//AI IS ALWAYS BLUE
public class ArtificialIntelligence {
	
	public static int getMove(Chip[][] chips)
	{
		int col;
		//Check to see if the AI can win in one move, if it can, take it!
		col = checkAIWin(chips);
		if (col != -1)
			return col;
		//Check to see if the opponent can win in one move, if it can, take that move
		col = checkPlayerWin(chips);
		if (col != -1)
			return col;
		
		//Go in center if no one else has
		if (chips[3][0] == null)
			return 3;

		//Go in the center of the board, not the edges
		if (!centerFull(chips))
			return (int)(Math.random()*5)+1;


		//If one edge is full, go in the other
		if (chips[0][5] != null)
			return 6;

		if (chips[6][5] != null)
			return 0;

		// If center is full, but edges are not, go in a random edge
		return (Math.random() < 0.5) ? 0 : 6;
	}

	private static int checkAIWin(Chip[][] chips)
	{
		int col;
		//Check if there are 3 pieces in a horizontal line
		col = checkHorizontal(chips,Color.BLUE);
		if (col != 1)
			return col;
		//Check if there are 3 pieces in a vertical line
		col = checkVertical(chips, Color.BLUE);
		if (col != -1)
			return col;
		//Check if there are 3 pieces in an upward diagonal line
		col = checkDiagonalUp(chips, Color.BLUE);
		if (col != -1)
			return col;
		//Check if there ar 3 pieces in a downward diagonal line
		col = checkDiagonalDown(chips, Color.BLUE);
		if (col != -1)
			return col;
		//Return -1 if there are no one move wins
		return -1;
	}
	
	private static int checkPlayerWin(Chip[][] chips) 
	{
		int col;
		//Check if there are 3 pieces in a horizontal line
		col = checkHorizontal(chips, Color.RED);
		if (col != -1)
			return col;
		//Check if there are 3 pieces in a vertical line
		col = checkVertical(chips,Color.RED);
		if (col != -1)
			return col;
		//Check if there are 3 pieces in an upward diagonal line
		col = checkDiagonalUp(chips, Color.RED);
		if (col != -1)
			return col;
		//Check if there ar 3 pieces in a downward diagonal line
		col = checkDiagonalDown(chips, Color.RED);
		if (col != -1)
			return col;
		//Return -1 if there are no one move blocks (block as in blocking an opponent win)
		return -1;
	}

	/*This checks to see if there are 3 pieces of the same color lined up in a horizontal fashion. 
	 * If there are, whether or not they are my color, I go there*/
	private static int checkHorizontal(Chip[][] chips, Color c)
	{
		for (int j = 0;  j < chips[0].length; j++)
		{
			for (int i = 0; i < chips.length - 3; i++)
			{
				//Add all 4 piece horizontal lines to an array list (one line at a time)
				ArrayList<Color> colors = new ArrayList<>();
				colors.add(chips[i+0][j] == null ? null : chips[i+0][j].getColor());
				colors.add(chips[i+1][j] == null ? null : chips[i+1][j].getColor());
				colors.add(chips[i+2][j] == null ? null : chips[i+2][j].getColor());
				colors.add(chips[i+3][j] == null ? null : chips[i+3][j].getColor());
				int possibleCol = colorChecker(colors, c);
				possibleCol += possibleCol == -1 ? 0 : i;
				// There exists a place to win/block on the horizontal
				if (possibleCol != -1)
				{
					if (j == 0 || chips[possibleCol][j-1] != null)
						return possibleCol;
				}
			}
		}
		//No horizontal win
		return -1;
	}
	
	/*This method checks to see if there are 3 of the same colors in a line (not necessarily in a row) and returns the position of the space to go
	  * if no space exists, return -1*/
	private static int colorChecker(ArrayList<Color> cList, Color c)
	{
		//If the list does not contain the color, return -1
		if (!cList.contains(c))
			return -1;
		//If the list does not contain a null, then there is no place to go, return -1
		if (!cList.contains(null))
			return -1;
		//count the number of that color
		else
		{
			int numOfColor = 0;
			for (Color color: cList)
				if (color == c)
					numOfColor++;
			//If there are 3 of the same color, find the position of the null space
			if (numOfColor == 3)
				return cList.indexOf(null);
			else
				return -1;
		}
	}
	
	/*This checks to see if there are 3 pieces of the same color lined up in a vertical fashion. 
	 * If there are, whether or not they are my color, I go there*/
	private static int checkVertical(Chip[][] chips, Color c)
	{
		for (int i = 0; i < chips.length; i++)
		{
			for (int j = 0; j < chips[0].length-3; j++)
			{
				try
				{
					if (chips[i][j].getColor() == chips[i][j + 1].getColor() && chips[i][j + 1].getColor() == chips[i][j + 2].getColor() && chips[i][j + 2].getColor() == c)
					{
						if (chips[i][j+3] == null)
							return i;
					}
				} catch (Exception e){}
			}
		}
		return -1;
	}
	
	/*This checks to see if there are 3 pieces of the same color lined up in a upward diagonal fashion. 
	 * If there are, whether or not they are my color, I go there. This is done in a very similar way to horizontal*/
	private static int checkDiagonalUp(Chip[][] chips, Color c)
	{
		for (int j = 0;  j < chips[0].length-3; j++)
		{
			for (int i = 0; i < chips.length-3; i++)
			{
				//Add all 4 piece upward diagonal lines to an array list (one line at a time)
				ArrayList<Color> colors = new ArrayList<>();
				colors.add(chips[i+0][j+0] == null ? null : chips[i+0][j+0].getColor());
				colors.add(chips[i+1][j+1] == null ? null : chips[i+1][j+1].getColor());
				colors.add(chips[i+2][j+2] == null ? null : chips[i+2][j+2].getColor());
				colors.add(chips[i+3][j+3] == null ? null : chips[i+3][j+3].getColor());
				int possibleCol = colorChecker(colors, c);
				possibleCol += possibleCol == -1 ? 0 : i;
				//There exists a place to win/block on the upward diagonal
				try
				{
					if (possibleCol != -1)
						if (chips[possibleCol][possibleCol-i-1] != null)
							return possibleCol;
				} catch (Exception e){}
			}

		}
		//No upward diagonal win
		return -1;
	}
	
	private static int checkDiagonalDown(Chip[][] chips, Color c)
	{
		for (int j =  chips[0].length-1; j >= 3; j--)
		{
			for (int i = 0; i < chips.length-3; i++)
			{
				//Add all 4 piece upward diagonal lines to an array list (one line at a time)
				ArrayList<Color> colors = new ArrayList<>();
				colors.add(chips[i+0][j-0] == null ? null : chips[i+0][j-0].getColor());
				colors.add(chips[i+1][j-1] == null ? null : chips[i+1][j-1].getColor());
				colors.add(chips[i+2][j-2] == null ? null : chips[i+2][j-2].getColor());
				colors.add(chips[i+3][j-3] == null ? null : chips[i+3][j-3].getColor());
				int possibleCol = colorChecker(colors, c);
				possibleCol += possibleCol == -1 ? 0 : i;
				//There exists a place to win/block on the upward diagonal
				try
				{
					if (possibleCol != -1)
						if (chips[possibleCol][possibleCol-i] != null)
							return possibleCol;
				} catch (Exception e){}
			}

		}
		//No downward diagonal win
		return -1;
	}
	/*This method checks to see if the center of the board is full by checking if the tops of the center cols are null
	* If all of the chips at [i][5] are null then the center cannot be full*/
	private static boolean centerFull(Chip[][] chips)
	{
		for (int i = 1; i < chips.length-1; i++)
		{
			if (chips[i][5] == null)
				return false;
		}
		return true;
	}

}
