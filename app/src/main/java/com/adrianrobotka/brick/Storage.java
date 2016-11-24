package com.adrianrobotka.brick;

import java.util.ArrayList;

/**
 * Stores the models and run round of ModelModifiers and ModelObservers
 */
public final class Storage {
    /**
     * Models' list
     */
    protected final static ArrayList<Model> models = new ArrayList<>();

    /**
     * Model modifiers' list
     */
    protected final static ArrayList<Modifier> modifiers = new ArrayList<>();

    /**
     * Model drawers' list
     */
    protected final static ArrayList<Drawer> drawers = new ArrayList<>();

    /**
     * Add a model to the storage. Model's constructor call this.
     *
     * @param model the Model object
     */
    protected static void addModel(Model model) {
        models.add(model);
    }

    /**
     * Add a Modifier to the storage. Modifier's constructor call this.
     *
     * @param modifier The modifier
     */
    protected static void addModifier(Modifier modifier) {
        modifiers.add(modifier);
    }

    /**
     * Add a Drawer to the storage. Drawer's constructor call this.
     *
     * @param drawer The drawer
     */
    protected static void addDrawer(Drawer drawer) {
        drawers.add(drawer);
    }

    /**
     * Clean the storage
     */
    protected static void clean() {
        models.clear();
        drawers.clear();
        modifiers.clear();
    }

    /**
     * Get the models
     *
     * @return Model list
     */
    public static ArrayList<Model> getModels() {
        return models;
    }

    /**
     * Get a specific model list
     *
     * @param assignedFrom Parent of the models
     * @return Specified models' list
     */
    public static ArrayList<Model> getModels(Class assignedFrom) {
        ArrayList<Model> list = new ArrayList<>();

        for (Model model : models)
            if (model.getClass().isAssignableFrom(assignedFrom))
                list.add(model);

        return list;
    }

    /**
     * Get a specific model
     *
     * @param id Id of the models
     * @return Specified models' list
     */
    public static Model getModel(String id) {
        for (Model model : models)
            if (model.id != null && model.id.equals(id))
                return model;

        return null;
    }
}
