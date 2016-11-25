package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;
import com.adrianrobotka.brick.util.GameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.pendroid.dnga.vectorcar.Config;
import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.FatalPothole;
import hu.pendroid.dnga.vectorcar.model.Ground;

public class FatalPotholeSpawner extends Modifier {
    private static Random random = new Random();
    private Car car;
    private Ground ground;
    private List<FatalPothole> fatalPotholes = new ArrayList<>();
    private int time = 0;
    private int[] spawnBlock;

    public FatalPotholeSpawner(List<FatalPothole> fatalPotholes, Ground ground, Car car) {
        this.fatalPotholes = fatalPotholes;
        this.ground = ground;
        this.car = car;
        spawnBlock = new int[fatalPotholes.size()];
    }

    @Override
    public void doRound() throws GameException {
        List<FatalPothole> freeFatalPotholes = getFreePotholes();

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
            laneSpawnSleep = 7;

        if (time % (int) (Config.FPS * laneSpawnSleep) == 0) {

            int lane = car.getLane();

            if (spawnBlock[lane] == 0 && freeFatalPotholes.size() > 0) {
                spawnBlock[lane] = (int) (Config.FPS * laneSpawnSleep);
                freeFatalPotholes.get(0).spawn(lane);
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

    public List<FatalPothole> getFreePotholes() {
        List<FatalPothole> freeFatalPotholes = new ArrayList<>();
        for (FatalPothole fatalPothole : fatalPotholes) {
            if (!fatalPothole.isOnTheRoad()) {
                freeFatalPotholes.add(fatalPothole);
            }
        }
        return freeFatalPotholes;
    }
}
