package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The pothole that causes problem on the car
 */
public final class Pothole extends LaneBasedModel {
    private float potholePadding;
    private Ground ground;

    public Pothole(Ground ground) {
        this.ground = ground;

        float zoneWidth = Ground.getZoneWidth();
        potholePadding = zoneWidth / 10 * 3;

        metrics = new Vector(zoneWidth - potholePadding * 2, 0);
        motion = new Vector(); // DO NOT USE THIS (the ground moves)
        position = new Vector(0, Config.HEIGHT * 2);
    }

    private void calculatePositionByLane() {
        position = Ground.calculateModelPositionByLane(lane).add(new Vector(potholePadding, ground.position.getY()));
    }

    public boolean isOnTheRoad() {
        float y = ground.position.getY() - position.getY();
        return y <= Config.HEIGHT && y >= -metrics.getY();
    }

    public float getPotholePadding() {
        return potholePadding;
    }

    /**
     * Randomly initialize this pothole
     */
    public void spawn(int lane) {
        this.lane = lane;
        calculatePositionByLane();
    }
}