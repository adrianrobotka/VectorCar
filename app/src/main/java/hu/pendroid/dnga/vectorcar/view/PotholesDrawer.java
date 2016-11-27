package hu.pendroid.dnga.vectorcar.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;
import com.adrianrobotka.brick.Vector;

import java.util.ArrayList;

import hu.pendroid.dnga.vectorcar.R;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.model.Pothole;

import static hu.pendroid.dnga.vectorcar.model.Pothole.fatalPotholeMetrics;
import static hu.pendroid.dnga.vectorcar.model.Pothole.lightPotholeMetrics;

public class PotholesDrawer extends Drawer {

    private ArrayList<Pothole> potholes;
    private Bitmap fatalPotholeBitmap;
    private Bitmap lightPotholeBitmap;
    private Ground ground;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param potholes The potholes to draw
     */
    public PotholesDrawer(ArrayList<Pothole> potholes, Ground ground) {
        super(null);
        this.potholes = potholes;
        this.ground = ground;
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);

        Resources res = context.getResources();

        // This order is needed
        Pothole firstFatalPothole = potholes.get(0);
        Pothole firstLightPothole = potholes.get(1);

        // Set the FatalPothole metrics
        fatalPotholeBitmap = BitmapFactory.decodeResource(res, R.drawable.pothole_fatal);
        float scale = firstFatalPothole.metrics.getX() / fatalPotholeBitmap.getWidth();
        float newWidth = (int) (fatalPotholeBitmap.getWidth() * scale);
        float newHeight = (int) (fatalPotholeBitmap.getHeight() * scale);
        fatalPotholeBitmap = Bitmap.createScaledBitmap(fatalPotholeBitmap, (int) newWidth, (int) newHeight, false);
        fatalPotholeMetrics = new Vector(newWidth, newHeight);

        // Set the FatalPothole metrics
        lightPotholeBitmap = BitmapFactory.decodeResource(res, R.drawable.pothole_light);
        scale = firstLightPothole.metrics.getX() / lightPotholeBitmap.getWidth();
        newWidth = (int) (lightPotholeBitmap.getWidth() * scale);
        newHeight = (int) (lightPotholeBitmap.getHeight() * scale);
        lightPotholeBitmap = Bitmap.createScaledBitmap(lightPotholeBitmap, (int) newWidth, (int) newHeight, false);
        lightPotholeMetrics = new Vector(newWidth, newHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        for (Pothole pothole : potholes) {
            float y = ground.position.getY() - pothole.position.getY();
            if (pothole.isOnTheRoad()) {

                if (pothole.getType() == Pothole.PotholeType.FATAL)
                    canvas.drawBitmap(fatalPotholeBitmap, pothole.position.getX(), y, null);

                if (pothole.getType() == Pothole.PotholeType.LIGHT)
                    canvas.drawBitmap(lightPotholeBitmap, pothole.position.getX(), y, null);
            }
        }
    }
}
