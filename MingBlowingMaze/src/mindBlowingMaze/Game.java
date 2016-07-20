/** Game - creates the actual game using different classes 
  * ICS4U1
  * @author Nirmit Zinzuwadia
  * @since June 5th 2015
  */

package mindBlowingMaze;

// importing certain libraries 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Game {
    
    private PictureMap mazeMap;  // variable used to draw out the map 
    private Player user;         // user controlled block which moves during the game   
    private Mouse mouse;         // mouse exclusivly for the instruction page
    private int fastOrSlow = 0;     // variable used to determine the speed of the block (even = slow, odd = fast)
    private int timeLooped = 0;     // variable used to detect how many times the update method has looped  
    private int gameScore = 1002;   // variable used to store the game score  
    private int timesCollided = 0;  // stores the amount of time user had collided   
    private int stuck = 0;          // store the amount of time person is stuck inside the fences 
    private boolean instructionPage = true; // stores the current state of the instruction page 
    private boolean gameOver = false;       // stores the current state of the gameOver page  
    private boolean gameDisplay = false;    // stores the current state of the gameDisplay page
    private boolean stuckDisplay = false;   // stores the current state of the stuckDisplay page  

    /** Constructor method for the game object     
      */
    public Game() {
        mazeMap = new PictureMap();  // creating a maze map 
        user = new Player(22, 310);  // instantiating block that user can move (to check finish use ---> (774, 347)) 
        mouse = new Mouse(200, 520); // creating a mouse that is used in the instuction page 
    }
    
    /** Updates player's new direction 
      * @param heading - new heading of the block       
      */
    public void playerMoveUpdate(char heading) {
        this.user.setDirection(heading);  // setting new direction                
    }    
    
    /** Moves the mouse horizontally by certain units 
      * @param x - amount of units mouse has to move   
      */
    public void mouseMovment(int x) {
        this.mouse.moveMouse(x);  // passes the parameters in the mouse object to move the mouse certain units 
    }
    
    /** Updates the attributes of the block class 
      * @param move - amount the block has to move (varies with speed)  
      */
    public void update(int move){
        
        if (timeLooped % 50 == 0){
            fastOrSlow = (int) Math.floor(Math.random()*100);  // determing the speed of the block (even = slow, odd = fast)
            this.gameScore += -2;            // game score decreases due to time delay 
        }  
        
        timeLooped++;  // increments by one every time the update method is run 
   
        collision();  // detects if an collision has occured      
       
        // moves the block certain units based on the current heading
        if (user.getDirection() == 'R'){
            this.user.setCoordinateX(this.user.getCoordinateX() + move);
        } else if (this.user.getDirection() == 'L'){
            this.user.setCoordinateX(this.user.getCoordinateX() - move);
        } else if (this.user.getDirection() == 'U') {
            this.user.setCoordinateY(this.user.getCoordinateY() - move);
        } else if (this.user.getDirection() == 'D') {
            this.user.setCoordinateY(this.user.getCoordinateY() + move);
        }    
    }        

    /** Function which checks to see if a collision with wall has take place.       
      */
    public void collision() {
        
        // determines where each of the corner of the block is located (grass/ brick)
        String topLeftCornerIndex = mazeMap.getIndexSymbol(user.getCoordinateX()/ 18, user.getCoordinateY() / 18);
        String topRightCornerIndex = mazeMap.getIndexSymbol((user.getCoordinateX() + 5)/ 18, user.getCoordinateY() / 18);
        String bottomLeftCornerIndex = mazeMap.getIndexSymbol(user.getCoordinateX()/ 18, (user.getCoordinateY()+ 5) / 18);
        String bottomRightCornerIndex = mazeMap.getIndexSymbol((user.getCoordinateX() + 5)/ 18, (user.getCoordinateY() + 5) / 18);
      
        // Appropriate action is performed depending on what part of the block has touched the wall 
        if (topLeftCornerIndex.equals("b")|| topRightCornerIndex.equals("b") || bottomLeftCornerIndex.equals("b") || bottomRightCornerIndex.equals("b")){
           
            gameScore = gameScore - 20;   // game score is decreased by 20
            this.timesCollided++;        // increments the amount of time block has collided 
            
            // new direction of the block is set upon hitting the wall     
            if (user.getDirection() == 'R'){              
                user.setDirection('L');          
            } else if (this.user.getDirection() == 'L'){                
                user.setDirection('R');           
            } else if (this.user.getDirection() == 'U') {                
                user.setDirection('D');           
            } else if (this.user.getDirection() == 'D') {               
                user.setDirection('U');         
            }             
        }
        
        // if all the sides of the block are toucing the grass, it will not get stuck inside the bricks! 
        if (topLeftCornerIndex.equals("g") && topRightCornerIndex.equals("g") && bottomLeftCornerIndex.equals("g") && bottomRightCornerIndex.equals("g")){
            stuck = 0;  
        } else { 
            stuck ++;  // collision occurs 
        }
        
        // if the block gets stuck inside the brick, state of gameDisplay page is chaged 
        if (stuck > 8) {
            gameDisplay = false; // game is over 
            stuckDisplay = true; // stuck page is displayed            
        }     
    }   
    
    /** painting the mouse
      * @param g - passes the graphics object which allows us to draw and color shapes
      */ 
    public void paint(Graphics g) { 
      
        Font font = new Font("Calibria", Font.BOLD, 15);  // new font to print on the screen 
        g.setFont(font);
        
        if (this.instructionPage == true) {
            
            // filling the background with black colour 
            g.setColor(Color.black);
            g.fillRect(0, 0, 888, 665); 
     
            // printing the titles for the instruction page 
            font = new Font("Calibria", Font.BOLD, 30); 
            g.setFont(font);
            g.setColor(Color.orange);
            g.drawString("INSTRUCTIONS", 60, 100);
            g.drawString("_____________", 60, 100);
            g.drawString("SCORING", 60, 300);
            g.drawString("________", 60, 300);
            
            // displaying each rules 
            font = new Font("Arial", Font.PLAIN, 18); 
            g.setFont(font);
            g.setColor(Color.white);            
            g.drawString("> The box moves automatically. Your task is to direct the correct parthway using arrow keys! ", 60, 150);
            g.drawString("> You must not collide with the wall to attain a better score. ", 60, 190);
            g.drawString("> The speed of the box will change automatically, therefore you must watch out! ", 60, 230);          
            // instuctions that go under SCORING title  
            g.drawString("> The score start of with 1000. ", 60, 350);
            g.drawString("> The score DECREASES by 20 every time a collision occurs! ", 60, 390);
            g.drawString("> Faster you finish = better your score! ", 60, 430);
           
            // side notes which are displayed in smaller fonts 
            font = new Font("Arial", Font.PLAIN, 12); 
            g.setFont(font);       
            g.drawString("(Everytime the speed of the box is changed, the score is decreased by 2!) ", 75, 445);
            g.setColor(Color.orange);
            g.drawString("Move box ----> when ready to start!", 140, 590);
           
            // drwaing a START box 
            g.setColor(Color.pink);
            g.fillRect(600, 505, 200, 80);
            g.setColor(Color.white);
            g.fillRect(530, 505, 80, 80);
            
            // displaying START on the box 
            font = new Font("Arial", Font.BOLD, 50); 
            g.setFont(font);
            g.setColor(Color.blue);   
            g.drawString("START ", 620, 562);
            
            this.mouse.paint(g); // runs mouse's paint method 
            
            // states of the page are changed when the user gets the mouse to START box 
            if (this.mouse.getX() > 535){
                this.gameDisplay = true;
                this.instructionPage = false;
            }
        }
      
        // displaying gamePage when the condition is met 
        if (this.gameDisplay == true) {          
            
            this.mazeMap.paint(g);                   // running mazeMap's paint method   
            this.user.paint(g);                      // running block's paint method
            
            font = new Font("Calibria", Font.BOLD, 15);        
            g.setFont(font); 
            
            // speed of the block is printed based on wether fastOrSlow variable is odd or even  
            if (this.fastOrSlow % 2 == 0){
                g.setColor(Color.cyan);
                g.drawString("slow", this.user.getCoordinateX() + 10, this.user.getCoordinateY() - 5);
            } else {
                g.setColor(Color.orange);
                g.drawString("FAST", this.user.getCoordinateX() + 10, this.user.getCoordinateY() - 5);
            }
            
            // printing the score in the upper-left corner  
            g.drawString("Score: " , 10 ,15);
            g.drawString("_____ " , 10 ,15);
            g.setColor(Color.pink);
            g.drawString("Finish" , 836, 340);  // displaying Finish at the end of the maze 
            font = new Font("Arial", Font.PLAIN, 18);
            g.setFont(font);
            g.drawString("" + this.gameScore, 10 ,35);  // printing the current game score 
        
            // upon completing the stage, state of the gameOver page is set true 
            if (mazeMap.getIndexSymbol(user.getCoordinateX()/ 18, user.getCoordinateY() / 18).equals("f")) { 
                this.gameOver = true; 
                this.gameDisplay = false;               
            }
        }
        
        // displaying gameOver page when the condition is met 
        if (this.gameOver == true) {            
            
            // filling the background with black colour 
            g.setColor(Color.black);
            g.fillRect(0, 0, 888, 665); 
            
            // displaying GAME OVER in big fonts 
            font = new Font("Arial", Font.BOLD, 40); // changing fonts 
            g.setFont(font);
            g.setColor(Color.orange);             
            g.drawString("GAME OVER!", 320, 170);
            
            // displaying the titles of the game stats 
            font = new Font("Calibria", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.white);
            g.drawString ("Final Score:  ", 100, 300); 
            g.drawString ("Points lost due to time delay: ", 130, 400);           
            g.drawString("Times Collided:", 130, 350);
 
            // displaying the final score 
            font = new Font("Calibria", Font.BOLD, 50);
            g.setFont(font);
            g.setColor(Color.blue);
            g.drawString ("" + this.gameScore , 230, 300); 
            
            // displaying game stats based on user's performance  
            font = new Font("Calibria", Font.BOLD, 25);
            g.setFont(font);
            g.setColor(Color.red);
            g.drawString(("" + this.timesCollided + " (points lost: " + this.timesCollided * 20 + ")"), 290, 350);
            g.drawString (" " + ((1000 - this.gameScore) - (this.timesCollided*20)), 410, 400);   
           
            // Displaying performace title 
            font = new Font("Calibria", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.green);
            g.drawString("Performance:", 100, 480);                     
            
            font = new Font("Calibria", Font.BOLD, 30);
            g.setFont(font);
            
            // Displaying user's performace based on their final score 
            if (this.gameScore > 800) { 
                g.drawString ("Excellent!", 240, 480); 
            } else if (this.gameScore > 600) {
                g.setColor(Color.blue);
                g.drawString ("Good Job!", 240, 480); 
            } else if (this.gameScore> 400) {
                 g.setColor(Color.cyan);
                g.drawString ("Good Try", 240, 480); 
            } else if (this.gameScore > 300) {
                g.setColor(Color.orange);
                g.drawString ("Need Practice!", 240, 480); 
            } else { 
                font = new Font("Calibria", Font.BOLD, 10);
                g.setFont(font);
                g.setColor(Color.red);
                g.drawString ("You should not play this game! Extremly Poor!", 240, 480);    
            }    
        }
        
        // displaying stuckDisplay page when the condition is met 
        if (stuckDisplay == true) {
            
            // filling the background with orange colour 
            g.setColor(Color.orange);
            g.fillRect(0, 0, 888, 665);  
            
            // displaying "O Oh!!!" in big fonts 
            font = new Font("Calibria", Font.BOLD, 100);
            g.setFont(font); 
            g.setColor(Color.red);
            g.drawString("O Oh!!!!!!", 240, 250);
            
            // displaying a title called "HINTS for next time"
            font = new Font("Calibria", Font.BOLD, 30);
            g.setFont(font); 
            g.setColor(Color.blue);
            g.drawString("HINTS for next time: ", 80, 430);
            g.drawString("_________________ ", 80, 430);      
            
            // displaying possible suggetions for the next game  
            font = new Font("Calibria", Font.BOLD, 25);
            g.setFont(font); 
            g.setColor(Color.black);
            g.drawString("The box got stuck inside the fences! ", 250, 350);
            g.drawString(" > DO NOT TAKE SHARP TURNS" , 80, 470);
            g.drawString(" > AVOIDE PRESSING MORE THAN ONE ARROW KEYS" , 80, 510);
            g.drawString(" > STAY AWAY FROM THE WALLS/FENCES" , 80, 550);
            
            g.setColor(Color.black);
            g.drawString(" Press SPACE BAR to re-start the game! " , 210, 600);   // upon pressing SPACE-BAR game re-starts             
                 
        }      
     }
    
    /** Accessor method to determine the speed of the block (even = slow, odd = fast)
      * @return the current speed of the block
      */
    public int getFastOrSlow(){
        return this.fastOrSlow; 
    }

    /** Accessor method for the current state of the instruction page 
      * @return the current state of the instruction page 
      */
    public boolean getInstructionPage(){
        return this.instructionPage;
    }
    
    /** Accessor method for the current state of the game page 
      * @return the current state of the game page 
      */
    public boolean getGamePage() {
         return this.gameDisplay;
    }
    
    /** Accessor method for the current state of the stuck page 
      * @return the current state of the stuck page 
      */
    public boolean getStuckPage() {
        return this.stuckDisplay;
    }
      
   /** Accessor method for the current state of the game over page 
      * @return the current state of the game over page 
      */
    public boolean getGameOver() {
        return this.gameOver;
    }
       
    /** Mutator method to set the state of the game page 
      * @param state - state of the game page 
      */
    public void setGamePage(boolean state ) {
        this.gameDisplay = state;
    }
  
    /** Mutator method to set the state of the instruction page 
      * @param state - state of the instruction page 
      */
    public void setInstructionPage(boolean state) {
        this.instructionPage = state;
    }

    /** Mutator method to set the state of the stuck page 
      * @param state - state of the stuck page 
      */
    public void setStuckPage(boolean state) {
        this.stuckDisplay = state;
    }
    
    /** Mutator method to set the new x-coordinate of the block
      * @param x - new x-coordinate 
      */
    public void setUserXLocation(int x) {
        this.user.setCoordinateX(x);
    }
    
    /** Mutator method to set the new y-coordinate of the block
      * @param y - new y-coordinate 
      */
    public void setUserYLocation(int y) {
        this.user.setCoordinateY(y);
    }

    /** Mutator method to set the new score of the user 
      * @param newScore - new score 
      */
    public void setGameScore(int newScore) {
        this.gameScore = newScore;
    }


}
