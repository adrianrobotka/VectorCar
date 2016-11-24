package hu.pendroid.dnga.vectorcar.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.adrianrobotka.brick.Drawer;

import hu.pendroid.dnga.vectorcar.R;
import hu.pendroid.dnga.vectorcar.model.Car;

public class CarDrawer extends Drawer {

    private Car car;

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
    public void draw(Canvas canvas) {
        Resources res = context.getResources();
        Bitmap groundBitmap = BitmapFactory.decodeResource(res, R.drawable.car);
        groundBitmap = Bitmap.createScaledBitmap(groundBitmap, (int) car.metrics.getX(), (int) car.metrics.getY(), false);
        canvas.drawBitmap(groundBitmap, car.position.getX(), car.position.getY(), null);
    }
}
