public class Room {

  private int roomNum, x, y, dimensionX, dimensionY;

  public Room(int rm, int startX, int startY) {
    roomNum = rm;
    x = startX;
    y = startY;
  }

  public int getRoomNum() {
    return roomNum;
  }

  public void setRoomNum(int newrm) {
    roomNum = newrm;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int newX) {
    x = newX;
  }

  public void setY(int newY) {
    y = newY;
  }

  public int getDimX() {
    return dimensionX;
  }

  public int getDimY() {
    return dimensionY;
  }

  public int getExitX() {
    return (x + dimensionX - 2);
  }

  public int getExitY() {
    return (y + dimensionY - 6);
  }
}
