package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Modifier;

import hu.pendroid.dnga.vectorcar.model.Ground;

/**
 * Discount puncture time
 */
public final class PunctureTimeModifier extends Modifier {

    /**
     * Get all models
     */
    private Ground ground;

    public PunctureTimeModifier(Ground ground) {
        this.ground = ground;
    }

    @Override
    public void doRound() {
        ground.discountPuncture();
    }
}
