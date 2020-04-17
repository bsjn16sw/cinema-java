package cinemaserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * This class functions as server of this program.
 * 
 * In main method of this class, if ServerSocket is assigned normally, 
 * the string "Server is ready..." is printed out on console.
 * Then flow goes on infinite loop, waiting new client's access. accept() method is used.
 * 
 * In infinite loop, the server received a client's reservation information as ArrayList<String>.
 * And using this, it finds an appropriate csv file has right information of matched movie name and movie time. 
 * After finding, it imports the data using methods of CSVBoolReader class and send the data to client using new thread.
 * 
 * Very similar to above, the server received x and y coordinates as ArrayList<Interger>.
 * Then it changes boolean value of corresponding coordinates of ArrayList<ArrayList<Boolean>> from false to true.
 * That changed ArrayList will be sent to csv file that is opened previous procedures by CSVBoolWriter. 
 * 
 * Last information that server received is personal information of client: name, phone number, etc.
 * That information is stored in instance of Person class.
 * Then the server maintains all client's personal information using LinkedList<Person>.
 *
 */
class Server {
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main (String[] args) {
		ServerSocket ss = null;
		ArrayList<String> finalrsv = new ArrayList<String>();
		ArrayList<ArrayList<Boolean>> rsvseats;
		String filename;
		ArrayList<Integer> pos = new ArrayList<Integer>();
		ArrayList<String> tempstrperson = new ArrayList<String>();
		LinkedList<Person> personlkdlst = new LinkedList<Person>();

		try {
			ss = new ServerSocket(7000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Server is ready...");

		while(true){
			try {
				//System.out.print("ss.accept");
				// get reservation information
				Socket soc1 = ss.accept();
				InputStream is = soc1.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				finalrsv = (ArrayList<String>) ois.readObject();
				
				// find corresponding csv file and read it
				String temp = finalrsv.get(1);
				StringTokenizer tokens = new StringTokenizer(temp);
				filename = "./resources/suwon-"+finalrsv.get(0)+"-"+tokens.nextToken(":")+".csv";
				CSVBoolReader in = new CSVBoolReader(filename);
				rsvseats = in.read();
				
				// send boolean 2d array
				Thread t = new CommThread2(soc1, rsvseats);
				t.start();
				
				// get x, y pos
				Socket soc2 = ss.accept();
				InputStream is2 = soc2.getInputStream();
				ObjectInputStream ois2 = new ObjectInputStream(is2);
				pos = (ArrayList<Integer>) ois2.readObject();
				// pos.get(0) : x pos
				// pos.get(1) : y pos
				
				// mirror x, y position information to rsvseats
				rsvseats.get(pos.get(0)-1).set(pos.get(1)-1, true);
				CSVBoolWriter out = new CSVBoolWriter(filename);
				out.write(rsvseats);
				
				// get person information
				Socket soc3 = ss.accept();
				InputStream is3 = soc3.getInputStream();
				ObjectInputStream ois3 = new ObjectInputStream(is3);
				tempstrperson = (ArrayList<String>) ois3.readObject();
				
				// link that to Person Linked List
				Person tempperson = new Person(null, null, null, null, null, null);
				tempperson.username = tempstrperson.get(0);
				tempperson.userphone = tempstrperson.get(1);
				tempperson.usermoviename = tempstrperson.get(2);
				tempperson.usermovietime = tempstrperson.get(3);
				tempperson.x = tempstrperson.get(4);
				tempperson.y = tempstrperson.get(5);
				personlkdlst.add(tempperson);
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}