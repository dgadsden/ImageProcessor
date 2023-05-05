package controller.commands;

import java.io.IOException;

import model.IModel;
import model.Image;
import model.Pixel;

/**
 * Class for the color transforming methods. Includes greyscale and sepia
 */
public class Transform implements ImageProcessorCommands {

  private final String type;
  private final String version;

  /**
   * Constructor for a color transformation. Includes greyscale and sepia.
   *
   * @param arg list of string arguments that are given through the controller.
   */
  public Transform(String[] arg) {
    this.type = arg[0];
    this.version = arg[2];

  }

  @Override
  public void run(IModel model) throws IOException {
    Pixel[][] array;
    switch (type) {
      case "greyscale":

        double[][] greyscaleKernel = new double[][]{
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722}};

        array = transform(model, greyscaleKernel);

        break;

      case "sepia":

        double[][] sepiaKernel = new double[][]{
                {0.393, 0.769, 0.189},
                {0.349, 0.686, 0.168},
                {0.272, 0.534, 0.131}};

        array = transform(model, sepiaKernel);

        break;
      default:
        throw new IllegalArgumentException("Not a valid filter type");
    }
    model.addToVersions(new Image(array, version));
  }

  protected Pixel[][] transform(IModel model, double[][] kernel) {
    Image copyImage = model.copyImage(version);
    Pixel[][] pixels = copyImage.copyPixels();
    for (int row = 0; row < copyImage.getImageHeight(); row++) {
      for (int col = 0; col < copyImage.getImageWidth(); col++) {

        double[] newPixels = new double[3];


        newPixels[0] = kernel[0][0] * pixels[row][col].getR() +
                kernel[0][1] * pixels[row][col].getG() +
                kernel[0][2] * pixels[row][col].getB();
        newPixels[1] = kernel[1][0] * pixels[row][col].getR() +
                kernel[1][1] * pixels[row][col].getG() +
                kernel[1][2] * pixels[row][col].getB();
        newPixels[2] = kernel[2][0] * pixels[row][col].getR() +
                kernel[2][1] * pixels[row][col].getG() +
                kernel[2][2] * pixels[row][col].getB();


        pixels[row][col].setR(clamp((int) newPixels[0]));
        pixels[row][col].setG(clamp((int) newPixels[1]));
        pixels[row][col].setB(clamp((int) newPixels[2]));
      }
    }

    return pixels;
  }

  // clamp for the rgb values
  private int clamp(int value) {
    if (value > 255) {
      value = 255;
    } else if (value < 0) {
      value = 0;
    }
    return value;
  }
}
