package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;
import com.adrianrobotka.brick.util.GameOverException;

import java.util.ArrayList;
import java.util.List;

import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.FatalPothole;
import hu.pendroid.dnga.vectorcar.model.Ground;

public final class FatalPotHoleCollisionDetector extends Modifier {
    private Car car;
    private Ground ground;
    private List<FatalPothole> fatalPotholes = new ArrayList<>();


    public FatalPotHoleCollisionDetector(List<FatalPothole> fatalPotholes, Ground ground, Car car) {
        this.fatalPotholes = fatalPotholes;
        this.ground = ground;
        this.car = car;
    }

    private boolean checkCollision(FatalPothole fatalPothole) {
        if (!fatalPothole.isOnTheRoad())
            return false;

        if (car.getLane() != fatalPothole.getLane())
            return false;

        if (car.position.getY() + car.metrics.getY() < fatalPothole.position.getY())
            return false;

        float potholeScreenY = ground.position.getY() - fatalPothole.position.getY();
        potholeScreenY += fatalPothole.metrics.getY();

        return car.position.getY() < potholeScreenY;
    }

    @Override
    public void doRound() throws GameOverException {
        for (FatalPothole fatalPothole : fatalPotholes) {
            if (checkCollision(fatalPothole)) {
                throw new GameOverException();
            }
        }
    }
}
