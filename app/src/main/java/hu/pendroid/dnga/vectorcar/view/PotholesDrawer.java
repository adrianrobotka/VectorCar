package hu.pendroid.dnga.vectorcar.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;
import com.adrianrobotka.brick.Vector;

import java.util.List;

import hu.pendroid.dnga.vectorcar.R;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.model.Pothole;

public class PotholesDrawer extends Drawer {

    private List<Pothole> potholes;
    private Bitmap potholeBitmap;
    private Ground ground;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param potholes The potholes to draw
     */
    public PotholesDrawer(List<Pothole> potholes, Ground ground) {
        super(null);
        this.potholes = potholes;
        this.ground = ground;
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);

        Resources res = context.getResources();
        potholeBitmap = BitmapFactory.decodeResource(res, R.drawable.pothole_light);

        Pothole firstPothole = potholes.get(0);

        float scale = firstPothole.metrics.getX() / potholeBitmap.getWidth();
        float newWidth = (int) (potholeBitmap.getWidth() * scale);
        float newHeight = (int) (potholeBitmap.getHeight() * scale);

        for (Pothole pothole : potholes) {
            pothole.metrics = new Vector(newWidth, newHeight);
        }

        potholeBitmap = Bitmap.createScaledBitmap(potholeBitmap, (int) newWidth, (int) newHeight, false);
    }

    @Override
    public void draw(Canvas canvas) {
        for (Pothole pothole : potholes) {
            float y = ground.position.getY() - pothole.position.getY();
            if (pothole.isOnTheRoad()) {
                canvas.drawBitmap(potholeBitmap, pothole.position.getX(), y, null);
            }
        }
    }
}
