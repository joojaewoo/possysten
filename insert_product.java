package pos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class insert_product {
	JFrame Jframe;
	JTextField barcode,name,stock,price,category;
	public insert_product() {
		Jframe= new JFrame();
		setJframe();
		setJtextField();
		setButton();
		Jframe.pack();
		Jframe.setVisible(true);
		Jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void setJframe(){
		Dimension d=new Dimension(400,300);
		Jframe.setLocation(500,400);
		Jframe.setLayout(new GridLayout(6,1));
		Jframe.setPreferredSize(d);
	}
	public void setJtextField() {
		Jframe.add(new JLabel("상품바코드"));
		barcode=new JTextField(10);
		Jframe.add(barcode);
		Jframe.add(new JLabel("상품명"));
		name=new JTextField(10);
		Jframe.add(name);
		Jframe.add(new JLabel("가격"));
		price=new JTextField(10);
		Jframe.add(price);
		Jframe.add(new JLabel("수량"));
		stock=new JTextField(10);
		Jframe.add(stock);
		Jframe.add(new JLabel("종류"));
		category=new JTextField(10);
		Jframe.add(category);
	}
	public void setButton() {
		Jframe.add(new JLabel());
		JButton button=new JButton("완료");
		Jframe.add(button);
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(barcode.getText().contentEquals("")){
					JOptionPane.showMessageDialog(null, "바코드를 입력하세요");
				}
				else if(name.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "상품명을 입력하세요");
				}
				else if(price.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "가격을 입력하세요");
				}
				else if(stock.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "재고를 입력하세요");
				}
				else if(category.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "종류를 입력하세요");
				}
				else {
					if(db.insert_P(barcode.getText(),name.getText(),price.getText(),stock.getText(),category.getText()))
						Jframe.setVisible(false);
					else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
			}
		};
		button.addActionListener(listener);
}
	static public void main(String args[]) {
		new insert_product();
		new stock_add();
	}
}
