import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

/*********************************************************************
 * 
 * Class Name: Principal
 * Author/s name: Pedro-Manuel Gómez-Portillo López
 * Release/Creation date: 22nd November 2014
 * Class version: v1.0
 * Class description: The main class of the program. From here we are 
 * 					building the diffrent objetcs and calling to the 
 * 					other classes. 
 * 
 **********************************************************************/
public class Principal {

	public static void main(String[] args){ 
		try{
			FileReader fileReaderCities = new FileReader(new File ("Ciudades.txt"));
			FileReader fileReaderBoxes = new FileReader (new File("Cajas.txt"));
			
			Warehouse warehouse = new Warehouse(fileReaderCities, fileReaderBoxes);
			warehouse.buildBinarySearchTree();
			
			warehouse.printTree();
			System.out.println("Nearest box: "+warehouse.getNearestBox().toString());
			System.out.println("Farthest box: "+warehouse.getFarthestBox().toString());
			
		} catch (FileNotFoundException e){
			System.out.println("Error. File not found.");
			System.exit(1); //0 if everything went OK. 1 if it went wrong
		} catch (Exception e2){
			System.out.println(e2); //general exception
		}

	}
}
