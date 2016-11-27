package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;
import com.adrianrobotka.brick.Vector;

import hu.pendroid.dnga.vectorcar.Config;
import hu.pendroid.dnga.vectorcar.model.Ground;

/**
 * Keep models in motion with their motion vector
 */
public final class GroundMotionModifier extends Modifier {

    private Ground ground;

    public GroundMotionModifier(Ground ground) {
        this.ground = ground;
    }

    @Override
    public void doRound() {
        // 15 is a magic constant
        Vector newMotion = ground.motion.subtract(ground.motion.divide(15 * Config.FPS));

        if (ground.isPunctured()) {
            newMotion = newMotion.divide(2);
        }

        if (newMotion.getY() >= 0)
            ground.motion = newMotion;
        else
            ground.motion = new Vector();
    }
}
