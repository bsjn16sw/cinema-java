package cinemaclient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 * This class is one of CommThread that functions as a bridge between server and client. 
 * This thread will be used to send name and time of movie that user selected. 
 * 
 * It has two member variables:
 * 1. Socket soc: a socket that will be used do this work
 * 2. ArrayList<ArrayList<String>> finalrsv: an instance of ArrayList<ArrayList<String>> to receive data from parameter
 *    and write it on output stream
 * 
 * The constructor receives two data by parameter and initialize two member variables. 
 * 
 * run() method is run when start of instance of this thread is called in Main. 
 * It writes linear String data on output stream. 
 *
 */

public class CommThread1 extends Thread{
	private Socket soc;
	private ArrayList<String> finalrsv = new ArrayList<String>();
	
	public CommThread1 (Socket soc, ArrayList<String> finalrsv){
		/**
		 * 
		 * @param Socket soc
		 * @param ArrayList<String> finalrsv
		 * @see cinemaclient.Main.change
		 */
		this.soc = soc;
		this.finalrsv = finalrsv;
	}
	
	public void run(){
		try {
			OutputStream os = soc.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(finalrsv);
			//oos.close();
			//this.soc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}