package com.adrianrobotka.brick.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Collect accelerometer data
 */
public final class PositionSensor implements SensorEventListener {
    private static SensorEventListener listener = new PositionSensor();
    private static SensorManager sensorManager;
    private static Sensor sensor;
    private static float[] lastMeasurement = new float[3];
    private static boolean isSensingStarted = false;

    public static synchronized void start(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(listener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        isSensingStarted = true;
    }

    public static synchronized void stop() {
        sensorManager.unregisterListener(listener, sensor);
        isSensingStarted = false;
        sensorManager = null;
        sensor = null;

        synchronized (lastMeasurement) {
            lastMeasurement = new float[3];
        }
    }

    public static synchronized float[] getLastMeasurement() {
        if (!isSensingStarted)
            throw new IllegalStateException("PositionSensor has not been started");

        return lastMeasurement;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        synchronized (lastMeasurement) {
            lastMeasurement = values;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(getClass().getName(), "onAccuracyChanged()");
    }
}
