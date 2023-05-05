package controller;

import java.io.IOException;

/**
 * Interface for the methods used for the controller as custom ActionEvents.
 */
public interface GUIEvents {

  /**
   * An event that loads the image.
   *
   * @param imageName the name of the image.
   * @throws IOException if the image cannot be loaded.
   */

  public void loadEvent(String imageName) throws IOException;

  /**
   * An event that saves the image.
   *
   * @param imageName the name of the image.
   * @throws IOException If saving doesn't work
   */
  public void saveEvent(String imageName) throws IOException;


  /**
   * An event that flips the image horizontally.
   *
   * @param imageName imageName the name of the image.
   */
  public void flipHorizEvent(String imageName) throws IOException;

  /**
   * An event that flips the image vertically.
   *
   * @param imageName ImageName the name of the image.
   */
  public void flipVertEvent(String imageName) throws IOException;

  /**
   * An event that brightens an image.
   *
   * @param amount    the value by which the image will be brightened or darkened.
   * @param imageName the name of the image.
   * @throws IOException if there is an error brightening the image.
   */
  public void brightenEvent(int amount, String imageName) throws IOException;

  /**
   * An event that greyscales an image using the red component.
   *
   * @param imageName the name of the image.
   * @throws IOException if there is an error in scaling the image.
   */
  public void redComponentEvent(String imageName) throws IOException;

  /**
   * An event that greyscales an image using the green component.
   *
   * @param imageName the name of the image.
   * @throws IOException if there is an error in scaling the image.
   */
  public void greenComponentEvent(String imageName) throws IOException;


  /**
   * An event that greyscales an image using the blue component.
   *
   * @param imageName the name of the image.
   * @throws IOException if there is an error in scaling the image.
   */
  public void blueComponentEvent(String imageName) throws IOException;


  /**
   * An event that greyscales an image using the value component.
   *
   * @param imageName the name of the image.
   * @throws IOException if there is an error in scaling the image.
   */
  public void valueComponentEvent(String imageName) throws IOException;

  /**
   * An event that greyscales an image using the intensity component.
   *
   * @param imageName the name of the image.
   * @throws IOException if there is an error in scaling the image.
   */
  public void intensityComponentEvent(String imageName) throws IOException;

  /**
   * An event that greyscales an image using the luma component.
   *
   * @param imageName the name of the image.
   * @throws IOException if there is an error in scaling the image.
   */
  public void lumaComponentEvent(String imageName) throws IOException;

  /**
   * An event that blurs an image.
   *
   * @param imageName the name of an image.
   * @throws IOException if there is an error in blurring the image.
   */
  public void blurEvent(String imageName) throws IOException;

  /**
   * An event that sharpens an image.
   *
   * @param imageName the name of an image.
   * @throws IOException if there is an error in sharpens the image.
   */
  public void sharpenEvent(String imageName) throws IOException;

  /**
   * An even that applies a sepia filter on an image.
   *
   * @param imageName the name of the image.
   * @throws IOException if there is an image in the filter being applied.
   */
  public void sepiaEvent(String imageName) throws IOException;

  /**
   * An even that applies a greyscale filter on an image.
   *
   * @param imageName the name of the image.
   * @throws IOException if there is an image in the filter being applied.
   */
  public void greyscaleEvent(String imageName) throws IOException;

}
