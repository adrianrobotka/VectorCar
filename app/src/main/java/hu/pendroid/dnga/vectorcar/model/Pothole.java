package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

/**
 * The pothole that causes problem
 */
public final class Pothole extends LaneModel {
    public Pothole(int lane) {
        super(lane);
        position = calculatePositionByLane(lane);
        metrics = new Vector(1, 1);
        motion = new Vector(0, 0);
    }

    private Vector calculatePositionByLane(int lane) {
        //TODO implement this
        return null;
    }
}