package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;
import com.adrianrobotka.brick.util.GameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.pendroid.dnga.vectorcar.model.Pothole;

public class PotholeSpawner extends Modifier {

    private static Random random = new Random();
    private List<Pothole> potholes = new ArrayList<>();


    public PotholeSpawner(List<Pothole> potholes) {
        this.potholes = potholes;
    }

    @Override
    public void doRound() throws GameException {
        boolean spawn = random.nextInt(10) == 2;
        boolean stop = false;
        if (spawn && !stop) {
            potholes.get(1).spawn();
            stop = true;
        }
    }
}
