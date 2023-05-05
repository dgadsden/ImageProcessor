import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import model.IImage;
import model.IModel;
import model.IPixel;
import model.Image;
import model.ImageUtil;
import model.ModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Class for the test of the methods in the model of the image processor.
 */
public class ImageProcessorModelTest {

  IPixel pix1;
  IPixel pix2;

  IPixel pix3;

  IImage image1;


  @Before
  public void initData() {

    pix1 = new Pixel(20, 10, 1, 1, 1);
    pix2 = new Pixel(20, 10, 1, 2, 3);
    pix3 = new Pixel(0, 0, 0, 0, 0);
    Pixel[][] array = new Pixel[4][4];
    array[0][0] = (Pixel) pix3;
    image1 = new Image(array, "testImage");
  }

  // pixel test
  @Test(expected = IllegalArgumentException.class)
  public void PixelConstructorDisallowsNegCoordinates() {
    new Pixel(-1, 0, 255, 255, 255);
    new Pixel(0, -1, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void PixelConstructorDisallowsNegRBG() {
    new Pixel(1, 1, -1, 1, 1);
    new Pixel(1, 1, 1, -1, 1);
    new Pixel(1, 1, 1, 1, -1);

  }

  // test getters
  @Test
  public void testGetRow() {
    assertEquals(20, pix1.getRow());
  }

  @Test
  public void testGetCol() {
    assertEquals(10, pix1.getCol());
  }

  @Test
  public void testGetR() {
    assertEquals(1, pix2.getR());
  }

  @Test
  public void testGetG() {
    assertEquals(2, pix2.getG());
  }

  @Test
  public void testGetB() {
    assertEquals(3, pix2.getB());
  }

  // test setters
  @Test(expected = IllegalArgumentException.class)
  public void SetRowAndColDisAllowNegative() {
    pix3.setRow(-1);
    pix3.setCol(-1);
  }

  @Test
  public void testSetRow() {
    pix3.setRow(20);
    assertEquals(20, pix3.getRow());
  }

  @Test
  public void testSetCol() {
    pix3.setCol(10);
    assertEquals(10, pix3.getCol());
  }

  @Test(expected = IllegalArgumentException.class)
  public void SetRGBlDisAllowNegative() {
    pix3.setR(-1);
    pix3.setB(-1);
    pix3.setG(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void SetRGBlDisAllowOver255() {
    pix3.setR(257);
    pix3.setB(300);
    pix3.setG(499);
  }

  @Test
  public void testSetR() {
    pix3.setR(1);
    assertEquals(1, pix3.getR());
  }

  @Test
  public void testSetG() {
    pix3.setG(2);
    assertEquals(2, pix3.getG());
  }

  @Test
  public void testSetB() {
    pix3.setB(3);
    assertEquals(3, pix3.getB());
  }

  //image test
  @Test(expected = IllegalArgumentException.class)
  public void ImageConstructorDisallowsNullPixels() {
    new Image(null, "null-image");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ImageConstructorDisallowsNullName() {
    Pixel[][] fullArray = new Pixel[0][];

    new Image(fullArray, null);
  }

  @Test
  public void testGetImageName() {
    assertEquals("testImage", image1.getImageName());
  }

  @Test
  public void testGetImageHeight() {
    assertEquals(4, image1.getImageHeight());
  }

  @Test
  public void testGetImageWidth() {
    assertEquals(4, image1.getImageWidth());
  }

  @Test
  public void testGetPixel() {
    assertEquals(pix3, image1.getPixel(0, 0));
  }

  @Test
  public void testCopyPixels() {
    Pixel[][] copy = image1.copyPixels();
    assertEquals(image1.getPixel(0, 0), copy[0][0]);
  }


  @Test
  public void testReadPPM() {
    Pixel[][] imagePixels = ImageUtil.readPPM("Resources/test.ppm");
    assertEquals(255, imagePixels[0][0].getR());
    assertEquals(255, imagePixels[0][0].getG());
    assertEquals(255, imagePixels[0][0].getB());
    assertEquals(255, imagePixels[0][1].getR());
    assertEquals(255, imagePixels[0][1].getG());
    assertEquals(255, imagePixels[0][1].getB());
  }


  // test readImages for other formats as well as PPM
  @Test
  public void testReadImagesStillReadsPPM() {
    Pixel[][] imagePixels = ImageUtil.readImages("Resources/test.ppm");
    assertEquals(255, imagePixels[0][0].getR());
    assertEquals(255, imagePixels[0][0].getG());
    assertEquals(255, imagePixels[0][0].getB());
    assertEquals(255, imagePixels[0][1].getR());
    assertEquals(255, imagePixels[0][1].getG());
    assertEquals(255, imagePixels[0][1].getB());
  }

  @Test
  public void testReadImagesPNG() {
    Pixel[][] imagePixels = ImageUtil.readImages("Resources/4ColorPNG.png");
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

    int[] test = new int[]{5};
    assertEquals(test.length, 1);
    assertEquals(test[0], 5);
  }

  @Test
  public void testReadImagesJPEG() {
    Pixel[][] imagePixels = ImageUtil.readImages("Resources/4ColorJPEG.jpg");
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
  public void testGetImagePixel() {
    List<Image> images = new ArrayList<Image>();
    Pixel[][] image1Pixels = ImageUtil.readImages("Resources/2colorHoriz.ppm");
    Image twoHor = new Image(image1Pixels, "2hor");
    Pixel[][] image2Pixels = ImageUtil.readImages("Resources/2colorVert.ppm");
    Image twoVert = new Image(image1Pixels, "2vert");
    images.add(twoHor);
    images.add(twoVert);


    IModel testModel = new ModelImpl(images);
    Pixel[][] copyArrayOfHor = testModel.getImagePixels("2hor");
    assertEquals(255, copyArrayOfHor[0][0].getR());
    assertEquals(0, copyArrayOfHor[0][0].getG());
    assertEquals(0, copyArrayOfHor[0][0].getB());

    assertEquals(0, copyArrayOfHor[0][1].getR());
    assertEquals(255, copyArrayOfHor[0][1].getG());
    assertEquals(0, copyArrayOfHor[0][1].getB());
  }
  //previous test for load and saved moved to controller since it is no longer part of the model


  // test for greyscale, brighten and flip in controller test

}