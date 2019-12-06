package pos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

public class POSPanel extends JPanel {
	int[] price;
	int sum = 0;
	String category = "";
	int a; // a=1 ���ڵ� a=2 ���� a=3 ������
	String[] Str1 = { "�������", "��ü���", "����" }; // SBtn
	String[] Str2 = { "Ȯ��", "Ȯ��" }; // miniBtn

	RoundButton[] SBtn = new RoundButton[4];
	RoundButton[] menuBtn = new RoundButton[6];
	RoundButton[] KBtn = new RoundButton[12];
	RoundButton[] miniBtn = new RoundButton[2];
	int count = 1;
	String stock = "";
	String[] ColName = { "��ǰ �̸�", "����", "����", "����" };
	String[] menu = { "�������", "������", " �����", "����߰�", "��ǰ�߰�" }; // menuBtn
	String[] ColName2 = { "��ǰ �̸�", "����" };
	String[] keyPad = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "���", "����" }; // KBtn
	DefaultTableModel model = new DefaultTableModel(new Object[][] {}, ColName);
	// DefaultTableModel model2 = new DefaultTableModel(new Object[][] {},
	// ColName2);

	JTable table = new JTable(model);
	// JTable table2 = new JTable(model2);

	class Screen extends JPanel { // ��ϵ� ��ǰ ���
		Screen() {
			setBackground(Color.WHITE);
			DefaultTableModel m1 = (DefaultTableModel) table.getModel();
			table.setRowHeight(50);
			table.getTableHeader().setFont(new Font("�������", Font.BOLD, 15));
			add(new JScrollPane(table));
		}
	}
	/*
	 * class Information extends JPanel { //���ڵ� �Է½� ��ǰ ����â Information() {
	 * setBackground(Color.WHITE); DefaultTableModel m2 = (DefaultTableModel)
	 * table2.getModel(); table2.setRowHeight(50);
	 * table2.getTableHeader().setFont(new Font("�������", Font.BOLD, 15)); Dimension
	 * d= new Dimension(400,200); JScrollPane s=new JScrollPane(table2);
	 * s.setPreferredSize(d); add(s);
	 * 
	 * } }
	 */

	class menuBtn extends JPanel { // ���� ��� �޴� ��ư
		menuBtn() {
			setBackground(Color.WHITE);
			setLayout(new GridLayout(6, 1, 5, 4));
			for (int i = 0; i < menu.length; i++) {
				menuBtn[i] = new RoundButton(menu[i]);
				menuBtn[i].setFont(new Font("���� ���", Font.BOLD, 15));
				add(menuBtn[i]);
			}
		}
	}

	class KeyBtn extends JPanel { // Ű�е�
		KeyBtn() {
			setBackground(Color.WHITE);
			setLayout(new GridLayout(5, 3, 3, 3));

			for (int i = 0; i < keyPad.length; i++) {
				KBtn[i] = new RoundButton(keyPad[i]);
				KBtn[i].setFont(new Font("���� ���", Font.BOLD, 20));
				add(KBtn[i]);
			}
		}
	}

	class StrBtn extends JPanel { // �������, ��ü���, ���� ��ư
		StrBtn() {
			setBackground(Color.WHITE);
			setLayout(new GridLayout(1, 4, 3, 3));

			for (int i = 0; i < Str1.length; i++) {
				SBtn[i] = new RoundButton(Str1[i]);
				SBtn[i].setFont(new Font("���� ���", Font.BOLD, 15));
				add(SBtn[i]);
			}
		}
	}

	class MiniBtn extends JPanel { // ���ڵ� �Է�â, ���� �Է�â �� Ȯ�ι�ư
		MiniBtn() {
			setBackground(Color.WHITE);
			setLayout(new GridLayout(2, 1, 3, 3));
			for (int i = 0; i < Str2.length; i++) {
				miniBtn[i] = new RoundButton(Str2[i]);
				miniBtn[i].setFont(new Font("���� ���", Font.BOLD, 15));
				add(miniBtn[i]);
			}
		}
	}

	public POSPanel() {
		JTextField tf1 = new JTextField(30); // ���� �Ѿ� ���
		JTextField tf2 = new JTextField(30); // ���� �ݾ� �Է�
		JTextField tf3 = new JTextField(30); // �Ž�����
		JTextField bcode = new JTextField(8); // ���ڵ� �Է�â
		JTextField cnt = new JTextField(2); // ���� �Է�â
		JTextField p_name = new JTextField(10);
		JTextField p_price = new JTextField(8);
		String BARCODE; // ���ڵ�
		String CNT; // ����
		String MONEY; // ������
		JPanel jp = new JPanel();

		String[] T = new String[4];
		String[] T2 = new String[2];
		JLabel label1 = new JLabel("���ڵ�");
		JLabel label2 = new JLabel("����");
		JLabel total = new JLabel("�� �ݾ�");
		JLabel receiveM = new JLabel("���� �ݾ�");
		JLabel cngM = new JLabel("�Ž�����");
		JLabel pdt_name = new JLabel("��ǰ�̸�");
		JLabel pdt_price = new JLabel("����");
		setLayout(null);
		setBackground(Color.WHITE);
		RoundButton loginBtn = new RoundButton("����� ����");
		menuBtn Mbtn = new menuBtn();
		MiniBtn mini = new MiniBtn();
		KeyBtn kbtn = new KeyBtn();
		StrBtn sbtn = new StrBtn();
		Screen sc = new Screen();
		// Information inf = new Information();

		jp.setSize(400, 150);
		jp.setLocation(730, 190);
		jp.setBackground(Color.white);
		jp.setLayout(new GridLayout(2, 2, 5, 5));
		jp.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		jp.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "��ǰ����"));
		add(jp);

		label1.setSize(70, 30); // ���ڵ�
		label1.setLocation(730, 100);
		label1.setFont(new Font("�������", Font.BOLD, 15));
		add(label1);

		label2.setSize(70, 30); // ����
		label2.setLocation(730, 135);
		label2.setFont(new Font("�������", Font.BOLD, 15));
		add(label2);

		total.setSize(100, 30); // �ѱݾ�
		total.setLocation(250, 560);
		total.setFont(new Font("�������", Font.BOLD, 20));
		add(total);

		receiveM.setSize(100, 30); // ���� �ݾ�
		receiveM.setLocation(250, 605);
		receiveM.setFont(new Font("�������", Font.BOLD, 20));
		add(receiveM);

		cngM.setSize(100, 30); // �Ž�����
		cngM.setLocation(250, 650);
		cngM.setFont(new Font("�������", Font.BOLD, 20));
		add(cngM);

		bcode.setSize(260, 30); // ���ڵ� �Է�â
		bcode.setLocation(800, 100);
		bcode.setFont(new Font("�������", Font.BOLD, 15));
		bcode.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				a = 1;
			}
		});
		add(bcode);

		cnt.setSize(260, 30); // ���� �Է�â
		cnt.setLocation(800, 135);
		cnt.setFont(new Font("�������", Font.BOLD, 15));
		cnt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				a = 2;
			}
		});
		add(cnt);

		tf1.setSize(350, 40); // �ݾ׶�
		tf1.setLocation(350, 560);
		tf1.setFont(new Font("�������", Font.BOLD, 15));
		add(tf1);

		tf2.setSize(350, 40);
		tf2.setLocation(350, 605);
		tf2.setFont(new Font("�������", Font.BOLD, 15));
		tf2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				a = 3;
			}
		});
		add(tf2);

		tf3.setSize(350, 40);
		tf3.setLocation(350, 650);
		tf3.setFont(new Font("�������", Font.BOLD, 15));
		add(tf3);

		sc.setSize(500, 500);
		sc.setLocation(225, 100);
		add(sc);

		/*
		 * inf.setSize(405, 180); inf.setLocation(730, 100); add(inf);
		 */
		pdt_name.setSize(70, 30);
		pdt_name.setLocation(730, 100);
		pdt_name.setFont(new Font("�������", Font.BOLD, 15));
		pdt_name.setHorizontalAlignment(JLabel.CENTER);
		jp.add(pdt_name);

		pdt_price.setSize(70, 30);
		pdt_price.setLocation(730, 180);
		pdt_price.setFont(new Font("�������", Font.BOLD, 15));
		pdt_price.setHorizontalAlignment(JLabel.CENTER);
		jp.add(pdt_price);

		p_name.setSize(80, 30);
		p_name.setHorizontalAlignment(JTextField.CENTER);
		p_name.setFont(new Font("�������", Font.BOLD, 15));
		p_name.setBorder(null);
		jp.add(p_name);

		p_price.setSize(80, 30);
		p_price.setHorizontalAlignment(JTextField.CENTER);
		p_price.setFont(new Font("�������", Font.BOLD, 15));
		p_price.setBorder(null);
		jp.add(p_price);
		
		Mbtn.setSize(150, 380);
		Mbtn.setLocation(50, 105);
		add(Mbtn);

		mini.setSize(70, 65);
		mini.setLocation(1065, 100);
		add(mini);

		kbtn.setSize(400, 260);
		kbtn.setLocation(730, 360);
		add(kbtn);

		sbtn.setSize(400, 70);
		sbtn.setLocation(730, 620);
		add(sbtn);

		loginBtn.setSize(120, 60);
		loginBtn.setLocation(1015, 10);
		loginBtn.setFont(new Font("���� ���", Font.BOLD, 15));
		add(loginBtn);
		
		// Ű�е� 1 ~ 0
		KBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_one = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "1");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "1");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "1");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_two = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "2");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "2");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "2");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_three = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "3");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "3");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "3");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_four = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "4");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "4");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "4");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_five = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "5");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "5");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "5");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_six = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "6");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "6");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "6");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_seven = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "7");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "7");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "7");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_eight = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "8");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "8");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "8");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_nine = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "9");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "9");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "9");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		KBtn[9].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton k_zero = (RoundButton) e.getSource();
				if (a == 1) {
					bcode.setText(bcode.getText() + "0");
					bcode.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 2) {
					cnt.setText(cnt.getText() + "0");
					cnt.setFont(new Font("�������", Font.BOLD, 20));
				} else if (a == 3) {
					tf2.setText(tf2.getText() + "0");
					tf2.setFont(new Font("�������", Font.BOLD, 20));
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		// ���
		KBtn[10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int change = Integer.valueOf(tf2.getText()) - sum;
				tf2.setText(tf2.getText());
				tf3.setText(Integer.toString(change));
				tf3.setFont(new Font("�������", Font.BOLD, 20));
			}
		});
		KBtn[11].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (a == 1) {
					if (bcode.getText().length() - 1 > 0)
						bcode.setText(bcode.getText().substring(0, bcode.getText().length() - 1));
					else
						bcode.setText("");
				} else if (a == 2) {
					if (cnt.getText().length() - 1 > 0)
						cnt.setText(cnt.getText().substring(0, cnt.getText().length() - 1));
					else
						cnt.setText("");
				} else if (a == 3) {
					if (tf2.getText().length() - 1 > 0)
						tf2.setText(tf2.getText().substring(0, tf2.getText().length() - 1));
					else
						tf2.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});
//      KBtn[12].addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//        	  if(a==1) {
//        		  bcode.setText("");
//        	  }
//        	  else if(a==2) {
//        		  cnt.setText("");
//        	  }
//        	  else if(a==3) {
//        		  tf2.setText("");
//        	  }
//        	  else {
//        		  JOptionPane.showMessageDialog(null, "�����ϼ���");
//        	  }
//          }
//       });

		menuBtn[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_list().setVisible(true);
			}
		});
		menuBtn[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_refund().setVisible(true);
			}
		});
		menuBtn[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (LoginView.Enable == 1)
					new GUI_profit();
				else // ������ ���� �ƴҶ� �޽��� ����
				{
					JOptionPane.showMessageDialog(null, "������ ������ �ʿ��մϴ�");
				}
			}
		});
		menuBtn[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_stock_add();
			}
		});
		menuBtn[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (LoginView.Enable == 1)
					new GUI_insert_product();
				else // ������ ���� �ƴҶ� �޽��� ����
				{
					JOptionPane.showMessageDialog(null, "������ ������ �ʿ��մϴ�");
				}
			}
		});
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�α׾ƿ�");
				LoginView.setEnable(-1);
				new LoginView();
			}
		});

		miniBtn[0].addActionListener(new ActionListener() { // ���ڵ� Ȯ�� ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				// model2.setNumRows(0);
				product a = db.search_L(bcode.getText());
				if (a != null) {
					T2[0] = a.getName();
					T2[1] = a.getPrice();
					stock = a.getStock();
					category = a.getCategory();
					// model2.addRow(T2);
					p_name.setText(T2[0]);
					p_price.setText(T2[1]);
				} else {
				}
			}
		});

		miniBtn[1].addActionListener(new ActionListener() { // ���� Ȯ�� ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(cnt.getText());
				if (Integer.parseInt(stock) < num) {
					JOptionPane.showMessageDialog(null, "��ǰ�� ��� �ʰ��Ͽ����ϴ�. ��� : " + stock);
					cnt.setText(stock);
				} else {
					T[0] = T2[0];
					T[1] = Integer.toString(num);
					T[2] = category;
					T[3] = Integer.toString(Integer.parseInt(T2[1]) * num);
					model.addRow(T);
					stock = "";
					sum += Integer.parseInt(T[3]);
					tf1.setText(String.valueOf(sum));
					tf1.setFont(new Font("�������", Font.BOLD, 20));
					bcode.setText("");
					cnt.setText("");
					// model2.setNumRows(0);
				}
			}

		});

		SBtn[0].addActionListener(new ActionListener() { // �������
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					RoundButton SB = (RoundButton) e.getSource();
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					sum = Integer.parseInt(tf1.getText())
							- Integer.parseInt((String) table.getModel().getValueAt(table.getSelectedRow(), 3));
					tf1.setText(Integer.toString(sum));
					m.removeRow(table.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(null, "�����ϼ���");
				}
			}
		});

		SBtn[1].addActionListener(new ActionListener() { // ��ü���
			@Override
			public void actionPerformed(ActionEvent e) {
				RoundButton SB = (RoundButton) e.getSource();
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				m.setRowCount(0);
				sum = 0;
				tf1.setText(String.valueOf(""));
			}
		});

		SBtn[2].addActionListener(new ActionListener() { // ���� (������� ����, ������ �����
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				Date d = new Date();
				String date = format.format(d);
				// String
				// date=Integer.toString(cal.get(cal.YEAR))+Integer.toString(cal.get(cal.MONTH)+1)+Integer.toString(cal.get(cal.DATE));
				int num = db.chk_Bill_num(date);
				if (num == 0)
					date += "1";
				else
					date = Integer.toString(num + 1);
				for (int i = 0; i < table.getRowCount(); i++) {
					db.insert_B(date, (String) table.getValueAt(i, 0), (String) table.getValueAt(i, 3),
							(String) table.getValueAt(i, 1), (String) table.getValueAt(i, 2));
					db.update_S((String) table.getValueAt(i, 0), -Integer.parseInt((String) table.getValueAt(i, 1)));
				}
				model.setNumRows(0);
				new bill(date);
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
			}
		});
	}
}
