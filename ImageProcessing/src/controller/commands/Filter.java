package controller.commands;

import java.io.IOException;

import model.IModel;
import model.Image;
import model.Pixel;

/**
 * Abstract class to reduce the code duplication for all fliter types.
 */
public class Filter implements ImageProcessorCommands {

  private final String type;
  private final String version;

  /**
   * Constructor for a color Filter. Includes blur and sharpten.
   *
   * @param arg list of string arguments that are given through the controller.
   */
  public Filter(String[] arg) {
    this.type = arg[0];
    this.version = arg[2];
  }

  @Override
  public void run(IModel model) throws IOException {
    Image filteredImg = model.copyImage(version);
    Pixel[][] array = new Pixel[0][];
    for (int i = 0; i < filteredImg.getImageHeight(); i++) {
      for (int j = 0; j < filteredImg.getImageWidth(); j++) {
        switch (type) {
          case "blur":
            double[][] blurKernel = {
                    {0.0625, 0.125, 0.0625},
                    {0.125, 0.25, 0.125},
                    {0.0625, 0.125, 0.0625}};

            array = filter(model, blurKernel);

            break;
          case "sharpen":

            double[][] sharpenKernel = new double[][]{
                    {-0.125, -0.125, -0.125, -0.125, -0.125},
                    {-0.125, 0.25, 0.25, 0.25, -0.125},
                    {-0.125, 0.25, 1.00, 0.25, -0.125},
                    {-0.125, 0.25, 0.25, 0.25, -0.125},
                    {-0.125, -0.125, -0.125, -0.125, -0.125}};
            array = filter(model, sharpenKernel);
            break;
          default:
            throw new IllegalArgumentException("Not a valid filter");
        }
      }
    }
    model.addToVersions(new Image(array, this.version));
  }

  private Pixel[][] filter(IModel model, double[][] kernel) {
    Image copyImage = model.copyImage(version);
    Pixel[][] imagePixels = copyImage.copyPixels();
    for (int row = 0; row < copyImage.getImageHeight(); row++) {
      for (int col = 0; col < copyImage.getImageWidth(); col++) {

        double[] newPixels = new double[3];
        newPixels[0] = kernel[0][0] * imagePixels[row][col].getR() +
                kernel[0][1] * imagePixels[row][col].getG() +
                kernel[0][2] * imagePixels[row][col].getB();
        newPixels[1] = kernel[1][0] * imagePixels[row][col].getR() +
                kernel[1][1] * imagePixels[row][col].getG() +
                kernel[1][2] * imagePixels[row][col].getB();
        newPixels[2] = kernel[2][0] * imagePixels[row][col].getR() +
                kernel[2][1] * imagePixels[row][col].getG() +
                kernel[2][2] * imagePixels[row][col].getB();

        if ((int) newPixels[0] > 255) {
          newPixels[0] = 255;
        } else if (newPixels[0] < 0) {
          newPixels[0] = 0;
        }

        if ((int) newPixels[1] > 255) {
          newPixels[1] = 255;
        } else if (newPixels[1] < 0) {
          newPixels[1] = 0;
        }

        if ((int) newPixels[2] > 255) {
          newPixels[2] = 255;
        } else if (newPixels[2] < 0) {
          newPixels[2] = 0;
        }

        imagePixels[row][col].setR((int) newPixels[0]);
        imagePixels[row][col].setG((int) newPixels[1]);
        imagePixels[row][col].setB((int) newPixels[2]);
      }
    }

    return imagePixels;
  }
}

