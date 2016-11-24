package com.adrianrobotka.brick;

import android.util.Log;

import com.adrianrobotka.brick.util.GameException;
import com.adrianrobotka.brick.util.ProcessIndicator;

/**
 * Initialize storage and create model logic
 */
public abstract class Controller {
    private static final String LOGTAG = Controller.class.getSimpleName();

    protected Runnable drawerCallback = null;
    protected Runnable gameOverCallback = null;
    protected ProcessIndicator indicator = null;
    protected float fps = 60;

    protected boolean run = false;

    /**
     * Set FPS
     *
     * @param fps The FPS
     */
    public void setFps(float fps) {
        this.fps = fps;
    }

    /**
     * Set process status indicator
     *
     * @param indicator The indicator
     */
    public void setIndicator(ProcessIndicator indicator) {
        this.indicator = indicator;
    }

    /**
     * Async prepare app logic. Create models, drawers and modifiers
     */
    public void init() {
        init(null);
    }

    /**
     * Async prepare app logic. Create models, drawers and modifiers
     *
     * @param indicator Callback to update process status
     */
    public void init(ProcessIndicator indicator) throws IllegalStateException {
        Log.d(LOGTAG, Controller.class.getSimpleName() + ".init()");

        run = false;

        if (indicator != null)
            this.indicator = indicator;

        // Create a worker thread for the model calculations
        new Thread(new Runnable() {
            @Override
            public void run() {
                Storage.clean();

                createModels();
                setProcessPercentage(50);

                createDrawers();
                setProcessPercentage(70);

                createBaseModifiers();
                setProcessPercentage(80);

                createModifiers();
                run = true;
                setProcessPercentage(100);
            }
        }).start();
    }

    public void setDrawerCallback(Runnable drawerCallback) {
        this.drawerCallback = drawerCallback;
    }

    public void setGameOverCallback(Runnable gameOverCallback) {
        this.gameOverCallback = gameOverCallback;
    }

    /**
     * Called frame by frame to do model modifications
     */
    private void doRound() throws GameException {
        // Modify models
        for (Modifier modifier : Storage.modifiers)
            modifier.doRound();

        // Draw models
        drawerCallback.run();
    }

    public void start() {
        Log.d(LOGTAG, Controller.class.getSimpleName() + ".start()");

        // Create a worker thread for the model calculations
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Set FPS (10 is a correction value)
                int waitPerFrame = (int) (1000 / fps - 10);

                // Reduce load
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // TODO Is it thread safe?
                long before, after;
                while (run) {
                    // TODO Is it worth?
                    before = System.currentTimeMillis();
                    try {
                        doRound();
                    } catch (GameException e) {
                        run = false;
                        gameOverCallback.run();
                        break;
                    }
                    after = System.currentTimeMillis();

                    try {
                        // Can lead exception
                        Thread.sleep(waitPerFrame - (after - before));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stop() {
        Log.d(LOGTAG, Controller.class.getSimpleName() + ".stop()");

        run = false;
    }

    /**
     * Callback to set init process percentage
     *
     * @param percentage Process' percentage
     */
    protected void setProcessPercentage(final int percentage) {
        if (indicator != null)
            indicator.setProcessPercentage(percentage);
    }

    /**
     * Create systematical modifiers
     */
    private void createBaseModifiers() {
        new MotionModifier();
    }

    /**
     * Create models
     */
    protected abstract void createModels();

    /**
     * Create drawers
     */
    protected abstract void createDrawers();

    /**
     * Create model modifiers
     */
    protected abstract void createModifiers();
}
