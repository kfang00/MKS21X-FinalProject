public class Path {
   
  private int x, y;
  private String dir;

  public Path(String direction, int X, int Y) { //constructor
    x = X;
    y = Y;
    dir = direction;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int newx) {
    x = newx;
  }

  public void getY(int newy) {
    y = newy;
  }

  public String getDir() {
    return dir;
  }

  public void setDir(String newdir) {
    dir = newdir;
  }

  public String[][] placeHorPath(String[][] field) { //makes the horizontal paths
    int holdX = 0;
    int inc = 0;
    makeHorEntrance(getX(), getY(), field);
    if (getDir() == "left") {
      inc = -1;
    }
    else {
      inc = 1;
    }
    for (int a = getY(); a < (getY() + 3); a += 2) {
      int b = getX();
      while ((field[a][b] != "#") && (field[a][b] != "-")) {
        field[a][b] = "-";
        b+= inc;
	holdX = b;
      }
    }
    makeHorEntrance2(holdX, getY(), field);
    return field;
  }

  public String[][] placeVerPath(String[][] field) { //makes the vertical paths
    int holdY = 0;
    int inc = 0;
    makeVerEntrance(getX(), getY(), field);
    if (getDir() == "up") {
      inc = -1;
    }
    else {
      inc = 1;
    }
    for (int b = getX(); b < (getX() + 5); b += 4) {
      int a = getY();
      while ((field[a][b] != "#") && (field[a][b] != "-")) {
        field[a][b] = "-";
	a+= inc;
	holdY = a;
      }
    }
    makeVerEntrance2(getX(), holdY, field);
    return field;
  }
  
  //any intersections would be replaced with empty spaces
  public void makeHorEntrance(int xInt, int yInt, String[][] field) { 
    for (int a = yInt; a < (getY() + 3); a++) {
      field[a][xInt] = " ";
    }
  }

  public void makeVerEntrance(int xInt, int yInt, String[][] field) {
    for (int b = xInt; b < (getX() + 5); b++) {
      field[yInt][b] = " ";
    }
  }

  public void makeHorEntrance2(int xInt, int yInt, String[][] field) {
    for (int a = (yInt + 1); a < (getY() + 2); a++) {
      field[a][xInt] = " ";
    }
  }

  public void makeVerEntrance2(int xInt, int yInt, String[][] field) {
    for (int b = (xInt + 1); b < (getX() + 4); b++) {
      field[yInt][b] = " ";
    }
  }

}