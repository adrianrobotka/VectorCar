package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The pothole that causes problem on the car
 */
public final class FatalPothole extends LaneBasedModel {
    public static final float potholePadding = 20;
    private Ground ground;

    public FatalPothole(Ground ground) {
        this.ground = ground;

        float zoneWidth = Ground.getZoneWidth();

        metrics = new Vector(zoneWidth - potholePadding * 2, 0);
        motion = new Vector(); // DO NOT USE THIS (the ground moves)
        position = new Vector(0, Config.HEIGHT * 2);
    }

    private void calculatePositionByLane() {
        position = Ground.calculateModelPositionByLane(lane).add(new Vector(potholePadding, ground.position.getY() - metrics.getY()));
    }

    public boolean isOnTheRoad() {
        float y = ground.position.getY() - position.getY();
        return y <= Config.HEIGHT && y >= -metrics.getY();
    }

    /**
     * Randomly initialize this pothole
     */
    public void spawn(int lane) {
        this.lane = lane;
        calculatePositionByLane();
    }
}