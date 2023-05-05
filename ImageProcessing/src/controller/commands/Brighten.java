package controller.commands;

import java.io.IOException;

import model.IModel;
import model.Image;

import static java.lang.Integer.parseInt;

/**
 * Class for the Brighten command that is used by the controller.
 */
public class Brighten implements ImageProcessorCommands {
  private final int value;

  private final String version;

  /**
   * Constructor for a color brighten. Includes greyscale and sepia.
   *
   * @param arg list of string arguments that are given through the controller.
   */
  public Brighten(String[] arg) {
    this.value = parseInt(arg[1]);
    this.version = arg[3];
  }

  @Override
  public void run(IModel model) throws IOException {
    Image brightenedImage = model.copyImage(version);
    for (int i = 0; i < brightenedImage.getImageHeight(); i++) {
      for (int j = 0; j < brightenedImage.getImageWidth(); j++) {
        int brightenedR = brightenedImage.getPixel(i, j).getR() + value;
        int brightenedG = brightenedImage.getPixel(i, j).getG() + value;
        int brightenedB = brightenedImage.getPixel(i, j).getB() + value;

        brightenedImage.getPixel(i, j).setR(brightnessClamp(brightenedR));
        brightenedImage.getPixel(i, j).setG(brightnessClamp(brightenedG));
        brightenedImage.getPixel(i, j).setB(brightnessClamp(brightenedB));
      }
    }
    model.addToVersions(brightenedImage);
  }

  /**
   * Method that makes sure the rbg values stay between 0 and 255.
   * @param value given the RGB values.
   * @return the given RBG values between the max and min.
   */
  private int brightnessClamp(int value) {
    if (value < 0) {
      value = 0;
    } else if (value > 255) {
      value = 255;
    }
    return value;
  }
}

