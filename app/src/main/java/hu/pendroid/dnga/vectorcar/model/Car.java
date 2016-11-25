package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The car
 * <p>
 * Position: the position on the screen
 * Motion: motion of the ground
 */
public final class Car extends LaneBasedModel {
    private static final float carPadding = 20;
    // Width of a zone
    float zoneWidth;
    // Width of a lane
    float laneWidth;

    public Car(int lane, Ground ground) {
        super(lane);

        laneWidth = ground.metrics.getX();
        zoneWidth = Config.WIDTH - ((Config.LANES - 1) * laneWidth);
        zoneWidth /= Config.LANES;

        metrics = new Vector(zoneWidth - carPadding * 2, 0);
        motion = new Vector(0, 2f);

        setPositionByLane(lane);
    }

    private void setPositionByLane(int lane) {
        float x = lane * zoneWidth;
        x += lane * laneWidth;
        x += carPadding;

        position = new Vector(x, Config.HEIGHT - metrics.getY() - 20);
    }

    public void changeLane(int lane) {
        this.lane = lane;
        setPositionByLane(lane);
    }
}
