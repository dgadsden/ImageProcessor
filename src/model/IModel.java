package model;

/**
 * Interface for the methods that images should be able to use.
 */
public interface IModel {

  // Refactored to move I/O processes out of model and into controller.
  // Old methods included, imageLoad, imageSave, greyscale, imageFlip, imageBrightness.


  /**
   * Method that copies an image in the model.
   * @param name given to the copy.
   * @return the array of an image with a given name.
   */
  Image copyImage(String name);

  /**
   * Method that copies the array of pixels of an image.
   * @param name the name of the image being copied.
   * @return An array of pixels that is the same as the original image.
   */
  Pixel[][] getImagePixels(String name);

  /**
   * Methods that adds a given image to the versions of a model.
   */
  void addToVersions(Image image);
}
