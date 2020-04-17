package cinemaclient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 * This class is one of CommThread that functions as a bridge between server and client. 
 * This thread will be used to send x and y coordinates of seat that user selected.  
 * 
 * It has two member variables:
 * 1. Socket soc: a socket that will be used do this work
 * 2. ArrayList<ArrayList<Integer>> pos: an instance of ArrayList<ArrayList<Integer>> to receive data from parameter
 *    and write it on output stream
 * 
 * The constructor receives two data by parameter and initialize two member variables. 
 * 
 * run() method is run when start of instance of this thread is called in Main. 
 * It writes linear Integer data on output stream. 
 *
 */

public class CommThread3 extends Thread{
	private Socket soc;
	private ArrayList<Integer> pos = new ArrayList<Integer>();
	
	public CommThread3 (Socket soc, ArrayList<Integer> pos){
		/**
		 * 
		 * @param Socket soc
		 * @param ArrayList<Integer> pos
		 * @See cinemaclient.Main.change
		 */
		this.soc = soc;
		this.pos = pos;
	}
	
	public void run(){
		try {
			OutputStream os = soc.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(pos);
			oos.close();
			this.soc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}