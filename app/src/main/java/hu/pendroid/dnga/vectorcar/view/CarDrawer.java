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
    private Bitmap carBitmap;

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
        carBitmap = BitmapFactory.decodeResource(res, R.drawable.car);
        float scale = car.metrics.getX() / carBitmap.getWidth();
        float newWidth = (int) (carBitmap.getWidth() * scale);
        float newHeight = (int) (carBitmap.getHeight() * scale);
        car.metrics = new Vector(newWidth, newHeight);

        // Just refresh car`s position
        car.refresh();

        carBitmap = Bitmap.createScaledBitmap(carBitmap, (int) car.metrics.getX(), (int) car.metrics.getY(), false);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(carBitmap, car.position.getX(), car.position.getY(), null);
    }
}
