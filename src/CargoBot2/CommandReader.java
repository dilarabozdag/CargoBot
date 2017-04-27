package CargoBot2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CommandReader {
	static String line;
	static  ArrayList<String> nameList = new ArrayList<String>();
	static ArrayList <String> commands = new ArrayList<String>();
	String[] lineElement = null;
	String[] commandElement  = null;
	private static BufferedReader br;

	public static void read() throws IOException {
		br = new BufferedReader(new FileReader("Commands.cb"));
		String line;
		while ((line = br.readLine()) != null) {

			String[] lineElement = line.split("-");
			for (int i = 0; i < lineElement.length; i++) {
				if(lineElement[i].equals("Right") || lineElement[i].equals("Down") 
						|| lineElement[i].equals("Left") || lineElement[i].equals("Stop")) 
					commands.add(lineElement[i]);
				else 
					nameList.add(lineElement[i]);

			}
		}
	}
}
