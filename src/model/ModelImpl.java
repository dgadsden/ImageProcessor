package model;

import java.util.List;


/**
 * Class for the methods of an Image processing model.
 */
public class ModelImpl implements IModel {
  private final List<Image> versions;

  /**
   * Constructor of model of the image processor that takes in an arraylist of images.
   * @param versions an arraylist of images saved through the processor.
   * @throws IllegalArgumentException if the version is null.
   */
  public ModelImpl(List<Image> versions) throws IllegalArgumentException {
    if (versions == null) {
      throw new IllegalArgumentException("Image versions can not be null");
    }
    this.versions = versions;
  }


  @Override
  public Image copyImage(String name) {
    Image copies = null;
    for (Image i : versions) {

      copies = new Image(i.copyPixels(), name);
    }
    return copies;
  }

  @Override
  public Pixel[][] getImagePixels(String name) {
    Pixel[][] pixels = new Pixel[0][];
    for (int i = 0; i < versions.size(); i++) {
      String s = versions.get(i).getImageName();
      if (name.equals(s)) {
        pixels = versions.get(i).copyPixels();
      }
    }
    return pixels;
  }

  @Override
  public void addToVersions(Image image) {
    this.versions.add(image);
  }

}
