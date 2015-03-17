package engine;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Point;

abstract class MouseInput {
	
	public static Point getMousePosition(){
		return new Point(Mouse.getX(), Mouse.getY());
	}
	
	public static boolean isClicked()
	{
		return  (Mouse.next() && !Mouse.getEventButtonState() && Mouse.getEventButton() == 0);
	}

	
	
}
