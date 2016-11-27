package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;
import com.adrianrobotka.brick.util.GameException;
import com.adrianrobotka.brick.util.GameOverException;

import java.util.ArrayList;

import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.model.Pothole;

public class PotholeCollisionDetector extends Modifier {
    private Car car;
    private Ground ground;
    private ArrayList<Pothole> potholes = new ArrayList<>();


    public PotholeCollisionDetector(ArrayList<Pothole> potholes, Ground ground, Car car) {
        this.potholes = potholes;
        this.ground = ground;
        this.car = car;
    }

    private boolean checkCollision(Pothole fatalPothole) {
        boolean onTheRoad = fatalPothole.isOnTheRoad();
        if (!onTheRoad)
            return false;

        boolean inTheLane = car.getLane() == fatalPothole.getLane();
        if (!inTheLane)
            return false;

        float potholeScreenY = ground.position.getY() - fatalPothole.position.getY();

        boolean topPunch = potholeScreenY < car.position.getY() && potholeScreenY + fatalPothole.metrics.getY() > car.position.getY();

        boolean bottomPunch = potholeScreenY < car.position.getY() + car.metrics.getY() && potholeScreenY + fatalPothole.metrics.getY() > car.position.getY() + car.metrics.getY();

        return topPunch || bottomPunch;
    }

    @Override
    public void doRound() throws GameException {
        for (Pothole pothole : potholes) {
            if (checkCollision(pothole)) {
                if (pothole.getType() == Pothole.PotholeType.FATAL)
                    throw new GameOverException();

                if (pothole.getType() == Pothole.PotholeType.LIGHT) {
                    ground.doPuncture();
                    pothole.clear();
                }
            }
        }
    }
}
