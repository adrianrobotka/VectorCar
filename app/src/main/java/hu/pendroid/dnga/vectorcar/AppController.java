package hu.pendroid.dnga.vectorcar;

import com.adrianrobotka.brick.Controller;
import com.adrianrobotka.brick.util.ProcessIndicator;

import java.util.ArrayList;
import java.util.List;

import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.FatalPothole;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.modifier.FatalPotHoleCollisionDetector;
import hu.pendroid.dnga.vectorcar.modifier.FatalPotholeSpawner;
import hu.pendroid.dnga.vectorcar.modifier.GroundMotionModifier;
import hu.pendroid.dnga.vectorcar.modifier.MotionModifier;
import hu.pendroid.dnga.vectorcar.view.CarDrawer;
import hu.pendroid.dnga.vectorcar.view.FatalPotholesDrawer;
import hu.pendroid.dnga.vectorcar.view.GroundDrawer;

final class AppController extends Controller {
    private static final String LOGTAG = AppController.class.getSimpleName();
    private static AppController instance = null;

    private Ground ground;
    private Car car;
    private List<FatalPothole> fatalPotholes = new ArrayList<>();

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

        for (int i = 0; i < 7; i++)
            fatalPotholes.add(new FatalPothole(ground));

        car = new Car(Config.LANES / 2, ground);
    }

    protected void createDrawers() {
        new GroundDrawer(ground);
        new CarDrawer(car);
        new FatalPotholesDrawer(fatalPotholes, ground);
    }

    protected void createModifiers() {
        MotionModifier motionModifier = new MotionModifier();
        motionModifier.addModel(ground);
        for (FatalPothole fatalPothole : fatalPotholes) {
            motionModifier.addModel(fatalPothole);
        }

        new GroundMotionModifier(ground);

        new FatalPotholeSpawner(fatalPotholes, ground, car);

        new FatalPotHoleCollisionDetector(fatalPotholes, ground, car);
    }
}
