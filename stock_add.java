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
		Jframe.add(new JLabel("상품명"));
		name=new JTextField(15);
		Jframe.add(name);
		Jframe.add(new JLabel("추가수량입력"));
		num=new JTextField(10);
		Jframe.add(num);
	}
	public void setButton() {
		Jframe.add(new JLabel());
		JButton button=new JButton("완료");
		Jframe.add(button);
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				db.update_S(name.getText(),Integer.parseInt(num.getText()));	
				Jframe.setVisible(false);
			}
		};
		button.addActionListener(listener);
	}
static public void main(String args[]) {
	new stock_add();
}
}
