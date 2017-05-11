package CargoBot2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

	public Board board;
	public LevelReader lr;
	public CommandReader cr;
	private HashMap<String, Function> functions;


	public Game(HashMap<String, Function> functions) {
		lr = new LevelReader();
		cr = new CommandReader();
		this.functions = functions;
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner= new Scanner(System.in);
	
		Game game =  new Game(CommandReader.functions);
		
		reachTheFile(scanner, game);
		game.fillTheBoard();
		game.board.printTarget();

		System.out.println("----------------------------------------");
		System.out.println("Board");
		System.out.println("----------------------------------------");
		game.board.printBoard();
		
		reachTheCommandFile(scanner, game);
		//System.out.println(CommandReader.functions);
		game.run();
		
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

	private static void reachTheCommandFile(Scanner scanner, Game game) throws IOException {
		String commandFile = "Commands";
		commandFile = getKeysForCommandFile(scanner, commandFile);
		commandFile += ".cb";
		game.cr.read(commandFile);
	}

	private static String getKeysForCommandFile(Scanner scanner, String commandFile) {
		System.out.println("Choose the first key word for command file name: (H), (M), (E)");
		String hardness = scanner.next();
		if (hardness.equals("E") || hardness.equals("M") || hardness.equals("H"))
			commandFile += hardness;
		System.out.println("Choose the second key word for command file name: (1), (2), (3)");
		String levelNo = scanner.next();
		scanner.nextLine(); // Consume newline left-over
		commandFile += levelNo;
		return commandFile;
	}

	private void run() throws IOException {
		runFunction("Main");
	}

	public void runFunction(String name) {
		CommandReader.functions.get(name).execute(this);
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
