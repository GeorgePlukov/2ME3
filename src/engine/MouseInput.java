package engine;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Point;

abstract class MouseInput {
	
	public static Point getMousePosition(){
		return new Point(Mouse.getX(), Mouse.getY());
	}

	
	public static boolean isButtonDown(int i) {
		return Mouse.isButtonDown(i);
	}
	
	public static boolean isRedActive(boolean current){
		//Highlight Selected
		if(Mouse.getX() > 0 && Mouse.getY() > 0 && Mouse.getX() < 64 && Mouse.getY() < 64 && Mouse.isButtonDown(0))
			return false;
		else if(Mouse.getX() > 64*8 && Mouse.getY() > 0 && Mouse.getX() < 64*8+64 && Mouse.getY() < 64 && Mouse.isButtonDown(0))
			return true;
		
		return current;
	}
	
}
