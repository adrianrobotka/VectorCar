package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Model;
import com.adrianrobotka.brick.Vector;

/**
 * The ground is a fix object in physics, so this model should contains null vectors.
 * But to imitate movements we will move the ground. (This is a drawer task)
 */
public final class Ground extends Model {

    public Ground() {
        // Nothing to do here
        position = new Vector();
        metrics = new Vector();
        motion = new Vector();
    }
}
