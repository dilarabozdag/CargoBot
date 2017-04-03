package CargoBot2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CommandReader {
	private static Scanner reader;
	String name;
	ArrayList <String> commands = new ArrayList<String>();
	String[] lineElement = null;
	String[] commandElement  = null;

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
		String[] lineElement = line.split("{");
		name = lineElement[0];
		for (int i = 1; i < lineElement.length; i++) {
			commandElement = lineElement[i].split("-");
			while(!lineElement[i].equals("}")){
				commands = (ArrayList<String>) Arrays.asList(commandElement);
			}
		}
	}
}













