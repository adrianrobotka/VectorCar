package hu.pendroid.dnga.vectorcar.model;

import com.adrianrobotka.brick.Model;

/**
 * Model based on lane
 */
abstract class LaneModel extends Model {
    protected int lane;

    LaneModel(int lane) {
        this.lane = lane;
    }
}
