package hu.pendroid.dnga.vectorcar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.adrianrobotka.brick.Drawer;
import com.adrianrobotka.brick.MainDrawer;

/**
 * Draw game
 */
public final class GameDrawer extends MainDrawer {
    private static final String LOGTAG = GameDrawer.class.getSimpleName();

    /**
     * Creates GameDrawer
     *
     * @param context Context of the activity (to get resources)
     */
    public GameDrawer(Context context) {
        super(context);
        construct(context);
    }

    public GameDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        construct(context);
    }

    public GameDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        construct(context);
    }

    private void construct(Context context) {
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

        try {
            for (Drawer drawer : getDrawers())
                drawer.draw(canvas);
        } catch (NullPointerException e) {
            Log.e(LOGTAG, "A drawer failed:");
            Log.e(LOGTAG, e.getMessage());
        }

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