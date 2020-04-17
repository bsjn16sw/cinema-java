package cinemaclient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * gui : user information
 * This panel is used to get user's information.
 * This program considers only user name and his or her phone number for user information.
 *
 */

@SuppressWarnings("serial")
class WhenBookInfo extends JPanel {
	/**
	 * getting user information gui
	 * @param Main
	 */
	private JTextField name;
	private JTextField phone;
	private JButton confirm;
	private Font f;
	private Main win;
	
	String inputname;
	String inputphone;

	
	
	public WhenBookInfo(Main win){
		this.win=win;
	
		f=new Font("돋움",Font.BOLD,30);
		setLayout(null);
		JLabel label_name = new JLabel("이름");
		label_name.setFont(f);
		label_name.setBounds(200,150,150,50);
		add(label_name);
		
		name=new JTextField();
		name.setBounds(300,150,400,50);
		name.setColumns(10);
		name.setFont(f);
		add(name);
		
		JLabel label_phone = new JLabel("번호");
		label_phone.setBounds(200,250,150,50);
		label_phone.setFont(f);
		add(label_phone);
		
		phone = new JTextField();
		phone.setBounds(300,250,400,50);
		phone.setFont(f);
		add(phone);
		
		confirm = new JButton("예매");
		confirm.setSize(100,100);
		confirm.setFont(f);
		confirm.setLocation(400,400);
		add(confirm);
		
		confirm.addActionListener(new MyActionListener());
	}
	

	class MyActionListener implements ActionListener{
		/**
		 * String form in textfields when pushing button
		 * @see cinema.Main.change
		 */
		public void actionPerformed(ActionEvent e){
			inputname=name.getText();
			inputphone=phone.getText();
		
			win.change("finishconfirm");
		}
	}
}
