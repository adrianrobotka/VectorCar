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
    public void doRound() throws GameOverException {
        for (FatalPothole fatalPothole : fatalPotholes) {
            if (checkCollision(fatalPothole)) {
                throw new GameOverException();
            }
        }
    }
}
