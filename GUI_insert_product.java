package pos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_insert_product {
	JFrame Jframe;
	boolean chk_p=false;
	JTextField barcode,name,stock,price,category;
	JPanel jp = new JPanel();
	public GUI_insert_product() {
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
		Dimension d=new Dimension(400,300);
		Jframe.setLocation(500,400);
		GridLayout a=new GridLayout(6,1);
		a.setVgap(10);
		a.setHgap(10);
		jp.setLayout(a);
		jp.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 , 20));
		Jframe.setPreferredSize(d);
	}
	public void setJtextField() {
		JLabel lb1 = new JLabel("상품바코드");
		lb1.setHorizontalAlignment(JLabel.CENTER);
		lb1.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(lb1);
		barcode=new JTextField(10);
		barcode.setHorizontalAlignment(JTextField.CENTER);
		barcode.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(barcode);
		
		JLabel lb2 = new JLabel("상품명");
		lb2.setHorizontalAlignment(JLabel.CENTER);
		lb2.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(lb2);
		name=new JTextField(10);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(name);
		
		JLabel lb3 = new JLabel("가격");
		lb3.setHorizontalAlignment(JLabel.CENTER);
		lb3.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(lb3);
		price=new JTextField(10);
		price.setHorizontalAlignment(JTextField.CENTER);
		price.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(price);
		
		JLabel lb4 = new JLabel("수량");
		lb4.setHorizontalAlignment(JLabel.CENTER);
		lb4.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(lb4);
		stock=new JTextField(10);
		stock.setHorizontalAlignment(JTextField.CENTER);
		stock.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(stock);
		
		JLabel lb5 = new JLabel("종류");
		lb5.setHorizontalAlignment(JLabel.CENTER);
		lb5.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(lb5);
		category=new JTextField(10);
		category.setHorizontalAlignment(JTextField.CENTER);
		category.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(category);
	}
	public void setButton() {
		jp.add(new JLabel());
		RoundButton button=new RoundButton("완료");
		button.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jp.add(button);
		ActionListener listener=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(check_information()){
					if(db.insert_P(barcode.getText(),name.getText(),price.getText(),stock.getText(),category.getText())) {
						JOptionPane.showMessageDialog(null, "입력 성공");
						Jframe.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		};
		button.addActionListener(listener);
	}
	public boolean check_information() {
		if(barcode.getText().contentEquals("")){
			JOptionPane.showMessageDialog(null, "바코드를 입력하세요");
			return false;
		}
		else if(name.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "상품명을 입력하세요");
			return false;
		}
		else if(price.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "가격을 입력하세요");
			return false;
		}
		else if(Integer.parseInt(price.getText())>1000000&&!chk_p) {
			int result =JOptionPane.showConfirmDialog(null, "가격을 확인하세요", "가격확인" , JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				chk_p=true;
			}
			return false;
		}
		else if(stock.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "재고를 입력하세요");
			return false;
		}
		else if(category.getText().contentEquals("")) {
			JOptionPane.showMessageDialog(null, "종류를 입력하세요");
			return false;
		}
		else{
			if(chk_p)
				return true;
			return false;
		}
	}
	public static void main(String args[]) {
		new GUI_insert_product();
	}
}
