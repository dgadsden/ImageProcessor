package controller;


/**
 * Interface to the controller for the GUI controller.
 */
public interface IGUIController {
  /**
   * Method thqt gets the histogram values of an image.
   *
   * @param color the colors of an image.
   * @return an array of color values of the image.
   */
  int[] getImageHistogramValues(String color);


}
