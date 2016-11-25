package hu.pendroid.dnga.vectorcar.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;
import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.R;
import hu.pendroid.dnga.vectorcar.model.Car;

public class CarDrawer extends Drawer {

    private Car car;
    private Bitmap groundBitmap;

    /**
     * Create Drawer and add itself to the Storage
     *
     * @param car The model to draw
     */
    public CarDrawer(Car car) {
        super(car);
        this.car = car;
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);

        Resources res = context.getResources();
        groundBitmap = BitmapFactory.decodeResource(res, R.drawable.car);
        float scale = car.metrics.getX() / groundBitmap.getWidth();
        float newWidth = (int) (groundBitmap.getWidth() * scale);
        float newHeight = (int) (groundBitmap.getHeight() * scale);
        car.metrics = new Vector(newWidth, newHeight);

        // Just refresh car`s position
        car.goRight();
        car.goLeft();
    }

    @Override
    public void draw(Canvas canvas) {
        groundBitmap = Bitmap.createScaledBitmap(groundBitmap, (int) car.metrics.getX(), (int) car.metrics.getY(), false);
        canvas.drawBitmap(groundBitmap, car.position.getX(), car.position.getY(), null);
    }
}
