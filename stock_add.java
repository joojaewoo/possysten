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
				if(name.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "상품명을 입력하세요");
				}
				else if(num.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "수량을 입력하세요");
				}
				else {
				if(db.chk_P(name.getText())) {
					db.update_S(name.getText(),Integer.parseInt(num.getText()));
					JOptionPane.showMessageDialog(null, "재고추가가 완료되었습니다.");
					Jframe.setVisible(false);}
				else
					JOptionPane.showMessageDialog(null, "상품명을 확인해주세요");
				}
			}
		};
		button.addActionListener(listener);
	}
static public void main(String args[]) {
	new stock_add();
}
}
