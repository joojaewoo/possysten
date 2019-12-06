package pos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class LoginView extends JFrame{
    private gui lo;
    private gui testFrm;
    JFrame L=new JFrame();
   
    private RoundButton btnLogin;
    private RoundButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;
    public static int Enable=1;
    public static void main(String[] args) {
        //new LoginView();
    }
 
    public LoginView() {
        // setting
    	setEnable(-1);
        L.setTitle("login");
        L.setSize(280, 150);
        L.setResizable(false);
        L.setLocation(800, 450);
        L.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        // panel
        JPanel panel = new JPanel();
        placeLoginPanel(panel);
       panel.setBackground(Color.white);
       
        // add
        L.add(panel);
       
        // visiible
        L.setVisible(true);
    }
   
    public void placeLoginPanel(JPanel panel){
        panel.setLayout(null);     
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);
       
        JLabel passLabel = new JLabel("Pass");
        passLabel.setBounds(10, 40, 80, 25);
        panel.add(passLabel);
       
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);
       
        passText = new JPasswordField(20);
        passText.setBounds(100, 40, 160, 25);
        panel.add(passText);
        passText.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
       
        btnInit = new RoundButton("Reset");
        btnInit.setBounds(10, 80, 100, 25);
        panel.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });
       
        btnLogin = new RoundButton("Login");
        btnLogin.setBounds(160, 80, 100, 25);
        panel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Enable=db.loginchk(userText.getText(),passText.getText());
            	isLoginCheck();
            }
        });
    }
   
    public void isLoginCheck(){
        if(Enable!=-1){
            JOptionPane.showMessageDialog(null, "Success");
            bLoginCheck = true;
           
            // 로그인 성공이라면 매니져창 뛰우기
            if(isLogin()){
            	if(POS_Frame.isJ()) {
            		L.dispose();
            		gui.show(); // 메인창 메소드를 이용해 창뛰우기
            	}
            	else {
            		dispose();
            		L.dispose();
            	}
            }                  
        }else{
            JOptionPane.showMessageDialog(null, "Faild");
        }
    }
 
   
    // mainProcess와 연동
    public void setMain(gui lo) {
        this.lo = lo;
    }
    public boolean isLogin() {     
        return bLoginCheck;
    }
    public static void setEnable(int a) {
    	Enable=a;
    }
    public int getEnable() {
    	return Enable;
    }
    
 
}