package CargoBot2;

import java.io.IOException;
import java.util.Scanner;

public class Game {

	public Board board;
	public LevelReader lr;

	public Game() {
		lr = new LevelReader();
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner= new Scanner(System.in);
		Game game = new Game();
		reachTheFile(scanner, game);
		game.fillTheBoard();
		game.board.printTarget();

		System.out.println("----------------------------------------");
		System.out.println("Board");
		System.out.println("----------------------------------------");
		runCommandList( game);
	}

	private static void reachTheFile(Scanner scanner, Game game) {
		String fileName = "lvl";
		fileName = getHardnessChoice(scanner, fileName);
		fileName = getLevelChoice(scanner, fileName);
		fileName += ".cb";
		game.lr.read(fileName);
	}

	private static String getHardnessChoice(Scanner scanner, String fileName) {
		System.out.println("Choose a Hardness: Hard(H), Medium(M), Easy(E)");
		String hardness = scanner.next();
		if (hardness.equals("E") || hardness.equals("M") || hardness.equals("H"))
			fileName += hardness;
		return fileName;
	}

	private static String getLevelChoice(Scanner scanner, String fileName) {
		System.out.println("Choose a level: 1-2-3");
		String levelNo = scanner.next();
		scanner.nextLine(); // Consume newline left-over
		fileName += levelNo;
		return fileName;
	}

	public void fillTheBoard() {
		board = new Board(lr.width, lr.initial, lr.target);
	}

	private static void runCommandList( Game game) throws IOException {
		boolean error = false;
		boolean stop = false;
		String currentCommand = "";
		CommandReader.read();
		while (!stop) {
			game.board.printBoard();
			System.out.println("FUNCTION");
			for(int i = 0; i < CommandReader.commands.size(); i++) {
				currentCommand = CommandReader.commands.get(i);

				if(currentCommand.equals("Right")){
					if (!game.board.crane.goRight(game.board.width)) //if the crane cannot go right-- 
						error = true;										//give invalid command error
				}else if(currentCommand.equals("Left")) {
					if(!game.board.crane.goLeft()) 			
						error = true;
				}else if(currentCommand.equals("Down")) {

					game.board.crane.goDown(game.board.current);
				}else if(currentCommand.equals("Stop")) {			// there is something WRONG with this!
					game.board.printBoard();
					game.compareCurrentWithTarget();
					stop = true;
				}else {
					error = true;
					System.out.println("Command -" + currentCommand + "- is invalid");
				}if(error || stop) {    // if there is error/stop - don't do the rest of the command list
					CommandReader.commands.clear();
					CommandReader.commands.add("Stop");
					break;
				}
			}
			error = false;
		}
	}

	public void compareCurrentWithTarget() {
		board.current[board.crane.x][board.crane.y] = null;  //target has no crane so it is easier to compare when we delete it in current
		for (int row = 0; row < board.LENGTH; row++) {
			for (int col = 0; col < board.width; col++) {
				if (board.target[row][col] != null) {						//if this index is not empty- if there is a box in target table--
					if (board.current[row][col] != null) {						// there is a box in current table--
						Box table = (Box) (board.current[row][col]);
						Box target = (Box) (board.target[row][col]);
						if (!table.equals(target)) {									//check if they are equals
							System.out.println("Guess What? You Just LOST the Game!!!");
							return;
						}
					}else {											    	//if there is a box in target but not in current
						System.out.println("Guess What? You Just LOST the Game!!!");
						return;
					}
				}else {													//if there is no box in target but there is in current
					if (board.current[row][col] != null) {							
						System.out.println("Guess What? You Just LOST the Game!!!");
						return;
					}
				}
			}
		}
		System.out.println("YOU'RE SUCH A GENIUS!");	
	}
}
