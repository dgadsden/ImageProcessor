import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.IModel;
import model.Image;
import model.ImageUtil;
import model.ModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;


/**
 * Class for the test of the controller methods.
 */
public class ImageProcessorControllerTest {

  Readable redChangeReadable;
  Readable blueChangeReadable;

  Readable greenChangeReadable;

  Readable valueChangeReadable;

  Readable intensityChangeReadable;

  Readable lumaChangeReadable;

  Readable horizontalFlipReadable;


  Readable verticalFlipReadable;

  Readable brightnessReadableBright;

  Readable brightnessReadableDim;

  Readable blurReadable;

  Readable sharpenReadable;

  Readable greyscaleReadable;

  Readable sepiaReadable;

  Appendable appendable1;

  ArrayList<Image> version1;

  Image sixColor;

  Image twoColorVert;
  Image twoColorHoriz;

  Image fourColorPNG;

  Image fourColorJPEG;


  IModel colorModel1;


  @Before
  public void initData() {
    redChangeReadable = new StringReader("red-component 6Color 6ColorRed");
    blueChangeReadable = new StringReader("blue-component 6Color 6ColorBlue");
    greenChangeReadable = new StringReader("green-component 6Color 6ColorGreen");
    valueChangeReadable = new StringReader("value-component 6Color 6ColorValue");
    intensityChangeReadable = new StringReader("intensity-component 6Color 6ColorIntense");
    lumaChangeReadable = new StringReader("luma-component 6Color 6ColorLuma");
    horizontalFlipReadable = new StringReader("horizontal-flip 2ColorHoriz 2ColorHFlip");
    verticalFlipReadable = new StringReader("vertical-flip 2ColorVert 2ColorVFlip");
    brightnessReadableBright = new StringReader("brighten 10 2ColorHoriz 2ColorHBrighten");
    brightnessReadableDim = new StringReader("brighten -10 2ColorHoriz 2ColorHDim");
    blurReadable = new StringReader("blur 4Color 4ColorBlur");
    sharpenReadable = new StringReader("sharpen 4Color 4ColorSharp");
    greyscaleReadable = new StringReader("greyscale 2ColorHoriz 2ColorGrey");
    sepiaReadable = new StringReader("sepia 2ColorHoriz 2ColorSepia");


    appendable1 = new StringBuilder();
    version1 = new ArrayList<Image>();
    sixColor = new Image(ImageUtil.readPPM("Resources/6color.ppm"), "6color");
    twoColorVert = new Image(ImageUtil.readPPM("Resources/2colorVert.ppm"), "2ColorVert");
    twoColorHoriz = new Image(ImageUtil.readPPM("Resources/2colorHoriz.ppm"), "2ColorHoriz");
    fourColorPNG = new Image(ImageUtil.readImages("Resources/4ColorPNG.png"), "4ColorPNG");
    fourColorJPEG = new Image(ImageUtil.readImages("Resources/4ColorJPEG.jpg"), "4ColorJPEG");


    version1.add(sixColor);
  }

  @Test(expected = IllegalArgumentException.class)
  public void controllerConstructorDisallowsNulls() {
    ImageController nullModel = new ImageControllerImpl(null, redChangeReadable, appendable1);
    ImageController nullModelReader = new ImageControllerImpl(colorModel1, null, appendable1);
    ImageController nullAppendable = new ImageControllerImpl(colorModel1,
            redChangeReadable, null);
  }

  @Test
  public void testGreyscaleRed() throws IOException {
    IModel colorModel1 = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/6color.ppm 6color");
    Readable save = new StringReader("save Resources/redGray6color.ppm 6color");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController redGreyscale = new ImageControllerImpl(colorModel1,
            redChangeReadable, appendable1);
    ImageController saved = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    redGreyscale.runCommand();
    saved.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("6color");

    assertEquals(255, imagePix[0][0].getR());
    assertEquals(255, imagePix[0][0].getG());
    assertEquals(255, imagePix[0][0].getB());
    assertEquals(0, imagePix[0][1].getR());
    assertEquals(0, imagePix[0][1].getG());
    assertEquals(0, imagePix[0][1].getB());
    assertEquals(0, imagePix[0][2].getR());
    assertEquals(0, imagePix[0][2].getG());
    assertEquals(0, imagePix[0][2].getB());
    assertEquals(255, imagePix[1][0].getR());
    assertEquals(255, imagePix[1][0].getG());
    assertEquals(255, imagePix[1][0].getB());
    assertEquals(255, imagePix[1][1].getR());
    assertEquals(255, imagePix[1][1].getG());
    assertEquals(255, imagePix[1][1].getB());
    assertEquals(0, imagePix[1][2].getR());
    assertEquals(0, imagePix[1][2].getG());
    assertEquals(0, imagePix[1][2].getB());

  }

  @Test
  public void testGreyscaleGreen() throws IOException {
    IModel colorModel1 = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/6color.ppm 6color");
    Readable save = new StringReader("save Resources/greenGray6color.ppm 6color");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController greenGreyscale = new ImageControllerImpl(colorModel1,
            greenChangeReadable, appendable1);
    ImageController saved = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    greenGreyscale.runCommand();
    saved.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("6color");

    assertEquals(0, imagePix[0][0].getR());
    assertEquals(0, imagePix[0][0].getG());
    assertEquals(0, imagePix[0][0].getB());
    assertEquals(255, imagePix[0][1].getR());
    assertEquals(255, imagePix[0][1].getG());
    assertEquals(255, imagePix[0][1].getB());
    assertEquals(0, imagePix[0][2].getR());
    assertEquals(0, imagePix[0][2].getG());
    assertEquals(0, imagePix[0][2].getB());
    assertEquals(255, imagePix[1][0].getR());
    assertEquals(255, imagePix[1][0].getG());
    assertEquals(255, imagePix[1][0].getB());
    assertEquals(255, imagePix[1][1].getR());
    assertEquals(255, imagePix[1][1].getG());
    assertEquals(255, imagePix[1][1].getB());
    assertEquals(0, imagePix[1][2].getR());
    assertEquals(0, imagePix[1][2].getG());
    assertEquals(0, imagePix[1][2].getB());

  }

  @Test
  public void testGreyscaleBlue() throws IOException {
    IModel colorModel1 = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/6color.ppm 6color");
    Readable save = new StringReader("save Resources/blueGray6color.ppm 6color");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController blueGreyscale = new ImageControllerImpl(colorModel1,
            blueChangeReadable, appendable1);
    ImageController saved = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    blueGreyscale.runCommand();
    saved.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("6color");

    assertEquals(0, imagePix[0][0].getR());
    assertEquals(0, imagePix[0][0].getG());
    assertEquals(0, imagePix[0][0].getB());
    assertEquals(0, imagePix[0][1].getR());
    assertEquals(0, imagePix[0][1].getG());
    assertEquals(0, imagePix[0][1].getB());
    assertEquals(255, imagePix[0][2].getR());
    assertEquals(255, imagePix[0][2].getG());
    assertEquals(255, imagePix[0][2].getB());
    assertEquals(0, imagePix[1][0].getR());
    assertEquals(0, imagePix[1][0].getG());
    assertEquals(0, imagePix[1][0].getB());
    assertEquals(255, imagePix[1][1].getR());
    assertEquals(255, imagePix[1][1].getG());
    assertEquals(255, imagePix[1][1].getB());
    assertEquals(0, imagePix[1][2].getR());
    assertEquals(0, imagePix[1][2].getG());
    assertEquals(0, imagePix[1][2].getB());

  }

  @Test
  public void testGreyscaleValue() throws IOException {
    IModel colorModel1 = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/6color.ppm 6color");
    Readable save = new StringReader("save Resources/valueGray6color.ppm 6color");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController valueGreyscale = new ImageControllerImpl(colorModel1,
            valueChangeReadable, appendable1);
    ImageController saved = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    valueGreyscale.runCommand();
    saved.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("6color");

    assertEquals(255, imagePix[0][0].getR());
    assertEquals(255, imagePix[0][0].getG());
    assertEquals(255, imagePix[0][0].getB());
    assertEquals(255, imagePix[0][1].getR());
    assertEquals(255, imagePix[0][1].getG());
    assertEquals(255, imagePix[0][1].getB());
    assertEquals(255, imagePix[0][2].getR());
    assertEquals(255, imagePix[0][2].getG());
    assertEquals(255, imagePix[0][2].getB());
    assertEquals(255, imagePix[1][0].getR());
    assertEquals(255, imagePix[1][0].getG());
    assertEquals(255, imagePix[1][0].getB());
    assertEquals(255, imagePix[1][1].getR());
    assertEquals(255, imagePix[1][1].getG());
    assertEquals(255, imagePix[1][1].getB());
    assertEquals(0, imagePix[1][2].getR());
    assertEquals(0, imagePix[1][2].getG());
    assertEquals(0, imagePix[1][2].getB());

  }


  @Test
  public void testGreyscaleIntensity() throws IOException {
    IModel colorModel1 = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/6color.ppm 6color");
    Readable save = new StringReader("save Resources/intenseGray6color.ppm 6color");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController intenseGreyscale = new ImageControllerImpl(colorModel1,
            intensityChangeReadable, appendable1);
    ImageController saved = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    intenseGreyscale.runCommand();
    saved.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("6color");

    assertEquals(85, imagePix[0][0].getR());
    assertEquals(85, imagePix[0][0].getG());
    assertEquals(85, imagePix[0][0].getB());
    assertEquals(85, imagePix[0][1].getR());
    assertEquals(85, imagePix[0][1].getG());
    assertEquals(85, imagePix[0][1].getB());
    assertEquals(85, imagePix[0][2].getR());
    assertEquals(85, imagePix[0][2].getG());
    assertEquals(85, imagePix[0][2].getB());
    assertEquals(170, imagePix[1][0].getR());
    assertEquals(170, imagePix[1][0].getG());
    assertEquals(170, imagePix[1][0].getB());
    assertEquals(255, imagePix[1][1].getR());
    assertEquals(255, imagePix[1][1].getG());
    assertEquals(255, imagePix[1][1].getB());
    assertEquals(0, imagePix[1][2].getR());
    assertEquals(0, imagePix[1][2].getG());
    assertEquals(0, imagePix[1][2].getB());

  }

  @Test
  public void testGreyscaleLuma() throws IOException {
    IModel colorModel1 = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/6color.ppm 6color");
    Readable save = new StringReader("save Resources/lumaGray6color.ppm 6color");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController lumaGreyscale = new ImageControllerImpl(colorModel1,
            lumaChangeReadable, appendable1);
    ImageController saved = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    lumaGreyscale.runCommand();
    saved.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("6color");

    assertEquals(54, imagePix[0][0].getR());
    assertEquals(54, imagePix[0][0].getG());
    assertEquals(54, imagePix[0][0].getB());
    assertEquals(182, imagePix[0][1].getR());
    assertEquals(182, imagePix[0][1].getG());
    assertEquals(182, imagePix[0][1].getB());
    assertEquals(18, imagePix[0][2].getR());
    assertEquals(18, imagePix[0][2].getG());
    assertEquals(18, imagePix[0][2].getB());
    assertEquals(236, imagePix[1][0].getR());
    assertEquals(236, imagePix[1][0].getG());
    assertEquals(236, imagePix[1][0].getB());
    assertEquals(254, imagePix[1][1].getR());
    assertEquals(254, imagePix[1][1].getG());
    assertEquals(254, imagePix[1][1].getB());
    assertEquals(0, imagePix[1][2].getR());
    assertEquals(0, imagePix[1][2].getG());
    assertEquals(0, imagePix[1][2].getB());

  }


  @Test
  public void testFlipHorizontal() throws IOException {
    IModel colorModel1 = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/2ColorHoriz.ppm 2ColorHoriz");
    Readable save = new StringReader("save Resources/hFlip.ppm 2ColorHoriz");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController flipHorizontal = new ImageControllerImpl(colorModel1,
            horizontalFlipReadable, appendable1);
    ImageController saved = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    flipHorizontal.runCommand();
    saved.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("2ColorHoriz");
    assertEquals(255, imagePix[0][0].getR());
    assertEquals(0, imagePix[0][0].getG());
    assertEquals(0, imagePix[0][0].getB());

    assertEquals(0, imagePix[0][1].getR());
    assertEquals(255, imagePix[0][1].getG());
    assertEquals(0, imagePix[0][1].getB());
  }

  @Test
  public void testFlipVertical() throws IOException {
    IModel colorModel1 = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/2colorVert.ppm 2ColorVert");
    Readable save = new StringReader("save Resources/VFlip2Color.ppm 2ColorVert");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController vflip = new ImageControllerImpl(colorModel1,
            verticalFlipReadable, appendable1);
    ImageController saved = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    vflip.runCommand();
    saved.runCommand();

    Pixel[][] imagePix = colorModel1.getImagePixels("2ColorVert");
    assertEquals(255, imagePix[0][0].getR());
    assertEquals(0, imagePix[0][0].getG());
    assertEquals(0, imagePix[0][0].getB());

    assertEquals(255, imagePix[1][0].getR());
    assertEquals(255, imagePix[1][0].getG());
    assertEquals(0, imagePix[1][0].getB());

  }

  @Test
  public void testBrightenBrigthens() throws IOException {
    version1.add(twoColorHoriz);
    IModel colorModel1 = new ModelImpl(version1);
    ImageController brightenHorizontal = new ImageControllerImpl(colorModel1,
            brightnessReadableBright,
            appendable1);
    brightenHorizontal.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("2ColorHoriz");
    assertEquals(255, imagePix[0][0].getR());
    assertEquals(10, imagePix[0][0].getG());
    assertEquals(10, imagePix[0][0].getB());

    assertEquals(10, imagePix[0][1].getR());
    assertEquals(255, imagePix[0][1].getG());
    assertEquals(10, imagePix[0][1].getB());
  }

  @Test
  public void testBrightenDarkens() throws IOException {
    version1.add(twoColorHoriz);
    IModel colorModel1 = new ModelImpl(version1);
    ImageController dimHorizontal = new ImageControllerImpl(colorModel1, brightnessReadableDim,
            appendable1);
    dimHorizontal.runCommand();
    Pixel[][] imagePix = colorModel1.getImagePixels("2ColorHoriz");
    assertEquals(245, imagePix[0][0].getR());
    assertEquals(0, imagePix[0][0].getG());
    assertEquals(0, imagePix[0][0].getB());

    assertEquals(0, imagePix[0][1].getR());
    assertEquals(245, imagePix[0][1].getG());
    assertEquals(0, imagePix[0][1].getB());
  }


  @Test
  public void testFilterBlur() throws IOException {
    IModel model = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/4Color.ppm 4Color");
    Readable save = new StringReader("save Resources/Blurred4Color.ppm 4Color");


    ImageController loadImage = new ImageControllerImpl(model,
            load,
            appendable1);
    ImageController blur = new ImageControllerImpl(model,
            blurReadable,
            appendable1);
    ImageController saveCoding = new ImageControllerImpl(model,
            save,
            appendable1);

    loadImage.runCommand();
    blur.runCommand();
    saveCoding.runCommand();

    Pixel[][] imagePix = model.getImagePixels("4Color");
    assertEquals(0, imagePix[0][0].getR());
    assertEquals(1, imagePix[0][0].getG());
    assertEquals(0, imagePix[0][0].getB());

    assertEquals(1, imagePix[0][1].getR());
    assertEquals(3, imagePix[0][1].getG());
    assertEquals(1, imagePix[0][1].getB());

    assertEquals(2, imagePix[1][0].getR());
    assertEquals(4, imagePix[1][0].getG());
    assertEquals(2, imagePix[1][0].getB());

    assertEquals(3, imagePix[1][1].getR());
    assertEquals(6, imagePix[1][1].getG());
    assertEquals(3, imagePix[1][1].getB());
  }

  @Test
  public void testFilterSharpen() throws IOException {
    IModel model = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/4Color.ppm 4Color");
    Readable save = new StringReader("save Resources/Sharpen4Color.ppm 4Color");


    ImageController loadImage = new ImageControllerImpl(model,
            load,
            appendable1);
    ImageController sharpen = new ImageControllerImpl(model,
            sharpenReadable,
            appendable1);
    ImageController saveCoding = new ImageControllerImpl(model,
            save,
            appendable1);

    loadImage.runCommand();
    sharpen.runCommand();
    saveCoding.runCommand();
    Pixel[][] imagePix = model.getImagePixels("4Color");
    assertEquals(0, imagePix[0][0].getR());
    assertEquals(0, imagePix[0][0].getG());
    assertEquals(0, imagePix[0][0].getB());

    assertEquals(0, imagePix[0][1].getR());
    assertEquals(28, imagePix[0][1].getG());
    assertEquals(91, imagePix[0][1].getB());

    assertEquals(0, imagePix[1][0].getR());
    assertEquals(13, imagePix[1][0].getG());
    assertEquals(44, imagePix[1][0].getB());

    assertEquals(0, imagePix[1][1].getR());
    assertEquals(85, imagePix[1][1].getG());
    assertEquals(255, imagePix[1][1].getB());
  }

  // test greyscale and saved the image for the submission.
  @Test
  public void testTransformGreyscale() throws IOException {

    version1.add(twoColorHoriz);
    IModel colorModel1 = new ModelImpl(version1);
    Readable load = new StringReader("load Resources/2ColorHoriz.ppm 2ColorHoriz");
    Readable save = new StringReader("save Resources/testSaveGreyHorizController.ppm 2ColorHoriz");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController greyHorizontal = new ImageControllerImpl(colorModel1,
            greyscaleReadable,
            appendable1);
    ImageController savedHor = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    greyHorizontal.runCommand();
    savedHor.runCommand();

    Pixel[][] imagePix = colorModel1.getImagePixels("2ColorHoriz");
    assertEquals(54, imagePix[0][0].getR());
    assertEquals(54, imagePix[0][0].getG());
    assertEquals(54, imagePix[0][0].getB());

    assertEquals(182, imagePix[0][1].getR());
    assertEquals(182, imagePix[0][1].getG());
    assertEquals(182, imagePix[0][1].getB());
  }

  // test sepia and saved the image for the submission.
  @Test
  public void testTransformSepia() throws IOException {

    version1.add(twoColorHoriz);
    IModel colorModel1 = new ModelImpl(version1);
    Readable load = new StringReader("load Resources/2ColorHoriz.ppm 2ColorHoriz");
    Readable save = new StringReader("save Resources/testSaveSepiaHorizController.ppm 2ColorHoriz");
    ImageController loadImage = new ImageControllerImpl(colorModel1,
            load,
            appendable1);
    ImageController sepiaHorizontal = new ImageControllerImpl(colorModel1,
            sepiaReadable,
            appendable1);
    ImageController savedHor = new ImageControllerImpl(colorModel1,
            save,
            appendable1);

    loadImage.runCommand();
    sepiaHorizontal.runCommand();
    savedHor.runCommand();

    Pixel[][] imagePix = colorModel1.getImagePixels("2ColorHoriz");
    assertEquals(100, imagePix[0][0].getR());
    assertEquals(88, imagePix[0][0].getG());
    assertEquals(69, imagePix[0][0].getB());

    assertEquals(196, imagePix[0][1].getR());
    assertEquals(174, imagePix[0][1].getG());
    assertEquals(136, imagePix[0][1].getB());
  }

  // test methods that were previously in IModel

  @Test
  public void testLoadThroughControllerPPM() throws IOException {
    IModel model = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/test.ppm loadedPPM");
    ImageController testLoad = new ImageControllerImpl(model, load, appendable1);

    testLoad.runCommand();

    Pixel[][] imagePixels = model.getImagePixels("loadedPPM");
    assertEquals(255, imagePixels[0][0].getR());
    assertEquals(255, imagePixels[0][0].getG());
    assertEquals(255, imagePixels[0][0].getB());
    assertEquals(255, imagePixels[0][1].getR());
    assertEquals(255, imagePixels[0][1].getG());
    assertEquals(255, imagePixels[0][1].getB());
  }

  @Test
  public void testLoadThroughControllerPNG() throws IOException {
    IModel model = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/4ColorPNG.png 4Color");
    ImageController testLoad = new ImageControllerImpl(model, load, appendable1);

    testLoad.runCommand();

    Pixel[][] imagePixels = model.getImagePixels("4Color");
    assertEquals(255, imagePixels[0][0].getR());
    assertEquals(0, imagePixels[0][0].getG());
    assertEquals(0, imagePixels[0][0].getB());

    assertEquals(255, imagePixels[1][0].getR());
    assertEquals(255, imagePixels[1][0].getG());
    assertEquals(0, imagePixels[1][0].getB());

    assertEquals(0, imagePixels[0][1].getR());
    assertEquals(255, imagePixels[0][1].getG());
    assertEquals(0, imagePixels[0][1].getB());

    assertEquals(255, imagePixels[1][1].getR());
    assertEquals(255, imagePixels[1][1].getG());
    assertEquals(255, imagePixels[1][1].getB());
  }

  @Test
  public void testLoadThroughControllerJPEG() throws IOException {
    IModel model = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/4ColorJPEG.jpg 4Color");
    ImageController testLoad = new ImageControllerImpl(model, load, appendable1);

    testLoad.runCommand();

    Pixel[][] imagePixels = model.getImagePixels("4Color");
    assertEquals(254, imagePixels[0][0].getR());
    assertEquals(0, imagePixels[0][0].getG());
    assertEquals(0, imagePixels[0][0].getB());

    assertEquals(255, imagePixels[1][0].getR());
    assertEquals(255, imagePixels[1][0].getG());
    assertEquals(1, imagePixels[1][0].getB());

    assertEquals(0, imagePixels[0][1].getR());
    assertEquals(255, imagePixels[0][1].getG());
    assertEquals(1, imagePixels[0][1].getB());

    assertEquals(255, imagePixels[1][1].getR());
    assertEquals(255, imagePixels[1][1].getG());
    assertEquals(255, imagePixels[1][1].getB());
  }

  @Test
  public void testSaveThroughControllerPPM() throws IOException {
    IModel model = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/test.ppm loadedPPM");
    Readable save = new StringReader("save Resources/testSaveController.ppm loadedPPM");
    Readable loadSaved = new StringReader("load Resources/testSaveController.ppm SavedPPM");

    ImageController testLoad = new ImageControllerImpl(model, load, appendable1);
    ImageController testSave = new ImageControllerImpl(model, save, appendable1);
    ImageController testLoadSaved = new ImageControllerImpl(model, loadSaved, appendable1);

    testLoad.runCommand();
    testSave.runCommand();
    testLoadSaved.runCommand();

    Pixel[][] imagePixelsLoaded = model.getImagePixels("loadedPPM");
    Pixel[][] imagePixelsSaved = model.getImagePixels("SavedPPM");

    assertEquals(imagePixelsLoaded[0][0].getR(),
            imagePixelsSaved[0][0].getR());
    assertEquals(imagePixelsLoaded[0][0].getG(),
            imagePixelsSaved[0][0].getG());
    assertEquals(imagePixelsLoaded[0][0].getB(),
            imagePixelsSaved[0][0].getB());

    assertEquals(imagePixelsLoaded[0][1].getR(),
            imagePixelsSaved[0][1].getR());
    assertEquals(imagePixelsLoaded[0][1].getG(),
            imagePixelsSaved[0][1].getG());
    assertEquals(imagePixelsLoaded[0][1].getB(),
            imagePixelsSaved[0][1].getB());

    assertEquals(imagePixelsLoaded[1][0].getR(),
            imagePixelsSaved[1][0].getR());
    assertEquals(imagePixelsLoaded[1][0].getG(),
            imagePixelsSaved[1][0].getG());
    assertEquals(imagePixelsLoaded[1][0].getB(),
            imagePixelsSaved[1][0].getB());

    assertEquals(imagePixelsLoaded[1][1].getR(),
            imagePixelsSaved[1][1].getR());
    assertEquals(imagePixelsLoaded[1][1].getG(),
            imagePixelsSaved[1][1].getG());
    assertEquals(imagePixelsLoaded[1][1].getB(),
            imagePixelsSaved[1][1].getB());
  }

  @Test
  public void testSaveThroughControllerOtherType() throws IOException {
    IModel model = new ModelImpl(new ArrayList<Image>());
    Readable load = new StringReader("load Resources/4ColorPNG.png loaded4Color");
    Readable save = new StringReader("save Resources/4ColorPNGSaveController.png loaded4Color");
    Readable loadSaved = new StringReader("load Resources/4ColorPNGSaveController.png " +
            "Saved4ColorPNG");

    ImageController testLoad = new ImageControllerImpl(model, load, appendable1);
    ImageController testSave = new ImageControllerImpl(model, save, appendable1);
    ImageController testLoadSaved = new ImageControllerImpl(model, loadSaved, appendable1);

    testLoad.runCommand();
    testSave.runCommand();
    testLoadSaved.runCommand();

    Pixel[][] imagePixelsLoaded = model.getImagePixels("loaded4Color");
    Pixel[][] imagePixelsSaved = model.getImagePixels("Saved4ColorPNG");

    assertEquals(imagePixelsLoaded[0][0].getR(),
            imagePixelsSaved[0][0].getR());
    assertEquals(imagePixelsLoaded[0][0].getG(),
            imagePixelsSaved[0][0].getG());
    assertEquals(imagePixelsLoaded[0][0].getB(),
            imagePixelsSaved[0][0].getB());

    assertEquals(imagePixelsLoaded[1][0].getR(),
            imagePixelsSaved[1][0].getR());
    assertEquals(imagePixelsLoaded[1][0].getG(),
            imagePixelsSaved[1][0].getG());
    assertEquals(imagePixelsLoaded[1][0].getB(),
            imagePixelsSaved[1][0].getB());

    assertEquals(imagePixelsLoaded[0][1].getR(),
            imagePixelsSaved[0][1].getR());
    assertEquals(imagePixelsLoaded[0][1].getG(),
            imagePixelsSaved[0][1].getG());
    assertEquals(imagePixelsLoaded[0][1].getB(),
            imagePixelsSaved[0][1].getB());

    assertEquals(imagePixelsLoaded[1][1].getR(),
            imagePixelsSaved[1][1].getR());
    assertEquals(imagePixelsLoaded[1][1].getG(),
            imagePixelsSaved[1][1].getG());
    assertEquals(imagePixelsLoaded[1][1].getB(),
            imagePixelsSaved[1][1].getB());


  }

}
