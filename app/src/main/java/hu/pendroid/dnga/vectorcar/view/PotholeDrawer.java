package hu.pendroid.dnga.vectorcar.view;

import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;

import hu.pendroid.dnga.vectorcar.model.Pothole;

public class PotholeDrawer extends Drawer {
    private Pothole pothole;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param pothole The pothole to draw
     */

    public PotholeDrawer(Pothole pothole) {
        super(pothole);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
