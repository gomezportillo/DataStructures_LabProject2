/*********************************************************************
* 
* Class Name: NoCityFoundException
* Author/s name: Pedro-Manuel Gómez-Portillo López
* Release/Creation date: 21st November 2014
* Class version: v1.0
* Class description:  This Exception will be thrown whether the program
* 		does not find the distance of a house in the file Ciudades.txt
* 
**********************************************************************/

public class NoCityFoundException extends Exception {
	
	private static final long serialVersionUID = -220770507509314719L; //universal version identifier
	private static String message = "One of the cities' distance was not found in the database"; //message of this exception
	
	
	/*********************************************************************
	 * 
	 * Method name: NoCityFoundException
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: Constructor method of the class
	 * 
	 * Calling arguments: none
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public NoCityFoundException() {
		super(message);
	}

}

  
  
  
