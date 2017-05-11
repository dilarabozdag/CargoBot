package CargoBot2;

public abstract class Command {

	public abstract void execute(Game game);
	Crane crane = new Crane();

}

class GoLeft extends Command {

	@Override
	public void execute(Game game) {
		if(crane.y - 1 > crane.leftLimit)
			crane.y = crane.y - 1;
		else
			System.out.println("You can't go there!NO MORE LEFT");
	}

	@Override
	public String toString() {
		return "GoLeft []";
	}
	
}

class GoRight extends Command {

	public void execute(Game game) {
		if(crane.y + 1 < game.board.width)	// if there is a place to go right
			crane.y = crane.y + 1;
		else							// if the crane is in the last column of the table, it cannot go right anymore.
			System.out.println("You can't go there!NO MORE RIGHT");
	}

	@Override
	public String toString() {
		return "GoRight []";
	}
	
}

class GoDown extends Command {

	public void execute(Game game) {
		for(int row = 0; row < game.board.LENGTH; row++){
			if(game.board.current[row][crane.y] != null && game.board.current[row][crane.y] instanceof Box){   	//if there is a box in the column line of the crane--
				if(crane.hold != null){															// if the crane is holding a box--
					game.board.current[row-1][crane.y] = crane.hold;			     								//set this over the current box
					crane.hold = null;								 								//crane is empty
					break;
				}else {																		// if the crane is not holding a box--
					crane.hold = (Box) game.board.current[row][crane.y];											//change the position of the first box it saw, with the position of crane
					game.board.current[row][crane.y] = null;
					break;
				}
			}
			else if (crane.hold != null && row == game.board.current.length-1){						// there is no box in that column and the crane is holding a box
				game.board.current[game.board.current.length-1][crane.y] = crane.hold;										// drop the box to the last row in that column
				crane.hold = null;
			}
		}
	}

	@Override
	public String toString() {
		return "GoDown []";
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

	@Override
	public String toString() {
		return "CallFunction [functionName=" + functionName + "]";
	}

	
}
