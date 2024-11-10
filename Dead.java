import java.util.ArrayList;

public class Dead extends BasicBranch {

	public Dead() {
		super();
	}
	public Dead(double ang, BasicBranch parent) {
		super(ang, parent);
	}
	public void update(ArrayList<BasicBranch> tree) {
		done = true;
		return;
	}
	
}
