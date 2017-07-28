

public abstract class Command {

	public abstract void execute(Game game);

}

class GoLeft extends Command {

	@Override
	public void execute(Game game) {
		Crane crane = game.crane;
		
		if(crane.xPosition - 41 > LevelReader.leftLimit){
			crane.xPosition = crane.xPosition - 40;
		}else
			System.out.println("You can't go there!NO MORE LEFT");
	}

}

class GoRight extends Command {
static Crane crane;
	public void execute(Game game) {
		crane = game.crane;

		if(crane.xPosition + 41 < LevelReader.rightLimit)	{// if there is a place to go right
			crane.xPosition = crane.xPosition + 40;
		}else	{						// if the crane is in the last column of the table, it cannot go right anymore.
			System.out.println("You can't go there!NO MORE RIGHT");
		}
	}

}

class GoDown extends Command {

	public void execute(Game game) {
		Crane crane = game.crane;

		for(int row = 0; row < game.board.LENGTH; row++){
			
			if(game.board.current[row][crane.xPosition] != null && game.board.current[row][crane.xPosition] instanceof Box){   	//if there is a box in the column line of the crane--
				if(crane.hold != null){															// if the crane is holding a box--
					game.board.current[row-1][crane.xPosition] = crane.hold;			     								//set this over the current box
					crane.hold = null;								 								//crane is empty
					break;
				}else {																		// if the crane is not holding a box--
					crane.hold = (Box) game.board.current[row][crane.xPosition];											//change the position of the first box it saw, with the position of crane
					game.board.current[row][crane.xPosition] = null;
					break;
				}
			}
			else if (crane.hold != null && row == game.board.current.length-1){						// there is no box in that column and the crane is holding a box
				game.board.current[game.board.current.length-1][crane.xPosition] = crane.hold;										// drop the box to the last row in that column
				crane.hold = null;
			}
		}
	}

}

class GuardedCommand extends Command {
	Condition cond;
	Command command;
	String cmd ;

	public GuardedCommand(Condition cond,String command) {
		this.cond=cond;
		this.cmd=command;
	}
	@Override
	public void execute(Game game) {
		if(cond.check(game)){
			if(cmd.equals("Right")){
				command = new GoRight();
				command.execute(game);		
			} else if(cmd.equals("Left")){
				command = new GoLeft();
				command.execute(game);
			}else if(cmd.equals("Down")){
				command = new GoDown();
				command.execute(game);
			} else {
				command = new CallFunction(cmd);
				command.execute(game);

			}
		}
	}

}

class CallFunction extends Command {

	private String functionName;
	public CallFunction(String functionName) {
		this.functionName = functionName;
	}

	public void execute(Game game) {
		game.runFunction(functionName);
	}

}
