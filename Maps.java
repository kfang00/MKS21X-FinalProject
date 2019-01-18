public class Maps {

  private int num;
  private String[][] field, room1, room2, room3, room4, room5, room6;

  public Maps(int n, int len, int wid) {
    num = n;
    field = new String[wid][len];
    room1 = new String[wid][len];
    room2 = new String[wid][len];
    room3 = new String[wid][len];
    room4 = new String[wid][len];
    room5 = new String[wid][len];
    room6 = new String[wid][len];
  }

  public int getNum() { //get map number
    return num;
  }

  public void makeRoom() {//makes all the rooms
    RegRoom two = new RegRoom(17, 7, 46, 1);
    RegRoom one = new RegRoom(17, 10, 20, 3);
    RegRoom three = new RegRoom(20, 7, 71, 7);
    RegRoom four = new RegRoom(20, 7, 6, 15);
    RegRoom five = new RegRoom(21, 9, 74, 17);
    RegRoom six = new RegRoom(25, 7, 36, 23);


    if (getNum() == 1) {
      placeRoom(one, field);
      placeRoom(two, field);
      placeRoom(three, field);
      placeRoom(four, field);
      placeRoom(five, field);
      placeRoom(six, field);
    }
  }

  public void placeRoom(Room rm, String[][] rmNum) { //places the rooms onto map
    int storeX = rm.getX();
    int storeY = rm.getY();
    int lenX = rm.getDimX();
    int lenY = rm.getDimY();
    for (int a = storeY; a < (storeY + lenY); a = ((a - 1) + lenY)) {
      for (int b = storeX; b < (storeX + lenX); b++) {
        rmNum[a][b] = "#";
      }
    }
    for (int a = (storeY + 1); a < (storeY + lenY - 1); a++) {
      rmNum[a][storeX] = "#";
      rmNum[a][((storeX + lenX) - 1)] = "#";
      for (int b = (storeX + 1); b < (storeX + lenX - 1); b++) {
	rmNum[a][b] = "_";
      }
    }
    makeExit(rm, rmNum);
  }


  public void makePaths() { //makes all the paths
    Path three = new Path("left", 71, 9);
    Path two = new Path("down", 51, 7);
    Path five = new Path("left", 74, 18);
    Path six = new Path("up", 46, 23);
    Path seven = new Path("down", 40, 11);

    if (getNum() == 1) {
      field = three.placeHorPath(field);
      field = two.placeVerPath(field);
      field = five.placeHorPath(field);
      field = six.placeVerPath(field);
      field = seven.placeVerPath(field);
    }
  }

  public String toString() {//turns the map into a string
    String output = "";
    for (int a = 0; a < field.length; a++) {
      for (int b = 0; b < field[a].length; b++) {
        output = output + field[a][b];
      }
      output = output + "\n";
    }
    return output;
  }


  public String[][] getField() {
    return field;
  }

  public String[][] getRoom1() {
    return room1;
  }

  public String[][] getRoom2() {
    return room2;
  }

  public String[][] getRoom3() {
    return room3;
  }

  public String[][] getRoom4() {
    return room4;
  }

  public String[][] getRoom5() {
    return room5;
  }

  public String[][] getRoom6() {
    return room6;
  }

  public void putSpaces(String[][] map) {//this is to prevent nulls from printing
    for (int a = 0; a < field.length; a++) {
      for (int b = 0; b < field[a].length; b++) {
        if (map[a][b] == null) {
          map[a][b] = " ";
        }
      }
    }
  }

  public void spawnPlayer(String[][] rmNum, Character alice) {
    rmNum[alice.getY()][alice.getX()] = (alice.getSymbol() + "");
  }

  public void spawnLizard(String[][] rmNum, int x, int y, Character lizard) {
      lizard.setX(x);
      lizard.setY(y);

      rmNum[lizard.getY()][lizard.getX()] = (lizard.getSymbol());
  }

  public void spawnFrog(String[][] rmNum, int x, int y, Character frog) {
      frog.setX(x);
      frog.setY(y);

      rmNum[frog.getY()][frog.getX()] = (frog.getSymbol());
  }

  public void makeExit(Room rm, String[][] rmNum) {
    rmNum[rm.getExitY()][rm.getExitX()] = "E";
  }


}
