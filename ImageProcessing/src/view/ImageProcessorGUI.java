package view;

/**
 * Interface for the view of the image processor gui.
 */
public interface ImageProcessorGUI {

  /**
   * Refreshes histogram panel when action is performed on an image.
   */
  void histoRefresh();


  /**
   * Updates the image in the gui by creating a new image.
   * @param fileName the name of the file that is being updated.
   */
  void updateImage(String fileName);


  /**
   * Method that works with the controller to find the path of the image given and be loaded in to
   * the panel.
   * @return path of the image of the device.
   */
  String importImage();

  public void updateHistogram();
}
