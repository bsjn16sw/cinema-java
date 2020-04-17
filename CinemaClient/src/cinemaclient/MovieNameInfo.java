package cinemaclient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * gui : panel for selecting movie name
 * This panel show all of movie name.
 *
 */
@SuppressWarnings("serial")
class MovieNameInfo extends JPanel {
	/**
	 * gui : panel for selecting movie name
	 * @param Main
	 * @param ArrayList<String>
	 * @see cinemaclient.Cinema
	 */
	private JButton confirm;
	@SuppressWarnings("rawtypes")
	private JComboBox moviename;
	private Main win;
	private Font f;
	private Font J;
	String selectedname;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MovieNameInfo(Main win, ArrayList<String> movies){
		f=new Font("돋움", Font.BOLD,30);
		J=new Font("돋움", Font.BOLD,60);
		this.win=win;

		setLayout(new BorderLayout());
		JLabel expl = new JLabel("영화를 선택해주세요.");
		expl.setFont(f);
		moviename = new JComboBox();
		
		for(String temp : movies){
			moviename.addItem(temp);
		}
		moviename.setFont(J);
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,0,100,100));
		panel2.setBorder(new EmptyBorder(200,200,200,200));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());

		panel1.add(expl);
		panel2.add(moviename);

		confirm = new JButton("조회");
		confirm.setFont(f);
		panel3.add(confirm);
		
	
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
	
		confirm.addActionListener(new MyActionListener());
		
	}
		
	class MyActionListener implements ActionListener{
		/**
		 * @see cinemaclient.Main.change
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			selectedname=moviename.getSelectedItem().toString();
			win.change("movietimeinfo");
		}
	}
}
