package com.adrianrobotka.brick;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Draw a model to the canvas
 */
public abstract class Drawer {
    protected Model model;
    protected Context context;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param model The model to draw
     */
    public Drawer(Model model) {
        this.model = model;
        Storage.addDrawer(this);
    }

    /**
     * Set context to the drawer
     *
     * @param context Context of the activity (to get resources)
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Draw the model to the canvas
     */
    public abstract void draw(Canvas canvas);
}
