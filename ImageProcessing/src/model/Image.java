package model;

/**
 * Class that represents an image and its methods.
 */
public class Image implements IImage {

  private final Pixel[][] pixels;
  private final String name;

  /**
   * Constructor for an image.
   * @param pixels the array of pixels that make up an image.
   * @param name the name of the image.
   */
  public Image(Pixel[][] pixels, String name) throws IllegalArgumentException {
    if (pixels == null || name == null) {
      throw new IllegalArgumentException("Pixels or name cannot be null");
    }
    this.pixels = pixels;
    this.name = name;
  }

  /**
   * Method that gets the name of the image.
   *
   * @return the string value name of the image.
   */
  @Override
  public String getImageName() {
    return this.name;
  }

  /**
   * Method that gets the height of the image.
   *
   * @return the int value of the height of the image.
   */
  @Override
  public int getImageHeight() {
    return this.pixels.length;
  }

  /**
   * Method that get the width of the image.
   *
   * @return the int value of the width of the image.
   */
  @Override
  public int getImageWidth() {
    return this.pixels[0].length;
  }

  /**
   * Method that copies the array of pixels in an image.
   *
   * @return a new array that is a copy of the original image.
   */
  @Override
  public Pixel[][] copyPixels() {
    Pixel[][] copyArray = new Pixel[pixels.length][];
    for (int i = 0; i < pixels.length; i++) {
      copyArray[i] = new Pixel[pixels[i].length];
      for (int j = 0; j < pixels[i].length; j++) {
        copyArray[i][j] = pixels[i][j];
      }
    }
    return copyArray;
  }

  /**
   * Method that returns the pixel at the given row and col.
   *
   * @param row the row position of the given pixel.
   * @param col the col position of the given pixel.
   * @return the pixel of that position in the image.
   */
  @Override
  public Pixel getPixel(int row, int col) {
    return this.pixels[row][col];
  }
}
