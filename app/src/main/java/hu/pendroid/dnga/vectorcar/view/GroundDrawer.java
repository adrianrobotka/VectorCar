package hu.pendroid.dnga.vectorcar.view;

import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;

import hu.pendroid.dnga.vectorcar.model.Car;

public class GroundDrawer extends Drawer {
    /**
     * The car to get the ground speed
     */
    private Car car;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param car The car to get the ground speed
     */
    public GroundDrawer(Car car) {
        super(car);
        this.car = car;
    }

    @Override
    public void draw(Canvas canvas) {
        /**/
    }
}
