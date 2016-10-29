package hu.pendroid.dnga.vectorcar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.adrianrobotka.brick.Drawer;
import com.adrianrobotka.brick.MainDrawer;

import hu.pendroid.dnga.vectorcar.Config;

/**
 * Draw game
 */
public final class GameDrawer extends MainDrawer {
    private static final int BORDER_WIDTH = 2, INNER_PADDING = 2;
    private Paint borderPaint;

    public GameDrawer(Context context) {
        super(context);
        init();
    }

    public GameDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB)
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        borderPaint = new Paint();
        borderPaint.setColor(Color.WHITE);
        borderPaint.setStrokeWidth(BORDER_WIDTH);
        borderPaint.setStyle(Paint.Style.STROKE);
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
        float r1 = (float) getWidth() / (Config.WIDTH + BORDER_WIDTH + INNER_PADDING * 2);
        float r2 = (float) getHeight() / (Config.HEIGHT + BORDER_WIDTH + INNER_PADDING * 2);

        if (r1 < r2) {
            canvas.translate(0, (getHeight() - r1 * (Config.HEIGHT + BORDER_WIDTH + INNER_PADDING * 2)) / 2);
            canvas.scale(r1, r1);
        }
        else {
            canvas.translate((getWidth() - r2 * (Config.WIDTH + BORDER_WIDTH + INNER_PADDING * 2)) / 2, 0);
            canvas.scale(r2, r2);
        }

        canvas.translate((float) BORDER_WIDTH / 2 + INNER_PADDING, (float) BORDER_WIDTH / 2 + INNER_PADDING);

        // Border
        canvas.drawRect(-INNER_PADDING, -INNER_PADDING, Config.WIDTH + INNER_PADDING,
                Config.HEIGHT + INNER_PADDING, borderPaint);
    }

    /**
     * Draw something after drawers
     *
     * @param canvas to draw on
     */
    private void postDrawers(Canvas canvas) {

    }

}