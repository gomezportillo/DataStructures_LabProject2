/*********************************************************************
 * 
 * Class Name: Box
 * Author/s name: Pedro-Manuel Gomez-Portillo Lopez
 * Release/Creation date: 22nd November 2014
 * Class version: v1.0
 * Class description: This class reflects the object of a box 
 * 
 **********************************************************************/

public class Box {

	private String box_id, //identifier of the box
	storageUnit_id; //identifier of the storage unit
	private City city; //destination city

	/*********************************************************************
	 * 
	 * Method name: Box
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: Constructor method of the class
	 * 
	 * Calling arguments: 
	 * 		- String box_id: the identifier of the box
	 * 		- String storageUnit_id: the identifier of the storage unit
	 * 		- City city: a variable which will store the destination city
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public Box(String box_id, String storageUnit_id, City city) {
		this.box_id = box_id;
		this.storageUnit_id = storageUnit_id;
		this.city = city;
	}

	public String getStorageUnit_id() {
		return storageUnit_id;
	}

	public City getCity(){
		return this.city;
	}

	@Override
	public String toString() {
		return "("+box_id+", "+storageUnit_id+", "+city.getName()+")";
	}
}
