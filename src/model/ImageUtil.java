package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Pixel[][] readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return new Pixel[0][];
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    Pixel[][] image = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        image[i][j] = new Pixel(j, i, r, g, b);
      }
    }
    return image;
  }

  /**
   * Read an image file in any format and print the colors. A new method that inherits the
   * readPPM file in order to read PPM files along with other more popular image types.
   * refactored to build the array the right way around.
   *
   * @param filename the path of the file.
   */
  public static Pixel[][] readImages(String filename) {
    FileInputStream file;
    BufferedImage input;

    if (filename.endsWith(".ppm")) {
      return readPPM(filename);
    } else {
      try {
        file = new FileInputStream(filename);
      } catch (FileNotFoundException e) {
        System.out.println("File " + filename + " not found!");
        return new Pixel[0][];
      }

      try {
        input = ImageIO.read(file);
        file.close();
      } catch (IOException e) {
        System.out.println("File cannot be read.");
        return null;
      }


      int width = input.getWidth();
      System.out.println("Width of image: " + width);
      int height = input.getHeight();
      System.out.println("Height of image: " + height);

      Pixel[][] image = new Pixel[height][width];

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          int rgb = input.getRGB(i, j);
          Color pixColor = new Color(rgb);
          System.out.println("Color of pixel (" + j + "," + i + "): " + pixColor.getRed() + ","
                  + pixColor.getGreen() + "," + pixColor.getBlue());
          image[j][i] = new Pixel(j, i, pixColor.getRed(), pixColor.getGreen(), pixColor.getBlue());
        }
      }
      return image;
    }
  }

  // Load method was refactored to use the readImages method instead of the readPPM method.
  public static Image load(String path, String name) {
    return new Image(readImages(path), name);

  }


}

