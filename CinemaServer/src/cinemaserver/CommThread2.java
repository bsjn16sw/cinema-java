package cinemaserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class is one of CommThread that functions as a bridge between server and client. 
 * This thread will be used to send data in csv file using ArrayList<ArrayList<Boolean>> type.
 * 
 * It has two member variables:
 * 1. Socket soc: a socket that will be used do this work
 * 2. ArrayList<ArrayList<Boolean>> rsvseats: an instance of ArrayList<ArrayList<Boolean>> to receive data from parameter
 *    and write it on output stream
 * 
 * The constructor receives two data by parameter and initialize two member variables. 
 * 
 * run() method is run when start of instance of this thread is called by server. 
 * It writes 2 dimensional boolean data on output stream. 
 *
 */

public class CommThread2 extends Thread{
	private Socket soc;
	private ArrayList<ArrayList<Boolean>> rsvseats;
	
	public CommThread2(Socket soc, ArrayList<ArrayList<Boolean>> rsvseats){
		this.soc = soc;
		this.rsvseats = rsvseats;
	}
	
	public void run(){
		try {
			OutputStream os = soc.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(rsvseats);
			//oos.close();
			//this.soc.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}