public class Monster extends Character implements Moveable{
	private String name;

	public Monster(String newName){
		super("m", 15, 15, 3, 5, 5, 1, 1);
		name = newName;
	}

	public void moveRandomly(){
		setX((int)Math.random()*16);
		setY((int)Math.random()*16);
	}
}
