package com.adrianrobotka.brick;

/**
 * Represents an Euclidean vector
 */
public class Vector {
    /**
     * The width
     */
    protected float x;

    /**
     * The height
     */
    protected float y;

    /**
     * The depth
     */
    protected float z;

    /**
     * Null vector
     */
    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Two dimensional vector
     *
     * @param x The width
     * @param y The height
     */
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Tho dimensional vector
     *
     * @param x The width
     * @param y The height
     * @param z The depth
     */
    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Add vector
     *
     * @param vector The addend vector
     * @return The result
     */
    public Vector add(Vector vector) {
        return new Vector(x + vector.x, y + vector.y, z + vector.z);
    }

    /**
     * Substract vector
     *
     * @param vector The substrahend vector
     * @return The result
     */
    public Vector minus(Vector vector) {
        return new Vector(x - vector.x, y - vector.y, z - vector.z);
    }

    /**
     * Negate vector
     *
     * @return The result
     */
    public Vector negate() {
        return new Vector(-x, -y, -z);
    }

    /**
     * Multiply vector by a scalar
     *
     * @param n The multiplier
     * @return The result
     */
    public Vector multiple(float n) {
        return new Vector(x * n, y * n, z * n);
    }

    /**
     * Divide the vector with a scalar
     *
     * @param n The divider
     * @return The result
     */
    public Vector divide(float n) {
        return new Vector(x / n, y / n, z / n);
    }

    /**
     * Check equality with the vector
     *
     * @param o An object to compare
     * @return Is the object equals to the vector
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector))
            return false;

        Vector v = (Vector) o;
        return v.x == x && v.y == y && v.z == z;
    }

    /**
     * Create a readable text from the vector
     *
     * @return Vector's string representant
     */
    @Override
    public String toString() {
        if (z == 0)
            return "(" + x + ", " + y + ")";
        else
            return "(" + x + ", " + y + ", " + y + ")";
    }

    /**
     * Get the vector's width
     *
     * @return The vector's width
     */
    public float getX() {
        return x;
    }

    /**
     * Get the vector's height
     *
     * @return The vector's height
     */
    public float getY() {
        return y;
    }

    /**
     * Get the vector's depth
     *
     * @return The vector's depth
     */
    public float getZ() {
        return y;
    }
}
