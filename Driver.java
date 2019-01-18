//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;

public class Driver {

	private static String[][] display, current;
	private static String[] top;
	private static int enemies = 0;
	private static int roomNum = 1;

	static Maps one = new Maps(1, 140, 31); //new empty map
	static RegRoom first = new RegRoom(70, 28, 17, 3);
	static RegRoom second = new RegRoom(85, 20, 10, 4);
	static RegRoom third = new RegRoom(90, 16, 6, 5);
	static RegRoom fourth = new RegRoom(75, 20, 15, 4);
	static RegRoom fifth = new RegRoom(90, 26, 10, 2);
	static RegRoom sixth = new RegRoom(90, 16, 9, 5);
	static Character alice = new Alice();
	static Character frog = new Frog();
	static Character lizard = new Lizard();
	static Character monster = new Monster("killSpot");

	public static void setCurrent(Maps num, Room rm, String[][] rmNum) {
		int lizX = (int)(Math.floor( (Math.random() * 49) + 20));
		int lizY = (int)(Math.floor( (Math.random() * 21) + 6));
		int frogX =  ((int)(Math.floor( (Math.random() * 49) + 20)));
		int frogY =  ((int)(Math.floor( (Math.random() * 21) + 6)));

		num.putSpaces(rmNum);
		num.placeRoom(rm, rmNum);
		num.spawnPlayer(rmNum, alice);

		if((alice.getX() == frog.getX() && alice.getY() == frog.getY()) ){
				frog.setSymbol("m");
		}

		if((alice.getX() == lizard.getX() && alice.getY() == lizard.getY())){
				lizard.setSymbol("m");
		}

		current = rmNum;
	}

	public static String[][] getCurrent() {
		return current;
	}

	public static int getEnemies() {
		return enemies;
	}

	public static String[] getTop() {
		return top;
	}

	public static void setEnemies(int e) {
		enemies = e;
	}

	public static int getRoomNum() {
		return roomNum;
	}

	public static void setRoomNum(int r) {
		roomNum = r;
	}

	public static boolean roomChange(Room rm) {
		if ((getEnemies() == 0) && (alice.getX() == rm.getExitX()) && (alice.getY() == rm.getExitY())) {
			return true;
		}
		return false;
	}


	public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

	public static void putString(int r, int c,Terminal t,
        	String s, Terminal.Color forg, Terminal.Color back ){
    		t.moveCursor(r,c);
    		t.applyBackgroundColor(forg);
    		t.applyForegroundColor(Terminal.Color.BLACK);

    		for(int i = 0; i < s.length();i++){
      			t.putCharacter(s.charAt(i));
   		}
    		t.applyBackgroundColor(Terminal.Color.DEFAULT);
    		t.applyForegroundColor(Terminal.Color.DEFAULT);
  	}

	public static void putString1(int r, int c, String s, Screen screen,
		Terminal.Color forg, Terminal.Color back, ScreenCharacterStyle styles) {//prints strings
		screen.setCursorPosition(r, c);
		for(int i = 0; i < s.length();i++, r++){
      			screen.putString(r, c, (s.charAt(i) + ""), forg,back, styles);
   		}
	}
	public static void putString2(int r, int c, Screen screen, String[][] field,
		Terminal.Color forg, Terminal.Color back, ScreenCharacterStyle styles) { //prints out the map
    		int a,b,y,x;
		screen.setCursorPosition(r, c);
    		for (a = 0, y = c; a < field.length; a++, y++) {
     			for (b = 0, x = r; b < field[a].length; b++, x++) {
				if (field[a][b] == "#") {//make the rooms visible
					screen.putString(x, y, ("" + (field[a][b])), Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
				}
				else if (field[a][b] == "_") {//inside of the rooms
					screen.putString(x, y, " ", Terminal.Color.WHITE,Terminal.Color.WHITE, ScreenCharacterStyle.Bold);

				}
				else if (field[a][b] == "@") {//player is visible
					screen.putString(x, y, "@", Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);

				}

				else if (field[a][b] == "f") {//frog is visible
					screen.putString(x, y, "fm", Terminal.Color.WHITE,Terminal.Color.GREEN, ScreenCharacterStyle.Bold);
				}

				else if (field[a][b] == "l") {//lizard is visible
					screen.putString(x, y, "lm", Terminal.Color.WHITE,Terminal.Color.GREEN, ScreenCharacterStyle.Bold);
				}

				//else if (field[a][b] == "m") {//monster disappears
				//	screen.putString(x, y, " ", Terminal.Color.WHITE,Terminal.Color.WHITE, ScreenCharacterStyle.Bold);
				//}

				else if (field[a][b] == "-") {//make the paths visible
					screen.putString(x, y, ("" + (field[a][b])), Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
				}
				else if (field[a][b] != "#") {//background
					screen.putString(x, y, " ", Terminal.Color.WHITE,Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
				}
      			}
    		}
  	}

public static void main(String[] args) {


	int x = 10;
	int y = 10;
	Terminal terminal = TerminalFacade.createTextTerminal();
	terminal.enterPrivateMode();
	TerminalSize size = new TerminalSize(100, 40);
	size = terminal.getTerminalSize();
	terminal.setCursorVisible(false);
	boolean running = true;
        long tStart = System.currentTimeMillis();
	long lastSecond = 0;
        int mode = 0;
        long lastTime =  System.currentTimeMillis();
        long currentTime = lastTime;
        long timer = 0;

	if (getRoomNum() == 0) {
		alice.setX(20);
		alice.setY(7);

		lizard.setX(60);
		lizard.setX(15);
//		num.spawnLizard(rmNum, 50, 25, lizard2);
//		num.spawnLizard(rmNum, 30, 10, lizard3);
		frog.setX(30);
		frog.setX(26);
//		num.spawnFrog(rmNum, 60, 20, frog2);
//		num.spawnFrog(rmNum, 50, 15, frog3);
	  //SET MONSTER VALUES
	}
	if (getRoomNum() == 1) {
		alice.setX(19);  //character would show up near the corner of the room
		alice.setY(5);
	}
	if (getRoomNum() == 2) {
		alice.setX(12);
		alice.setY(6);
	}
	if (getRoomNum() == 3) {
		alice.setX(8);
		alice.setY(7);
	}
	if (getRoomNum() == 4) {
		alice.setX(17);
		alice.setY(6);
	}
	if (getRoomNum() == 5) {
		alice.setX(12);
		alice.setY(4);
	}
	if (getRoomNum() == 6) {
		alice.setX(11);
		alice.setY(7);
	}

	Screen screen = new Screen(terminal, 100, 40);
	screen.startScreen();
	while(running){

		Key key = screen.readInput();

		if (key != null)
		{
			if(mode == 0){
          			if (key.getKind() == Key.Kind.Enter) { //go from game screen to backstory
					screen.clear();
					mode = 1;
				}
			}
			if(mode == 1){
          			if (key.getKind() == Key.Kind.Tab) { //go from backstory to map
					screen.clear();
					mode = 2;

				}
			}
			if(mode == 2){
          			if (key.getKind() == Key.Kind.Enter) { //go from map to game
					screen.clear();
					mode = 3;
				}
			}

			if (key.getKind() == Key.Kind.Escape) { //exits the game regardless of mode

				screen.stopScreen();
				running = false;
			}
			if (mode == 3) {  //in game - use keys to interact with character
				if ((key.getKind() == Key.Kind.ArrowLeft)) { //should not go past walls
					alice.setX(alice.getX() - 1);
					getCurrent()[alice.getY()][alice.getX()] = (alice.getSymbol() + ""); //changes where Alice is on the 2D array
					screen.refresh();
				}

				if ((key.getKind() == Key.Kind.ArrowRight)) {
					alice.setX(alice.getX() + 1);
					getCurrent()[alice.getY()][alice.getX()] = (alice.getSymbol() + "");
					screen.refresh();
				}

				if ((key.getKind() == Key.Kind.ArrowUp)) {
					alice.setY(alice.getY() - 1);
					getCurrent()[alice.getY()][alice.getX()] = (alice.getSymbol() + "");
					screen.refresh();
				}

			if ((key.getKind() == Key.Kind.ArrowDown)) {
					alice.setY(alice.getY() + 1);
					getCurrent()[alice.getY()][alice.getX()] = (alice.getSymbol() + "");
					screen.refresh();
				}
				//space moves it diagonally
			if ((key.getCharacter() == ' ')) {
					alice.setX(alice.getX() + 1);
					alice.setY(alice.getY() + 1);
					getCurrent()[alice.getY()][alice.getX()] = (alice.getSymbol() + "");
					screen.refresh();
				}
				if (key.getKind() == Key.Kind.Backspace) { //goes back to map
					screen.clear();
					mode = 2;
				}
			}
			putString(1,4,terminal,"["+key.getCharacter() +"]");
			putString(1,1,terminal,key+"        ");//to clear leftover letters pad withspaces
		}

		//DO EVEN WHEN NO KEY PRESSED:

		//TOP INFO
		long tEnd = System.currentTimeMillis();
		long millis = tEnd - tStart;
		screen.putString(1,2,"Milliseconds since start of program: "+millis, Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
		if(millis/1000 > lastSecond){
			lastSecond = millis / 1000;
			//screen.putString(1,3,"Seconds since start of program: "+lastSecond, Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			screen.putString(1,3,"hello", Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			//one second has passed.
			//one second has passed.

		}
                if(mode == 0){ //game screen
			putString1(1,6, "Welcome to Alice in Asylum!!",screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,8, "Press ENTER to play.....", screen, Terminal.Color.RED,Terminal.Color.WHITE, ScreenCharacterStyle.Blinking);
			screen.refresh();
		}
		else if(mode == 1){ //backstory
			putString1(1,6, "This is the story of Alice. Alice refuses the proposal of the dull Chudley", screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,7, "Vonsworth and runs off for hours. When she rejoins her family, she tells ", screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,8, "them of a Wonderful Land, filled with Queens and Hatters and Jabberwockeys.", screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,9, "Naturally, she is shipped off to an asylum. Now in the madhouse, Alice ", screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,10, "realizes Wonderland connects to the concentrated madness around her and", screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,11, "her friends are able to come out and help her. However, as her friends ", screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,12, "are able to come out, so are her enemies. Help Alice	escape the Asylum!", screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,14, "Press TAB to continue.....", screen, Terminal.Color.MAGENTA,Terminal.Color.WHITE, ScreenCharacterStyle.Blinking);
			screen.refresh();
		}
		else if(mode == 2){ //map


			one.putSpaces(one.getField());
			one.makeRoom();
			one.makePaths();
			putString2(1,6,screen, one.getField(), Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			screen.refresh();
		}
		else if(mode == 3){ //game
			if (getRoomNum() == 1) {
				setCurrent(one, first, one.getRoom1());
				if (roomChange(first)) {
					setEnemies(0);
					setRoomNum(getRoomNum() + 1);
				}
				screen.setCursorPosition((first.getExitX() + 1), (first.getExitY() + 14));
				screen.putString((first.getExitX() + 1), (first.getExitY() + 6), "E", Terminal.Color.WHITE,Terminal.Color.BLACK, ScreenCharacterStyle.Bold); //prints the exit
			}
			if (getRoomNum() == 2) {
				setCurrent(one, second, one.getRoom2());
				if (roomChange(second)) {
					setEnemies(0);
					setRoomNum(getRoomNum() + 1);
				}
				screen.setCursorPosition((second.getExitX() + 1), (second.getExitY() + 6));
				screen.putString((second.getExitX() + 1), (second.getExitY() + 6), "E", Terminal.Color.WHITE,Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
			}
			if (getRoomNum() == 3) {
				setCurrent(one, third, one.getRoom3());
				if (roomChange(third)) {
					setEnemies(0);
					setRoomNum(getRoomNum() + 1);
				}
				screen.setCursorPosition((third.getExitX() + 1), (third.getExitY() + 6));
				screen.putString((third.getExitX() + 1), (third.getExitY() + 6), "E", Terminal.Color.WHITE,Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
			}
			if (getRoomNum() == 4) {
				setCurrent(one, fourth, one.getRoom4());
				if (roomChange(fourth)) {
					setEnemies(0);
					setRoomNum(getRoomNum() + 1);
				}
				screen.setCursorPosition((fourth.getExitX() + 1), (fourth.getExitY() + 6));
				screen.putString((fourth.getExitX() + 1), (fourth.getExitY() + 6), "E", Terminal.Color.WHITE,Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
			}
			if (getRoomNum() == 5) {
				setCurrent(one, fifth, one.getRoom5());
				if (roomChange(fifth)) {
					setEnemies(0);
					setRoomNum(getRoomNum() + 1);
				}
				screen.setCursorPosition((fifth.getExitX() + 1), (fifth.getExitY() + 6));
				screen.putString((fifth.getExitX() + 1), (fifth.getExitY() + 6), "E", Terminal.Color.WHITE,Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
			}
			if (getRoomNum() == 6) {
				setCurrent(one, sixth, one.getRoom6());
				if (roomChange(sixth)) {
					setEnemies(0);
					setRoomNum(getRoomNum() + 1);
				}
				screen.setCursorPosition((sixth.getExitX() + 1), (sixth.getExitY() + 6));
				screen.putString((sixth.getExitX() + 1), (sixth.getExitY() + 6), "E", Terminal.Color.WHITE,Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
			}
			putString2(1,6,screen, getCurrent(), Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold); //prints the room
			screen.setCursorPosition((alice.getX() + 1), (alice.getY() + 6));
			screen.putString((alice.getX() + 1), (alice.getY() + 6), "@", Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold); //prints your character
			screen.refresh();
		}
		else if(mode == 4) {
        		lastTime = currentTime;
        		currentTime = System.currentTimeMillis();
        		timer += (currentTime -lastTime);//add the amount of time since the last frame.
        		//DO GAME STUFF HERE
       			putString(1,3,terminal, "Game here...",Terminal.Color.WHITE,Terminal.Color.RED);
        		putString(3,5,terminal, "Time: "+timer,Terminal.Color.WHITE,Terminal.Color.RED);

      		}else if (mode == 5) {
        		terminal.applySGR(Terminal.SGR.ENTER_BOLD,Terminal.SGR.ENTER_BLINK);
        		putString(1,3,terminal, "Not game, just a pause!",Terminal.Color.RED,Terminal.Color.WHITE);
        		terminal.applySGR(Terminal.SGR.RESET_ALL);
      		}
		else if (mode == 6) { //Game over
        		putString1(1,6, "GAME OVER!!!",screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,8, "PLEASE TRY AGAIN!!!!", screen, Terminal.Color.RED,Terminal.Color.WHITE, ScreenCharacterStyle.Blinking);
			screen.refresh();
      		}
		else if (mode == 7) { //Mission accomplished! You won!!
        		putString1(1,6, "YOU WON!!!!",screen,  Terminal.Color.WHITE,Terminal.Color.RED, ScreenCharacterStyle.Bold);
			putString1(1,8, "CONGRATS!!! FEEL FREE TO PLAY AGAIN!!", screen, Terminal.Color.RED,Terminal.Color.WHITE, ScreenCharacterStyle.Blinking);
			screen.refresh();
      		}
	}


	}
}
