package engine;



import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import org.lwjgl.opengl.GL11;

/**
 * Description: 
 * 
 * @Author Theo R. Stone ©
 */

public class SpriteSheet 
{
	//Declare Variables
	private Texture texture;
	float imgX, imgY;
	
	
	public SpriteSheet(String address, float imgX, float imgY) throws IOException
	{
		this.imgX = imgX; 
		this.imgY = imgY;
		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(address));
		
	}

	
	public void draw(float x, float y, float gridX, float gridY, boolean pop)
	{
		if(pop)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, 0);
	        //Color.white.bind();
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
	    	GL11.glPopMatrix();
		}
		else
		{
			GL11.glTranslatef(x, y, 0);
	        //Color.white.bind();
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
		}
		
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
