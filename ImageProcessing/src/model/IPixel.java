package model;

/**
 * Interface for the pixel of an image and its methods.
 */
public interface IPixel {

  /**
   * Method that returns the row value of a Pixel.
   *
   * @return the int value of the row.
   */
  int getRow();

  /**
   * Method that returns the col value of a Pixel.
   *
   * @return the int value of the col.
   */
  int getCol();


  /**
   * Method that returns the value of red of the Pixel.
   *
   * @return the int value of red.
   */
  int getR();


  /**
   * Method that returns the value of green of the Pixel.
   *
   * @return the int value of green.
   */
  int getG();

  /**
   * Method that returns the value of blue of the Pixel.
   *
   * @return the int value of blue.
   */
  int getB();

  /**
   * Method that sets the row value of a Pixel.
   *
   * @param row the new row position
   * @throws IllegalArgumentException if the row value is negative.
   */
  void setRow(int row) throws IllegalArgumentException;

  /**
   * Method that sets the col value of a Pixel.
   *
   * @param col the new col position
   * @throws IllegalArgumentException if the col value is negative.
   */
  void setCol(int col) throws IllegalArgumentException;


  /**
   * Method that sets the red value of a Pixel.
   *
   * @param red the new red position
   * @throws IllegalArgumentException if the red value is negative or over 255.
   */
  void setR(int red) throws IllegalArgumentException;


  /**
   * Method that sets the green value of a Pixel.
   *
   * @param green the new green position
   * @throws IllegalArgumentException if the green value is negative or over 255.
   */
  void setG(int green) throws IllegalArgumentException;

  /**
   * Method that sets the blue value of a Pixel.
   *
   * @param blue the new blue position
   * @throws IllegalArgumentException if the blue value is negative or over 255.
   */
  void setB(int blue) throws IllegalArgumentException;

}
