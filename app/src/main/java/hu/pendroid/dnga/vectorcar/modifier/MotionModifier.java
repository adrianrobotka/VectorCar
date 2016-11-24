package hu.pendroid.dnga.vectorcar.modifier;

import com.adrianrobotka.brick.Model;
import com.adrianrobotka.brick.Modifier;

import java.util.ArrayList;

/**
 * Keep models in motion with their motion vector
 */
public final class MotionModifier extends Modifier {

    /**
     * Get all models
     */
    private ArrayList<Model> models = new ArrayList<>();

    public void addModel(Model model) {
        models.add(model);
    }

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
