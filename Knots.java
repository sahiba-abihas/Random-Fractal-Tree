import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;

public class Knots extends BasicBranch{
	public Knots() {
		super();
	}
	public Knots(double ang, BasicBranch parent) {
		super(ang, parent);
		tint = tint.brighter();
		done = true;

	}
	public void update(ArrayList<BasicBranch> tree) {
		if(done) return;
		if(level == MAXLEVEL) return;
		
		tree.add(makeBranch(0)); //left
		tree.add(makeBranch(1)); //right
		tree.add(makeBranch(2)); //center
		
		done = true;
	}
	
	public BasicBranch makeBranch(int dir) {
		
		BasicBranch temp;
		
		double angle = this.angle;
		if(dir == 0) angle +=1.5* DELANGLE;
		if(dir == 1) angle -= 1.5* DELANGLE;
		
		temp = new Knots(angle, this);
		
		//temp = new BasicBranch(angle, this);
		if(dir == 2) {
			temp.length +=10;
			temp.endx = temp.startx +(int)(temp.length*Math.cos(temp.angle));
			temp.endy = temp.starty -(int)(temp.length*Math.sin(temp.angle));
		}
		
		
		return temp;
		
	}
	public void draw(Graphics g) {

		g.setColor(tint);
		int wide = 2*(MAXLEVEL - level);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(wide, 1, 1));
		if(count>1) count--;
		int endx2 = startx + (int)((length/count)*Math.cos(angle));
		int endy2 = starty - (int)((length/count)*Math.sin(angle));
		
		if(count==1) {
			finish = true;
			g.setColor(Color.PINK);
			g.fillOval(endx, endy, 15, 15);
		}
		g.drawLine(startx, starty, endx2, endy2);
	}
}
