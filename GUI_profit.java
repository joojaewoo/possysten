package pos;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class GUI_profit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JDateChooser dateChooser = new JDateChooser();
	JDateChooser dateChooser_1 = new JDateChooser();
	//private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               total_price frame = new total_price();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });*/
		new GUI_profit();
	}

	/**
	 * Create the frame.
	 */
	public GUI_profit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.white);
		getContentPane().setBackground(Color.white);
		Dimension d= new Dimension(600,600);
		/*   dateChooser.getCalendarButton().addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });*/
		
		dateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        dateChooser_1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });
		final RoundButton btnOk = new RoundButton("OK");
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(d);
		final GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(dateChooser_1, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
										.addGap(41)
										.addComponent(btnOk))
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnOk)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);

		final JTable table = new JTable();
		table.setToolTipText("");
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		final DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"�궇吏�","�떇�뭹瑜�", "�옟�솕瑜�", "�쓬猷�", "�떞諛곕쪟", "怨쇱옄瑜�", "二쇰쪟","珥앺빀"
				}
				);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		contentPane.setLayout(gl_contentPane);

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				final String DATE_PATTERN = "yyyyMMdd";
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
				Date start_date = (Date) dateChooser.getDate();
				String inputStartDate = sdf.format(start_date);
				Date end_date = (Date) dateChooser_1.getDate();
				String inputEndDate = sdf.format(end_date);

				Date startDate = null;
				try {
					startDate = (Date) sdf.parse(inputStartDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Date endDate = null;
				try {
					endDate = (Date) sdf.parse(inputEndDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				model.setNumRows(0);
				ArrayList<String> dates = new ArrayList<String>();
				Date currentDate = startDate;

				int []total_tps = new int[7];   // allday total

				while (currentDate.compareTo(endDate) <= 0) {

					int []tps = new int[7];   // oneday total

					String x = sdf.format(currentDate);

					dates.add(sdf.format(currentDate));
					Calendar c = Calendar.getInstance();
					c.setTime(currentDate);
					c.add(Calendar.DAY_OF_MONTH, 1);
					currentDate = (Date) c.getTime();
					ArrayList<sale> a = db.profit(x);
					cal(tps, a);
					
					for(int i = 0; i < 7; i++)
						total_tps[i] += tps[i];

					Object []tmp = {x, tps[0], tps[1], tps[2], tps[3], tps[4],tps[5],tps[6]};
					if(tps[6]!=0) 
					model.addRow(tmp);
					// sum[] = total_price


				}

				Object []tmp = {"珥앺빀", total_tps[0], total_tps[1], total_tps[2], total_tps[3], total_tps[4],total_tps[5],total_tps[6]};
				if(total_tps[6]==0)
					JOptionPane.showMessageDialog(null, "議고쉶�맂 留ㅼ텧�씠 �뾾�뒿�땲�떎.");
				else
				model.addRow(tmp);
				/*for(int i = 0; i < dates.size(); i++) {
               //db.profit(dates[i]);
               table.insertRow(dates[i]);

            }*/
				//object total_price 

			}

			private void cal(int[] tps, ArrayList<sale> a) {
				// TODO Auto-generated method stub
				for(int i = 0; i < a.size(); i++) {
					if(a.get(i).category.equalsIgnoreCase("�떇�뭹瑜�"))
						tps[0] += a.get(i).price;
					else if(a.get(i).category.equalsIgnoreCase("�옟�솕瑜�"))
						tps[1] += a.get(i).price;
					else if(a.get(i).category.equalsIgnoreCase("�쓬猷�"))
						tps[2] += a.get(i).price;
					else if(a.get(i).category.equalsIgnoreCase("�떞諛곕쪟"))
						tps[3] += a.get(i).price;
					else if(a.get(i).category.equalsIgnoreCase("怨쇱옄瑜�"))
						tps[4] += a.get(i).price;
					else if(a.get(i).category.equalsIgnoreCase("二쇰쪟"))
						tps[5] += a.get(i).price;
					tps[6] += a.get(i).price;

				}
			}
		});

		setSize(600,400);
		setVisible(true);

	}
	private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {                                             
        // TODO add your handling code here:
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date start_date = dateChooser.getDate();
        String a = format.format(start_date);
        int aa = Integer.parseInt(a);
        Date end_date = dateChooser_1.getDate();
        String b = format.format(end_date);
        int bb = Integer.parseInt(b);
        if(aa > bb) {
            JOptionPane.showMessageDialog(null, "�궇吏쒕�� �떎�떆 �꽑�깮�븯�꽭�슂");
            dateChooser.setDate(end_date);
        }
    }
    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {                                             
        // TODO add your handling code here:
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date start_date = dateChooser.getDate();
        String a = format.format(start_date);
        int aa = Integer.parseInt(a);
        Date end_date = dateChooser_1.getDate();
        String b = format.format(end_date);
        int bb = Integer.parseInt(b);
        if(aa > bb) {
            JOptionPane.showMessageDialog(null, "�궇吏쒕�� �떎�떆 �꽑�깮�븯�꽭�슂");
            dateChooser_1.setDate(start_date);
        }
    }
}