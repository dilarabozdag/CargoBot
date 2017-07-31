import java.awt.image.BufferedImage;

public class Crane extends Item {
	public BufferedImage image;
	public Box hold = null;
	public int xPosition = LevelReader.leftLimit; 
	public int yPosition = 0;  //positions of the crane
	public int xIndex = 0;
	public int yIndex = 0;
	
	
	public int leftLimit = LevelReader.leftLimit;
	public int rightLimit = LevelReader.rightLimit;

	public Crane(BufferedImage image){
		this.image = image;
		
		/*System.out.println("Crane start index :");
		System.out.println(xIndex + "  --crane  start x index");
		System.out.println(yIndex + "  --crane  start y index");
		System.out.println("Crane start position :");
		System.out.println(xPosition + "  --crane start x position");
		System.out.println(yPosition + "  --crane start y position");*/

	}
	
	public void setIndex(int x, int y) {
		this.xIndex = x;
		this.yIndex = y;
	}
	
	
	public void setPosition(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}

}
