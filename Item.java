public class Item{
	private char symbol;
	private String name;
	
	public Item(char symbol, String name){
        this.symbol = symbol;
        this.name = name;
    }
	
    public char getSymbol(){
		return symbol; 
	}

    public String getName(){
		return name; 
	}
}