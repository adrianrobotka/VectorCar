package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Model;
import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * The car
 */
public final class Car extends Model {
    public Car(Vector position) {
        this.position = position;
        metrics = new Vector(1, 1);
        motion = new Vector(Config.INITIAL_CAR_SPEED, 0);
    }
}
