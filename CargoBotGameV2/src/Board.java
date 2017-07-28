
public class Board {
	public final int LENGTH = 650;	//The length of the table is constant (650)
	public int widthIndex;      			//The width will changed by level selection
	public Item[][] current;
	public Item[][] target;
	public Crane crane;

	public Board(int widthIndex) {
		this.widthIndex = widthIndex;
		//crane = new Crane();
		
		current = new Item[widthIndex][7]; 		
		current[0][0] = crane;
		target  = new Item[widthIndex][7];
	}

	public void printTarget() {
		for (int row = 0; row < LENGTH; row++) {
			for (int col = 0; col < widthIndex; col++) {
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
			for (int col = 0; col < widthIndex; col++) {
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
			for (int col = 0; col < widthIndex; col++) 
				if (current[row][col] instanceof Crane) 
					current[row][col] = null;
		current[crane.xPosition][crane.yPosition] = crane;
	}
}
