package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * A pothole that causes problem on the car
 */
public final class Pothole extends LaneBasedModel {
    public static Vector fatalPotholeMetrics;
    public static Vector lightPotholeMetrics;
    private float potholePadding;
    private Ground ground;
    private PotholeType type;

    public Pothole(Ground ground, PotholeType type) {
        this.ground = ground;
        setType(type);

        motion = new Vector(); // DO NOT USE THIS (the ground moves)
        position = new Vector(0, Config.HEIGHT * 2); // jus be out of the screen

        // To init draw(real) metrics with the drawer
        float zoneWidth = Ground.getZoneWidth();
        metrics = new Vector(zoneWidth - potholePadding * 2, 0);
    }

    public Pothole(Ground ground) {
        this.ground = ground;

        motion = new Vector(); // DO NOT USE THIS (the ground moves)
        position = new Vector(0, Config.HEIGHT * 2); // jus be out of the screen
    }

    private void calculatePositionByLane() {
        position = Ground.calculateModelPositionByLane(lane).add(new Vector(potholePadding, ground.position.getY() - metrics.getY()));
    }

    public void clear() {
        position = new Vector(0, Config.HEIGHT * 2); // jus be out of the screen
    }

    public PotholeType getType() {
        return type;
    }

    private void setType(PotholeType type) {
        this.type = type;
        float zoneWidth = Ground.getZoneWidth();

        if (type == PotholeType.FATAL) {
            potholePadding = zoneWidth * 0.1f;
            metrics = fatalPotholeMetrics;
        }

        if (type == PotholeType.LIGHT) {
            potholePadding = zoneWidth * 0.3f;
            metrics = lightPotholeMetrics;
        }
    }

    public boolean isOnTheRoad() {
        float y = ground.position.getY() - position.getY();
        return y <= Config.HEIGHT && y >= -metrics.getY();
    }

    /**
     * Randomly initialize this pothole
     */
    public void spawn(int lane, PotholeType type) {
        this.lane = lane;
        setType(type);
        calculatePositionByLane();
    }

    public enum PotholeType {
        FATAL, LIGHT
    }
}