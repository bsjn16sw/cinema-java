package cinemaclient;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * 
 * This class is used to read csv file that has String 2 dimensional data. 
 * 
 * It has one private member variable br of BufferedReader, constructor, read(), and close() methods:
 * 1. Constructor
 * - It opens input stream to get data of 'filename'.
 * 
 * 2. read()
 * - It reads data to the end in corresponding file using readline() method.
 * - And it saves and returns. Return data type is ArrayList<ArrayList<String>>. 
 * 
 * 3. close()
 * - It closes br. 
 *
 */

public class CSVStrReader {
	private BufferedReader br;
	
	// initiate all fields
	public CSVStrReader(String filename) {
		/**
		 * 
		 * @param String filename
		 * @See cinemaclient.Main.main
		 */
		try {
			InputStream fis = new FileInputStream(filename);
			Reader isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// read data and return them of ArrayList typed object
	public ArrayList<ArrayList<String>> read () {
		/**
		 * 
		 * @return ArrayList<ArrayList<String>>
		 */
		ArrayList<ArrayList<String>> arrLst= new ArrayList<ArrayList<String>>();
		String line="";

		try {
			while((line=br.readLine())!=null){
				String[] data = line.split(",");
				ArrayList<String> temp = new ArrayList<String>();
				for(String str: data){
					temp.add(str);
				}
				arrLst.add(temp);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrLst;

	}
	
	// close the Reader
	public void close () {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
