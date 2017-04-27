package CargoBot2;

public abstract class Command {

	public abstract void execute(Game game);
}

class GoLeft extends Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class GoRight extends Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class GoDown extends Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

class CallFunction extends Command {
	private String functionName;

	public CallFunction(String functionName) {
		this.functionName = functionName;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
