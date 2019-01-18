public class RegRoom extends Room {

  private int dimensionX, dimensionY, enemies;
  private boolean hasEntrance;
  private String direction;

  public RegRoom(int dimX, int dimY, int startX, int startY) {
    super(1, 1, 1);
    dimensionX = dimX;
    dimensionY = dimY;
    super.setX(startX);
    super.setY(startY);
  }

  public int getDimX() {
    return dimensionX;
  }

  public int getDimY() {
    return dimensionY;
  }

  public int getX() {
    return super.getX();
  }

  public int getY() {
    return super.getY();
  }

  public void setDimX(int X) {
    dimensionX = X;
  }

  public void setDimY(int Y) {
    dimensionY = Y;
  }

  public void setX(int newX) {
    super.setX(newX);
  }

  public void setY(int newY) {
    super.setY(newY);
  }

  public int getExitX() {
    return (x + dimensionX - 2);
  }

  public int getExitY() {
    return (y + dimensionY - 6);
  }
}
