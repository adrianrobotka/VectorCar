package hu.pendroid.dnga.vectorcar;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import hu.pendroid.dnga.vectorcar.view.GameDrawer;

public final class GameActivity extends Activity {
    private static final String LOGTAG = GameActivity.class.getSimpleName();
    private GameDrawer drawer;
    private AppController controller = AppController.getInstance();

    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideNavigationBar();

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Do nothing on back button pressing
            return false;
        }

        return super.onKeyDown(keyCode, event);
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

    private void hideNavigationBar() {
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}