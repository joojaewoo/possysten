package pos;

public class gui {
	static LoginView loginView;
	public gui() {
		this.loginView = new LoginView();
    	this.loginView.setMain(this);
	}
	public static void show() {
		// TODO Auto-generated method stub
		loginView.dispose();
    	new POS_Frame();
	}
}
