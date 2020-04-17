package cinemaserver;

/**
 * This class is used to store user's personal information.
 * 
 * It has six member variables whose type is String:
 * 1. username: user's name that he entered
 * 2. userphone: user's phone number that he entered
 * 3. usermoviename: name of movie that user selected
 * 4. usermovietime: time of movie of moviename that user selected
 * 5,6. x, y: x and y coordinates of selected seat
 * 
 * This class only has six member variables and a constructor. 
 * The constructor has six parameters to bring personal information explained above.
 * In Server class, 
 * some new instances of Person class are made to maintain all user's information using LinkedList of this Person class. 
 * 
 */

class Person{
	/**
	 * @param String username
	 * @param String userphone
	 * @param String usermoviename
	 * @param String usermovietime
	 * @param String x
	 * @param String y
	 */
	String username;
	String userphone;
	String usermoviename;
	String usermovietime;
	String x;
	String y;
	
	Person(String username, String userphone, String usermoviename, String usermovietime, String x, String y){
		this.username=username;
		this.userphone=userphone;
		this.usermoviename=usermoviename;
		this.usermovietime=usermovietime;
		this.x=x;
		this.y=y;
	}
}