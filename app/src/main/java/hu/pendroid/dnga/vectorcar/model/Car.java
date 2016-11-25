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
    // Width of a zone
    float zoneWidth;
    // Width of a lane
    float laneWidth;

    private Ground ground;

    public Car(int lane, Ground ground) {
        super(lane);
        this.ground = ground;

        laneWidth = ground.metrics.getX();
        zoneWidth = Config.WIDTH - ((Config.LANES - 1) * laneWidth);
        zoneWidth /= Config.LANES;

        metrics = new Vector(zoneWidth - carPadding * 2, 0);
        motion = new Vector(); // DO NOT USE THIS

        setPositionByLane(lane);
    }

    private void setPositionByLane(int lane) {
        float x = lane * zoneWidth;
        x += lane * laneWidth;
        x += carPadding;

        position = new Vector(x, Config.HEIGHT - metrics.getY() - 20);
    }

    public void goRight() {
        if (lane <= Config.LANES - 2) {
            setPositionByLane(++lane);
        }
    }

    public void goLeft() {
        if (lane > 0) {
            setPositionByLane(--lane);
        }
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

        Vector newMotion = ground.motion.minus(new Vector(0, multiplier * Config.SMALLEST_SPEED));
        if (newMotion.getY() > 0)
            ground.motion = newMotion;
        else
            ground.motion = new Vector();
    }
}
