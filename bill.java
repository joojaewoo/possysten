package pos;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

class bill {
private String barcode,name,price,count,category;
static JTextArea Bill_Text1=new JTextArea(30,100);
public bill(String barcode,String name,String price,String count,String category) {
	this.barcode=barcode;
	this.name=name;
	this.price=price;
	this.count=count;	
	this.category=category;
}
public bill(String barcode) {
	JFrame Jframe=new JFrame();
	setJframe(Jframe,Bill_Text1);
    Bill(barcode);
    Jframe.add(Bill_Text1);
    Jframe.pack();
	Jframe.setVisible(true);
	Jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
public void setJframe(JFrame Jframe,JTextArea a){
	Dimension d=new Dimension(260,300);
	Jframe.setLocation(500,400);
	Jframe.setPreferredSize(d);
}
public String getBarcode() {
	return this.barcode;
}
public String getName() {
	return this.name;
}
public String getPrice() {
	return this.price;
}
public String getCount() {
	return this.count;
}
public String getCategory() {
	return this.category;
}
public void setBarcode(String barcode) {
	this.barcode=barcode;
}
public void setName(String name) {
	this.name=name;
}
public void setPrice(String price) {
	this.price=price;
}
public void setCount(String count) {
	this.count=count;
}
public void setCategory(String category) {
	this.category=category;
}
public static void Bill(String barcode) {
	Bill_Text1.setEnabled(false);
	Bill_Text1.setText("");
    String header[] = {"상품명", "수량", "가격"};
    ArrayList<bill> a= db.search_B(barcode);
    String contents[][]=new String[a.size()][3];
    for(int i=0;i<a.size();i++) {
    	contents[i][0]=a.get(i).getName();
    	contents[i][1]=a.get(i).getCount();
    	contents[i][2]=a.get(i).getPrice();
    }
    
    int sum = 0;
    DefaultTableModel model = new DefaultTableModel(contents, header);
    JTable table = new JTable(model);

    Bill_Text1.setText(Bill_Text1.getText() + "=================================\n");
    Bill_Text1.setText(Bill_Text1.getText() + "====           POSBILL       ====\n");
    Bill_Text1.setText(Bill_Text1.getText() + "=================================\n");

    Bill_Text1.setText(Bill_Text1.getText() + "상품명" + "\t" + "수량" + "\t" + "가격" + "\n");

    for (int i = 0; i < model.getRowCount(); i++) {

        String pname = (String) model.getValueAt(i, 0);
        String amount = (String) model.getValueAt(i, 1);
        String price = (String) model.getValueAt(i, 2);

        Bill_Text1.setText(Bill_Text1.getText() + pname + "\t" + amount + "\t" + price + "\n");
    }

    Bill_Text1.setText(Bill_Text1.getText() + "\n");
    Bill_Text1.setText(Bill_Text1.getText() + "\n");

    // 합계
    for (int i = 0; i < model.getRowCount(); i++) {
        String price = (String) model.getValueAt(i, 2);
        sum += Integer.parseInt(price);
    }
    Bill_Text1.setText(Bill_Text1.getText() + "\t" + "\t" + "합계 :" + sum + "\n");
    Bill_Text1.setText(Bill_Text1.getText() + "=================================\n");
    Bill_Text1.setText(Bill_Text1.getText() + "====           이용해주셔서 감사합니다       ====\n");

}
}
