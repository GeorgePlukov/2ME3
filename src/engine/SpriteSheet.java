package engine;



import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import org.lwjgl.opengl.GL11;

/**
 * Description: SpriteSheet uses an address to a PNG image and splits the images
 *  contained into tiles based on an x,y grid assuming the top left of the image is 0,0.
 * 
 * @Author Theo R. Stone �
 */

public class SpriteSheet 
{
	//Declare Variables
	private Texture texture;
	private float imgX, imgY;		//Dimensions of Each Tile
	
	
	public SpriteSheet(String address, float imgX, float imgY)
	{
		this.imgX = imgX; 
		this.imgY = imgY;
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(address));
		} catch (IOException e) {
			System.out.println("Sprite sheet wont load");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void draw(float x, float y, float gridX, float gridY)
	{
			GL11.glPushMatrix();	//Pushes New Matrix to Draw On
			GL11.glTranslatef(x, y, 0);
	        	texture.bind();
		        GL11.glBegin(GL11.GL_QUADS);
		            GL11.glTexCoord2f((float)(gridX)*(imgX/texture.getTextureWidth()), (float)(gridY)*(imgY/texture.getTextureHeight()));
		            	GL11.glVertex2f(0, 0);
		            GL11.glTexCoord2f((float)(gridX + 1)*(imgX/texture.getTextureWidth()), (float)(gridY)*(imgY/texture.getTextureHeight()));
		            	GL11.glVertex2f(this.imgX, 0);
		            GL11.glTexCoord2f((float)(gridX + 1)*(imgX/texture.getTextureWidth()), (float)(gridY + 1)*(imgY/texture.getTextureHeight()));
		            	GL11.glVertex2f(this.imgX, this.imgY);
		            GL11.glTexCoord2f((float)(gridX)*(imgX/texture.getTextureWidth()), (float)(gridY + 1)*(imgY/texture.getTextureHeight()));
		            	GL11.glVertex2f(0, this.imgY);
		        GL11.glEnd();
	    	GL11.glPopMatrix();		//Pops Drawn Matrix
		
	}
	
	public int getXSize()
	{
		return (int) imgX;
	}
	
	public int getYSize()
	{
		return (int) imgY;
	}
}
