public class Player extends Character{

	private String name;
	//private Weapon weapon;
	//private Shield shield;
	private int coins;
	private Item[] inventory;


	public Player(String newName, int setCoins, int x, int y){
		super("@", x, y, 2, 10, 10, 2, 3);
		name = newName;
		coins = setCoins;
		inventory = new Item[5];
	}

	public Item[] getInventory(){
		return inventory;
	}

    public Item get(int index){
		return inventory[index];
	}

	public String getName(){
		return name;
	}

	public int getCoins(){
		return coins;
	}

	public void setCoins(int setCoins){
		coins = setCoins;
	}

	public boolean addCoins(int newCoins){
		coins += newCoins;
		return true;
	}

	public boolean purchase(int price){
		if (coins >= price){
			coins -= price;
			return true;
		}

		else {
		return false;
		}

	}

	public void add(Item item){
		for (int i = 0; i < inventory.length; i++){
			if (inventory[i] == null){
				inventory[i] = item;
				break;
			}
		}
	}

	public void remove(Item item){
		for (int i = 0; i < inventory.length; i++){
			if (inventory[i] == item){
				inventory[i] = null;
				return;
			}
		}
	}

	public boolean isFull(){
		int size = 0;
		for (int i = 0; i < inventory.length; i++){
			if (inventory[i] != null)
				size++;
		}
		return size == inventory.length;
	}

	boolean contains(Item item){
		for (int i = 0; i < inventory.length; i++) {
			if (item == inventory[i]){
				return true;
			}
		}
		return false;
	}

	public boolean usePotion(int potionValue){
		if(potionValue == 0 ){
			setHealth(getHealth() + 5);
			return true;
		}

		if(potionValue == 1){
			setDefense((getDefense() + 2));
			return true;
		}

		return false;
	}

	/*public boolean equip(Weapon newWeapon){
		if (inventory.contains(Weapon){
			attackPower += 5;
			return true;
		}
		return false;
	}

	public boolean equipShield(Shield newShield){
		if (inventory.contains(Shield){
			defense += 5;
			return true;
		}
		return false;
	}*/
}
