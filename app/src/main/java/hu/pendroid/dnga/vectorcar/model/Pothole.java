package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Model;
import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The Pothole
 */
public final class Pothole extends Model {
    public Pothole(Vector position) {
        this.position = position;
        metrics = new Vector(1, 1);
        motion = new Vector(Config.INITIAL_CAR_SPEED, 0);
    }
}