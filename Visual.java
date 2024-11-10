import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.util.ArrayList;
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer;
    
 
 
public class Visual implements ActionListener, KeyListener {
 
    private JFrame frame;       //REQUIRED! The outside shell of the window
    public DrawingPanel panel;  //REQUIRED! The interior window
    private Timer visualtime;   //REQUIRED! Runs/Refreshes the screen. 
   
    //Adjust these values:
    public static final int WIDE = 1000;
    public static final int HIGH = 800;
    
    //All other public data members go here:
    public ArrayList<BasicBranch> tree;
    public boolean next;
    
    public Visual()
    {
        //Adjust the name, but leave everything else alone.
        frame = new JFrame("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new DrawingPanel();
        panel.setPreferredSize(new Dimension(WIDE, HIGH));
        frame.getContentPane().add(panel);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.addKeyListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        
        Initialize();
        
        //This block of code is fairly constant too -- always have it.
        visualtime = new Timer(20, this);     
        visualtime.start();
    } 
 
    public void Initialize()
    {
        //Initialize all data members here...
    	next = false;
    	tree = new ArrayList<BasicBranch>();
    	tree.add(new BasicBranch());
        
    }
    public void actionPerformed(ActionEvent e)
    {    
        //Once the new Visual() is launched, this method runs an infinite loop
        //next = true;
    	if(next) {
    		for(int n = tree.size()-1; n>=0; n--) 
    			tree.get(n).update(tree);
    		next = false;
    		
    	}
    	
        panel.repaint();
    }
 
    public void keyPressed(KeyEvent e)
    {   
    	if(e.getKeyCode() == KeyEvent.VK_SPACE)
    		next = true;
    	
    	
        if(e.getKeyCode() == KeyEvent.VK_HOME)
            Initialize();
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0); 
        
    }
    
    public void keyTyped(KeyEvent e) {  }   //not used
    public void keyReleased(KeyEvent e) {  }//not used
        
//BIG NOTE:  The coordinate system for the output screen is as follows:     
//  (x,y) = (0, 0) is the TOP LEFT corner of the output screen;    
//  (x,y) = (WIDE, 0) is the TOP RIGHT corner of the output screen;     
//  (x,y) = (0, HIGH) is the BOTTOM LEFT corner of the screen;   
//  (x,y) = (WIDE, HIGH) is the BOTTOM RIGHT corner of the screen;
//REMEMBER::
// Strings are referenced from their BOTTOM LEFT corner.
// Virtually all other objects (Rectangles, Ovals, Images...) 
//    are referenced from their TOP LEFT corner.
    private class DrawingPanel extends JPanel { 
        public void paintComponent(Graphics g)         
        {
            super.paintComponent(g);
            panel.setBackground(Color.black);
            
            //this is where you draw items on the screen.
     
            for(int n = 0; n<tree.size(); n++) {
            		tree.get(n).draw(g);
            }
        }
    }
}