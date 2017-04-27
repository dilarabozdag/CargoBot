package CargoBot2;

import java.util.List;

public class Function {
	private final String name;
	private final List<Command> commands;
	
	public Function(String name, List<Command> commands) {
		super();
		this.name = name;
		this.commands = commands;
	}

	public void execute() {
		for (Command cmd : commands) {
			cmd.execute();
		}
	}
	
	
}
