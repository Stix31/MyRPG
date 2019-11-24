package engine;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Renderer {

  private int pixelWidth;
  private int pixelHeight;
  private int[] pixels;


  public Renderer (GameContainer gameContainer) {
    this.pixelWidth = gameContainer.getWidth();
    this.pixelHeight = gameContainer.getHeight();
    int[] dataBuffer =
        ((DataBufferInt) gameContainer.getWindow().getbImage()
            .getRaster().getDataBuffer()).getData();
    this.pixels = dataBuffer;
  }

  public void clear () {
    for (int i = 0 ; i < pixels.length ; i++) {
      pixels[i] += 0;
    }
  }
}
