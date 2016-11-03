package hu.pendroid.dnga.vectorcar;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import hu.pendroid.dnga.vectorcar.view.GameDrawer;

public final class GameActivity extends Activity {
    private static final String LOGTAG = GameActivity.class.getSimpleName();
    private GameDrawer drawer;
    private AppController controller = AppController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        drawer = (GameDrawer) findViewById(R.id.gameDrawer);
        controller.setDrawerCallback(new Runnable() {
            @Override
            public void run() {
                drawer.post(new Runnable() {
                    @Override
                    public void run() {
                        drawer.invalidate();
                    }
                });
            }
        });

        controller.setGameOverCallback(new Runnable() {
            @Override
            public void run() {
                Log.i(LOGTAG, "Game over");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(R.layout.gameover);
                        TextView tv = (TextView) findViewById(R.id.gameoverLabel);
                        tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Wanted.ttf"));
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.start();
        Log.i(LOGTAG, "AppController.start()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.stop();
        Log.i(LOGTAG, "AppController.stop()");
    }

    public void refresh() {
        drawer.invalidate();
    }
}