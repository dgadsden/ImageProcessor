import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.IModel;

import model.ModelImpl;
import view.ImageProcessorGUI;
import view.ImageProcessorGUIImpl;

/**
 * Class for the main function of the image processor.
 */
public class Main {

  /**
   * The actual main method.
   *
   * @param args a list of arguments provided by the user.
   * @throws IOException if the argument is not one that is available.
   */
  public static void main(String[] args) throws IOException {
    IModel model = new ModelImpl(new ArrayList<>());
    Appendable ap = System.out;
    ImageController controller;

    ap.append("Welcome to Dejah's Image Processor to get started please load an image and choose " +
            "a way to run the program: -file, -text, application \n" +
            "\n" +
            "List of Commands: \n" +
            "load imagePath imageName \n" +
            "save imagePath imageName \n" +
            "brighten value currentImageName newImageName \n" +
            "vertical-flip currentImageName newImageName \n" +
            "horizontal-flip currentImageName newImageName \n" +
            "red-component currentImageName newImageName \n" +
            "green-component currentImageName newImageName \n" +
            "blue-component currentImageName newImageName \n" +
            "value-component currentImageName newImageName \n" +
            "intensity-component currentImageName newImageName \n" +
            "luma-component currentImageName newImageName \n" +
            "blur currentImageName newImageName \n" +
            "sharpen currentImageName newImageName \n" +
            "greyscale currentImageName newImageName \n" +
            "sepia currentImageName newImageName \n");

    if (args.length < 1) {
      ImageProcessorGUI gui = new ImageProcessorGUIImpl();
    } else if (args[0].equalsIgnoreCase("-text")) {
      Readable rd = new InputStreamReader(System.in);
      controller = new ImageControllerImpl(model, rd, ap);
      controller.runCommand();
    } else if (args[0].equalsIgnoreCase("-file")) {
      if (args.length < 2) {
        throw new IllegalArgumentException("Please supply the command file with the -script " +
                "command");
      }
      Readable rd = new FileReader(args[1]);
      controller = new ImageControllerImpl(model, rd, ap);
      controller.runCommand();
    } else {
      throw new IllegalArgumentException("Please enter '-script' + fileName \n" +
              "to edit an image using script, \n" +
              "-text to edit an image using text inputs or \n" +
              "simply run the jar for GUI interface.");
    }
  }
}

