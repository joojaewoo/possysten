package pos;

import javax.swing.JFrame;

public class POS_Frame extends JFrame {
	static JFrame j= new JFrame();
	public POS_Frame() {
		j.setTitle("POS ���α׷�");
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