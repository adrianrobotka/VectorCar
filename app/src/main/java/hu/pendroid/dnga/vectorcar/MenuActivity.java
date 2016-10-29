package hu.pendroid.dnga.vectorcar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.adrianrobotka.brick.util.ProcessIndicator;

public final class MenuActivity extends Activity {
    private AppController controller = AppController.getInstance();
    private boolean loaded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final ProgressBar progress = (ProgressBar) findViewById(R.id.initProgressBar);
        ProcessIndicator indicator = new ProcessIndicator() {
            @Override
            public void setProcessPercentage(final int percentage) {
                progress.setProgress(percentage);
                if (percentage == 100)
                    loaded = true;
            }
        };

        controller.setFps(Config.FPS);
        controller.setIndicator(indicator);

        Button newGameButton = (Button) findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loaded)
                    return;

                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.init();
    }
}
