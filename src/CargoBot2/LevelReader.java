package CargoBot2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelReader {

	private static Scanner reader;
	Item[][] initial = null;
	Item[][] target  = null;
	int width = 0;
	String[] lineElement = null;
	String[] boxElement  = null;

	public void read(String fileName) {
		try {
			File f = new File(fileName);
			reader = new Scanner(f);
			while (reader.hasNext())    					 //read all the lines in file
				getDataFromFile();
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found");
		}
	}

	private void getDataFromFile() {
		String line = reader.nextLine();
		String[] lineElement = line.split(":");  
		if (lineElement[0].equals("WidthOfTable")) {  		
			getDataForWidth(lineElement);
		} 
		else if (lineElement[0].equals("Initial")) {			// get the initial table data from file
			getDataForInitial(lineElement);
		} 
		else if (lineElement[0].equals("Target")) {		    	// get the target table data from file
			getDataForTarget(lineElement);
		}
	}

	private void getDataForWidth(String[] lineElement) {
		width = Integer.parseInt(lineElement[1]);				 //get the width size of the table
		initial = new Item[7][width]; 				
		target  = new Item[7][width];
	}

	private void getDataForInitial(String[] lineElement) {
		for (int i = 1; i < lineElement.length; i++) {
			boxElement = lineElement[i].split("-");
			String color = boxElement[0];
			int rowIndex = Integer.parseInt(boxElement[1]);
			int colIndex = Integer.parseInt(boxElement[2]);
			initial[rowIndex][colIndex] = new Box(color);
		}
	}

	private void getDataForTarget(String[] lineElement) {
		for (int i = 1; i < lineElement.length; i++) {
			boxElement = lineElement[i].split("-");
			String color = boxElement[0];
			int rowIndex = Integer.parseInt(boxElement[1]);
			int colIndex = Integer.parseInt(boxElement[2]);
			target[rowIndex][colIndex] = new Box(color);
		}
	}

}


