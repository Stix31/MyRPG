package engine;

public class GameContainer implements Runnable {

  /**
   * Max fps cap.
   */
  private final double UPDATE_CAP = 1.0/60.0;

  /**
   * Width of the game render engine.
   */
  private int width = 320;

  /**
   * Height of the game render engine.
   */
  private int height = 240;

  /**
   * Scale of the game render engine.
   */
  private float scale = 1f;

  /**
   * Title of the game render engine.
   */
  private String title = "RPGEngine_v1.0";

  /**
   * Main Thread.
   */
  private Thread thread;

  /**
   * running status.
   */
  private boolean running = false;

  /**
   * Window.
   */
  private Window window;

  /**
   * Constructor.
   */
  public GameContainer () {

  }

  /**
   * Get the width of the game render engine.
   * @return int width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Set the width of the game render engine.
   * @param width int
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Set the height of the game render engine.
   * @param height int
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Get the height of the game render engine.
   * @return int height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get the scale of the game render engine.
   * @return float scale.
   */
  public float getScale() {
    return scale;
  }

  /**
   * Set the scale of the game render engine.
   * @param scale float
   */
  public void setScale(float scale) {
    this.scale = scale;
  }

  /**
   * Get title of the game render engine.
   * @return String title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set title of the game render engine.
   * @param title String
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Start the main thread.
   */
  public void start() {
    window = new Window(this);
    thread = new Thread(this);
    thread.run();
  }

  /**
   * Stop the thread.
   */
  public void stop() {
    running = false;
  }

  /**
   * Thread running.
   */
  @Override
  public void run() {
    running = true;
    boolean render = false;
    double firstTime = 0;
    double lastTime = System.nanoTime()/1000000000.0;
    double passedTime = 0;
    double unprocessedTime = 0;

    /**
     * Time to render a frame.
     */
    double frameTime = 0;
    /**
     * Number frames past.
     */
    int frames = 0;
    /**
     * frames per seconds.
     */
    int fps = 0;

    while (running) {
      // Stop render in each iteration.
      render = false;

      // Get time in seconds.
      firstTime = System.nanoTime()/1000000000.0;
      passedTime = firstTime - lastTime;
      lastTime = firstTime;

      unprocessedTime += passedTime;
      frameTime += passedTime;

      while (unprocessedTime >= UPDATE_CAP) {
        unprocessedTime -= UPDATE_CAP;
        render = true;

        if (frameTime >= 1.0) {
          frameTime = 0;
          fps = frames;
          frames = 0;
          System.out.println("FPS: " + fps);
        }
        //System.out.println("Updating");

        // Todo: Update Game !

        if (render) {
          // Todo: Render Game
          window.update();
          frames++;
        } else {
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
    dispose();
  }


  /**
   * I don't  know what it is for now.
   */
  public void dispose() {

  }


  /**
   * Just for testing purpose.
   * @param args
   */
  public static void main(String[] args) {
    GameContainer gameC = new GameContainer();
    gameC.start();
  }
}
