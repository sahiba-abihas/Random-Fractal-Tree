import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BasicBranch {
	
	public static final int MAXLEVEL = 10;
	public static final double DELANGLE = .45; // about 30 degrees ish
	
	public int startx;
	public int starty;
	public int endx;
	public int endy;
	
	public double length;
	public double angle;
	
	public Color tint;
	public int level;
	public boolean done;
	public boolean finish;
	public int count;
	
	public BasicBranch() {
		startx = Visual.WIDE/2;
		starty = Visual.HIGH;
		
		length = 150;
		angle = Math.PI/2; //straight up
		
		endx = Visual.WIDE/2;
		endy = (int)(Visual.HIGH-length);
		
		level = 0;
		done = false;
		tint = new Color(90,30,0);
		finish = false;
		count = 20;
	}	
	
	public BasicBranch(double ang, BasicBranch parent) { 
		startx = parent.endx;
		starty = parent.endy;
		
		angle = ang;
		length = .8*parent.length;
		
		endx = startx + (int)(length*Math.cos(angle));
		endy = starty - (int)(length*Math.sin(angle));
		
		level = parent.level + 1;
		done = false;
		
		int R = parent.tint.getRed();
		int G = parent.tint.getGreen();
		int B = parent.tint.getBlue();
		
		R-=5;
		G+=15;
		B+=10;
		
		R = Math.max(R, 0);
		G = Math.min(G, 255);
		B = Math.min(B, 255);
		tint = new Color(R, G, B);
		finish = false;
		count = 20;
	}
	
	
	public void update(ArrayList<BasicBranch> tree) {
		if(done) return;
		if(level == MAXLEVEL) return;
		if(!finish) return;
		tree.add(makeBranch(0)); //left
		tree.add(makeBranch(1)); //right
		done = true;
	}
	
	public BasicBranch makeBranch(int dir) {
		
		BasicBranch temp;
		
		double angle = this.angle;
		if(dir == 0) angle += DELANGLE;
		if(dir == 1) angle -= DELANGLE;
		if(Math.random() <.3)
			temp = new Trip(angle, this);
		else if(Math.random() <.6 && Math.random()>=.3)
			temp = new BasicBranch(angle, this);
		else
			temp = new Dead(angle, this);
		
		return temp;
		
		
	}
	
	public void draw(Graphics g) {
		int wide = 2*(MAXLEVEL - level);
		Graphics2D g2 = (Graphics2D)g;
		
		
		g2.setStroke(new BasicStroke(wide, 1, 1));
		g.setColor(tint);

		
		if(count>1) count--;
		int endx2 = startx + (int)((length/count)*Math.cos(angle));
		int endy2 = starty - (int)((length/count)*Math.sin(angle));
		
		if(count==1) finish = true;
		g.drawLine(startx, starty, endx2, endy2);
	}
}

/*
I started by adding a Dead class, that randomly kills a certain percentage of branches that are just BasicBranch, and also some branches that are trips, by returning done = true in the update to stop the branch from spawning more. I also added a Knots class to have cherry blossoms randomly bloom on some of the trips, using a fillOval in the draw. Finally, in the draw of BasicBranch, 
*/