package controller;

import java.io.IOException;

/**
 * Interface for the controllers for the image processor. The controller takes in an input and
 * transmits an image with the given manipulations performed.
 */
public interface ImageController {

  /**
   * Runs the commands on the image.
   *
   * @throws IOException if the command given is not supported.
   */
  void runCommand() throws IOException;

}
