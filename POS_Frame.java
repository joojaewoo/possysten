package pos;

import javax.swing.JFrame;

public class POS_Frame extends JFrame {
	public POS_Frame() {
		setTitle("POS ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new POSPanel());
		setSize(1200,800);
		setVisible(true);
	}
}