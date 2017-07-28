

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CommandReader {
	public static HashMap<String, Function> functions;

	public void read(ArrayList<String[]> commandArrayArrayList) throws IOException {
		functions = new HashMap<>();
		
		for (int j = 0; j < commandArrayArrayList.size(); j++) {

			if(!commandArrayArrayList.get(j)[0].equals("")){
				
				String[] lineElement =commandArrayArrayList.get(j);
				String name = ""+j;
				ArrayList<Command> commands = new ArrayList<Command>();
				for (int i = 0; i < lineElement.length; i++) {
					Command cmd; 
					if(lineElement[i].contains(":")){
						String[] colorCommand = lineElement[i].split(":");
						Condition cond=new Condition(colorCommand[0],colorCommand[1]);
						cmd = new GuardedCommand(cond,colorCommand[1]);
					}
					else if(lineElement[i].equals("Right")){
						cmd = new GoRight();
					}else if(lineElement[i].equals("Left"))
						cmd = new GoLeft();
					else if(lineElement[i].equals("Down"))
						cmd = new GoDown();
					else
						cmd = new CallFunction(lineElement[i]);

					commands.add(cmd);
					//System.out.println(cmd.toString()); //all is well
				}
				Function function = new Function(name, commands);
				functions.put(name, function);
					System.out.println(function.toString()); // working just fine
			}
		}
	}
}
