package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Filter;
import controller.commands.Brighten;
import controller.commands.Flip;
import controller.commands.Greyscale;
import controller.commands.ImageProcessorCommands;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Transform;
import model.IModel;

/**
 * Class for the controller that manipulates the images. The controller takes in a readable and
 * an image.
 */
public class ImageControllerImpl implements ImageController {

  private IModel model;
  private Readable input;

  private Appendable out;

  Map<String, Function<String[], ImageProcessorCommands>> availableCommands;


  /**
   * Constructor for the controller that takes in the model and a readable as an input of commands.
   * It throws an exception if the model or the readable is null.
   *
   * @param model the model which is a version history of a given image.
   * @param input the input of commands to do on image.
   * @throws IllegalArgumentException if the model or the readable is null.
   */
  public ImageControllerImpl(IModel model, Readable input, Appendable out)
          throws IllegalArgumentException {
    if (model == null || input == null || out == null) {
      throw new IllegalArgumentException("Given null model or input.");
    }

    this.model = model;
    this.input = input;
    this.availableCommands = new HashMap<>();
    this.availableCommands.put("save", arg -> new Save(arg));
    this.availableCommands.put("load", arg -> new Load(arg));
    this.availableCommands.put("red-component", arg -> new Greyscale(arg));
    this.availableCommands.put("green-component", arg -> new Greyscale(arg));
    this.availableCommands.put("blue-component", arg -> new Greyscale(arg));
    this.availableCommands.put("value-component", arg -> new Greyscale(arg));
    this.availableCommands.put("luma-component", arg -> new Greyscale(arg));
    this.availableCommands.put("intensity-component", arg -> new Greyscale(arg));
    this.availableCommands.put("horizontal-flip", arg -> new Flip(arg));
    this.availableCommands.put("vertical-flip", arg -> new Flip(arg));
    this.availableCommands.put("brighten", arg -> new Brighten(arg));
    this.availableCommands.put("blur", arg -> new Filter(arg));
    this.availableCommands.put("sharpen", arg -> new Filter(arg));
    this.availableCommands.put("greyscale", arg -> new Transform(arg));
    this.availableCommands.put("sepia", arg -> new Transform(arg));

  }

  public ImageControllerImpl(IModel model) {
    this (model, new InputStreamReader(System.in), System.out);
  }

  /**
   * Method that makes the mutations on the image.
   */
  @Override
  public void runCommand() throws IOException {
    Scanner scan = new Scanner(this.input);

    while (scan.hasNextLine()) {
      ImageProcessorCommands c;
      String in = scan.nextLine();
      String[] inputs = in.split(" ");

      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        return;
      }
      Function<String[], ImageProcessorCommands> cmd =
              availableCommands.getOrDefault(inputs[0], null);
      if (cmd == null) {
        throw new IllegalArgumentException("Please enter a valid command");
      } else {
        c = cmd.apply(inputs);
        c.run(this.model);
      }
    }
  }
}