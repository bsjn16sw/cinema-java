package cinemaclient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * gui : panel of selecting seating information
 * Consider the property of cinema, construct each seating information differently.
 * We have A, and B type. A has 10 seats, and B has 9 seats.
 * By user's clicking, program gets the information of seat which user wants.
 *
 */
@SuppressWarnings("serial")
class SeatingInfo extends JPanel {
	/**
	 * gui : panel of selection seating information
	 * @param Main
	 * @param Seat
	 * @see cinemaclient.Seat
	 */
	private JButton screen;
	private JButton confirm;
	private JButton cancel;
	private Main win;
	private Font f;
	private Font J;
	private ArrayList<JButton> chair;
	private Seat seat;
	private JButton previous=null;

	String x, y;
	
	public SeatingInfo(Main win, Seat seat){
		this.seat=seat;
		setLayout(new BorderLayout());
		this.win=win;
		f=new Font("돋움",Font.BOLD,30);
		J=new Font("돋움",Font.BOLD,40);
		
		screen = new JButton("스크린");
		screen.setFont(f);
		screen.setEnabled(false);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(screen);
		
		JPanel panel2 = new JPanel();
		seatMouseListener listener = new seatMouseListener();
		//클릭할떄 색깔 바뀌게 하는거
		
		if(seat.seatType.equals ("A")){
			chair = new ArrayList<JButton>();
			
			panel2.setLayout(new GridLayout(2,5,10,10));
			//String index;
			//String Row;
			for(int i=0;i<2;i++){
				for(int j=1;j<=5;j++){
					String Row=String.valueOf(i+1);
					String Index=String.valueOf(j);
					chair.add(new JButton(Row+"-"+Index));
				}
			}
			int ind=0;
			for(int i=0;i<2;i++){ //나중에 .size 함수 이용해서 구현???????????
				for(int j=0;j<5;j++){
					if(seat.seats.get(i).get(j)){
						//chair.get(ind).setBackground(Color.gray);

						chair.get(ind).setVisible(false);

					}
					panel2.add(chair.get(ind));
					chair.get(ind).addMouseListener(listener);////////
					ind++;
				}
			}
			
			for(JButton temp : chair){
				temp.setFont(J);
			}
		}
		if(seat.seatType.equals("B")){
			chair = new ArrayList<JButton>();
			panel2.setLayout(new GridLayout(3,3,10,10));
			//String index;
			//String Row;
			for(int i=0;i<3;i++){
				for(int j=1;j<=3;j++){
					String Row=String.valueOf(i+1);
					String Index=String.valueOf(j);
					chair.add(new JButton(Row+"-"+Index));
				}
			}
			int ind=0;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(seat.seats.get(i).get(j)){
						//chair.get(ind).setBackground(Color.gray);

						chair.get(ind).setVisible(false);
					}
					panel2.add(chair.get(ind));
					chair.get(ind).addMouseListener(listener);////////
					ind++;
				}
			}
			for(JButton temp : chair){
				temp.setFont(J);
			}
		}
		
		panel2.setBorder(new EmptyBorder(100,100,100,100));
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		confirm = new JButton("조회");
		cancel = new JButton("취소");
		confirm.setFont(f);
		cancel.setFont(f);
		panel3.add(confirm);
		panel3.add(cancel);
		
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
		
		cancel.addActionListener(new cancelActionListener());
		confirm.addActionListener(new confirmActionListener());
		
	}
	class seatMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e){
			
			JButton buttonn = (JButton)e.getSource();
			String str = buttonn.getText();
			
			if(seat.seatType.equals("A")){
				//String Row;
				//String Index;
				if(previous!=null) previous.setBackground(null);
				for(int i=0;i<2;i++){
					for(int j=1;j<=5;j++){
						String Row=String.valueOf(i+1);
						String Index=String.valueOf(j);
						if(str.equals(Row+"-"+Index)){
							buttonn.setBackground(Color.yellow);
							x=Row;
							y=Index;
							previous=buttonn;
						}
					}
				}
			}
			else if(seat.seatType.equals("B")){
				//String Row;
				//String Index;
				if(previous!=null) previous.setBackground(null);
				for(int i=0;i<3;i++){
					for(int j=1;j<=3;j++){
						String Row=String.valueOf(i+1);
						String Index=String.valueOf(j);
						if(str.equals(Row+"-"+Index)){
							buttonn.setBackground(Color.yellow);
							//buttonn.setEnabled(false);
							x=Row;
							y=Index;
							previous=buttonn;
						}
					}
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
	class cancelActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			
			win.change("firstoption");
		}
	}
	class confirmActionListener implements ActionListener{
		/**
		 * when button is pushed
		 * @see cinemaclient.Main.change
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			win.change("whenbookinfo");
			
			
			/*int i = 0,j=0;
			String index;
			for(int a=1;a<=5;a++){
				index=String.valueOf(a);
				if(str.equals("A"+index)){
					i=0; j=a-1;
				}
				else if(str.equals("B"+index)){
					i=1; j=a-1;
				}
				else if(str.equals("C"+index)){
					i=2; j=a-1;
				}
			}*/
			//seat.seats.get(i).set(j,true);
			//System.out.println(i+"\n"+j);
		}
		
	}
	
}
