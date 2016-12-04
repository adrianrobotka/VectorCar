package hu.pendroid.dnga.vectorcar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Set;

public class OptionsActivity extends AppCompatActivity {

    TextView maxSpeedText, laneNumberText;
    SeekBar maxSpeed, laneNumber;
    DataSync dataSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        dataSync = new DataSync(OptionsActivity.this);

        maxSpeedText = (TextView) findViewById(R.id.maxSpeedTextView);
        laneNumberText = (TextView) findViewById(R.id.laneNumberTextView);

        maxSpeed = (SeekBar) findViewById(R.id.maxSpeedSeekBar);
        laneNumber = (SeekBar) findViewById(R.id.laneNumberSeekBar);
        maxSpeed.setMax(95);
        laneNumber.setMax(4);
        initValues();


        maxSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                maxSpeedText.setText(getResources().getText(R.string.max_speed).toString() + (i + 5));
                Config.MAX_SPEED = (float)(i + 5);

                dataSync.setValue(getString(R.string.maximum_speed_key), String.valueOf(i + 5)); //Store that value
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
                laneNumberText.setText(getResources().getText(R.string.lane_number).toString() + (i + 3)); //Update the TextView
                Config.LANES = i + 3; //Write to the Config file

                dataSync.setValue(getString(R.string.lane_number_key), String.valueOf(i+3)); //Store that value
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
        maxSpeedText.setText(getResources().getText(R.string.max_speed).toString() + (Config.MAX_SPEED));
        laneNumberText.setText(getResources().getText(R.string.lane_number).toString() + (Config.LANES));

        maxSpeed.setProgress((int)Config.MAX_SPEED-5);
        laneNumber.setProgress((int)Config.LANES-3);

    }

    public static class DataSync
    {
        Context context;
        SharedPreferences sharedPreferences;

        public DataSync(Context context)
        {
            this.context = context;
            sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preferences_key),Context.MODE_PRIVATE);
        }
        public String getValue(String key, String defaultValue)
        {
            String value = sharedPreferences.getString(key, defaultValue);
            return value;
        }
        public boolean setValue(String key, String value)
        {
            try {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(key, value);
                editor.apply();
                return true;
            }catch(Exception e) {
                return false;
            }

        }
        public boolean setStringSet(String key, Set<String> value)
        {
            try {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet(key, value);
                editor.apply();
                return true;
            }catch(Exception e) {
                return false;
            }

        }
        public Set<String> getStringSet(String key, Set<String> defaultValue)
        {
            Set<String> value = sharedPreferences.getStringSet(key, defaultValue);
            return value;
        }
    }
}
