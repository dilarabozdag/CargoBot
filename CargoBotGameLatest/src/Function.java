

import java.util.List;

public class Function {
	private final String name;
	private final List<Command> commands;
	
	public Function(String name, List<Command> commands) {
		super();
		this.name = name;
		this.commands = commands;
	}

	public void execute(Game game) {
		for (Command cmd : commands) {
			cmd.execute(game);
			//game.board.printBoard(); her command sonrasi ekrana bas
		}
	}

	@Override
	public String toString() {
		return "Function [name=" + name + ", commands=" + commands + "]";
	}
	
	
}
