package controller.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import model.IModel;
import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * Class for the Save command that is used by the controller.
 */
public class Save implements ImageProcessorCommands {

  private final String version;
  private final String path;

  /**
   * Constructor for the save command of the controller. Takes in an array of string arguments
   * from the controller.
   *
   * @param arg array of string arguments from the controller.
   */
  public Save(String[] arg) {
    this.path = arg[1];
    this.version = arg[2];
  }

  @Override
  public void run(IModel model) throws IOException {
    Image savedImage = model.copyImage(version);
    PrintWriter writer;
    if (path.endsWith(".ppm")) {
      try {
        writer = new PrintWriter(path);
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("File does not exist.");
      }
      writer.write("P3\n");

      writer.write(savedImage.getImageWidth() + " " + savedImage.getImageHeight() + "\n");
      writer.write(255 + "\n");
      for (int i = 0; i < savedImage.getImageHeight(); i++) {
        for (int j = 0; j < savedImage.getImageWidth(); j++) {
          IPixel pixelAt = savedImage.getPixel(i, j);
          writer.write(pixelAt.getR() + "\n");
          writer.write(pixelAt.getG() + "\n");
          writer.write(pixelAt.getB() + "\n");
        }
      }
      writer.close();
    } else {
      String ex = path.substring(path.indexOf(".") + 1);
      BufferedImage bufferedImage = new BufferedImage(savedImage.getImageWidth(),
              savedImage.getImageHeight(), BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < savedImage.getImageHeight(); i++) {
        for (int j = 0; j < savedImage.getImageWidth(); j++) {
          Pixel pix = savedImage.getPixel(i, j);
          Color color = new Color(pix.getR(), pix.getG(), pix.getB());
          bufferedImage.setRGB(j, i, color.getRGB());
        }
      }
      File out = new File(path);
      try {
        ImageIO.write(bufferedImage, ex, out);
      } catch (FileNotFoundException e) {
        throw new FileNotFoundException("File cannot be read.");
      }
    }
  }
}
