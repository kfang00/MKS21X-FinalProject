public class Character{

	private int attackPower;
	private int health, lives;
	private int maxHealth;
	private int defense;
	private int Xcor;
	private int Ycor;
	private String symbol;

	public Character(String nowSymbol, int x, int y, int nowAttackPower, int nowHealth, int nowMaxHealth, int nowDefense, int li){
		symbol = nowSymbol;
		Xcor = x;
		Ycor = y;
		attackPower = nowAttackPower;
		health = nowHealth;
		maxHealth = nowMaxHealth;
		defense = nowDefense;
		lives = li;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setSymbol(String newSym){
		symbol = newSym;
	}

	public int getHealth() {
    		return health;
	}

	public void setHealth(int newHealth){
		health = newHealth;
	}

	public int getLives() {
		return lives;
	}

	public int getX(){
		return Xcor;
	}

	public int getY(){
		return Ycor;
	}

	public void setX(int newX){
		Xcor = newX;
	}

	public void setY(int newY){
		Ycor = newY;
	}

	public int getAttackPower(){
		return attackPower;
	}

	public void setAttackPower(int newPower){
		attackPower = newPower;
	}

	public int getDefense(){
		return defense;
	}

	public void setDefense(int newDefense){
		defense = newDefense;
	}

	public boolean takeDamage(int damage) {
        	health -= damage;

		if((health < 1) && (lives == 1)){
			return false;
		}
		else if ((health < 1) && (lives > 1)) {
			health = maxHealth;
			lives -= 1;
		}
		else {
			return true;
		}
		return true;
	}


	public void meleeAttack(Character other){
		int damage = Math.max(0, attackPower - other.getDefense());

		damage = (int)(Math.random() * damage) + 1;

		other.takeDamage(damage);
	}

	public void rangeAttack(Character other){
		int damage = Math.max(0, attackPower / 2 + attackPower - other.getDefense());

        damage = (int)(Math.random() * damage) + 1;

        other.takeDamage(damage);
	}

}
