package pos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class stock_add {
	JFrame Jframe;
	JTextField name,num;
	public stock_add() {
		Jframe= new JFrame();
		setJframe();
		setJtextField();
		setButton();
		Jframe.pack();
		Jframe.setVisible(true);
		Jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void setJframe(){
		Dimension d=new Dimension(500,150);
		Jframe.setLocation(500,400);
		Jframe.setLayout(new GridLayout(3,1));
		Jframe.setPreferredSize(d);
	}
	public void setJtextField() {
		Jframe.add(new JLabel("��ǰ��"));
		name=new JTextField(15);
		Jframe.add(name);
		Jframe.add(new JLabel("�߰������Է�"));
		num=new JTextField(10);
		Jframe.add(num);
	}
	public void setButton() {
		Jframe.add(new JLabel());
		JButton button=new JButton("�Ϸ�");
		Jframe.add(button);
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(name.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "��ǰ���� �Է��ϼ���");
				}
				else if(num.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "������ �Է��ϼ���");
				}
				else {
				if(db.chk_P(name.getText())) {
					db.update_S(name.getText(),Integer.parseInt(num.getText()));
					JOptionPane.showMessageDialog(null, "����߰��� �Ϸ�Ǿ����ϴ�.");
					Jframe.setVisible(false);}
				else
					JOptionPane.showMessageDialog(null, "��ǰ���� Ȯ�����ּ���");
				}
			}
		};
		button.addActionListener(listener);
	}
static public void main(String args[]) {
	new stock_add();
}
}
