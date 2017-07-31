

public class Condition {
	public final int LENGTH = 7;	
	public int width; 

	String state;
	String command;


	public Condition(String state, String command) {
		this.state=state;
		this.command=command;
	}

	public boolean check(Game game) {
		if(game.crane.hold != null){
			if(game.crane.hold.boxName.equals(state))
				return true;
	
		
		} else 	if(game.crane.hold==null){ // BAKMAN LAZIM
			
						return true;
					}
				
		return false;


	}
}
