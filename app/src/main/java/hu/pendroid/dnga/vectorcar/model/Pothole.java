package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

/**
 * The pothole that causes problem
 */
public final class Pothole extends LaneBasedModel {
    private static final float potholePadding = 20;

    public Pothole(int lane) {
        super(lane);

        float zoneWidth = Ground.getZoneWidth();

        metrics = new Vector(zoneWidth - potholePadding * 2, 0);
        motion = new Vector(0, 0);

        calculatePositionByLane(lane);
    }

    private void calculatePositionByLane(int lane) {
        position = Ground.calculateModelPositionByLane(lane).add(new Vector(potholePadding, 0));
    }
}