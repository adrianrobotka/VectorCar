package hu.pendroid.dnga.vectorcar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public final class MenuActivity extends Activity {
    public static final int imageResources[] = {R.drawable.play_icon, R.drawable.continue_icon, R.drawable.options_icon, R.drawable.help_icon, R.drawable.credits_icon, R.drawable.exit_icon};
    public static final int colorResources[] = {R.color.menuColor1, R.color.menuColor2, R.color.menuColor3, R.color.menuColor4, R.color.menuColor5, R.color.menuColor6};
    public static final int labelResources[] = {R.string.menu_item1, R.string.menu_item2, R.string.menu_item3, R.string.menu_item4, R.string.menu_item5, R.string.menu_item6};
    private AppController controller = AppController.getInstance();
    private int currentApiVersion;
    private boolean loaded = false;

    private MenuAdapter menuAdapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listView = (ListView) findViewById(R.id.listView);

        hideNavigationBar();

        initMenu();

        controller.setFps(Config.FPS);
    }

    private void initMenu() {
        List<MenuListItem> menuListItems = new ArrayList<>();

        for (int i = 0; i < labelResources.length; i++) {
            menuListItems.add(new MenuListItem(getString(labelResources[i]), imageResources[i], colorResources[i]));
        }
        menuAdapter = new MenuAdapter(this, R.layout.list_item, menuListItems);

        listView.setAdapter(menuAdapter);
    }

    @Override
    protected void onResume() {
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

    class MenuAdapter extends ArrayAdapter<MenuListItem> {
        private List<MenuListItem> menuListItems = new ArrayList<>();
        private Context context;

        MenuAdapter(Context context, int textViewResourceId, List<MenuListItem> menuListItems) {
            super(context, textViewResourceId, menuListItems);
            this.menuListItems = menuListItems;
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, final View convertView, ViewGroup parent) {
            View listItemView = convertView;

            MenuListItem menuListItem = getItem(position);

            int color = menuListItem.colorRes;
            int image = menuListItem.imageRes;
            String labelString = menuListItem.labelText;

            if (listItemView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                listItemView = layoutInflater.inflate(R.layout.list_item, null);
            }
            TextView textView = (TextView) listItemView.findViewById(R.id.textView);
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);
            imageView.setImageResource(image);
            listItemView.setBackgroundResource(color);
            textView.setText(labelString);

            pushLeft(listItemView, position);

            switch (position) {
                case 0: {
                    listItemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // new game
                            controller.init();
                            Intent intent = new Intent(context, GameActivity.class);
                            context.startActivity(intent);
                        }
                    });
                    break;
                }
                case 1: {
                    // load game
                    listItemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            controller.init();
                            Intent intent = new Intent(context, GameActivity.class);
                            context.startActivity(intent);
                        }
                    });
                    break;
                }
                case 2: {
                    // options
                    listItemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, OptionsActivity.class);
                            context.startActivity(intent);
                        }
                    });
                    break;
                }
                case 3: {
                    // help
                    listItemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, HelpActivity.class);
                            context.startActivity(intent);
                        }
                    });
                    break;
                }
                case 4: {
                    // credits
                    listItemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, CreditsActivity.class);
                            context.startActivity(intent);
                        }
                    });
                    break;
                }
                case 5: {
                    // exit
                    listItemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                    break;
                }
            }

            return listItemView;
        }

        public void pushLeft(View v, int pos) {
            // create set of animations

            AnimationSet pushAnimation = new AnimationSet(false);

            pushAnimation.setFillAfter(true);

            float width = getContext().getResources().getDisplayMetrics().widthPixels;
            float row_height = v.getHeight();

            // create translation animation
            TranslateAnimation trans = new TranslateAnimation(width, row_height * pos,
                    0, row_height * pos);
            trans.setStartOffset(pos * 50);
            trans.setDuration(1000);
            pushAnimation.addAnimation(trans);

            // start our animation
            v.startAnimation(pushAnimation);
        }

    }
}
