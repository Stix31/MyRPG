package engine;

public class GameContainer implements Runnable {

  /**
   * Main Thread.
   */
  private Thread thread;
  /**
   * Max fps cap.
   */
  private final double UPDATE_CAP = 1.0/60.0;
  /**
   * running condition.
   */
  private boolean running = false;

  /**
   * Constructor.
   */
  public GameContainer () {

  }

  /**
   * Start the main thread.
   */
  public void start() {
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