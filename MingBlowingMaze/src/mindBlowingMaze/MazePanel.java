/** Maze Panel 
  * ICS4U1
  * @author Nirmit Zinzuwadia 
  * @since June 5th 2015
  */

package mindBlowingMaze;

// importing certain libraries 
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class MazePanel extends javax.swing.JPanel {

    private Game mazeGame = new Game(); // maze game created
    
    // Creates new form MazePanel 
    public MazePanel() {
        
        initComponents();
        
        //set a timer (1 milli-seconds)
        Timer myTimer = new Timer(1, new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                formActionPerformed(evt);
            }
        });
        
        myTimer.start();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /** This method is called from within the constructor to
      * initialize the form.
      * WARNING: Do NOT modify this code. The content of this method is
      * always regenerated by the Form Editor.
      */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(420, 420));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /** If the user hits any key, this method is run
      * @param evt - carries information about which key is pressed 
      */
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
      
        if (mazeGame.getGamePage() == true) {   // runs the following code to move the block 
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                this.mazeGame.playerMoveUpdate('U');
            } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                this.mazeGame.playerMoveUpdate('D');  
            } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.mazeGame.playerMoveUpdate('R');   
            } else if (evt.getKeyCode() == KeyEvent.VK_LEFT){
                this.mazeGame.playerMoveUpdate('L');     
            }        
        } else if (mazeGame.getInstructionPage() == true) {   // runs the following code to move the mouse (sqaure) for the instrcution page
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.mazeGame.mouseMovment(10);
            } else if (evt.getKeyCode() == KeyEvent.VK_LEFT){
                this.mazeGame.mouseMovment(-10);
            }
        } else if (mazeGame.getStuckPage() == true) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {   // restarting the game 
                this.mazeGame.setStuckPage(false); 
                this.mazeGame.setGamePage(true);     
                this.mazeGame.playerMoveUpdate('R');  // block statrs moving right 
                this.mazeGame.setUserXLocation(22);   // new x-coordinate of the block 
                this.mazeGame.setUserYLocation(310);  // new y-coordinate of the block 
                this.mazeGame.setGameScore(1002);     // setting up new score
            }
        }
        this.repaint();
    }//GEN-LAST:event_formKeyPressed

    /** If any action is performed the following method is run
      * @param evt - carries information about the event performed 
      */
    private void formActionPerformed(ActionEvent evt) {
        if (mazeGame.getGamePage() == true) {  // only enters if the state of gameDisplay is true
            if (this.mazeGame.getFastOrSlow() % 2 == 0){
                this.mazeGame.update(1);  // the block is moving slow 
            } else {
                this.mazeGame.update(3);  // the block is moving fast 
            }
        }      
        this.repaint();      
    }

   
    @Override
    /** paints all the objects on the screen (calls the game object's paint method)
      * @param g - passes the graphics object which allows us to draw, color shapes, print strings and etc.  
      */
    public void paint(Graphics g) {
        super.paint(g);      // passes the parameter to the parent class 
        this.mazeGame.paint(g);    // drating the entire maze game 
    }         
     
        
}     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


