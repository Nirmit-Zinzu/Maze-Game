/** Mouse class - used when the state of the instruction page is true to start the game
  * ICS4U1
  * @author Nirmit Zinzuwadia 
  * @since June 5th 2015
  */

package mindBlowingMaze;

// importing certain libraries 
import java.awt.Color;
import java.awt.Graphics;

public class Mouse {

    private int x;           // x value of the paddle
    private int y;           // y value of the paddle

    /** Constructor method for the Mouse object
      * @param x - The mouse's x-coordinate
      * @param y - The mouse's y-coordinate
      */
    public Mouse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Moves the mouse by changing its x-coordinate
      * @param x - carries the x-units mouse has to move 
      */
    public void moveMouse(int x) {
       this.x += x; 
    }

    /** Accessor method for the x-coordinate of the mouse
      * @return Returns the current x-coordinate of the paddle
      */
    public int getX() {
        return this.x;
    }

    /** paints the mouse
      * @param g - passes the graphics object which allows us to draw and color shapes
      */
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(this.x, this.y, 50, 50);  // colours and draws a sqaure
    }

}


