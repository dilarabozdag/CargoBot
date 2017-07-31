

public abstract class Command {
	final int moveLenght = 40;
	final int downLimit = 250;

	public abstract void execute(Game game);

}

class GoLeft extends Command {

	@Override
	public void execute(Game game) {
		Crane crane = game.crane;

		if(crane.xPosition - moveLenght >= LevelReader.leftLimit){
			crane.xPosition = crane.xPosition - moveLenght;
			crane.xIndex = crane.xIndex -1;
			
			/*System.out.println("Left yapti:");
			System.out.println(crane.xIndex + "  --crane x index");
			System.out.println(crane.yIndex + "  --crane y index");
			System.out.println(crane.xPosition + "  --crane x position");
			System.out.println(crane.yPosition + "  --crane y position");*/
		}else
			System.out.println("You can't go there!NO MORE LEFT");
	}

}

class GoRight extends Command {
	static Crane crane;
	public void execute(Game game) {
		crane = game.crane;

		if(crane.xPosition + moveLenght <= LevelReader.rightLimit)	{// if there is a place to go right
			crane.xPosition = crane.xPosition + moveLenght;
			crane.xIndex = crane.xIndex +1;
			
			/*System.out.println("Right yapti:");
			System.out.println(crane.xIndex + "  --crane x index");
			System.out.println(crane.yIndex + "  --crane y index");
			System.out.println(crane.xPosition + "  --crane x position");
			System.out.println(crane.yPosition + "  --crane y position");*/
		}else	{						// if the crane is in the last column of the table, it cannot go right anymore.
			System.out.println("You can't go there!NO MORE RIGHT");
		}
	}

}

class GoDown extends Command {

	public void execute(Game game) {
		Crane crane = game.crane;

		for(int row = 1; row  < game.board.LENGTH; row++){

			if(game.board.current[crane.xIndex][row] != null && game.board.current[crane.xIndex][row] instanceof Box){ //if there is a box in the column line of the crane--

				if(crane.hold != null){															// if the crane is holding a box--
					game.board.current[crane.xIndex][row-1] = crane.hold;			     								//set this over the current box
					crane.hold.setIndex(crane.xIndex, row-1);
					crane.hold.setPosition(crane.xPosition, downLimit - (9 - row) * crane.hold.size);			

					crane.setIndex(crane.xIndex, crane.hold.yIndex-1);													//set the crane position over the box
					crane.setPosition(crane.xPosition, downLimit - (9 - crane.hold.yIndex) * crane.hold.size);

					/*System.out.println("Kutuyu birakirken down yaptiginda kutunun bir ustunde olmali:");
					System.out.println(crane.yIndex+ "  --crane y index");
					System.out.println(crane.yPosition + "  --crane y position");
					System.out.println("Crane kutuyu biraktiginda kutunun yeni yeri:");
					System.out.println(crane.hold.yIndex + "  --box new y index");
					System.out.println(crane.hold.yPosition + "  --box new y position");*/

					crane.hold = null;								 								//crane is empty
					break;

				}else {																		// if the crane is not holding a box--
					crane.hold = (Box) game.board.current[crane.xIndex][row];	//change the position of the first box it saw, with the position of crane
					crane.setIndex(crane.xIndex, row - 1);
					crane.setPosition(crane.xPosition, downLimit - (9 - row) * crane.hold.size);

					crane.hold.setIndex(crane.xIndex, crane.yIndex + 1);
					crane.hold.setPosition(crane.xPosition, downLimit - (7 - crane.yIndex) * crane.hold.size);
					
					/*System.out.println("Kutuyu almak icin down yaptiginda kutunun bir ustunde olmali:");
					System.out.println(crane.yIndex+ "  --crane y index");
					System.out.println(crane.yPosition + "  --crane y position");
					System.out.println("Crane kutuyu alinca kutunun yeni yeri:");
					System.out.println(crane.hold.yIndex + "  --box new y index");
					System.out.println(crane.hold.yPosition + "  --box new y position"); */
					
					game.board.current[crane.xIndex][row] = null;
					break;
				}
			}else if (crane.hold != null && row == game.board.current.length-1){						// there is no box in that column and the crane is holding a box
				game.board.current[game.board.current.length-1][crane.yIndex] = crane.hold;										// drop the box to the last row in that column
				crane.hold = null;
			}
		}
		crane.setIndex(crane.xIndex, 0);
		crane.setPosition(crane.xPosition, 0);
		/*System.out.println("Down bitip yukari ciktiginda:");
		System.out.println(crane.yIndex+ "  --crane y index");
		System.out.println(crane.yPosition + "  --crane y position");

		System.out.println("Kutunun down sonrasi yeri:");
		System.out.println(crane.hold.yIndex + "  --box new y index");
		System.out.println(crane.hold.yPosition + "  --box new y position");*/
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
