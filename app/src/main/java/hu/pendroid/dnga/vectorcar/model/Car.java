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

    public Car(int lane) {
        super(lane);
        metrics = new Vector(206, 427);
        motion = new Vector(0, -1f);
        setPositionByLane(lane);
    }

    private void setPositionByLane(int lane) {
        position = new Vector(Config.WIDTH / 2 - metrics.getX() / 2, Config.HEIGHT - metrics.getY() - 20);
    }

    public void changeLane(int lane) {
        this.lane = lane;
        setPositionByLane(lane);
    }
}
