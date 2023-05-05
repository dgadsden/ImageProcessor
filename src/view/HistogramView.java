package view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JPanel;


/**
 * Class for a color histogram of an image.
 */
public class HistogramView extends JPanel {
  private int[] values;

  private Color color;

  /**
   * Constructor for the HistogramView.
   *
   * @param values an array of the rgb values of each pixel of image.
   * @param color the colors of an image.
   */
  public HistogramView(int[] values, Color color) {
    if (values == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.values = values;
    this.color = color;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g;

    int width = getWidth();
    int height = getHeight();
    int maxH = 0;
    int[] histoValues = new int[256];
    graphics.drawRect(0, 0, width, height);
    graphics.setColor(color);


    for (int value : values) {
      histoValues[value] = histoValues[value] + 1;
    }

    int newWidth = Math.round(width / 255);


    for (int i = 0; i < histoValues.length; i++) {
      if (histoValues[i] > maxH) {
        maxH += histoValues[i];
      }
    }
    for (int j = 0; j < histoValues.length; j++) {
      graphics.fillRect(j * newWidth, height - Math.min((int) Math.round((histoValues[j]) * .02),
              maxH), newWidth, (histoValues[j]));
    }
  }

  /**
   * Override getPreferredSize to be a box that size of the max rgb value.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(256, 256);
  }

}
