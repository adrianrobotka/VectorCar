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
        float laneBreak = ground.metrics.getY() * 0.8f;
        float startPoint = ground.position.getY() % (ground.metrics.getY() + laneBreak);
        float end = Config.HEIGHT + ground.metrics.getY();

        for (float y = startPoint; y < end; y += ground.metrics.getY() + laneBreak) {
            drawLaneLevel(y - ground.metrics.getY(), canvas);
        }
    }

    private void drawLaneLevel(float y1, Canvas canvas) {
        float zoneWidth = Config.WIDTH - ((Config.LANES - 1) * ground.metrics.getX());
        zoneWidth /= Config.LANES;

        for (int i = 1; i < Config.LANES; i++) {
            float x1 = zoneWidth * i;
            x1 += ground.metrics.getX() * (i - 1);

            float x2 = x1 + ground.metrics.getX();
            float y2 = y1 + ground.metrics.getY();

            canvas.drawRect((int) x1, (int) y1, (int) x2, (int) y2, groundPaint);
        }
    }
}
