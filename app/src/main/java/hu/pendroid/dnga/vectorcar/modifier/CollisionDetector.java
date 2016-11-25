package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;
import com.adrianrobotka.brick.util.GameOverException;

import java.util.ArrayList;
import java.util.List;

import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.model.Pothole;

public final class CollisionDetector extends Modifier {
    private Car car;
    private Ground ground;
    private List<Pothole> potholes = new ArrayList<>();


    public CollisionDetector(List<Pothole> potholes, Ground ground, Car car) {
        this.potholes = potholes;
        this.ground = ground;
        this.car = car;
    }

    private boolean checkCollision(Pothole pothole) {
        if (!pothole.isOnTheRoad())
            return false;

        if (car.getLane() != pothole.getLane())
            return false;

        float y = ground.position.getY() - pothole.position.getY() + pothole.metrics.getY() - pothole.getPotholePadding() / 4;
        return y > car.position.getY();

    }

    @Override
    public void doRound() throws GameOverException {
        for (Pothole pothole : potholes) {
            if (checkCollision(pothole)) {
                throw new GameOverException();
            }
        }
    }
}
