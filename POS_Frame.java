package pos;

import javax.swing.JFrame;

public class POS_Frame extends JFrame {
	static JFrame j= new JFrame();
	public POS_Frame() {
		j.setTitle("POS 프로그램");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setContentPane(new POSPanel());
		j.setSize(1200,800);
		j.setVisible(true);
	}
	static public boolean isJ() {
		if(j==null)
			return false;
		else return true;
	}
}