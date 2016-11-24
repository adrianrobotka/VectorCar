package com.adrianrobotka.brick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * The main drawer
 */
public abstract class MainDrawer extends View {

    public MainDrawer(Context context) {
        super(context);
    }

    public MainDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected ArrayList<Drawer> getDrawers() {
        return Storage.drawers;
    }
}
