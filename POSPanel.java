package pos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.*;

public class POSPanel extends JPanel {
   int[] price;
   int sum = 0;
   String category="";
   int a; // a=1 바코드 a=2 개수 a=3 받은돈
   JTextField tf1 = new JTextField(30); // 가격 총액 출력
   JTextField tf2 = new JTextField(30); // 받은 금액 입력
   JTextField tf3 = new JTextField(30); // 거스름돈
   JTextField bcode = new JTextField(8); // 바코드 입력창
   JTextField cnt = new JTextField(2); // 수량 입력창
   String BARCODE; // 바코드
   String CNT; // 수량
   String MONEY; // 받은돈
   JButton[] SBtn = new JButton[4];
   JButton[] menuBtn = new JButton[6];
   JButton[] KBtn = new JButton[12];
   JButton[] miniBtn = new JButton[2];
   String[] menu = { "재고정보", "영수증", " 매출액", "재고추가", "상품추가","로그아웃" };      //menuBtn
   String[] Str1 = { "선택취소", "전체취소", "결제" };   //SBtn
   String[] Str2 = { "확인", "확인" };      //miniBtn
   String[] ColName = { "상품 이름", "수량","종류", "가격" };   
   String[] ColName2 = {"상품 이름", "가격"};
   String[] keyPad = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "등록","지움"};      //KBtn
   String[] T=new String[4];
   String[] T2=new String[2];
   JLabel label1 = new JLabel("바코드");   
   JLabel label2 = new JLabel("수량");
   JLabel total = new JLabel("총 금액");
   JLabel receiveM = new JLabel("받은 금액");
   JLabel cngM = new JLabel("거스름돈");
   int count = 1;
   String stock="";
   DefaultTableModel model = new DefaultTableModel(new Object[][] {}, ColName);
   DefaultTableModel model2 = new DefaultTableModel(new Object[][] {}, ColName2);

   JTable table = new JTable(model);
   JTable table2 = new JTable(model2);

   class Screen extends JPanel {      //등록된 상품 목록
      Screen() {
         setBackground(Color.WHITE);
         DefaultTableModel m1 = (DefaultTableModel) table.getModel();
         table.setRowHeight(50);
         table.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));
         add(new JScrollPane(table));
      }
   }
   class Information extends JPanel {      //바코드 입력시 상품 정보창
      Information() {
         setBackground(Color.WHITE);
         DefaultTableModel m2 = (DefaultTableModel) table2.getModel();
         table2.setRowHeight(50);
         table2.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));
         Dimension d= new Dimension(400,200);
         JScrollPane s=new JScrollPane(table2);
         s.setPreferredSize(d);
         add(s);
         
      }
   }

   class menuBtn extends JPanel {      //왼쪽 상단 메뉴 버튼
      menuBtn() {
         setBackground(Color.WHITE);
         setLayout(new GridLayout(6, 1, 5, 4));
         for (int i = 0; i < menu.length; i++) {
            menuBtn[i] = new JButton(menu[i]);
            menuBtn[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
            add(menuBtn[i]);
         }
      }
   }

   class KeyBtn extends JPanel {      //키패드
      KeyBtn() {
         setBackground(Color.WHITE);
         setLayout(new GridLayout(5, 3, 3, 3));

         for (int i = 0; i < keyPad.length; i++) {
            KBtn[i] = new JButton(keyPad[i]);
            KBtn[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
            add(KBtn[i]);
         }
      }
   }

   class StrBtn extends JPanel {      //선택취소, 전체취소, 결제 버튼
      StrBtn() {
         setBackground(Color.WHITE);
         setLayout(new GridLayout(1, 4, 3, 3));

         for (int i = 0; i < Str1.length; i++) {
            SBtn[i] = new JButton(Str1[i]);
            SBtn[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
            add(SBtn[i]);
         }
      }
   }

   class MiniBtn extends JPanel {      //바코드 입력창, 수량 입력창 옆 확인버튼
      MiniBtn() {
         setBackground(Color.WHITE);
         setLayout(new GridLayout(2, 1, 3, 3));
         for (int i = 0; i < Str2.length; i++) {
            miniBtn[i] = new JButton(Str2[i]);
            miniBtn[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
            add(miniBtn[i]);
         }
      }
   }

   public POSPanel() {
      setLayout(null);
      setBackground(Color.WHITE);
      menuBtn Mbtn = new menuBtn();
      MiniBtn mini = new MiniBtn();
      KeyBtn kbtn = new KeyBtn();
      StrBtn sbtn = new StrBtn();
      Screen sc = new Screen();
      Information inf = new Information();

      label1.setSize(70, 30); // 바코드
      label1.setLocation(730, 30);
      label1.setFont(new Font("맑은고딕", Font.BOLD, 15));
      add(label1);

      label2.setSize(70, 30); // 수량
      label2.setLocation(730, 65);
      label2.setFont(new Font("맑은고딕", Font.BOLD, 15));
      add(label2);

      total.setSize(100, 30);   //총금액
      total.setLocation(250, 480);
      total.setFont(new Font("맑은고딕", Font.BOLD, 20));
      add(total);

      receiveM.setSize(100, 30);   //받은 금액
      receiveM.setLocation(250, 525);
      receiveM.setFont(new Font("맑은고딕", Font.BOLD, 20));
      add(receiveM);

      cngM.setSize(100, 30);   //거스름돈
      cngM.setLocation(250, 570);
      cngM.setFont(new Font("맑은고딕", Font.BOLD, 20));
      add(cngM);

      bcode.setSize(260, 30);      //바코드 입력창
      bcode.setLocation(800, 30);
      bcode.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            a = 1;
         }
      });
      add(bcode);

      cnt.setSize(260, 30);      //수량 입력창
      cnt.setLocation(800, 65);
      cnt.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            a = 2;
         }
      });
      add(cnt);

      tf1.setSize(350, 40);   // 금액란
      tf1.setLocation(350, 480);
      add(tf1);

      tf2.setSize(350, 40);
      tf2.setLocation(350, 525);
      tf2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            a = 3;
         }
      });
      add(tf2);

      tf3.setSize(350, 40);
      tf3.setLocation(350, 570);
      add(tf3);

      sc.setSize(500, 500);
      sc.setLocation(225, 20);
      add(sc);

      inf.setSize(405, 180);
      inf.setLocation(730, 100);
      add(inf);

      Mbtn.setSize(150, 350);
      Mbtn.setLocation(25, 20);
      add(Mbtn);

      mini.setSize(70, 65);
      mini.setLocation(1065, 30);
      add(mini);

      kbtn.setSize(330, 250);
      kbtn.setLocation(800, 300);
      add(kbtn);

      sbtn.setSize(330, 70);
      sbtn.setLocation(800, 580);
      add(sbtn);

      //키패드 1 ~ 0
      KBtn[0].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_one = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"1");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"1");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"1");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });
      
      KBtn[1].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_two = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"2");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"2");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"2");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });

      KBtn[2].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_three = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"3");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"3");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"3");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });

      KBtn[3].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_four = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"4");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"4");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"4");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });

      KBtn[4].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_five = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"5");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"5");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"5");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });

      KBtn[5].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_six = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"6");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"6");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"6");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });

      KBtn[6].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_seven = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"7");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"7");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"7");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });

      KBtn[7].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_eight = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"8");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"8");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"8");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });

      KBtn[8].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_nine = (JButton) e.getSource();
            if (a == 1) {
                bcode.setText(bcode.getText()+"9");
                bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 2) {
                cnt.setText(cnt.getText()+"9");
                cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
             } else if (a == 3) {
                tf2.setText(tf2.getText()+"9");
                tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
             }
             else {
             	JOptionPane.showMessageDialog(null, "선택하세요");
             }
         }
      });

      KBtn[9].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton k_zero = (JButton) e.getSource();
            if (a == 1) {
               bcode.setText(bcode.getText()+"0");
               bcode.setFont(new Font("맑은고딕", Font.BOLD, 20));
            } else if (a == 2) {
               cnt.setText(cnt.getText()+"0");
               cnt.setFont(new Font("맑은고딕", Font.BOLD, 20));
            } else if (a == 3) {
               tf2.setText(tf2.getText()+"0");
               tf2.setFont(new Font("맑은고딕", Font.BOLD, 20));
            }
            else {
            	JOptionPane.showMessageDialog(null, "선택하세요");
            }
         }
      });

      // 등록
      KBtn[10].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             int change = Integer.valueOf(tf2.getText()) - sum;
             tf2.setText(tf2.getText());
             tf3.setText(Integer.toString(change));
             tf3.setFont(new Font("맑은고딕", Font.BOLD, 20));
         }
      });
      KBtn[11].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
        	  if(a==1) {
        		  if(bcode.getText().length()-1>0)
        			  bcode.setText(bcode.getText().substring(0, bcode.getText().length()-1));
        		  else
        			  bcode.setText("");
        	  }
        	  else if(a==2) {
        		  if(cnt.getText().length()-1>0)
        			  cnt.setText(cnt.getText().substring(0, cnt.getText().length()-1));
        		  else
        			  cnt.setText("");
        	  }
        	  else if(a==3) {
        		  if(tf2.getText().length()-1>0)
        			  tf2.setText(tf2.getText().substring(0, tf2.getText().length()-1));
        		  else 
        			  tf2.setText("");
        	  }
        	  else {
        		  JOptionPane.showMessageDialog(null, "선택하세요");
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
//        		  JOptionPane.showMessageDialog(null, "선택하세요");
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
		if(LoginView.Enable==1)
		new total();
		else //관리자 권한 아닐때 메시지 띄우기
		{
			JOptionPane.showMessageDialog(null, "관리자 권한이 필요합니다");
		}
	}
});
menuBtn[3].addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new stock_add();
	}
});
menuBtn[4].addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(LoginView.Enable==1)
		new insert_product();
		else //관리자 권한 아닐때 메시지 띄우기
		{
			JOptionPane.showMessageDialog(null, "관리자 권한이 필요합니다");
		}
	}
});
menuBtn[5].addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("로그아웃");
		LoginView.setEnable(-1);
		new LoginView();
	}
});

      miniBtn[0].addActionListener(new ActionListener() {   //바코드 확인 버튼
         @Override
         public void actionPerformed(ActionEvent e) {
        	 model2.setNumRows(0);
              product a=db.search_L(bcode.getText());
              if(a!=null) {
              T2[0]=a.getName();
              T2[1]=a.getPrice();
              stock=a.getStock();
              category=a.getCategory();
              model2.addRow(T2);}
              else {}	
            }        
      });
      
      miniBtn[1].addActionListener(new ActionListener() {   //수량 확인 버튼
         @Override
         public void actionPerformed(ActionEvent e) {
            int num=Integer.parseInt(cnt.getText());
            if(Integer.parseInt(stock)<num) {
            	JOptionPane.showMessageDialog(null, "상품의 재고를 초과하였습니다. 재고 : " + stock);
            }
            else {
               T[0]=T2[0];
               T[1]=Integer.toString(num);
               T[2]=category;
               T[3]=Integer.toString(Integer.parseInt(T2[1])*num);
               model.addRow(T);
               stock="";
               sum+=Integer.parseInt(T[3]);
               tf1.setText(String.valueOf(sum));
               tf1.setFont(new Font("맑은고딕", Font.BOLD, 20));
               bcode.setText("");
               cnt.setText("");
               model2.setNumRows(0);
            }
         }
         
      });
      
      SBtn[0].addActionListener(new ActionListener() {   // 선택취소
         @Override
         public void actionPerformed(ActionEvent e) {
        	if(table.getSelectedRow()>-1) {
            JButton SB = (JButton) e.getSource();
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            sum=Integer.parseInt(tf1.getText())-Integer.parseInt((String)table.getModel().getValueAt(table.getSelectedRow(), 3));
            tf1.setText(Integer.toString(sum));
            m.removeRow(table.getSelectedRow());}
        	else {
        		JOptionPane.showMessageDialog(null, "선택하세요");	
        	}
         }
      });

      SBtn[1].addActionListener(new ActionListener() {   // 전체취소
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton SB = (JButton) e.getSource();
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);
            sum=0;
            tf1.setText(String.valueOf(""));
         }
      });
      
      SBtn[2].addActionListener(new ActionListener() {   // 결제 (물건재고 수정, 영수증 만들기
         @Override
         public void actionPerformed(ActionEvent e) {
        	 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        	 Date d= new Date();
        	 String date = format.format(d);
        	 //String date=Integer.toString(cal.get(cal.YEAR))+Integer.toString(cal.get(cal.MONTH)+1)+Integer.toString(cal.get(cal.DATE));
        	int num=db.chk_Bill_num(date);
        	if(num==0)
        		date+="1";
        	else
        		date=Integer.toString(num+1);
        	for(int i=0;i<table.getRowCount();i++) {
        	db.insert_B(date, (String)table.getValueAt(i,0), (String)table.getValueAt(i,3),(String)table.getValueAt(i,1),(String) table.getValueAt(i,2));
        	db.update_S((String)table.getValueAt(i,0),-Integer.parseInt((String)table.getValueAt(i,1)));
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

