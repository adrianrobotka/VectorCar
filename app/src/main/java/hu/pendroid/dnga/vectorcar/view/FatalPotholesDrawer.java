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
import hu.pendroid.dnga.vectorcar.model.FatalPothole;
import hu.pendroid.dnga.vectorcar.model.Ground;

public class FatalPotholesDrawer extends Drawer {

    private List<FatalPothole> fatalPotholes;
    private Bitmap potholeBitmap;
    private Ground ground;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param fatalPotholes The fatalPotholes to draw
     */
    public FatalPotholesDrawer(List<FatalPothole> fatalPotholes, Ground ground) {
        super(null);
        this.fatalPotholes = fatalPotholes;
        this.ground = ground;
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);

        Resources res = context.getResources();
        potholeBitmap = BitmapFactory.decodeResource(res, R.drawable.pothole_fatal);

        FatalPothole firstFatalPothole = fatalPotholes.get(0);

        float scale = firstFatalPothole.metrics.getX() / potholeBitmap.getWidth();
        float newWidth = (int) (potholeBitmap.getWidth() * scale);
        float newHeight = (int) (potholeBitmap.getHeight() * scale);

        for (FatalPothole fatalPothole : fatalPotholes) {
            fatalPothole.metrics = new Vector(newWidth, newHeight);
        }

        potholeBitmap = Bitmap.createScaledBitmap(potholeBitmap, (int) newWidth, (int) newHeight, false);
    }

    @Override
    public void draw(Canvas canvas) {
        for (FatalPothole fatalPothole : fatalPotholes) {
            float y = ground.position.getY() - fatalPothole.position.getY();
            if (fatalPothole.isOnTheRoad()) {
                canvas.drawBitmap(potholeBitmap, fatalPothole.position.getX(), y, null);
            }
        }
    }
}
