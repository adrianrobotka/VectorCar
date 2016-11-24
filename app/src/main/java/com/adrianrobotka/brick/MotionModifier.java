package com.adrianrobotka.brick;

import java.util.ArrayList;

/**
 * Keep models in motion with their motion vector
 */
public final class MotionModifier extends Modifier {

    /**
     * Get all models
     */
    private static final ArrayList<Model> models = Storage.getModels();

    @Override
    public void doRound() {
        for (Model model : models) {
            /*
             * Vector position = getPosition(model);
             * Vector motion = getMotion(model);
             * position.add(motion);
             *
             * in one line:
            */
            getPosition(model).add(getMotion(model));
        }
    }
}
