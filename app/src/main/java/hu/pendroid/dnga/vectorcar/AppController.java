package hu.pendroid.dnga.vectorcar;

import com.adrianrobotka.brick.Controller;
import com.adrianrobotka.brick.util.ProcessIndicator;

import java.util.ArrayList;

import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.model.Pothole;
import hu.pendroid.dnga.vectorcar.modifier.GroundMotionModifier;
import hu.pendroid.dnga.vectorcar.modifier.MotionModifier;
import hu.pendroid.dnga.vectorcar.modifier.PotholeCollisionDetector;
import hu.pendroid.dnga.vectorcar.modifier.PotholeSpawner;
import hu.pendroid.dnga.vectorcar.modifier.PunctureTimeModifier;
import hu.pendroid.dnga.vectorcar.view.CarDrawer;
import hu.pendroid.dnga.vectorcar.view.GroundDrawer;
import hu.pendroid.dnga.vectorcar.view.PotholesDrawer;

final class AppController extends Controller {
    private static final String LOGTAG = AppController.class.getSimpleName();
    private static AppController instance = null;

    private Ground ground;
    private Car car;
    private ArrayList<Pothole> potholes = new ArrayList<>();

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
        potholes.clear();
        super.init(indicator);
    }

    protected void createModels() {
        ground = new Ground();

        // This order is important for the drawer init
        potholes.add(new Pothole(ground, Pothole.PotholeType.FATAL));
        potholes.add(new Pothole(ground, Pothole.PotholeType.LIGHT));

        for (int i = 0; i < 5; i++) {
            // potholes.add(new Pothole(ground)); // without any type
        }

        car = new Car(Config.LANES / 2, ground);
    }

    protected void createDrawers() {
        new GroundDrawer(ground);
        new PotholesDrawer(potholes, ground);
        new CarDrawer(car);
    }

    protected void createModifiers() {
        MotionModifier motionModifier = new MotionModifier();
        motionModifier.addModel(ground);

        for (Pothole pothole : potholes) {
            motionModifier.addModel(pothole);
        }

        new GroundMotionModifier(ground);
        new PotholeSpawner(potholes, ground, car);
        new PotholeCollisionDetector(potholes, ground, car);
        new PunctureTimeModifier(ground);
    }
}
