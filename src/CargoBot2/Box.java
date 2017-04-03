package CargoBot2;

public class Box extends Item{
	public String color;

	public Box(String color){
		this.color = color;
	}

	public String toString(){
		return "" + color.charAt(0);
	}

	public boolean equals(Box b){
		return this.color.equals(b.color);
	}
	
}
