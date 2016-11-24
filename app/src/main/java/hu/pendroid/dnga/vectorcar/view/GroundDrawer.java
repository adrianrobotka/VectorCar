package hu.pendroid.dnga.vectorcar.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;

import hu.pendroid.dnga.vectorcar.R;
import hu.pendroid.dnga.vectorcar.model.Car;

public class GroundDrawer extends Drawer {
    /**
     * The car to get the ground speed
     */
    private Car car;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param carModel The car to get the ground speed
     */
    public GroundDrawer(Car carModel) {
        super(carModel);
        car = carModel;
    }

    @Override
    public void draw(Canvas canvas) {
        Resources res = context.getResources();
        Bitmap groundBitmap = BitmapFactory.decodeResource(res, R.drawable.ground);
        //groundBitmap = Bitmap.createScaledBitmap(groundBitmap, 5, 5, false);

        canvas.drawBitmap(groundBitmap, 0, 0, null);
    }
}
