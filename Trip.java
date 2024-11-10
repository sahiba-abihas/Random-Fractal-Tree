import java.awt.Color;
import java.util.ArrayList;

public class Trip extends BasicBranch {

	public Trip() {
		super();
	}
	public Trip(double ang, BasicBranch parent) {
		super(ang, parent);
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
		
		if(Math.random() <.5)
			temp = new Trip(angle, this);
		else if(Math.random() <.6 && Math.random()>=.5)
			temp = new Knots(angle, this);
		else
			temp = new Dead(angle, this);
		
		//temp = new BasicBranch(angle, this);
		if(dir == 2) {
			temp.length +=10;
			temp.endx = temp.startx +(int)(temp.length*Math.cos(temp.angle));
			temp.endy = temp.starty -(int)(temp.length*Math.sin(temp.angle));
		}
		
		
		return temp;
		
		
	}
	
	
}
