 package engine;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Main 
{
	
	//Declare Variables & Objects
	private static final int WIDTH = 576;
	private static final int HEIGHT = 576;
	private static final String gameName = "Connect 4";
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
			Mouse.create();
			Display.setTitle(gameName);
			Display.setVSyncEnabled(true);
		}
		catch (LWJGLException e) { e.printStackTrace(); }
	}
	
	//*Setup OpenGL*//
	private static void initGL()
	{
		//Enables 2D Textures
	       GL11.glEnable(GL11.GL_TEXTURE_2D);              
	       
	       GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         
	         
	       GL11.glClearDepth(1);
	       
	       // Enable Alpha Blending
	       GL11.glEnable(GL11.GL_BLEND);
	       GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	       
	       //Sets Up Projection Matrix
	       GL11.glMatrixMode(GL11.GL_PROJECTION);
	       GL11.glOrtho(0, WIDTH,  0, HEIGHT, 1, -1);	//Sets Up Matrix (Top Left is [0,0])
	       
	       //Sets Up ModelView Matrix
	       GL11.glMatrixMode(GL11.GL_MODELVIEW);
		

	}
	
	//*Initialize Game*//
	private static void initGame() throws IOException
	{
		//Create the game board object.
		game = new GameBoard();
	}
	
	//*Start Game Loop*//
	private static void gameLoop()
	{
		// Main Game loop, runs until a close is requested
		while(!Display.isCloseRequested())
		{
			update();
			render();
		}
	}
	
	
	//*Update Game State*//
	private static void update()
	{
		game.update();
	}
	
	//*Render Game State*//
	private static void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);	//Remove What's On Screen
		glLoadIdentity();				//Reset Matrix for Frame
		
		View.render(game);
		
		Display.update();				//Double Buffering
		Display.sync(60);				//Sets Frame Rate to 60
	}

	//*Clean Up*//
	private static void cleanUp()
	{
		Display.destroy();
		Mouse.destroy();
	}	
}
