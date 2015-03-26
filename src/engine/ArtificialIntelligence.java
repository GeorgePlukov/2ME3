package engine;

import engine.ChipColor.Color;

//AI IS ALWAYS BLUE
public class ArtificialIntelligence {
	
	public static int getMove(Chip[][] chips)
	{
        int moveToMake = -1;
		//Check if I can win in 1 move
        try {
            outer:
            for (int i = 0; i < chips.length; i++) {
                for (int j = 0; j < chips[0].length; j++) {
                    //Check Horizontal
                    for (int k = 1; k <= 3; ++k)
                        if (chips[i + k][j].getColor() != Color.RED)
                            break outer;
                    //If i get to here, there should be 3 in a row, so return the next column
                    return i + 3;
                }
            }
        } catch (Exception e) {}
        return (int)Math.random()*7;



//                //Check Vertical
//                for (int k = 1; k < 3; ++k)
//                    if (chips[i + k * 0][j + k * 1].getColor() != Color.BLUE)
//                        break;
//                //Check Diagonal Up
//                for (int k = 1; k < 3; ++k)
//                    if (chips[i + k * 1][j + k * 1].getColor() != Color.BLUE)
//                        break;
//                //Check Diagonal Down
//                for (int k = 1; k < 3; ++k)
//                    if (chips[i + k * 1][j + k * -1].getColor() != Color.BLUE)
//                        break;
//
//            }
//       }

        //return 0;
	}

    private static int  checkThree(Chip[][] chips, int x, int y)
    {

        return 0;
    }
	
	private static void p(Chip[][]chips)
	{
		for (int i = chips[0].length-1; i >= 0; i--)
		{
			for(int y = 0; y < chips.length-1; y++)
			{
				if (chips[y][i] == null)
					System.out.print("_ ");
				else if (chips[y][i].getColor() == Color.BLUE)
					System.out.print("B ");
				else if (chips[y][i].getColor() == Color.RED)
					System.out.print("R ");
			}
			System.out.println();
		}
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	}

}
