package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

import java.util.Random;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The pothole that causes problem
 */
public final class Pothole extends LaneBasedModel {
    private static final float potholePadding = 20;
    private static Random random = new Random();

    public Pothole() {
        float zoneWidth = Ground.getZoneWidth();

        metrics = new Vector(zoneWidth - potholePadding * 2, 0);
        motion = new Vector(); // DO NOT USE THIS (the ground moves)
        position = new Vector(Config.WIDTH, Config.HEIGHT);
    }

    private void calculatePositionByLane() {
        position = Ground.calculateModelPositionByLane(lane).add(new Vector(potholePadding, -20 - metrics.getY()));
    }

    /**
     * Randomly initialize this pothole
     */
    public void spawn() {
        int lanes = random.nextInt(lane);
        calculatePositionByLane();
    }
}