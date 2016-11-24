package com.adrianrobotka.brick;

/**
 * A physical model
 * <p>
 * A thing on the display that's part of the app
 */
public abstract class Model {
    /**
     * The position vector
     * <p>
     * The model's closest point to the pole
     */
    public Vector position;

    /**
     * The metrics vector
     * <p>
     * The expansion of the model
     */
    public Vector metrics;

    /**
     * The motion vector
     * <p>
     * Model should move(change position vector) by this vector per frame.
     */
    public Vector motion;

    /**
     * Id of the model (just for unique logic)
     */
    public String id = null;

    // TODO What about attributes?

    /**
     * Create Model and add itself to the Storage
     */
    protected Model() {
        Storage.addModel(this);
    }

    /**
     * Create a readable text from the model
     *
     * @return Vector's string representant
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + position + " " + metrics + " " + motion;
    }
}
