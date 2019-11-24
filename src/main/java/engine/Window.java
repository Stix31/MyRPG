package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

  private JFrame frame;
  private BufferedImage bImage;
  private Canvas canvas;
  private BufferStrategy bStrategy;
  private Graphics graphics;

  public BufferedImage getbImage() {
    return bImage;
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public Window (int width, int height, float scale) {
    bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
  }

  public Window (GameContainer gameC) {
    bImage = new BufferedImage(gameC.getWidth(), gameC.getHeight(), BufferedImage.TYPE_INT_ARGB);
    canvas = new Canvas();
    Dimension dim = new Dimension((int) (gameC.getWidth() * gameC.getScale()), (int) (gameC.getHeight() * gameC.getScale()));
    canvas.setPreferredSize(dim);
    canvas.setMaximumSize(dim);
    canvas.setMinimumSize(dim);

    frame = new JFrame(gameC.getTitle());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(canvas, BorderLayout.CENTER);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);

    canvas.createBufferStrategy(2);
    bStrategy = canvas.getBufferStrategy();
    graphics = bStrategy.getDrawGraphics();

  }

  public void update() {
    graphics.drawImage(bImage, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
    bStrategy.show();
  }
}
