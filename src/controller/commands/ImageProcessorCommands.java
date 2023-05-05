package controller.commands;

import java.io.IOException;

import model.IModel;

/**
 * Interface for the commands of an image processor.
 */
public interface ImageProcessorCommands {
  /**
   * Methods that performs the given command on a model.
   *
   * @param model model that will be manipulated.
   * @throws IOException if there is an error in transmitting the image.
   */
  void run(IModel model) throws IOException;
}
