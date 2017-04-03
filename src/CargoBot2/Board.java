package CargoBot2;

public class Board {
	public final int LENGTH = 7;	//The length of the table is constant (7)
	public int width;      			//The width will changed by level selection
	public Item[][] current;
	public Item[][] target;
	public Crane crane;

	public Board(int width, Item[][] initial, Item[][] target) {
		this.width = width;
		crane = new Crane();
		current = initial;
		current[0][0] = crane;
		this.target = target;
	}

	public void printTarget() {
		System.out.println("----------------------------------------");
		System.out.println("Target");
		System.out.println("----------------------------------------");
		
		for (int row = 0; row < LENGTH; row++) {
			for (int col = 0; col < width; col++) {
				if (target[row][col] == null) 
					System.out.print("|   |");
				else if(!(target[row][col] instanceof Crane))  //if there is a box in this index--
					System.out.print("| " + target[row][col].toString() + " |");
			}
			System.out.println("");
		}
		System.out.println("----------------------------------------");
	}

	public void printBoard() {
		refresh();
		for (int row = 0; row < LENGTH; row++) {
			for (int col = 0; col < width; col++) {
				if (current[row][col] instanceof Crane) {      // if there is crane--
					Crane crane = (Crane) (current[row][col]);    
					if (crane.hold != null)                        // if the crane is holding a box--       
						System.out.print("| C" + crane.hold.toString() + "|");
					else 										   // if the crane is empty--
						System.out.print("| C |"); 
				} else if (current[row][col] == null) 
					System.out.print("|   |");
				else 										 // if there is a box--
					System.out.print("| " + current[row][col].toString() + " |");
			}
			System.out.println("");
		}
		System.out.println("----------------------------------------");
	}

	private void refresh() {
		for (int row = 0; row < LENGTH; row++) 
			for (int col = 0; col < width; col++) 
				if (current[row][col] instanceof Crane) 
					current[row][col] = null;
		current[crane.pos[0]][crane.pos[1]] = crane;
	}
}
