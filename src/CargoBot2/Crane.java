package CargoBot2;

public class Crane extends Item {

	public Box hold = null;
	public int x,y;  //positions of the crane
	public int leftLimit = -1;

	public Crane(){
		x = 0;    	 // x of crane
		y = 0;		 // y of crane
	}

	public boolean goRight(int rightLimit){
		if(y + 1 < rightLimit){		// if there is a place to go right
			y = y + 1;
			return true;
		}							// if the crane is in the last column of the table, it cannot go right anymore.
		System.out.println("You can't go there!");
		return false;
	}

	public boolean goLeft(){
		if(y - 1 > leftLimit){
			y = y - 1;
			return true;
		}
		System.out.println("You can't go there!");
		return false;
	}

	public void goDown(Item[][]board){
		for(int row = 0; row < board.length; row++){
			if(board[row][y] != null && board[row][y] instanceof Box){   	//if there is a box in the column line of the crane--
				if(hold != null){															// if the crane is holding a box--
					board[row-1][y] = hold;			     								//set this over the current box
					hold = null;								 								//crane is empty
					break;
				}else {																		// if the crane is not holding a box--
					hold = (Box) board[row][y];											//change the position of the first box it saw, with the position of crane
					board[row][y] = null;					
					break;
				}
			}
			else if (hold != null && row == board.length-1){						// there is no box in that column and the crane is holding a box
				board[board.length-1][y] = hold;										// drop the box to the last row in that column
				hold = null;
			}
		}
	}

}
