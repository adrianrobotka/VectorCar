package hu.pendroid.dnga.vectorcar.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;
import com.adrianrobotka.brick.Model;

import hu.pendroid.dnga.vectorcar.R;

public class GroundDrawer extends Drawer {

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param model The model to draw
     */
    public GroundDrawer(Model model) {
        super(model);
    }

    @Override
    public void draw(Canvas canvas) {
        Resources res = context.getResources();
        Bitmap groundBitmap = BitmapFactory.decodeResource(res, R.drawable.ground);
        groundBitmap = Bitmap.createScaledBitmap(groundBitmap, 5, 5, false);

        canvas.drawBitmap(groundBitmap, 0, 0, null);
    }
}
