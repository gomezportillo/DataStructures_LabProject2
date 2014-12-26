/*********************************************************************
 * 
 * Class Name: City 
 * Author/s name: Pedro-Manuel Gómez-Portillo López
* Release/Creation date: 22nd November 2014
* Class version: v1.0
 * Class description: This class refletcs the object of a City
 * 
 **********************************************************************/

public class City {

	private String name; //name of the city
	private int distance; //distance of the city
	
	/*********************************************************************
	 * 
	 * Method name: City
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: One of the onstructor method of the class
	 * 
	 * Calling arguments: 
	 * 		- String name: name of the city
	 * 		- int dist: the distance to the city 
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public City(String name, int dist){
		this.name = name;
		this.distance = dist;
	}
	
	/*********************************************************************
	 * 
	 * Method name: City
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: One of the onstructor method of the class
	 * 		This constructor will set the distance to the city as -1
	 * 
	 * Calling arguments: 
	 * 		- String name: name of the city
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public City(String n){
		this.name = n;
		this.distance = -1;
	}

	public int getDistance(){
		return distance;
	}

	public String getName(){
		return name;
	}

	@Override
	public String toString(){
		return "Name: "+name+". Distance: "+distance+"";
	}
	
	/*********************************************************************
	 * 
	 * Method name: compareTo
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will compare the name of the
	 *  city passed thorow it and the name of the city that call this method.
	 *  If both are equal, will return true. Otherwise, it will return false.
	 * 
	 * Calling arguments: 
	 * 		-City c: 
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public boolean compareTo(City c){
		if (this.name.equals(c.getName()))
			return true;
		else 
			return false;
	}

}



