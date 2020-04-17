package cinemaserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * This class is used to read csv file that has boolean 2 dimensional data. 
 * 
 * It has one private member variable br of BufferedReader, constructor, read(), and close() methods:
 * 1. Constructor
 * - It opens input stream to get data of 'filename'.
 * 
 * 2. read()
 * - It reads data to the end in corresponding file using readline() method.
 * - And it saves and returns. Return data type is ArrayList<ArrayList<Bool>>. 
 * - True in that ArrayList<ArrayList<Bool>> means a seat that is already reserved
 *   and False in that means a seat that is not reserved yet. 
 * 
 * 3. close()
 * - It closes br. 
 *
 */

public class CSVBoolReader {
	private BufferedReader br;

	// initiate all fields
	public CSVBoolReader (String filename) {
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
	public ArrayList<ArrayList<Boolean>> read () {
		/**
		 * Read data and return them of ArrayList typed object
		 * @return ArrayList<ArrayList<Boolean>>
		 */
		ArrayList<ArrayList<Boolean>> arrLst= new ArrayList<ArrayList<Boolean>>();
		String line="";

		try {
			while((line=br.readLine())!=null){
				String[] data = line.split(",");
				ArrayList<Boolean> temp = new ArrayList<Boolean>();
				for(String str: data){
					boolean flag = Boolean.parseBoolean(str);
					temp.add(flag);
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
		/**
		 * close the Reader
		 */
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
