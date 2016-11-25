package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Model;

/**
 * Model based on lane
 */
abstract class LaneBasedModel extends Model {
    int lane = 0;

    public int getLane() {
        return lane;
    }
}
