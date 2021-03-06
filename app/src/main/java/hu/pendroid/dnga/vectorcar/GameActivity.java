package hu.pendroid.dnga.vectorcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import hu.pendroid.dnga.vectorcar.model.Car;
import hu.pendroid.dnga.vectorcar.model.Ground;
import hu.pendroid.dnga.vectorcar.view.GameDrawer;

import static com.adrianrobotka.brick.Storage.getModels;

public final class GameActivity extends Activity implements
        GestureDetector.OnGestureListener {
    private static final String LOGTAG = GameActivity.class.getSimpleName();
    private int currentApiVersion;

    private GameDrawer drawer;
    private TextView velocityInfoText;
    private TextView roadInfoText;
    private TextView scoreInfoText;
    private TextView scoreInfoTextTitle;
    private TextView punctureText;
    private AppController controller = AppController.getInstance();
    private GestureDetectorCompat detector;

    private Car car;

    private String currentUserName;
    private int currentUserScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        askName();

        hideNavigationBar();

        setContentView(R.layout.activity_game);

        setCallbacks();

        car = (Car) getModels(Car.class).get(0);
        detector = new GestureDetectorCompat(this, this);
    }

    private void askName() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(getText(R.string.choose_name_title));

        // Set an EditText view to get user input
        final EditText input = new EditText(this);

        alert.setView(input);

        input.setScaleX(0.85f);
        input.setGravity(Gravity.CENTER_VERTICAL);


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                currentUserName = input.getText().toString();
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    private void refreshInfoPanel() {
        Ground ground = (Ground) getModels(Ground.class).get(0);
        final int multiplier = 3;
        int road = (int) (ground.position.getY() / Config.FPS * multiplier);
        int speed = (int) (Math.abs(ground.motion.getY()) * multiplier);
        roadInfoText.setText(road + "");
        velocityInfoText.setText(speed + "");
        scoreInfoText.setText((road + (Config.punctureCounter * Config.scoreReductionPuncture)) + "");
        currentUserScore = road;

        if (ground.isPunctured()) {
            punctureText.setVisibility(View.VISIBLE);
            scoreInfoText.setVisibility(View.GONE);
            scoreInfoTextTitle.setVisibility(View.GONE);
        } else {
            punctureText.setVisibility(View.GONE);
            scoreInfoText.setVisibility(View.VISIBLE);
            scoreInfoTextTitle.setVisibility(View.VISIBLE);
        }
    }

    private void setCallbacks() {
        drawer = (GameDrawer) findViewById(R.id.gameDrawer);
        velocityInfoText = (TextView) findViewById(R.id.velocityInfoText);
        roadInfoText = (TextView) findViewById(R.id.roadInfoText);
        scoreInfoText = (TextView) findViewById(R.id.scoreInfoText);
        scoreInfoTextTitle = (TextView) findViewById(R.id.scoreInfoTextTitle);
        punctureText = (TextView) findViewById(R.id.punctureText);

        controller.setDrawerCallback(new Runnable() {
            @Override
            public void run() {
                drawer.post(new Runnable() {
                    @Override
                    public void run() {
                        drawer.invalidate();
                        refreshInfoPanel();
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
                        TextView gameOverScoreText = (TextView) findViewById(R.id.gameoverScoreText);
                        findViewById(R.id.gameOverLayout).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });

                        currentUserName = currentUserName == null || currentUserName.equals("") ? "Unknown" : currentUserName; //If no name typed it is unknown

                        currentUserName = currentUserName.contains("%%") ? currentUserName.replaceAll("%%", "") : currentUserName; //Replace forbidden chars

                        //Save score value to sharedPreferences
                        OptionsActivity.DataSync dataSync = new OptionsActivity.DataSync(GameActivity.this);

                        String values = ""; //Saved string, contains key-value pairs separated with %% and ->
                        values = dataSync.getValue(getString(R.string.score_list_key), values); //Get the existing scores
                        Log.d("VC", "  " + (currentUserScore + (Config.punctureCounter * Config.scoreReductionPuncture)));

                        //Print the score on the gameover layout
                        gameOverScoreText.setText((currentUserScore + (Config.punctureCounter * Config.scoreReductionPuncture)) + "");

                        //Put the new score to the end
                        values += (currentUserScore + (Config.punctureCounter * Config.scoreReductionPuncture)) + "->" + currentUserName + "%%";

                        //Save it to the sharedPreferences
                        dataSync.setValue(getString(R.string.score_list_key), values);

                        //Reset punctureCounter
                        Config.punctureCounter = 0;
                    }
                });
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Do nothing on back button pressing
            controller.stop();
            return super.onKeyDown(keyCode, event);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.stop();
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        float x1 = event1.getX();
        float y1 = Config.HEIGHT - event1.getY();
        float x2 = event2.getX();
        float y2 = Config.HEIGHT - event2.getY();

        float dX = x2 - x1;
        float dY = y2 - y1;

        float length = (float) Math.sqrt(dX * dX + dY * dY);

        if (length >= Config.MIN_GESTURE_LENGTH) {


            if (Math.abs(dX) > Math.abs(dY)) {
                if (dX > 0) {
                    //right
                    car.goRight();

                    Log.d(LOGTAG, "Gesture: (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ") -> RIGHT");
                } else {
                    //left
                    car.goLeft();

                    Log.d(LOGTAG, "Gesture: (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ") -> LEFT");
                }
            } else {
                if (dY > 0) {
                    //up
                    if (dY > Config.HEIGHT / 10 * 5)
                        car.speedUp(5);
                    if (dY > Config.HEIGHT / 10 * 3)
                        car.speedUp(3);
                    if (dY > Config.HEIGHT / 10 * 2)
                        car.speedUp(2);
                    else
                        car.speedUp();

                    Log.d(LOGTAG, "Gesture: (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ") -> UP");
                } else {
                    //down
                    if (-dY > Config.HEIGHT / 10 * 5)
                        car.brake(5);
                    if (-dY > Config.HEIGHT / 10 * 3)
                        car.brake(3);
                    if (-dY > Config.HEIGHT / 10 * 2)
                        car.brake(2);
                    else
                        car.brake();

                    Log.d(LOGTAG, "Gesture: (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ") -> DOWN");
                }
            }
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

}