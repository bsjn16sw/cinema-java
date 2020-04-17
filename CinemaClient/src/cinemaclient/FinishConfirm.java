package cinemaclient;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * gui : Last panel for confirm booking
 * If user click 'confirm' button, the booking process ends and return the firstoption panel.
 *
 */
@SuppressWarnings("serial")
class FinishConfirm extends JPanel{
	/**
	 * last panel for confirm booking
	 * @param Main win
	 */
	private Main win;
	private Font f;
	private JButton confirm;
	
	
	public FinishConfirm(Main win){
		this.win = win;
		f=new Font("돋움",Font.BOLD,50);
		setLayout(new BorderLayout());
		JLabel expl = new JLabel("예매가 완료되었습니다.");
		confirm = new JButton("확인");
		confirm.setFont(f);
		expl.setFont(f);
		expl.setLocation(300,500);
		confirm.setLocation(500,600);
		add(expl,BorderLayout.CENTER);
		add(confirm,BorderLayout.SOUTH);
		
		confirm.addActionListener(new MyActionListener());
		
	}
	
	class MyActionListener implements ActionListener{
		/**
		 * when the button is pushed
		 * @see cinemaclient.Main.change
		 */
		public void actionPerformed(ActionEvent e){
			win.change("firstoption");
		}
	}
}
