package hu.pendroid.dnga.vectorcar.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.adrianrobotka.brick.Drawer;

import hu.pendroid.dnga.vectorcar.Config;
import hu.pendroid.dnga.vectorcar.model.Ground;

public class GroundDrawer extends Drawer {
    Paint groundPaint;
    /**
     * The car to get the ground speed
     */
    private Ground ground;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param ground The model to draw
     */
    public GroundDrawer(Ground ground) {
        super(ground);
        this.ground = ground;

        groundPaint = new Paint();
        groundPaint.setColor(Color.WHITE);
    }

    @Override
    public void draw(Canvas canvas) {
        drawLaneLevel(20, canvas);
    }

    private void drawLaneLevel(int y, Canvas canvas) {
        float zoneWidth = Config.WIDTH / Config.LANES;
        zoneWidth -= Config.LANES * ground.metrics.getY();

        for (int i = 0; i < Config.LANES; i++) {
            int x1 = (int) (zoneWidth * (i + 1) + ground.metrics.getY() * i);
            int y1 = y;

            int x2 = (int) (x1 + ground.metrics.getX());
            int y2 = (int) (y1 + ground.metrics.getY());

            canvas.drawRect(x1, y1, x2, y2, groundPaint);
        }
    }
}
