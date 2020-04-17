package cinemaclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * 
 * client 'main' class
 * panel flow : FirstOption, MovieNameInfo, MovieTimeInfo, SeatingInfo, WhenBookInfo, FinishConfirm
 * Using 2 socket
 *
 */

// x표 누르면 클라이언트가 종료되는지 확인하기
@SuppressWarnings("serial")
public class Main extends JFrame{
	/**
	 * First, make all panel which program should use.
	 * And swap those panel in right timing.
	 */

	public static Main win;
	public FirstOption firstoption = null;
	public MovieNameInfo movienameinfo = null;
	public MovieTimeInfo movietimeinfo = null;
	public SeatingInfo seatinginfo = null;
	public WhenBookInfo whenbookinfo = null;
	public FinishConfirm finishconfirm = null;

	static Cinema suwon;
	ArrayList<ArrayList<Boolean>> tempbools;

	public static void main(String[] args){
		win = new Main();

		win.setTitle("Movie Booking System");
		win.firstoption = new FirstOption(win);

		String filename = "./resources/suwon.csv";
		ArrayList<ArrayList<String>> suwonInfo;
		CSVStrReader in = new CSVStrReader(filename);
		suwonInfo = in.read();
		suwon = new Cinema(suwonInfo);

		win.movienameinfo = new MovieNameInfo(win, suwon.movies);

		win.add(win.firstoption);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(1000,700);
		win.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public void change(String panelName) {
		/**
		 * This method change the panel.
		 * @param String
		 */

		if(panelName.equals("firstoption")){
			getContentPane().removeAll();
			getContentPane().add(firstoption);
			revalidate();
			repaint();
		}

		if(panelName.equals("movienameinfo")){
			getContentPane().removeAll();
			getContentPane().add(movienameinfo);
			revalidate();
			repaint();
		}

		if(panelName.equals("movietimeinfo")){
			String temp = win.movienameinfo.selectedname;
			movietimeinfo = new MovieTimeInfo(win, suwon.scheds, temp);
			getContentPane().removeAll();
			getContentPane().add(movietimeinfo);
			revalidate();
			repaint();
		}

		if(panelName.equals("seatinginfo")){
			ArrayList<String> rsv = new ArrayList<String>();
			rsv.add(win.movienameinfo.selectedname);
			rsv.add(win.movietimeinfo.selectedtime);

			Socket soc1 = null;
			try {
				soc1 = new Socket("localhost", 7000);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Thread t1 = new CommThread1(soc1, rsv);
			t1.start();

			// get boolean table from Server (from csv)
			try {
				InputStream is = soc1.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				try {
					tempbools = (ArrayList<ArrayList<Boolean>>) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch(IOException e){
				e.printStackTrace();
			}

			Seat seatinfo;
			seatinfo = suwon.setOneBool(rsv, tempbools);
			//System.out.println(seatinfo.time);
			//System.out.println(seatinfo.seatType);
			//System.out.println(seatinfo.seats);
			seatinginfo = new SeatingInfo(win, seatinfo);

			getContentPane().removeAll();
			getContentPane().add(seatinginfo);
			revalidate();
			repaint();
		}

		if(panelName.equals("whenbookinfo")){
			whenbookinfo = new WhenBookInfo(win);
			getContentPane().removeAll();
			getContentPane().add(whenbookinfo);
			revalidate();
			repaint();
		}

		if(panelName.equals("finishconfirm")){
			// int x and y : index (from 0)
			ArrayList<Integer> pos = new ArrayList<Integer>();
			pos.add(Integer.parseInt(win.seatinginfo.x));
			pos.add(Integer.parseInt(win.seatinginfo.y));

			Socket soc2 = null;
			try {
				soc2 = new Socket("localhost", 7000);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread t2 = new CommThread3(soc2, pos);
			t2.start();

			ArrayList<String> strperson = new ArrayList<String>();
			strperson.add(win.whenbookinfo.inputname);
			strperson.add(win.whenbookinfo.inputphone);
			strperson.add(win.movienameinfo.selectedname);
			strperson.add(win.movietimeinfo.selectedtime);
			strperson.add(win.seatinginfo.x);
			strperson.add(win.seatinginfo.y);

			Socket soc3 = null;
			try {
				soc3 = new Socket("localhost", 7000);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread t3 = new CommThread4(soc3, strperson);
			t3.start();


			finishconfirm = new FinishConfirm(win);
			getContentPane().removeAll();
			getContentPane().add(finishconfirm);
			revalidate();
			repaint();
		}
	}
}
