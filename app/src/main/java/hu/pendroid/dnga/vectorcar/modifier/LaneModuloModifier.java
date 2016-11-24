package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;
import com.adrianrobotka.brick.util.GameException;

import hu.pendroid.dnga.vectorcar.model.Ground;

public class LaneModuloModifier extends Modifier {
    private Ground ground;

    public LaneModuloModifier(Ground ground) {
        this.ground = ground;
    }

    @Override
    public void doRound() throws GameException {
        // to do repeatable lanes
        //ground.position = new Vector(0, ground.position.getY() % ground.metrics.getY());
    }
}
