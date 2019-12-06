package pos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI_stock_add {
	JFrame Jframe;
	JTextField name,num;
	JPanel jp = new JPanel();
	public GUI_stock_add() {
		Jframe= new JFrame();
		jp.setBackground(Color.white);
		Jframe.add(jp);
		setJframe();
		setJtextField();
		setButton();
		Jframe.pack();
		Jframe.setVisible(true);
		Jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void setJframe(){
		Dimension d=new Dimension(400,200);
		Jframe.setLocation(500,400);
		jp.setLayout(new GridLayout(3,1));
		jp.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 , 20));
		Jframe.setPreferredSize(d);
	}
	public void setJtextField() {
		JLabel l1 = new JLabel("상품명");
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(l1);
		
		name=new JTextField(15);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(name);
		
		JLabel l2 = new JLabel("추가수량 입력");
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(l2);
		
		num=new JTextField(10);
		num.setHorizontalAlignment(JTextField.CENTER);
		num.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(num);
	}
	public void setButton() {
		jp.add(new JLabel());
		RoundButton button=new RoundButton("완료");
		button.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(button);
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chk_information()) {
				if(db.chk_P(name.getText())) {
					db.update_S(name.getText(),Integer.parseInt(num.getText()));
					JOptionPane.showMessageDialog(null, "재고추가가 완료되었습니다.");
					Jframe.setVisible(false);}
				else
					JOptionPane.showMessageDialog(null, "상품명을 확인해주세요");
				}
				else
					JOptionPane.showMessageDialog(null, "Error");
			}
		};
		button.addActionListener(listener);
	}
	public boolean chk_information() {
		if(name.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "상품명을 입력하세요");
			return false;
		}
		else if(num.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "수량을 입력하세요");
			return false;
		}
		else if(Integer.parseInt(num.getText())<0) {
			JOptionPane.showMessageDialog(null, "수량을 확인하세요");
			return false;
		}
		return true;
	}
static public void main(String args[]) {
	new GUI_stock_add();
}
}
