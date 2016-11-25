package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Model;

/**
 * Model based on lane
 */
abstract class LaneBasedModel extends Model {
    protected int lane;

    LaneBasedModel(int lane) {
        this.lane = lane;
    }
}
