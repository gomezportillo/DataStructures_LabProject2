/*********************************************************************
* 
* Class Name: NoCityFoundException
* Author/s name: Pedro-Manuel Gómez-Portillo López
* Release/Creation date: 21st November 2014
* Class version: v1.0
* Class description:  This Exception will be thrown whether there are
* 			not more boxes to read in Cajas.txt
* 
**********************************************************************/

public class NoMoreBoxesInFileException extends Exception {
	
	private static final long serialVersionUID = -7042977466786251210L;
	private static String message = "No more boxes in the file";
	
	/*********************************************************************
	 * 
	 * Method name: NoMoreBoxesInFileException
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
	public NoMoreBoxesInFileException() {
		super(message);
	}

}
