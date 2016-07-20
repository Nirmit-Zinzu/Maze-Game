/** Player class - class for the block which moves automatically   
  * ICS4U1
  * @author Nirmit Zinzuwadia 
  * @since June 5th 2015
  */

package mindBlowingMaze;

// importing certain libraries 
import java.awt.Color;
import java.awt.Graphics;

public class Player {
    
    private int xCoordinate, yCoordinate;      // x and y-coordinates of the block
    private char direction;                    // block's heading (R, L, U, D) 
    private static final char RIGHT = 'R';     // right direction 
    private static final char LEFT = 'L';      // left direction 
    private static final char UP = 'U';        // upward direction 
    private static final char DOWN = 'D';      // downward direction
    
    /** Constructor method for the Player object
      * @param x - The player's x-coordinate
      * @param y - The player's y-coordinate
      */
    public Player(int x, int y) {        
        this.xCoordinate = x; 
        this.yCoordinate = y;        
        this.direction = RIGHT;
    }
    
    /** moves the block by changing its x-coordinate and y-coordinate
      * @param x - movement of x-unit 
      * @param y - movement of y-unit 
      */
    public void move(int x, int y){
        this.xCoordinate += x; 
        this.yCoordinate += y;        
    }
    
    /** Accessor method for the ball's y-coordinates 
      * @return Returns the block's y-coordinate
      */
    public int getCoordinateY() {
        return this.yCoordinate;
    }
    
 /** Accessor method for the ball's x-coordinates 
      * @return Returns the block's x-coordinate
      */
    public int getCoordinateX() {
        return this.xCoordinate;   
    }    
    
    /** Accessor method for the ball's current heading
      * @return Returns the block's current heading (R, L, U, D)
      */
    public char getDirection() {
        return this.direction;
    }
    
    /** Mutator method for the block's x-coordinate
      * @param x - sets the new value for the x-coordinate of the block
      */
    public void setCoordinateX(int x) {
        this.xCoordinate = x;
    }

    /** Mutator method for the block's x-coordinate
      * @param y - sets the new value for the y-coordinate of the block
      */
    public void setCoordinateY(int y) {
        this.yCoordinate = y;
    }    
 
    /** Mutator method for the block's heading
      * @param recentHeading - sets the new heading of the block 
      */
    public void setDirection(char recentHeading) {
        this.direction = recentHeading;
    }
        
    /** paints the block which moves automatically
      * @param g - passes the graphics object which allows us to draw and color shapes
      */
    public void paint(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(this.xCoordinate, this.yCoordinate, 5, 5);
    }
    
}
