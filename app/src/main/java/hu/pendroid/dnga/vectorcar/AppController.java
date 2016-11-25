package hu.pendroid.dnga.vectorcar;

import com.adrianrobotka.brick.Controller;
import com.adrianrobotka.brick.util.ProcessIndicator;

import java.util.ArrayList;
import java.util.List;

import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.model.Pothole;
import hu.pendroid.dnga.vectorcar.modifier.MotionModifier;
import hu.pendroid.dnga.vectorcar.modifier.PotholeSpawner;
import hu.pendroid.dnga.vectorcar.view.CarDrawer;
import hu.pendroid.dnga.vectorcar.view.GroundDrawer;
import hu.pendroid.dnga.vectorcar.view.PotholesDrawer;

final class AppController extends Controller {
    private static final String LOGTAG = AppController.class.getSimpleName();
    private static AppController instance = null;

    private Ground ground;
    private Car car;
    private List<Pothole> potholes = new ArrayList<>();

    private AppController() {
    }

    static AppController getInstance() {
        if (instance != null)
            return instance;
        else
            return instance = new AppController();
    }

    @Override
    public void init(ProcessIndicator indicator) throws IllegalStateException {
        super.init(indicator);
    }

    protected void createModels() {
        ground = new Ground();
        potholes.add(new Pothole()); // It`s enough, I think
        potholes.add(new Pothole());
        potholes.add(new Pothole());
        car = new Car(Config.LANES / 2, ground);
    }

    protected void createDrawers() {
        new GroundDrawer(ground);
        new CarDrawer(car);
        new PotholesDrawer(potholes, ground);
    }

    protected void createModifiers() {
        MotionModifier motionModifier = new MotionModifier();
        motionModifier.addModel(ground);
        new PotholeSpawner(potholes);
    }
}
