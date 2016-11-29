package hu.pendroid.dnga.vectorcar;

/**
 * Constants
 */
public final class Config {
    /**
     * the height of the info panel  in the game (in px)
     */
    public static final float INFO_PANEL_HEIGHT = 60;
    /**
     * the full abstract width of the screen
     */
    public static final float WIDTH = 1080;
    /**
     * the full abstract height of the screen (calculated from the width)
     */
    public static final float HEIGHT = 1920 - INFO_PANEL_HEIGHT;
    /**
     * Frames per seconds
     */
    public static final float FPS = 50;
    /**
     * Number of lanes
     */
    public static int LANES = 3;
    /**
     * Min length of a control gesture
     */
    public static final float MIN_GESTURE_LENGTH = WIDTH / 5;
    /**
     * Max speed of the car
     */
    public static float MAX_SPEED = 30;
    /**
     * Smallest speed unit (unit of the negative acceleration)
     */
    public static final float SMALLEST_SPEED = 0.5f;

    /**
     * Min speed to control the car
     */
    public static final float MIN_CONTROL_SPEED = 0.5f;

    /**
     * Time interval of a puncture
     */
    public static float punctureTime = FPS * 10;
}