package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import controller.GUIController;
import controller.IGUIController;
import model.IModel;
import model.Image;
import model.ModelImpl;
import model.Pixel;

/**
 * Class for the view of a image processor gui and its methods.
 */
public class ImageProcessorGUIImpl extends JFrame implements ImageProcessorGUI {
  private final JPanel mainPanel;

  private final JPanel histogramPanel;

  private final JLabel ImageDisplay;

  private final IModel model;

  private final IGUIController guiController;

  private HistogramView redHistogram;

  private HistogramView greenHistogram;

  private HistogramView blueHistogram;

  private HistogramView intensityHistogram;

  /**
   * Constructor that initialize the data of the ImageProcessorGUIImpl.
   */
  public ImageProcessorGUIImpl() {
    model = new ModelImpl(new ArrayList<>());
    JFrame frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setResizable(true);
    mainPanel = new JPanel();
    guiController = new GUIController(this, model);
    ActionListener guiControllerListener = new GUIController(this, model);
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setPreferredSize(new Dimension(1000, 700));


    // a panel for the option buttons of the program.
    JPanel controlPanel = new JPanel();
    controlPanel.setBorder(BorderFactory.createTitledBorder("Editing Controls"));
    // use a box layout to put different panels on the screen
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
    controlPanel.setPreferredSize(new Dimension(200, 200));

    // put control panel on the right side of the main panel
    mainPanel.add(controlPanel, BorderLayout.EAST);

    // code for all the buttons in the program:

    // make a button for Importing images
    JPanel importFilePanel = new JPanel();
    // use flow layout to put the buttons in one col
    importFilePanel.setLayout(new FlowLayout());

    JButton importButton = new JButton("Import file");
    importButton.setPreferredSize(new Dimension(200, 15));
    importButton.setActionCommand("import-file");
    importButton.addActionListener(guiControllerListener);
    importFilePanel.add(importButton);
    ImageDisplay = new JLabel();
    importFilePanel.add(ImageDisplay);
    // add button to the control panel
    controlPanel.add(importFilePanel);

    // make a button for saving images
    JPanel savePanel = new JPanel();
    savePanel.setLayout(new FlowLayout());
    JButton saveButton = new JButton("Save file");
    saveButton.setPreferredSize(new Dimension(200, 15));
    saveButton.setActionCommand("save-file");
    saveButton.addActionListener(guiControllerListener);
    savePanel.add(saveButton);
    JLabel fileSaveDisplay = new JLabel();
    savePanel.add(fileSaveDisplay);
    controlPanel.add(savePanel);


    // make a button for flipping images vertically
    JPanel verticalFlipPanel = new JPanel();
    verticalFlipPanel.setLayout(new FlowLayout());
    JButton verticalFlipButton = new JButton("Vertical-Flip");
    verticalFlipButton.setPreferredSize(new Dimension(200, 15));
    verticalFlipButton.setActionCommand("vertical-flip");
    verticalFlipButton.addActionListener(guiControllerListener);
    verticalFlipPanel.add(verticalFlipButton);
    JLabel verticalFlipDisplay = new JLabel();
    verticalFlipPanel.add(verticalFlipDisplay);
    controlPanel.add(verticalFlipPanel);

    // make a button for flipping images horizontally
    JPanel horizontalFlipPanel = new JPanel();
    horizontalFlipPanel.setLayout(new FlowLayout());
    JButton horizontalFlipButton = new JButton("Horizontal-Flip");
    horizontalFlipButton.setPreferredSize(new Dimension(200, 15));
    horizontalFlipButton.setActionCommand("horizontal-flip");
    horizontalFlipButton.addActionListener(guiControllerListener);
    horizontalFlipPanel.add(horizontalFlipButton);
    JLabel horizontalFlipDisplay = new JLabel();
    horizontalFlipPanel.add(horizontalFlipDisplay);
    controlPanel.add(horizontalFlipPanel);


    // make a button for brightening images
    JPanel brightenPanel = new JPanel();
    brightenPanel.setLayout(new FlowLayout());
    JButton brightenButton = new JButton("Brightness");
    brightenButton.setPreferredSize(new Dimension(200, 15));
    brightenButton.setActionCommand("brighten");
    brightenButton.addActionListener(guiControllerListener);
    brightenPanel.add(brightenButton);
    JLabel brightenDisplay = new JLabel();
    brightenPanel.add(brightenDisplay);
    controlPanel.add(brightenPanel);


    // make a button for scaling using the red value
    JPanel redPanel = new JPanel();
    redPanel.setLayout(new FlowLayout());
    JButton redButton = new JButton("Red-Component");
    redButton.setPreferredSize(new Dimension(200, 15));
    redButton.setActionCommand("red-component");
    redButton.addActionListener(guiControllerListener);
    redPanel.add(redButton);
    JLabel redDisplay = new JLabel();
    redPanel.add(redDisplay);
    controlPanel.add(redPanel);


    // make a button for scaling using the green value
    JPanel greenPanel = new JPanel();
    greenPanel.setLayout(new FlowLayout());
    JButton greenButton = new JButton("Green-Component");
    greenButton.setPreferredSize(new Dimension(200, 15));
    greenButton.setActionCommand("green-component");
    greenButton.addActionListener(guiControllerListener);
    greenPanel.add(greenButton);
    JLabel greenDisplay = new JLabel();
    greenPanel.add(greenDisplay);
    controlPanel.add(greenPanel);


    // make a button for scaling using blue value
    JPanel bluePanel = new JPanel();
    bluePanel.setLayout(new FlowLayout());
    JButton blueButton = new JButton("Blue-Component");
    blueButton.setPreferredSize(new Dimension(200, 15));
    blueButton.setActionCommand("blue-component");
    blueButton.addActionListener(guiControllerListener);
    bluePanel.add(blueButton);
    JLabel blueDisplay = new JLabel();
    bluePanel.add(blueDisplay);
    controlPanel.add(bluePanel);


    // make a button for scaling using the value component
    JPanel valuePanel = new JPanel();
    valuePanel.setLayout(new FlowLayout());
    JButton valueButton = new JButton("Value");
    valueButton.setPreferredSize(new Dimension(200, 15));
    valueButton.setActionCommand("value-component");
    valueButton.addActionListener(guiControllerListener);
    valuePanel.add(valueButton);
    JLabel valueDisplay = new JLabel();
    valuePanel.add(valueDisplay);
    controlPanel.add(valuePanel);


    // make a button for scaling using the intensity value
    JPanel intensityPanel = new JPanel();
    intensityPanel.setLayout(new FlowLayout());
    JButton intensityButton = new JButton("Intensity");
    intensityButton.setPreferredSize(new Dimension(200, 15));
    intensityButton.setActionCommand("intensity-component");
    intensityButton.addActionListener(guiControllerListener);
    intensityPanel.add(intensityButton);
    JLabel intensityDisplay = new JLabel();
    intensityPanel.add(intensityDisplay);
    controlPanel.add(intensityPanel);


    // make a button for scaling using the luma value
    JPanel lumaPanel = new JPanel();
    lumaPanel.setLayout(new FlowLayout());
    JButton lumaButton = new JButton("Luma");
    lumaButton.setPreferredSize(new Dimension(200, 15));
    lumaButton.setActionCommand("luma-component");
    lumaButton.addActionListener(guiControllerListener);
    lumaPanel.add(lumaButton);
    JLabel lumaDisplay = new JLabel();
    lumaPanel.add(lumaDisplay);
    controlPanel.add(lumaPanel);


    // make a button for blurring an image
    JPanel blurPanel = new JPanel();
    blurPanel.setLayout(new FlowLayout());
    JButton blurButton = new JButton("Blur");
    blurButton.setPreferredSize(new Dimension(200, 15));
    blurButton.setActionCommand("blur");
    blurButton.addActionListener(guiControllerListener);
    blurPanel.add(blurButton);
    JLabel blurDisplay = new JLabel();
    blurPanel.add(blurDisplay);
    controlPanel.add(blurPanel);


    // make a button for sharpening an image
    JPanel sharpenPanel = new JPanel();
    sharpenPanel.setLayout(new FlowLayout());
    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setPreferredSize(new Dimension(200, 15));
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.addActionListener(guiControllerListener);
    sharpenPanel.add(sharpenButton);
    JLabel sharpenDisplay = new JLabel();
    sharpenPanel.add(sharpenDisplay);
    controlPanel.add(sharpenPanel);


    // make a button for using a sepia filter on an image
    JPanel sepiaPanel = new JPanel();
    sepiaPanel.setLayout(new FlowLayout());
    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setPreferredSize(new Dimension(200, 15));
    sepiaButton.setActionCommand("sepia");
    sepiaButton.addActionListener(guiControllerListener);
    sepiaPanel.add(sepiaButton);
    JLabel sepiaDisplay = new JLabel();
    sepiaPanel.add(sepiaDisplay);
    controlPanel.add(sepiaPanel);

    // grey scales an image
    JPanel greyscalePanel = new JPanel();
    greyscalePanel.setLayout(new FlowLayout());
    JButton greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setPreferredSize(new Dimension(200, 15));
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.addActionListener(guiControllerListener);
    greyscalePanel.add(greyscaleButton);
    JLabel greyscaleDisplay = new JLabel();
    greyscalePanel.add(greyscaleDisplay);
    controlPanel.add(greyscalePanel);

    // makes a scroll panel for the images to be shown in
    JScrollPane imageScrollPanel = new JScrollPane(ImageDisplay);
    imageScrollPanel.setBorder(BorderFactory.createTitledBorder("Current Image"));
    imageScrollPanel.setPreferredSize(new Dimension(500, 500));
    mainPanel.add(imageScrollPanel);


    //panel for the color histograms of an image
    histogramPanel = new JPanel();
    histogramPanel.getHeight();
    JScrollPane histogramView = new JScrollPane(histogramPanel);

    // Create the four histograms
    redHistogram = new HistogramView(new int[0], Color.RED);
    greenHistogram = new HistogramView(new int[0], Color.GREEN);
    blueHistogram = new HistogramView(new int[0], Color.BLUE);
    intensityHistogram = new HistogramView(new int[0], Color.GRAY);
    // add them to the panel
    histogramPanel.add(redHistogram);
    histogramPanel.add(greenHistogram);
    histogramPanel.add(blueHistogram);
    histogramPanel.add(intensityHistogram);

    histogramView.setBorder(BorderFactory.createTitledBorder("Image Color Histogram"));
    // put the histogram view at the bottom of the screen
    mainPanel.add(histogramView, BorderLayout.SOUTH);

    frame.setVisible(true);
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Dejah's Image Processor");
    frame.pack();
    repaint();
    revalidate();
    histoRefresh();


  }

  @Override
  public void histoRefresh() {
    histogramPanel.repaint();
    histogramPanel.revalidate();
  }


  @Override
  public void updateImage(String fileName) {
    Image copyImage = new Image(model.getImagePixels(fileName), fileName);

    BufferedImage displayedImage = new BufferedImage(copyImage.getImageWidth(),
            copyImage.getImageHeight(), BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < copyImage.getImageHeight(); i++) {
      for (int j = 0; j < copyImage.getImageWidth(); j++) {
        Pixel newPixel = copyImage.getPixel(i, j);
        int redValue = newPixel.getR();
        int greenValue = newPixel.getG();
        int blueValue = newPixel.getB();

        Color color = new Color(redValue, greenValue, blueValue);
        displayedImage.setRGB(j, i, color.getRGB());
      }
    }

    ImageDisplay.setIcon(new ImageIcon(displayedImage));
    mainPanel.repaint();
    histoRefresh();
  }

  @Override
  public String importImage() {

    final JFileChooser fc = new JFileChooser(".");
    this.add(fc);

    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      this.histoRefresh();
      return file.getAbsolutePath();
    }
    this.histoRefresh();
    return "";
  }


  @Override
  public void updateHistogram() {
    // remove old histogram.
    histogramPanel.remove(redHistogram);
    histogramPanel.remove(greenHistogram);
    histogramPanel.remove(blueHistogram);
    histogramPanel.remove(intensityHistogram);

    //re-evaluate histogramValues
    redHistogram = new HistogramView(guiController.getImageHistogramValues("red"), Color.RED);
    greenHistogram = new HistogramView(guiController.getImageHistogramValues("green"), Color.GREEN);
    blueHistogram = new HistogramView(guiController.getImageHistogramValues("blue"), Color.BLUE);
    intensityHistogram = new HistogramView(guiController.getImageHistogramValues("intensity"),
            Color.GRAY);
    // add the new histograms.
    histogramPanel.add(redHistogram);
    histogramPanel.add(greenHistogram);
    histogramPanel.add(blueHistogram);
    histogramPanel.add(intensityHistogram);
    repaint();
    revalidate();
    histoRefresh();
  }
}
