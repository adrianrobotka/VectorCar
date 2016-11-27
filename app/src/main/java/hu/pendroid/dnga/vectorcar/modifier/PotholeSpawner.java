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
    private ArrayList<Pothole> potholes = new ArrayList<>();
    private int time = 0;
    private int[] spawnBlock;

    public PotholeSpawner(ArrayList<Pothole> potholes, Ground ground, Car car) {
        this.potholes = potholes;
        this.ground = ground;
        this.car = car;
        spawnBlock = new int[Config.LANES];
    }

    @Override
    public void doRound() throws GameException {
        List<Pothole> freePotholes = getFreePotholes();

        decreaseBlocks();

        float laneSpawnSleep;

        if (ground.motion.getY() > 60)
            laneSpawnSleep = 0.5f;
        else if (ground.motion.getY() > 40)
            laneSpawnSleep = 1;
        else if (ground.motion.getY() > 20)
            laneSpawnSleep = 2;
        else if (ground.motion.getY() > 10)
            laneSpawnSleep = 3;
        else if (ground.motion.getY() > 5)
            laneSpawnSleep = 4;
        else if (ground.motion.getY() > 2)
            laneSpawnSleep = 5;
        else
            laneSpawnSleep = 10;

        if (time % (int) (Config.FPS * laneSpawnSleep) == 0) {

            int lane = car.getLane();

            if (spawnBlock[lane] == 0 && freePotholes.size() > 0) {
                spawnBlock[lane] = (int) (Config.FPS * laneSpawnSleep);
                if (random.nextBoolean())
                    freePotholes.get(0).spawn(lane, Pothole.PotholeType.FATAL);
                else
                    freePotholes.get(0).spawn(lane, Pothole.PotholeType.LIGHT);
            }
        }

        time++;
    }

    private void decreaseBlocks() {
        if (ground.motion.getY() > 1) {
            for (int i = 0; i < spawnBlock.length; i++) {
                spawnBlock[i]--;
                if (spawnBlock[i] < 0) {
                    spawnBlock[i] = 0;
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
