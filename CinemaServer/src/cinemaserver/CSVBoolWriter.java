package cinemaserver;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * This class is used to write boolean 2 dimensional data to right csv file.
 * 
 * It has one private member variable bw of BufferedWriter, constructor, write(), and close() methods:
 * 1. Constructor
 * - It opens output stream to write data to 'filename'.
 * 
 * 2. write()
 * - It writes boolean data in mat whose type is ArrayList<ArrayList<Boolean>> and be gotten from parameter. 
 * - To do that, this method used an enhanced for statement to iterate all data in mat. 
 * - It inserts , or \n in write positions.
 * 
 * 3. close()
 * - It closes bw. 
 *
 */

public class CSVBoolWriter {
	BufferedWriter bw;
	
	public CSVBoolWriter (String filename) {
		try{
			OutputStream fos=new FileOutputStream(filename);
			Writer osw = new OutputStreamWriter(fos);
			bw=new BufferedWriter(osw);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	

	//write nested ArrayList mat through bw
	public void write (ArrayList<ArrayList<Boolean>> mat) {
		/**
		 * Write nested ArrayList mat through bw
		 */
		int numColumn=mat.get(0).size();
		int temp=0;
		
		for(ArrayList<Boolean> arrbool : mat){
			for(boolean b : arrbool){
				temp++;
				String s = Boolean.toString(b);
				try {
					bw.write(s);
					
					if(temp<numColumn) bw.write(",");
					else bw.write("\n");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			temp=0;
		}
		try {
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// close the Writer
	public void close () {
		/**
		 * close the Writer
		 */
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
