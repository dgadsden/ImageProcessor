package controller.commands;

import java.io.IOException;

import model.IModel;

import static model.ImageUtil.load;

/**
 * Class for the Load command that is used by the controller.
 */
public class Load implements ImageProcessorCommands {

  private final String version;
  private final String path;

  /**
   * Constructor for the load function in the controller. Which takes in an array of strings from
   * the controller.
   *
   * @param arg array of string arguments from the controller.
   */
  public Load(String[] arg) {
    this.path = arg[1];
    this.version = arg[2];
  }

  @Override
  public void run(IModel model) throws IOException {
    model.addToVersions(load(path, version));
  }
}

