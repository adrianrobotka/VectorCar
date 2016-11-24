package hu.pendroid.dnga.vectorcar.view;

import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;

import hu.pendroid.dnga.vectorcar.model.FatalPothole;

public class FatalPotholeDrawer extends Drawer {
    private FatalPothole fatalPothole;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param fatalPothole The fatal pothole to draw
     */

    public FatalPotholeDrawer(FatalPothole fatalPothole) {
        super(fatalPothole);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}