package cinemaclient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * gui : panel of selection time of movie
 * Each movie has information of time. 
 * This panel shows that information.
 * If user select the time in combobox, get that time for String type.
 *
 */
@SuppressWarnings("serial")
class MovieTimeInfo extends JPanel {
	/**
	 * gui : panel of selection time of movie
	 * @see cinemaclient.Main
	 * @param Main
	 * @param HashMap
	 * @param String
	 * @see cinemaclient.Cinema
	 */
	private JButton confirm;
	@SuppressWarnings("rawtypes")
	private JComboBox movietime;
	private Main win;
	private Font f;
	
	private JButton image;
	
	String selectedtime;
	public String love="love";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MovieTimeInfo(Main win, HashMap<String, ArrayList<Seat>> scheds, String selectedname){
		f=new Font("돋움", Font.BOLD,30);
		this.win=win;

		setLayout(new BorderLayout());
		JLabel expl = new JLabel("시간 정보를 선택해주세요.");
		expl.setFont(f);
		movietime = new JComboBox();
		
		for(Seat temp : scheds.get(selectedname)){
			movietime.addItem(temp.time);
		}
		
		movietime.setFont(f);
		
		image = new JButton(new ImageIcon("./resources/"+selectedname+".png"));
		image.setBorderPainted(false);
		image.setContentAreaFilled(false);
		
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,50,50));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());

		panel1.add(expl);
		panel1.add(movietime);
		
		panel2.add(image);

		confirm = new JButton("확인");
		confirm.setFont(f);
		panel3.add(confirm);
		
	
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
	
		confirm.addActionListener(new MyActionListener());
		
	}

	class MyActionListener implements ActionListener{
		/**
		 * get information of movie time (String) when button is pushed
		 * @see cinemaclient.Main.change
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			selectedtime=movietime.getSelectedItem().toString();
			win.change("seatinginfo");
		}
	}
}
