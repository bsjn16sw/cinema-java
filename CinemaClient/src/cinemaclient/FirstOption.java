package cinemaclient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * gui : First panel for start booking
 */
@SuppressWarnings("serial")
class FirstOption extends JPanel {
	/**
	 * @see cinemaclient.Main
	 */
	private JButton Reservation;
	//private JButton Confirm;
	private Main win;
	private Font f;
	private Font J;
	private JButton icon;
	
	public FirstOption(Main win){
		f=new Font("돋움", Font.BOLD,30);
		J=new Font("바탕",Font.BOLD,80);
		this.win=win;
		setBounds(120,120,400,100);
		setLayout(new BorderLayout());
		JLabel expl = new JLabel("영화 예매 시스템");
		expl.setFont(J);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,0,0,0));
		JPanel panel3 = new JPanel();
		
		icon = new JButton(new ImageIcon("./resources/movie.png"));
		icon.setBorderPainted(false);
		icon.setContentAreaFilled(false);
		
		Reservation = new JButton("영화 예매");
		Reservation.setFont(f);
		Reservation.setBackground(Color.pink);
		/*Confirm = new JButton("예매정보 조회");
		Confirm.setFont(f);
		Confirm.setBackground(Color.pink);*/
		
		panel1.add(expl);
		panel3.add(Reservation);
		//panel3.add(Confirm);
		panel2.add(icon);
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
		
		
		//Confirm.addActionListener(new MyActionListener());
		Reservation.addActionListener(new CinActionListener());
		
		
		
	}
		

	class CinActionListener implements ActionListener{
		/**
		 * when button is pushed
		 * @see cinemaclient.Main.change
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			win.change("movienameinfo");
		}
	}
}
