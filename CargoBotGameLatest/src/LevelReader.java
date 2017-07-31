

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LevelReader {
	final int mainFrameMid = 325;
	public static int leftLimit;
	public static int rightLimit;
	final int downLimit = 250;

	private static Scanner reader;
	public static int widthIndex = 0;
	public static int width = 0;
	
	public static ArrayList<Box> targetBoxes = new ArrayList<Box>();
	public static  ArrayList<Box> initialBoxes = new ArrayList<Box>();

	Item[][] current = null;
	Item[][] target  = null;
	public static Board board ;
	
	String[] lineElement = null;
	String[] boxElement  = null;

	public void read(String fileName) {
		try {
			File f = new File(fileName);
			reader = new Scanner(f);
			while (reader.hasNext())
				//read all the lines in file
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
		widthIndex = Integer.parseInt(lineElement[1]);
		width = (widthIndex*30)+((widthIndex-1)*10);
		leftLimit = mainFrameMid - width/2 -1;
		rightLimit = mainFrameMid + width/2 +1;
		//System.out.println(rightLimit + "getdataforwidht right limiti");
		board = new Board(widthIndex);
	
		//System.out.println("Width of the table = " +width);

	}

	private void getDataForInitial(String[] lineElement) {
		
		for (int i = 1; i < lineElement.length; i++) {
			boxElement = lineElement[i].split("-");
			String boxName = boxElement[0];
			int xIndex = Integer.parseInt(boxElement[2]);
			int yIndex = Integer.parseInt(boxElement[1])+1;
			
			Box box = new Box(boxName);
			box.setPosition(leftLimit + xIndex * (box.size + box.space), downLimit - (8 - yIndex) * box.size);
			box.setIndex(xIndex, yIndex);
			initialBoxes.add(box);
			
			board.current[xIndex][yIndex] = new Box(boxName);
		}
	}

	private void getDataForTarget(String[] lineElement) {
		for (int i = 1; i < lineElement.length; i++) {
			boxElement = lineElement[i].split("-");
			String boxName = boxElement[0];
			int xIndex = Integer.parseInt(boxElement[2]);
			int yIndex = Integer.parseInt(boxElement[1])+1;

			Box box = new Box(boxName);
			box.setPosition(leftLimit + xIndex * (box.size + box.space), downLimit - (8 - yIndex) * box.size);
			box.setIndex(xIndex, yIndex);
			targetBoxes.add(box);

			board.target[xIndex][yIndex] = new Box(boxName);
		}
	}


}


