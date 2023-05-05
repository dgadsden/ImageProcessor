package model;

/**
 * Interface for images in an image processor.
 */
public interface IImage {

  /**
   * Method that gets the name of the image.
   *
   * @return the string value name of the image.
   */
  String getImageName();

  /**
   * Method that gets the height of the image.
   *
   * @return the int value of the height of the image.
   */
  int getImageHeight();

  /**
   * Method that get the width of the image.
   *
   * @return the int value of the width of the image.
   */
  int getImageWidth();

  /**
   * Method that copies the array of pixels in an image.
   *
   * @return a new array that is a copy of the original image.
   */
  Pixel[][] copyPixels();


  /**
   * Method that returns the pixel at the given row and col.
   *
   * @param row the row position of the given pixel.
   * @param col the col position of the given pixel.
   * @return the pixel of that position in the image.
   */
  Pixel getPixel(int row, int col);

}
