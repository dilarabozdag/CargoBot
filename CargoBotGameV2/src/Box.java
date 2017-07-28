

public class Box extends Item{
	public String boxName;
	int xPosition;
	int yPosition;
	int xIndex;
	int yIndex;
	
	final int size = 30;
	final int space = 10;


	public Box(String boxName){
		this.boxName = boxName;
	}

	public void setPosition(int x, int y) {
		this.xPosition=x;
		this.yPosition=y;
	}
	
	public void setIndex(int x, int y) {
		this.xIndex=x;
		this.yIndex=y;
	}
	
	public String toString(){
		return "" + boxName.charAt(0);
	}

	public boolean equals(Box b){
		return this.boxName.equals(b.boxName);
	}

}
