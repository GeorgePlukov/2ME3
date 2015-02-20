 package engine;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Main 
{
	
	//Declare Variables & Objects
	private static final int WIDTH = 576;
	private static final int HEIGHT = 576;
	private static GameBoard game;
	
	
	public static void main(String [] args) throws IOException
	{
		initDisplay();
		initGL();
		initGame();
		
		gameLoop();
		
		cleanUp();
	}
	
	//*Creates Display*//
	private static void initDisplay()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
			Keyboard.create();
			Mouse.create();
			Display.setVSyncEnabled(true);
		}
		catch (LWJGLException e) { e.printStackTrace(); }
	}
	
	//*Setup OpenGL*//
	private static void initGL()
	{
	       GL11.glEnable(GL11.GL_TEXTURE_2D);              
	       
	       GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         
	         
	            // Enable Alpha Blending
	            GL11.glEnable(GL11.GL_BLEND);
	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	         
	            GL11.glViewport(0,0,WIDTH, HEIGHT);
	        GL11.glMatrixMode(GL11.GL_PROJECTION);
	        GL11.glLoadIdentity();
	        GL11.glOrtho(0, WIDTH,  0, HEIGHT, 1, -1);
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	        GL11.glDisable(GL_DEPTH_TEST);
		

	}
	
	//*Initialize Game*//
	private static void initGame() throws IOException
	{
		game = new GameBoard();
	}
	
	//*Start Game Loop*//
	private static void gameLoop()
	{
		while(!Display.isCloseRequested())
		{
 
			getInput();
			update();
			render();
		}
	}
	
	//*Get User Input*//
	private static void getInput()
	{
		game.getInput();
	}
	
	//*Update Game State*//
	private static void update()
	{
		game.update();
	}
	
	//*Render Game State*//
	private static void render()
	{
		glPushMatrix();
		glClear(GL_COLOR_BUFFER_BIT);	//Remove What's On Screen
		glLoadIdentity();				//Reset Matrix for Frame
		
		game.render();
		
		Display.update();				//Double Buffering
		Display.sync(60);				//Sets Frame Rate to 60
		glPopMatrix();
	}

	//*Clean Up*//
	public static void cleanUp()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}	
}
