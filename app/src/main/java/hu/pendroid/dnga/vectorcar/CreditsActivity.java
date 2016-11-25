package hu.pendroid.dnga.vectorcar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by robotka1055 on 2016. 11. 24..
 */

public class CreditsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        Button backButton = (Button) findViewById(R.id.backButtonCredits);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreditsActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
