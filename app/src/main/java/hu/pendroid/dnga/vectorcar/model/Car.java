package hu.pendroid.dnga.vectorcar.model;

import android.util.Log;

import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The car
 * <p>
 * Position: the position on the screen
 * Motion: motion of the ground
 */
public final class Car extends LaneBasedModel {
    private static final String LOGTAG = Car.class.getSimpleName();

    private static final float carPadding = 20;

    private Ground ground;

    public Car(int lane, Ground ground) {
        this.lane = lane;
        this.ground = ground;

        float zoneWidth = Ground.getZoneWidth();

        metrics = new Vector(zoneWidth - carPadding * 2, 0);
        motion = new Vector(); // DO NOT USE THIS

        calculatePositionByLane(lane);
    }

    private void calculatePositionByLane(int lane) {
        position = Ground.calculateModelPositionByLane(lane).add(new Vector(carPadding, Config.HEIGHT - metrics.getY() - 20));
    }

    /**
     * Change lane right (if it is possible
     */
    public void goRight() {
        if (ground.motion.getY() > Config.MIN_CONTROL_SPEED && lane <= Config.LANES - 2) {
            calculatePositionByLane(++lane);
        }
    }

    /**
     * Change lane left (if it is possible
     */
    public void goLeft() {
        if (ground.motion.getY() > Config.MIN_CONTROL_SPEED && lane > 0) {
            calculatePositionByLane(--lane);
        }
    }

    /**
     * Refresh car`s position
     */
    public void refresh() {
        calculatePositionByLane(lane);
    }

    public void speedUp() {
        speedUp(1);
    }

    public void brake() {
        brake(1);
    }

    public void speedUp(int multiplier) {
        Log.i(LOGTAG, "Speed up car: " + multiplier);

        Vector newMotion = ground.motion.add(new Vector(0, multiplier * Config.SMALLEST_SPEED));
        if (newMotion.getY() < Config.MAX_SPEED)
            ground.motion = newMotion;
        else
            ground.motion = new Vector(0, Config.MAX_SPEED);
    }

    public void brake(int multiplier) {
        Log.i(LOGTAG, "Brake car: " + multiplier + "x");

        Vector newMotion = ground.motion.subtract(new Vector(0, multiplier * Config.SMALLEST_SPEED));
        if (newMotion.getY() > 0)
            ground.motion = newMotion;
        else
            ground.motion = new Vector();
    }
}
