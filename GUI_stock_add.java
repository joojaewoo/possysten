package pos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI_stock_add {
	JFrame Jframe;
	JTextField name,num;
	public GUI_stock_add() {
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
				if(chk_information()) {
				if(db.chk_P(name.getText())) {
					db.update_S(name.getText(),Integer.parseInt(num.getText()));
					JOptionPane.showMessageDialog(null, "����߰��� �Ϸ�Ǿ����ϴ�.");
					Jframe.setVisible(false);}
				else
					JOptionPane.showMessageDialog(null, "��ǰ���� Ȯ�����ּ���");
				}
				else
					JOptionPane.showMessageDialog(null, "Error");
			}
		};
		button.addActionListener(listener);
	}
	public boolean chk_information() {
		if(name.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "��ǰ���� �Է��ϼ���");
			return false;
		}
		else if(num.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "������ �Է��ϼ���");
			return false;
		}
		else if(Integer.parseInt(num.getText())<0) {
			JOptionPane.showMessageDialog(null, "������ Ȯ���ϼ���");
			return false;
		}
		return true;
	}
static public void main(String args[]) {
	new GUI_stock_add();
}
}
