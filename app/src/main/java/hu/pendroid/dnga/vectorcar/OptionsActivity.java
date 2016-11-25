package hu.pendroid.dnga.vectorcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
