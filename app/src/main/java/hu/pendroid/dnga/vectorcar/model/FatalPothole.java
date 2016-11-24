package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The fatal pothole that causes game over
 */
public final class FatalPothole extends LaneModel {
    public FatalPothole(int lane) {
        super(lane);
        position = calculatePositionByLane(lane);
        metrics = new Vector(1, 1);
        motion = new Vector(Config.INITIAL_CAR_SPEED, 0);
    }

    private Vector calculatePositionByLane(int lane) {
        //TODO implement this
        return null;
    }
}
