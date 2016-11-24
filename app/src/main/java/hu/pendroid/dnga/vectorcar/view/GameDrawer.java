package hu.pendroid.dnga.vectorcar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.adrianrobotka.brick.Drawer;
import com.adrianrobotka.brick.MainDrawer;

/**
 * Draw game
 */
public final class GameDrawer extends MainDrawer {
    /**
     * Creates GameDrawer
     *
     * @param context Context of the activity (to get resources)
     */
    public GameDrawer(Context context) {
        super(context);
        init(context);
    }

    public GameDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB)
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        for (Drawer drawer : getDrawers()) {
            drawer.setContext(context);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        preDrawers(canvas);

        for (Drawer drawer : getDrawers())
            drawer.draw(canvas);

        postDrawers(canvas);
    }

    /**
     * Draw something before drawers
     *
     * @param canvas to draw on
     */
    private void preDrawers(Canvas canvas) {

    }

    /**
     * Draw something after drawers
     *
     * @param canvas to draw on
     */
    private void postDrawers(Canvas canvas) {

    }

}