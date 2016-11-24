package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The car
 */
public final class Car extends LaneModel {

    public Car(int lane) {
        super(lane);
        metrics = new Vector(206, 427);
        motion = new Vector(0, Config.INITIAL_CAR_SPEED);
        setPositionByLane(lane);
    }

    private void setPositionByLane(int lane) {
        position = new Vector(Config.WIDTH / 2 - metrics.getX() / 2, Config.HEIGHT - metrics.getY() - 50);
    }

    public void changeLane(int lane) {
        this.lane = lane;
        setPositionByLane(lane);
    }
}
