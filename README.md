# 2ME3

###Running the program for the first time
Often when running the game for the first time on your machine you will get a java.lang.UnsatisfiedLinkError. To fix this problem(using eclipse):
1) Open the project in the project explorer
2) Select JRE Systems Library, and click "Build Path" -> "Configure Build Path"
3) Select "Native library location" and click edit 
4) Navigate to the native folder(inside the project) and select your operating system. 



Collaborators:
  - George Plukov
  - Theo Stone
  - Carson White
  - Tim Odd
  - Philip Habib



Main Class Imports:

org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT
org.lwjgl.opengl.GL11.glClear
org.lwjgl.opengl.GL11.glLoadIdentity
java.io.IOException
org.lwjgl.LWJGLException
org.lwjgl.input.Mouse
org.lwjgl.opengl.Display
org.lwjgl.opengl.DisplayMode
org.lwjgl.opengl.GL11

Main Class Types: Int, Gameboard

Chip Class Imports: NONE
Chip Class Types: Int, ChipColor

Gameboard Class Imports: org.newdawn.slick.geom.Point
Gameboard Class Types: boolean, Chip

Logic Class Imports: NONE
Logic Class Types: NONE

View Class Imports: NONE
View Class Types: SpriteSheet

MouseInput Class Imports: 

org.lwjgl.input.Mouse
org.newdawn.slick.geom.Point

MouseInput Types: NONE


SpriteSheet Class Imports:

java.io.IOException
org.newdawn.slick.opengl.Texture
org.newdawn.slick.opengl.TextureLoader
org.newdawn.slick.util.ResourceLoader

SpriteSheet Class Types: Texture, float




