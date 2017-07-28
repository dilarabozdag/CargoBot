import java.awt.image.BufferedImage;

public class Crane extends Item {
	public BufferedImage image;
	public Box hold = null;
	
	public int xPosition = LevelReader.leftLimit; 
	public int yPosition = 0;  //positions of the crane
	public int leftLimit = LevelReader.leftLimit;
	public int rightLimit = LevelReader.rightLimit;

	public Crane(BufferedImage image){
		this.image = image;
	}
	
	public void setPosition(int x, int y) {
		this.xPosition=x;
		this.yPosition=y;
	}

}
