package hu.pendroid.dnga.vectorcar;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public final class MenuActivity extends Activity {
    public static final int imageResources[] = {R.drawable.play_icon, R.drawable.continue_icon, R.drawable.options_icon, R.drawable.help_icon, R.drawable.credits_icon, R.drawable.exit_icon};
    public static final int colorResources[] = {R.color.menuColor1, R.color.menuColor2, R.color.menuColor3, R.color.menuColor4, R.color.menuColor5, R.color.menuColor6};
    public static final int labelResources[] = {R.string.menu_item1, R.string.menu_item2, R.string.menu_item3, R.string.menu_item4, R.string.menu_item5, R.string.menu_item6};
    private AppController controller = AppController.getInstance();
    private boolean loaded;
    private int currentApiVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        ListView listView = (ListView) findViewById(R.id.listView);

        List<ListItem> listItems = new ArrayList<>();

        for (int i = 0; i < labelResources.length; i++) {
            listItems.add(new ListItem(getString(labelResources[i]), imageResources[i], colorResources[i]));
        }

        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.list_item, listItems);
        listView.setAdapter(menuAdapter);

        controller.setFps(Config.FPS);

        if (!loaded) {
            controller.init();
        }
    }

    @Override
    protected void onResume() {
        //TODO remove after test
        controller.init();
        super.onResume();
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
