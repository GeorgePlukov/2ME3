package engine;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Point;

abstract class MouseInput {
	
	private static boolean clicked = false;
	
	public static Point getMousePosition(){
		return new Point(Mouse.getX(), Mouse.getY());
	}
	
	public static boolean isClicked()
	{
		//return  ( && !Mouse.getEventButtonState() && Mouse.getEventButton() == 0);
		
		boolean bool =  (Mouse.next() && clicked && Mouse.getEventButton() == 0);
		clicked = Mouse.isButtonDown(0);
		return bool;
	}

	
	
}
