package com.adrianrobotka.brick.util;

import com.adrianrobotka.brick.Vector;

public class GestureLine {
    private Vector A;
    private Vector B;

    public GestureLine(Vector start, Vector end) {
        this.A = start;
        this.B = end;
    }

    public GestureLineEquation getGestureLineEquation() {
        float m = (A.getY() - B.getY()) / (A.getX() - B.getX());
        float b = A.getY() - A.getX() * m;
        return new GestureLineEquation(m, b);
    }

    public float getLenght() {
        float a = (A.getY() - B.getY());
        float b = (A.getX() - B.getX());
        return (float) Math.sqrt(a * a + b * b);
    }

    public class GestureLineEquation {
        private float m;
        private float b;

        public GestureLineEquation(float m, float b) {
            this.m = m;
            this.b = b;
        }

        public float getM() {
            return m;
        }

        public float getB() {
            return b;
        }
    }
}
