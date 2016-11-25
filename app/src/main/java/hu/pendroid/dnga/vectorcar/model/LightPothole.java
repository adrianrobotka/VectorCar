package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

/**
 * The fatal pothole that causes game over
 */
public final class LightPothole extends LaneBasedModel {
    public LightPothole(int lane) {
        this.lane = lane;
        position = calculatePositionByLane(lane);
        metrics = new Vector(1, 1);
        motion = new Vector(0, 0);
    }

    private Vector calculatePositionByLane(int lane) {
        //TODO implement this
        return null;
    }
}
