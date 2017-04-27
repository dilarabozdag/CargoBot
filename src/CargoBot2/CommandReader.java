package CargoBot2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CommandReader {
	public static HashMap<String, Function> functions;
	
	public static void read() throws IOException {
		functions = new HashMap<>();
		BufferedReader br = new BufferedReader(new FileReader("Commands.cb"));
		String line;
		while ((line = br.readLine()) != null) {
			String[] lineElement = line.split("-");
			String name = lineElement[0];
			ArrayList<Command> commands = new ArrayList<Command>();
			for (int i = 1; i < lineElement.length; i++) {
				Command cmd;
				if(lineElement[i].equals("Right"))
					cmd = new GoRight();
				else if(lineElement[i].equals("Left"))
					cmd = new GoLeft();
				else if(lineElement[i].equals("Down"))
					cmd = new GoDown();
				else
					cmd = new CallFunction(lineElement[i]);
				commands.add(cmd);
			}
			Function function = new Function(name, commands);
			functions.put(name, function);
		}
	}
}
