/** PictureMap class - used to create a maze map using external pictures  
  * ICS4U1
  * @author Nirmit Zinzuwadia 
  * @since June 5th 2015
  */

package mindBlowingMaze;

// importing certain libraries 
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class PictureMap {

    private Scanner file;       // used to read the information on the text file    
    private String mazeMap[] = new String[35];  // array used to store each line from the text file
    private Image grass;   // used to store a grass image
    private Image brick;   // used to store a brick image   
    private Image finishLine; // used to store the finish line image   
    private ImageIcon img;    // used to store Icons from Images   

    /** Constructor method for the PictureMap object     
      */
    public PictureMap(){

        img = new ImageIcon("bricks.jpg"); // storing brick image 
        brick = img.getImage();            // storing img into brick variable 

        img = new ImageIcon("grass.jpg");  // storing grass image 
        grass = img.getImage();            // storing img into grass variable  
        
        img = new ImageIcon("finish.jpg"); // storing finish image 
        finishLine = img.getImage();       // storing img into finishLine variable  

        //locating the text file
        try {
            file = new Scanner(new File("map.txt"));
        } catch (Exception e) {
            System.out.println("Can not locate the text file! ");  // this is displayed when the file in not located 
        }

        // storing each line from the text file in an array
        for (int i = 0; i < 35; i++) {
            mazeMap[i] = file.next();
        }    
    }

    /** getIndexSymbol method to get the index symbol from the text file
      * @param x - symbol at x-unit from the line stored in an array
      * @param y - y-unit (line) of the text file (in other words: array location)
      * @return Returns the index symbol (g/b/f) depending on what is drawn at that location
      */
    public String getIndexSymbol(int x, int y){
        String indexSymbol = mazeMap[y].substring(x, x+1);
        return indexSymbol;
    }
    
    /** paints the entire maze map
      * @param g - passes the graphics object which allows us to draw and color shapes
      */
    public void paint(Graphics g) {      

        for(int y = 0; y < 35; y++) {
            for (int x = 0; x < 49; x++) {
                if (mazeMap[y].substring(x, x+1).equals("g")){
                    g.drawImage(grass, x*18, y*18 , null);
                }
                if (mazeMap[y].substring(x, x+1).equals("b")){
                    g.drawImage(brick, x*18, y*18 , null);
                }
                if (mazeMap[y].substring(x, x+1).equals("f")){
                    g.drawImage(finishLine, x*18, y*18 , null);
                }
            }
        }   
        
    } 

}
