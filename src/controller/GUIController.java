package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;


import controller.commands.Brighten;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.Greyscale;
import controller.commands.ImageProcessorCommands;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Transform;
import model.IImage;
import model.IModel;
import model.Pixel;
import view.ImageProcessorGUI;

/**
 * Controller for the GUI version of the image processor program.
 */
public class GUIController implements IGUIController, GUIEvents, ActionListener {

  private final ImageProcessorGUI view;

  private final IModel model;

  /**
   * Constructor for the controller of the GUI version of the program takes in a view and model.
   *
   * @param view  a ImageProcessorGUI.
   * @param model a IModel.
   */
  public GUIController(ImageProcessorGUI view, IModel model) {
    if (view == null | model == null) {
      throw new IllegalArgumentException("view or model cannot be null.");
    }
    this.view = view;
    this.model = model;
  }


  /**
   * Override method to takes in the created actionEvents and add interaction the gui.
   * Reacts to button presses and keyboard actions.
   *
   * @param guiEvent the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent guiEvent) {
    switch (guiEvent.getActionCommand()) {
      case "import-file": {
        try {
          loadEvent("currentImage");
        } catch (NullPointerException | IOException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "save-file": {
        try {
          saveEvent("currentImage");
        } catch (NullPointerException | IOException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "vertical-flip": {
        try {
          flipVertEvent("currentImage");
        } catch (NullPointerException | IOException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "horizontal-flip": {
        try {
          flipHorizEvent("currentImage");
        } catch (NullPointerException | IOException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "brighten": {
        JLabel inputBox = new JLabel();
        inputBox.setText(JOptionPane.showInputDialog("Brighten with positive ints and Darken " +
                "with negative"));
        try {
          try {
            brightenEvent(Integer.parseInt(inputBox.getText()), "currentImage");
          } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Please Provide a Value to Brighten or Darken By!",
                    "No Value Given: ", JOptionPane.ERROR_MESSAGE);
          }
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "red-component": {
        try {
          redComponentEvent("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "green-component": {
        try {
          greenComponentEvent("currentImage");
          view.updateImage("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "blue-component": {
        try {
          blueComponentEvent("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "value-component": {
        try {
          valueComponentEvent("currentImage");
          view.updateImage("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "intensity-component": {
        try {
          intensityComponentEvent("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "luma-component": {
        try {
          lumaComponentEvent("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "blur": {
        try {
          blurEvent("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "sharpen": {
        try {
          sharpenEvent("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "sepia": {
        try {
          sepiaEvent("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      case "greyscale": {
        try {
          greyscaleEvent("currentImage");
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "Please Import an Image!",
                  "No Image Found: ", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
          throw new RuntimeException();
        }
        view.updateImage("currentImage");
        view.updateHistogram();
      }
      break;
      default:
        throw new RuntimeException("action took too long.");
    }
  }

  // ActionEvents that use the previously created function objects.

  @Override
  public void loadEvent(String imageName) throws IOException {
    String[] args = new String[]{"load", view.importImage(), imageName};
    ImageProcessorCommands loadFunctionObj = new Load(args);
    loadFunctionObj.run(this.model);
  }


  @Override
  public void saveEvent(String imageName) throws IOException {
    final JFileChooser fileChooser = new JFileChooser(".");
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String[] args = new String[]{"save", file.getAbsolutePath(), imageName};
      ImageProcessorCommands saveFunctionObj = new Save(args);
      saveFunctionObj.run(this.model);
    }
  }

  @Override
  public void flipHorizEvent(String imageName) throws IOException {
    String[] args = new String[]{"horizontal-flip", "old-name", imageName};
    ImageProcessorCommands flipHorizFunctionObj = new Flip(args);
    flipHorizFunctionObj.run(this.model);
  }

  @Override
  public void flipVertEvent(String imageName) throws IOException {
    String[] args = new String[]{"vertical-flip", "old-name", imageName};
    ImageProcessorCommands flipHorizFunctionObj = new Flip(args);
    flipHorizFunctionObj.run(this.model);
  }

  @Override
  public void brightenEvent(int amount, String imageName) throws IOException {
    String[] args = new String[]{"brighten", Integer.toString(amount), "old-name", imageName};
    ImageProcessorCommands brightenFunctionObj = new Brighten(args);
    brightenFunctionObj.run(this.model);

  }

  @Override
  public void redComponentEvent(String imageName) throws IOException {
    String[] args = new String[]{"red-component", "old-name", imageName};
    ImageProcessorCommands redFunctionObj = new Greyscale(args);
    redFunctionObj.run(this.model);
  }

  @Override
  public void greenComponentEvent(String imageName) throws IOException {
    String[] args = new String[]{"green-component", "old-name", imageName};
    ImageProcessorCommands greenFunctionObj = new Greyscale(args);
    greenFunctionObj.run(this.model);
  }

  @Override
  public void blueComponentEvent(String imageName) throws IOException {
    String[] args = new String[]{"blue-component", "old-name", imageName};
    ImageProcessorCommands blueFunctionObj = new Greyscale(args);
    blueFunctionObj.run(this.model);
  }

  @Override
  public void valueComponentEvent(String imageName) throws IOException {
    String[] args = new String[]{"value-component", "old-name", imageName};
    ImageProcessorCommands valueFunctionObj = new Greyscale(args);
    valueFunctionObj.run(this.model);
  }

  @Override
  public void intensityComponentEvent(String imageName) throws IOException {
    String[] args = new String[]{"intensity-component", "old-name", imageName};
    ImageProcessorCommands intensityFunctionObj = new Greyscale(args);
    intensityFunctionObj.run(this.model);
  }

  @Override
  public void lumaComponentEvent(String imageName) throws IOException {
    String[] args = new String[]{"luma-component", "old-name", imageName};
    ImageProcessorCommands lumaFunctionObj = new Greyscale(args);
    lumaFunctionObj.run(this.model);
  }

  @Override
  public void blurEvent(String imageName) throws IOException {
    String[] args = new String[]{"blur", "old-name", imageName};
    ImageProcessorCommands blurFunctionObj = new Filter(args);
    blurFunctionObj.run(this.model);


  }


  @Override
  public void sharpenEvent(String imageName) throws IOException {
    String[] args = new String[]{"sharpen", "old-name", imageName};
    ImageProcessorCommands sharpenFunctionObj = new Filter(args);
    sharpenFunctionObj.run(this.model);

  }

  @Override
  public void sepiaEvent(String imageName) throws IOException {
    String[] args = new String[]{"sepia", "old-name", imageName};
    ImageProcessorCommands sepiaFunctionObj = new Transform(args);
    sepiaFunctionObj.run(this.model);

  }

  @Override
  public void greyscaleEvent(String imageName) throws IOException {
    String[] args = new String[]{"greyscale", "old-name", imageName};
    ImageProcessorCommands greyFunctionObj = new Transform(args);
    greyFunctionObj.run(this.model);

  }


  @Override
  public int[] getImageHistogramValues(String color) {
    IImage copyImage = model.copyImage("currentImage");
    int size = Objects.requireNonNull(copyImage.getImageHeight() * copyImage.getImageWidth());
    int[] histValues;
    histValues = new int[size];
    switch (color) {
      case "red":
        for (int s = 0; s < size; ) {
          for (int i = 0; i < copyImage.getImageHeight(); i++) {
            for (int j = 0; j < copyImage.getImageWidth(); j++) {
              Pixel current = copyImage.getPixel(i, j);
              int redVal = current.getR();
              histValues[s] = redVal;
              // iteration has to go inside because it takes to long to run on outside
              s++;
            }
          }
        }
        break;
      case "green":
        for (int s = 0; s < size; ) {
          for (int i = 0; i < copyImage.getImageHeight(); i++) {
            for (int j = 0; j < copyImage.getImageWidth(); j++) {
              Pixel current = copyImage.getPixel(i, j);
              int greenVal = current.getG();
              histValues[s] = greenVal;
              s++;
            }
          }
        }
        break;
      case "blue":
        for (int s = 0; s < size; ) {
          for (int i = 0; i < copyImage.getImageHeight(); i++) {
            for (int j = 0; j < copyImage.getImageWidth(); j++) {
              Pixel current = copyImage.getPixel(i, j);
              int blueVal = current.getB();
              histValues[s] = blueVal;
              s++;
            }
          }
        }
        break;
      case "intensity":
        for (int s = 0; s < size; ) {
          for (int i = 0; i < copyImage.getImageHeight(); i++) {
            for (int j = 0; j < copyImage.getImageWidth(); j++) {
              Pixel current = copyImage.getPixel(i, j);
              int intensityValue = Math.max(Math.max(current.getR(), current.getG()),
                      current.getB());
              histValues[i] = intensityValue;
              s++;
            }
          }
        }
        break;
      default:
        throw new IllegalArgumentException("Invalid Argument Given");
    }
    return histValues;
  }

}
