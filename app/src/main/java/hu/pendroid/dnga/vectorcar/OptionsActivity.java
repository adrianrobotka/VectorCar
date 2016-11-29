package hu.pendroid.dnga.vectorcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    TextView maxSpeedText, laneNumberText;
    SeekBar maxSpeed, laneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        maxSpeedText = (TextView) findViewById(R.id.maxSpeedTextView);
        laneNumberText = (TextView) findViewById(R.id.laneNumberTextView);

        maxSpeed = (SeekBar) findViewById(R.id.maxSpeedSeekBar);
        laneNumber = (SeekBar) findViewById(R.id.laneNumberSeekBar);
        initValues();


        maxSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                maxSpeedText.setText(getResources().getText(R.string.max_speed).toString() + i);
                Config.MAX_SPEED = (float)i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        laneNumber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                laneNumberText.setText(getResources().getText(R.string.lane_number).toString() + (i/5));
                Config.LANES = i / 5;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    protected void onPause() {
        super.onPause();
        finish();
    }

    private void initValues()
    {
        maxSpeedText.setText(getResources().getText(R.string.max_speed).toString() + Config.MAX_SPEED);
        laneNumberText.setText(getResources().getText(R.string.lane_number).toString() + Config.LANES);

        maxSpeed.setProgress((int)Config.MAX_SPEED);
        laneNumber.setProgress((int)Config.LANES*5);

    }
}
