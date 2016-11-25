package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;
import com.adrianrobotka.brick.util.GameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.pendroid.dnga.vectorcar.Config;
import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.model.Pothole;

public class PotholeSpawner extends Modifier {
    private static Random random = new Random();
    private Car car;
    private Ground ground;
    private List<Pothole> potholes = new ArrayList<>();
    private int time = 0;
    private int[] spawnBlock;

    public PotholeSpawner(List<Pothole> potholes, Ground ground, Car car) {
        this.potholes = potholes;
        this.ground = ground;
        this.car = car;
        spawnBlock = new int[potholes.size()];
    }

    @Override
    public void doRound() throws GameException {
        List<Pothole> freePotholes = getFreePotholes();

        if (time % (Config.FPS * 2) == 0) {
            time = 0;

            int lane = car.getLane();

            if (spawnBlock[lane] == 0) {
                spawnBlock[lane] = (int) Config.FPS;
                freePotholes.get(0).spawn(lane);
            }

            decreaseBlocks();
            time++;
        }
    }

    private void decreaseBlocks() {
        if (ground.motion.getY() != 0) {
            for (int i = 0; i < potholes.size(); i++) {
                if (spawnBlock[i] > 0) {
                    spawnBlock[i]--;
                }
            }
        }
    }

    public List<Pothole> getFreePotholes() {
        List<Pothole> freePotholes = new ArrayList<>();
        for (Pothole pothole : potholes) {
            if (!pothole.isOnTheRoad()) {
                freePotholes.add(pothole);
            }
        }
        return freePotholes;
    }
}
