package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Model;
import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The ground is a fix object in physics, so this model should contains null vectors.
 * But to imitate movements we will move the ground. (This is a drawer task)
 */
public final class Ground extends Model {
    // Width of a lane
    private static final float laneWidth = 20;
    private static final float laneHeight = 150;
    // Width of a zone
    private static float zoneWidth;

    public Ground() {
        // start position of the first lanes
        position = new Vector();

        // metrics of a lane
        metrics = new Vector(laneWidth, laneHeight);

        // motion of the lanes
        motion = new Vector();

        zoneWidth = Config.WIDTH - ((Config.LANES - 1) * laneWidth);
        zoneWidth /= Config.LANES;
    }

    public static float getZoneWidth() {
        return zoneWidth;
    }

    public static Vector calculateModelPositionByLane(int lane) {
        float x = lane * zoneWidth;
        x += lane * laneWidth;
        // Padding is missing from the X value (post correction needed!)
        return new Vector(x, 0);
    }
}
