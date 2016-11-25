package hu.pendroid.dnga.vectorcar.view;

import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;

import hu.pendroid.dnga.vectorcar.model.LightPothole;

public class LightPotholeDrawer extends Drawer {
    private LightPothole lightPothole;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param lightPothole The fatal pothole to draw
     */

    public LightPotholeDrawer(LightPothole lightPothole) {
        super(lightPothole);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}