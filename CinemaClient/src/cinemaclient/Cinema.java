package cinemaclient;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * 

 * This class named Cinema is , in plain language, schedule table of one cinema.
 * 
 * It has two member variables:
 * 1. ArrayList<String> movies: all movie names that will be screened
 * 2. HashMap<String, ArrayList<Seat>> scheds: 
 * - One movie is screened at various times and those have to be administered separately, so HashMap is needed. 
 * - key: one movie name
 * - value: real screen informations
 *
 * Constructor:
 * It gets ArrayList<ArrayList<String>> branchInfo.
 * One element of branchInfo whose type is ArrayList<String> has:
 * 1. Name of movie
 * 2. Seat types of real screenings in order
 * 3. Time of screenings in order
 * Number of movies(movieNum) and number of real screenings(playNum) can be calculated using size() method. 
 * We assumed that the number of screenings of each movie is all the same. 
 * We set movies and scheds using branchInfo. 
 * 
 * 
 * It has one method named setOneBool:
 * setOneBool method finally finds an instance of Seat class.
 * It firstly finds value of corresponding movie name from HashMap scheds, and finds Seat of corresponding movie time. 
 * 
 */

class Cinema{
	ArrayList<String> movies;
	HashMap<String, ArrayList<Seat>> scheds;

	
	Cinema(ArrayList<ArrayList<String>> branchInfo){
		/**
		 * 
		 * @param ArrayList<ArrayList<String>> branchInfo
		 * @see cinemaclient.Seat
		 * 
		 */
		final int movieNum = branchInfo.size();
		final int playNum = (branchInfo.get(0).size()-1)/2;
		
		// setting movies
		movies = new ArrayList<String>();
		for(int i=0; i<movieNum; i++){
			movies.add(branchInfo.get(i).get(0));
		}
		
		// setting scheds
		scheds = new HashMap<String, ArrayList<Seat>>();
		for(int i=0; i<movieNum; i++){
			ArrayList<Seat> valscheds = new ArrayList<Seat>();
			
			for(int j=0; j<playNum; j++){
				valscheds.add(new Seat(branchInfo.get(i).get(j+1), branchInfo.get(i).get(j+3)));
			}
			
			scheds.put(movies.get(i), valscheds);
		}
		
	}
	
	Seat setOneBool(ArrayList<String> rsv, ArrayList<ArrayList<Boolean>> tempbools){
		/**
		 * 
		 * @param ArrayList<String> rsv
		 * @param ArrayList<ArrayList<Boolean>> tempbools
		 * @return Seat
		 * @see cinemaclient.Seat
		 */
		
		ArrayList<Seat> tempSeatArrLst;
		
		// find value of corresponding name
		tempSeatArrLst = scheds.get(rsv.get(0));
		// find Seat of corresponding time
		for(Seat i : tempSeatArrLst){
			if(i.time.equals(rsv.get(1))){
				i.setSeats(tempbools);
				return i;
			}
		}
		return null;
	}
	
}

/*class Movie{
	String name;
	
	Movie(String name){
		this.name=name;
	}
}*/


/**
 * 
 * This class named Seat has components of one real screening.
 * 
 * It has three member variables:
 * 1. String seatType: there are two seat types A and B. 
 *    A is 2*5 and B is 3*3.
 * 2. String time: time of real screening (ex. 13:00)
 * 
 * Constructor receives seatType and time and assign them to his own member variables.
 * 
 * It has one method named setSeats:
 * setSeats receives seats and assign it to his own seats. 
 * The reason why assigning seats is not in constructor is that,
 * we do not have to call all information of all screenings. 
 *
 */

class Seat{
	String seatType;
	String time;
	ArrayList<ArrayList<Boolean>> seats;
	
	Seat(String seatType, String time){
		/**
		 * 
		 * @param String seatType
		 * @param String time
		 */
		this.seatType=seatType;
		this.time=time;
	}
	
	void setSeats(ArrayList<ArrayList<Boolean>> seats){
		/**
		 * 
		 * @param ArrayList<ArrayList<Boolean>> seats
		 */
		this.seats=seats;
	}
}
