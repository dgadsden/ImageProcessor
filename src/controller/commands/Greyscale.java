package controller.commands;

import java.io.IOException;

import model.IModel;
import model.Image;
import model.Pixel;

/**
 * Class for the Greyscale command that is used by the controller.
 */
public class Greyscale implements ImageProcessorCommands {

  private final String component;
  private final String version;

  /**
   * Constructor for the greyscale commands which take in an array of string from the controller.
   * @param arg array of string arguments from the controller.
   */
  public Greyscale(String[] arg) {
    this.component = arg[0];
    this.version = arg[2];
  }

  @Override
  public void run(IModel model) throws IOException {
    Image scaledImage = model.copyImage(version);
    int scale = 0;
    for (int i = 0; i < scaledImage.getImageHeight(); i++) {
      for (int j = 0; j < scaledImage.getImageWidth(); j++) {
        Pixel pixelAt = scaledImage.getPixel(i, j);

        switch (component) {
          case "red-component":
            scale = pixelAt.getR();
            break;

          case "green-component":
            scale = pixelAt.getG();
            break;

          case "blue-component":
            scale = pixelAt.getB();
            break;

          case "value-component":
            scale = Math.max(Math.max(pixelAt.getR(), pixelAt.getG()), pixelAt.getB());
            break;

          case "luma-component":
            scale =
                    (int) (pixelAt.getR() * 0.2126 + pixelAt.getG() * 0.7152
                            + pixelAt.getB() * 0.0722);
            break;

          case "intensity-component":
            scale = (pixelAt.getR() + pixelAt.getG() + pixelAt.getB()) / 3;
            break;
          default:
            throw new IllegalArgumentException("Not a valid Color Representation");
        }
        scaledImage.getPixel(i, j).setR(scale);
        scaledImage.getPixel(i, j).setG(scale);
        scaledImage.getPixel(i, j).setB(scale);

      }
    }
    model.addToVersions(scaledImage);
  }
}
