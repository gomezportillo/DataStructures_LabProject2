import treesDSESIUCLM.*;

import java.io.*;
import java.util.*;

/*********************************************************************
 * 
 * Class Name: Warehouse
 * Author/s name: Pedro-Manuel Gómez-Portillo López
 * Release/Creation date: 22nd November 2014
 * Class version: v1.0
 * Class description:  This class reflects the object of our warehouse.
 * 		Here we are reading the diffrent files and building our binary
 * 		search tree with them.
 * 
 **********************************************************************/
public class Warehouse {
	private List<City> listCity; // the list which will store the objects of the cities 
	private BinTree<My_Element> bsTree; //the binary search tree we are building
	private Scanner fileBoxesScanner; //the scanner to read the FileReader of Cajas.txt

	/*********************************************************************
	 * 
	 * Method name: Wharehouse
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: Constructor method of the class
	 * 
	 * Calling arguments: 
	 * 		- FileReader fileReaderCities: this will be the FileReader of 
	 * 		the file Ciudades.txt, which work as the database of the 
	 * 		different cities where we are sending the boxes and its 
	 * 		distance from the factory.
	 * 		- FileReader fileReaderBoxes: this will be the FileReader of 
	 * 		the file Cajas.txt, which work as the database of the 
	 * 		different boxes where we are sending.
	 * 
	 * Return value: void 
	 * 
	 * This method does require two files
	 * 		- Cajas.txt
	 * 		- Ciudades.txt
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public Warehouse(FileReader fileReaderCities, FileReader fileReaderBoxes){
		listCity = new LinkedList<City>();
		bsTree = new BinTree<My_Element>();
		fileBoxesScanner = new Scanner (fileReaderBoxes); //the scanner to read the FileReader

		readCities(fileReaderCities); //filling the listCity with all the cities
	}

	/*********************************************************************
	 * 
	 * Method name: buildTree
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will accomplish the task of 
	 * 		building the binary search tree with the boxes of the file
	 * 
	 * Calling arguments: 
	 * 		- BinTree bsTree: the tree we are going to build
	 * 		- LinkedList listCity: the list which is storing all the cities
	 * 		- LinkedList listBox: the list which is storing all the boxes
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public void buildBinarySearchTree(){
		NodeBinTree<My_Element> newNode; //the node of the tree
		My_Element elem; //the element of the node
		Box next_box; //the next box to be inserted
		int key_dist; //the key of the element of the node
		boolean keep_building=true; //boolean variable for denoting the tree is not completed

		try{ 
			while(keep_building){ 
				next_box = readABoxFromFile(fileBoxesScanner); //next box to be placen in the tree
				key_dist = getBoxKey(next_box); //setting the distance of the box's destination as the key

				if(bsTree.getRoot() == null){ /** Defining the root of the tree */ 
					elem = new My_Element(key_dist); //creating a new element with that key
					elem.insertBoxInElement(next_box);
					newNode = new NodeBinTree<My_Element>(elem); //creating a node with that element

					bsTree.addRoot(newNode); //setting that node as the root node of the tree

				} else{ /**Inserting the box in its corresponding place */

					if(findKeyInTree(bsTree.getRoot(), key_dist)) { //if the key already exists in the tree
						insertBoxInTree(bsTree.getRoot(),next_box, key_dist); //inserting the box in the node with its key

					}else { //if the key does not exists in the tree
						elem = new My_Element(key_dist); //setting the distance of the box's destination as the key
						elem.insertBoxInElement(next_box); //inserting the box in the element
						newNode = new NodeBinTree<My_Element> (elem); //creating a node with that element

						addNewNodeToTheTree(bsTree.getRoot(), newNode); //calling the method that will insert this node on the tree
					}
				}

			}

		}catch(ProblemInTreeException e1){ //it has been a problem in the tree
			System.out.println(e1);
		} catch (NoCityFoundException e2) { //one of the cities' destination was not found in the file 
			System.out.println(e2);
		} catch(NoMoreBoxesInFileException e3) { //there are not more boxes in the file to read
			//System.out.println(e3); //we do not want to show this message
			keep_building = false;
		} catch (Exception e4) { //general exception
			System.out.println(e4);
		}

	}

	/*********************************************************************
	 * 
	 * Method name: addNewNodeToTheTree
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will call it recursively in
	 * 		order to insert a new node in the tree based on its key 
	 * 
	 * Calling arguments: 
	 * 		- NodeBinTree parentNode: the previous node from where this 
	 * 		method was called
	 * 		- NodeBinTree newNode: the node we want to insert
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	private void addNewNodeToTheTree(NodeBinTree<My_Element> parentNode, NodeBinTree<My_Element> newNode) {
		if(newNode.getElement().getKey() > parentNode.getElement().getKey()) //if the key of the new node is greater than the one of the previous node it will go to its right
			if(parentNode.getRight() == null) //if the previous node does not have righ child node 
				parentNode.addRight(newNode); //setting the new node at the right of the previous node
			else 
				addNewNodeToTheTree(parentNode.getRight(), newNode); //calling this method recursively with the new parameters

		else //if the key of the new node not greater (is smaller) than the one of the previous node it will go to its left
			if(parentNode.getLeft() == null) //if the previous node does not have left child node
				parentNode.addLeft(newNode); //setting the new node at the left of the previous node
			else
				addNewNodeToTheTree(parentNode.getLeft(), newNode); //calling this method recursively with the new parameters
	}

	/*********************************************************************
	 * 
	 * Method name: getBoxKey
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will compare the city destination
	 * 		of the box passed through the arguments with all the cities in the
	 * 		list of the cities in order to get it distance
	 * 
	 * Calling arguments: 
	 * 		- Box box: the box we want to get its distance
	 * 		- LinkedList listCity: the list with all the cities
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method do throw a exception:
	 * 		- NoCityFoundException: it will be thrown if the destination cityof
	 * 		the box is not found in the list with all the cities
	 * 
	 *********************************************************************/ 
	private int getBoxKey(Box box) throws NoCityFoundException {
		int i;
		City my_city=box.getCity(); //the destination city of the box

		for(i=0; i<listCity.size() && !my_city.compareTo(listCity.get(i)); i++); //it will stop when the city is found

		if(i == listCity.size()) //if the loop did not stop is due to the city was not foud 
			throw new NoCityFoundException(); //exception that will be cathed and handled in the buildBinarySearchTree method
		else 
			return listCity.get(i).getDistance(); //returning the distance to the city
	}

	/*********************************************************************
	 * 
	 * Method name: inserBoxInTheTree
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method shoud be called if the
	 * 		key of the box passed through the argument was found in the tree.
	 * 		If so, it will find the node with the element with that key and
	 * 		will insert the box passed through the argument into it.
	 * 
	 * Calling arguments: 
	 * 		- NodeBinTree: the starting node of the algorithim
	 * 		- Box b: the box we want to insert
	 * 		- int key: the key of that box
	 * 			Note: the key will be passed in this way instead of getting
	 * 			it with the getBoxKey method for saving processor as we should
	 * 			had gotten it in the previous step
	 * 
	 * Return value: void 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	private void insertBoxInTree(NodeBinTree<My_Element> node, Box b, int key) {
		if(key == node.getElement().getKey()) //if the key i am looking for and the one of the node are equal
			node.getElement().insertBoxInElement(b); //inserting the box in the element of the node
		else {		
			if(node.getLeft() != null) 
				insertBoxInTree(node.getLeft(), b, key);
			if(node.getRight() != null) 
				insertBoxInTree(node.getRight(), b, key);
		}
	}

	/*********************************************************************
	 * 
	 * Method name: findKeyInTree
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will recursively look for 
	 * 		the key passed though the arguments in a tree begining on 
	 * 		the	NodeBinTree passed thought the args. If it is found it
	 * 		will be return true, otherwise it will return false.	
	 * 
	 * Calling arguments: 
	 * 		- NodeBinTree: the starting node of the algorithim
	 * 		- int key: the key to be found
	 * 
	 * Return value: boolean
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	private boolean findKeyInTree(NodeBinTree<My_Element> node, int key) {
		if(key == node.getElement().getKey()) //if the key i am looking for and the one of the node are equal
			return true;
		else {		
			if(node.getLeft() != null) 
				if (findKeyInTree(node.getLeft(), key)) return true;
			if(node.getRight() != null) 
				if (findKeyInTree(node.getRight(), key)) return true;
		}
		return false;
	}

	public void printTree() { //this method will print the resuling tree
		System.out.println(bsTree.toString());
	}

	/*********************************************************************
	 * 
	 * Method name: readCities
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will build the object of the
	 * 		cities in the file passed though the arguments and will store 
	 * 		them in the list  passed though the arguments.
	 * 
	 * Calling arguments: 
	 * 		- LinkedList listCity: the list where the cities have to be stored
	 * 		- File fileCities: the file where the information of the cities is
	 * 
	 * Return value: void 
	 * 
	 * This method does required the file with the information of the cities in
	 * 		the predefined format
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	private void readCities(FileReader fileReaderCities) {
		Scanner scan = null; //the scanner to read the FileReader
		String tmp_name; //the name of the city
		int tmp_distance; //the distance of the city 

		try {
			scan = new Scanner (fileReaderCities);

			while(scan.hasNext()){ //while there are more words
				tmp_name = scan.next(); //read and save the name of the city
				tmp_distance = Integer.parseInt(scan.next()); //read an save the distance of the city

				listCity.add(new City(tmp_name, tmp_distance)); //store the new city in the list
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{                    
				if( fileReaderCities != null ){   
					fileReaderCities.close();    
					scan.close();
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
	}

	/*********************************************************************
	 * 
	 * Method name: readBoxes
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will build the object of the
	 * 		boxes in the file passed though the arguments and will store them 
	 * 		in the list  passed though the arguments.
	 * 
	 * Calling arguments: 
	 * 		- LinkedList listBox: the list where the boxes have to be stored
	 * 		- File fileBoxes: the file where the information of the boxes is
	 * 
	 * Return value: Box 
	 * 
	 * This method does required the file with the information of the boxes in
	 * 		the predefined format
	 * 
	 * This method does throw a exception:
	 * 		- NoMoreBoxesInFileException when the file runs out of boxes
	 * 
	 *********************************************************************/ 
	private Box readABoxFromFile(Scanner fileBoxesScanner) throws NoMoreBoxesInFileException{
		String 	tmp_boxID=null, //the id of the box
				tmp_storageUnitID=null; //the id of the storage unit of the box
		City tmp_city=null; //the destination city of the box
		Box new_box;

		if(fileBoxesScanner.hasNext()){ //while there are more words 
			tmp_boxID = fileBoxesScanner.next(); //read  and save the id of the box
			tmp_storageUnitID = fileBoxesScanner.next(); //read and save the id of the storage unit
			tmp_city = new City(fileBoxesScanner.next()); //read the name of the city and create a new object City
		}

		if(tmp_boxID==null || tmp_storageUnitID==null || tmp_city==null ) throw new NoMoreBoxesInFileException();

		new_box = new Box(tmp_boxID, tmp_storageUnitID, tmp_city); //create and return the new box

		return new_box;

	}

	/*********************************************************************
	 * 
	 * Method name: getFarthestBox
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will compute the farthest box
	 * 		in the tree. For that, it will call to recursiveComputeFarthestBox,
	 * 		which will be the real method that will compute so calling itself 
	 * 		recursively. It no box is found, it will return void.
	 * 
	 * Calling arguments: none
	 * 
	 * Return value: Box 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public Box getFarthestBox(){
		return recursiveComputeFarthestBox(bsTree.getRoot()).getElement().getFirstBox();
	}
	private NodeBinTree<My_Element> recursiveComputeFarthestBox(NodeBinTree<My_Element> node){
		if(node.getRight() != null) //the farthest box will be at the most right node of the tree 
			return recursiveComputeFarthestBox(node.getRight());
		else
			return node;
	}

	/*********************************************************************
	 * 
	 * Method name: getNearesttBox
	 * 
	 * Name of the original author: Pedro-Manuel Gómez-Portillo López
	 * 
	 * Description of the Method: this method will compute the nearest box
	 * 		in the tree. For that, it will call to recursiveComputeNearesttBox,
	 * 		which will be the real method that will compute so calling itself 
	 * 		recursively. It no box is found, it will return void.
	 * 
	 * Calling arguments: none
	 * 
	 * Return value: Box 
	 * 
	 * This method does not require any file
	 * 
	 * This method does not throw any particular exception
	 * 
	 *********************************************************************/ 
	public Box getNearestBox(){
		return recursiveComputeNearestBox(bsTree.getRoot()).getElement().getFirstBox();
	}
	private NodeBinTree<My_Element> recursiveComputeNearestBox(NodeBinTree<My_Element> node){
		if(node.getLeft() != null) //the nearest box will be at the most left node of the tree
			return recursiveComputeNearestBox(node.getLeft());
		else
			return node; //base case
	}
}
