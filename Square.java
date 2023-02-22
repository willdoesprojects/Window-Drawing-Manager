

import java.awt.Color;
import java.awt.Graphics;

/**
 *  A single square in a window.
 *  Original code by Mike Clancy. 
 *  Modified by William Nguyen.
 */
public class Square {
	/**
	 *  The next id when a new square is created.
	 */
	private static int nextID = 0;
	
	/**
	 *  The X position of the upper left corner of this square.
	 */
	private int upperLeftX;
	
	/**
	 *  The Y position of the upper left corner of this square.
	 */
	private int upperLeftY;
	
	/**
	 *  The size of this square.
	 */
	private int size;
	
	/**
	 *  The color this square.
	 */
	private Color color;
	
	/**
	 *  The id of this square.
	 */
	private int id;
	
	/**
	 *  Initialize a square with the given position (specified 
	 *  relative to the WindowManager in which all this is being
	 *  run), dimensions, and color.
	 *  
	 *  @param centerX the x position of the center of this square
	 *  @param centerY the y position of the center of this square
	 *  @param size the size of this square
	 *  @param c the color of this square
	 */
	public Square (int centerX, int centerY, int size, Color c) {
		this.upperLeftX = centerX - size/2;
		this.upperLeftY = centerY - size/2;
		this.size = size;
		this.color = c;
		this.id = nextID;
		nextID++;
	}
	/**
	 * Taking a x and y coordinate input and insures that it is 
	 * within the java coordinate plane of the instance variables
	 * "upperLeftX" and "upperLeftY".
	 * @param x the inputed x position
	 * @param y the inputed y position
	 * @return true if input coordinates are within the plane, false if not
	 */
	public boolean contains (int x, int y) {
		//Returns whether or not a given x and y
		//position are contained within this square.
		
		//< YOUR_CODE_HERE >
		
		//Note: upper left x and y are _inclusive_.
		 
		 
		if (x >= upperLeftX && x <= upperLeftX + size && y >= upperLeftY && y <= upperLeftY + size) {
			return true;
		}
		 
		else {
			return false;
		}
		
	
	}

	/**
	 *  Fetches the id of this square.
	 *  
	 *  @return the id of this square
	 */
	public int id() {
		return id;
	}
	
	/**
	 *  Gets the upper left x position of the square.
	 *  
	 *  @return the upper left x position
	 */
	public int getUpperLeftX() {
		return upperLeftX;
	}
	
	/**
	 *  Gets the upper left y position of the square.
	 *  
	 *  @return the upper left y position
	 */
	public int getUpperLeftY() {
		return upperLeftY;
	}
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals (Object o) {
		if(o instanceof Square) {
			Square sq = (Square) o;
			return upperLeftX == sq.upperLeftX && 
				upperLeftY == sq.upperLeftY && 
				size == sq.size;
		}
		return false;
	}
	
	/**
	 * Draws our squares using the graphics and color libraries.
	 * calling simple methods to draw and outline the squares with
	 * various colors and sizes.
	 * @param g input of the type of graphics we will be using
	 */
	public void paint (Graphics g) {
		//Draws a square on the graphics (g) using methods outlined in
		//the project description.
		
		//You should draw a filled square-shaped-rectangle using
		//the color of this square. You should then draw a black
		//border in the same space.
		
		//< YOUR_CODE_HERE >
		g.setColor(color);
		g.fillRect(upperLeftX, upperLeftY, size, size);
		g.setColor(Color.black);
		g.drawRect(upperLeftX, upperLeftY, size, size);
		
	}
}
