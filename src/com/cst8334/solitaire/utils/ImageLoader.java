package com.cst8334.solitaire.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Loads images from the resources folder.
 * 
 * @version 1.0
 * @since 17
 * @author Austin Kirby
 */
public class ImageLoader {
  
  private static final String IMAGE_PATH = "/imgs/";

  private static ImageLoader instance;

  /// Cache images to avoid loading them multiple times
  private Map<String, BufferedImage> imageMap;

  /**
   * Empty constructor.
   */
  private ImageLoader() {
    imageMap = new HashMap<>();
  }

  /**
   * Returns the singleton instance of the ImageLoader.
   * 
   * @return The singleton instance of the ImageLoader.
   */
  public static ImageLoader getInstance() {
    if (instance == null) {
      instance = new ImageLoader();
    }
    return instance;
  }

  /**
   * Loads an image from the resources folder.
   * 
   * @param imageName The name of the image to load.
   * @return The loaded image as a BufferedImage or null if the image could not be loaded.
   */
  public BufferedImage loadImage(String imageName) {
    BufferedImage bufferedImage = null;
    // Check if the image has already been loaded
    if (imageMap.containsKey(imageName)) {
      return imageMap.get(imageName);
    }
    // Load the image
    try (
      InputStream inputStream = getClass().getResourceAsStream(IMAGE_PATH + imageName)
    ) {
      if (inputStream == null) {
        throw new IOException("Could not load image: " + imageName);
      }
      bufferedImage = ImageIO.read(inputStream);
      imageMap.put(imageName, bufferedImage);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return bufferedImage;
  }

  public BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
    BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
    Graphics2D gc = scaledImage.createGraphics();

    gc.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    gc.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    double scaleX = (double) width / originalImage.getWidth();
    double scaleY = (double) height / originalImage.getHeight();
    AffineTransform transform = AffineTransform.getScaleInstance(scaleX, scaleY);
    gc.drawRenderedImage(originalImage, transform);

    gc.dispose();

    return scaledImage;
  }

}
