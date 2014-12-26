import java.util.*;

/*********************************************************************
 * 
 * Class Name: My_Element
 * Author/s name: Pedro-Manuel Gómez-Portillo López
 * Release/Creation date: 19th November 2014
 * Class version: v1.0
 * Class description: This class reflects the object of a the element 
 * 					that will be part of each Node of the tree
 * 
 **********************************************************************/

public class My_Element {

	private int key;
	private List<Box> boxList;


	/*********************************************************************
	 * 
	 * Method name: My_Element
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: Constructor method of the class
	 * 
	 * Calling arguments: 
	 * 		- int key: this variable will store the key of the element
	 * 		of the node of the binary search tree
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public My_Element(int key) {
		this.key = key;
		boxList = new LinkedList<Box>();
	}

	public int getKey() {
		return key;
	}

	public void insertBoxInElement(Box b){ //ordenar las cajas por almacen
		int i;
		boolean continuar = true;
		for( i=0; i<boxList.size() && continuar; i++){
			if(b.getStorageUnit_id().compareTo(boxList.get(i).getStorageUnit_id()) < 0){ //if b is lexicographically bigger than boxList(i) it returns > 0
				boxList.add(i,b);
				continuar = false;
			}
		}
		if(i == boxList.size()) boxList.add(b);
	}

	@Override
	public String toString() {
		return "(key=" + key + ", boxList=" + boxList.toString() + ")";
		//return "(key:" + key+")"; //print only the keys
	}

	public Box getFirstBox() {
		return ((LinkedList<Box>) boxList).peek();
	}


}
