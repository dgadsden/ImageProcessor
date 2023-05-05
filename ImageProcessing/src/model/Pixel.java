package model;

/**
 * Class for the individual pixels of an image and its methods.
 */
public class Pixel implements IPixel {
  private int row;
  private int col;
  private int red;
  private int green;
  private int blue;


  /**
   * Constructor for a pixel that takes in a position and a color by its rbg values.
   *
   * @param row   the row position of a pixel in an image.
   * @param col   the col position of a pixel in an image.
   * @param red   the value of red of the pixel.
   * @param green the value of green of the pixel.
   * @param blue  the value of blue of the pixel.
   */
  public Pixel(int row, int col, int red, int green, int blue) throws IllegalArgumentException {
    if (row < 0 || col < 0 || red < 0 || green < 0
            || blue < 0 || red > 256 || green > 256 || blue > 256) {
      throw new IllegalArgumentException("Value of channel cannot be less than 0 or greater than " +
              "256 or Pixel coordinates are invalid");
    }
    this.row = row;
    this.col = col;
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public int getR() {
    return this.red;
  }

  @Override
  public int getG() {
    return this.green;
  }

  @Override
  public int getB() {
    return this.blue;
  }

  @Override
  public void setRow(int row) throws IllegalArgumentException {
    if (row >= 0) {
      this.row = row;
    } else {
      throw new IllegalArgumentException("row can't be negative.");
    }
  }

  @Override
  public void setCol(int col) throws IllegalArgumentException {
    if (col >= 0) {
      this.col = col;
    } else {
      throw new IllegalArgumentException("col can't be negative.");
    }
  }

  @Override
  public void setR(int red) throws IllegalArgumentException {
    if (red >= 0 && red <= 255) {
      this.red = red;
    } else {
      throw new IllegalArgumentException("red value is not valid.");
    }
  }

  @Override
  public void setG(int green) throws IllegalArgumentException {
    if (green >= 0 && green <= 255) {
      this.green = green;
    } else {
      throw new IllegalArgumentException("green value is not valid.");
    }
  }

  @Override
  public void setB(int blue) throws IllegalArgumentException {
    if (blue >= 0 && blue <= 255) {
      this.blue = blue;
    } else {
      throw new IllegalArgumentException("blue value is not valid.");
    }
  }
}
