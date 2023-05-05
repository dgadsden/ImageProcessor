package controller.commands;

import java.io.IOException;

import model.IModel;
import model.Image;
import model.Pixel;

/**
 * Class for the Flip command that is used by the controller.
 */
public class Flip implements ImageProcessorCommands {

  private final String version;
  private final String direction;

  /**
   * Constructor for the flip commands of the controller. Takes in an array of strings from the
   * controller.
   *
   * @param arg an array of strings from the controller.
   */
  public Flip(String[] arg) {
    this.direction = arg[0];
    this.version = arg[2];
  }

  @Override
  public void run(IModel model) throws IOException {
    Image flippedImage = model.copyImage(version);
    Pixel[][] pixelCopy = flippedImage.copyPixels();
    switch (direction) {
      case "vertical-flip":

        Pixel temp;
        for (int i = 0; i < pixelCopy.length / 2; i++) {
          for (int j = 0; j < pixelCopy[i].length; j++) {
            temp = pixelCopy[i][j];
            pixelCopy[i][j] = pixelCopy[pixelCopy.length - 1 - i][j];
            pixelCopy[pixelCopy.length - 1 - i][j] = temp;
          }
        }

        break;
      case "horizontal-flip":

        for (int i = 0; i < pixelCopy.length; i++) {
          for (int j = 0; j < pixelCopy[i].length / 2; j++) {
            temp = pixelCopy[i][j];
            pixelCopy[i][j] = pixelCopy[i][pixelCopy[i].length - 1 - j];
            pixelCopy[i][pixelCopy[i].length - 1 - j] = temp;
          }
        }

        break;
      default:
        throw new IllegalArgumentException("Not a valid direction");
    }
    model.addToVersions(new Image(pixelCopy, version));
  }
}

