package com.adrianrobotka.brick;

import com.adrianrobotka.brick.util.GameException;

/**
 * Able to modify the models
 * <p>
 * A rule to do modifications.
 */
public abstract class Modifier {

    /**
     * Initialize modifier
     */
    public Modifier() {
        Storage.addModifier(this);
    }

    /**
     * A method that is called frame by frame to do class' target
     */
    public abstract void doRound() throws GameException;

    /**
     * Get the position vector of a model
     *
     * @param model The model
     * @return The model's position vector
     */
    protected Vector getPosition(Model model) {
        return model.position;
    }

    /**
     * Get the metrics vector of a model
     *
     * @param model The model
     * @return The model's metrics vector
     */
    protected Vector getMetrics(Model model) {
        return model.metrics;
    }

    /**
     * Get the motion vector of a model
     *
     * @param model The model
     * @return The model's motion vector
     */
    protected Vector getMotion(Model model) {
        return model.motion;
    }
}
