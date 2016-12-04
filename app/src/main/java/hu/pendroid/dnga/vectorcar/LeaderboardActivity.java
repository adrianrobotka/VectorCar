package hu.pendroid.dnga.vectorcar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    ListView listView;
    ScoreMenuAdapter scoreMenuAdapter;
    OptionsActivity.DataSync dataSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        dataSync = new OptionsActivity.DataSync(LeaderboardActivity.this);

        listView = (ListView) findViewById(R.id.listViewLeaderboard);
        Button resetButton = (Button) findViewById(R.id.buttonReset);

        initListView();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSync.setValue(getString(R.string.score_list_key), null);
                initListView();
            }
        });
    }

    private void initListView(){
        List<ScoreMenuListItem> scoreMenuListItems = getScores();

        scoreMenuAdapter = new ScoreMenuAdapter(this, R.layout.score_list_item, scoreMenuListItems);

        listView.setAdapter(scoreMenuAdapter);
    }

    private List<ScoreMenuListItem> getScores(){
        List<ScoreMenuListItem> returnScores = new ArrayList<>();

        String valuesString = dataSync.getValue(getString(R.string.score_list_key), "");
        Log.d("VC", "Scores: " + valuesString.length());

        if(valuesString.length() != 0) {
            String values[] = valuesString.split(";");
            int score[] = new int[values.length];
            String name[] = new String[values.length];

            for(int i = 0; i < values.length; i++) {
                String scorename[] = values[i].split("-");
                score[i] = Integer.valueOf(scorename[0]);
                name[i] = scorename[1];
            }

            QuickSort quicksort = new QuickSort();
            quicksort.sort(score, name);

            for(int i = values.length-1; i > -1; i--){
            returnScores.add(new ScoreMenuListItem((values.length-i)+".", name[i], String.valueOf(score[i]), values.length-i == 1 ? R.drawable.first : values.length-i == 2 ? R.drawable.second :  values.length-i == 3 ? R.drawable.third : 0, Color.GRAY));
            }
        }
        else {
            Toast.makeText(this, getText(R.string.no_scores), Toast.LENGTH_LONG).show();
        }

        return returnScores;
    }

    class ScoreMenuAdapter extends ArrayAdapter<ScoreMenuListItem> {
        private List<ScoreMenuListItem> scoreMenuListItems = new ArrayList<>();
        private Context context;

        ScoreMenuAdapter(Context context, int textViewResourceId, List<ScoreMenuListItem> scoreMenuListItems) {
            super(context, textViewResourceId, scoreMenuListItems);
            this.scoreMenuListItems = scoreMenuListItems;
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, final View convertView, ViewGroup parent) {
            View scoreListItemView = convertView;

            ScoreMenuListItem scoreMenuListItem = getItem(position);

            int color = scoreMenuListItem.colorRes;
            int image = scoreMenuListItem.imageRes;
            String posText = scoreMenuListItem.posText;
            String nameText = scoreMenuListItem.nameText;
            String scoreText = scoreMenuListItem.scoreText;

            if (scoreListItemView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                scoreListItemView = layoutInflater.inflate(R.layout.score_list_item, null);
            }
            TextView posTextView = (TextView) scoreListItemView.findViewById(R.id.posTextView);
            TextView nameTextView = (TextView) scoreListItemView.findViewById(R.id.nameTextView);
            TextView scoreTextView = (TextView) scoreListItemView.findViewById(R.id.scoreTextView);
            ImageView imageView = (ImageView) scoreListItemView.findViewById(R.id.scoreImageView);
            if(scoreMenuListItem.mode == ScoreMenuListItem.DOBOGOS) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(image);
            }
            else {
                imageView.setVisibility(View.GONE);
            }

            scoreListItemView.setBackgroundColor(color);

            posTextView.setText(posText);
            nameTextView.setText(nameText);
            scoreTextView.setText(scoreText);

            //pushLeft(scoreListItemView, position);

            return scoreListItemView;
        }

        void pushLeft(View v, int pos) {
            // create set of animations

            AnimationSet pushAnimation = new AnimationSet(false);

            pushAnimation.setFillAfter(true);

            float width = getContext().getResources().getDisplayMetrics().widthPixels;

            // create translation animation
            TranslateAnimation trans = new TranslateAnimation(width, v.getY(),
                    0, v.getY());
            trans.setStartOffset(pos * 50);
            trans.setDuration(500);
            pushAnimation.addAnimation(trans);

            // start our animation
            v.startAnimation(pushAnimation);
        }

    }

    class ScoreMenuListItem {
        public static final int DOBOGOS = 0;
        public static final int NOOB = 1;
        int colorRes;
        int imageRes;
        String posText;
        String nameText;
        String scoreText;
        int mode;

        public ScoreMenuListItem(String posText, String nameText, String scoreText, int img, int color)
        {
            imageRes = img;
            colorRes = color;
            this.posText = posText;
            this.nameText = nameText;
            this.scoreText = scoreText;
            mode = DOBOGOS;
        }
        public ScoreMenuListItem(String posText, String nameText, String scoreText, int color)
        {
            colorRes = color;
            this.posText = posText;
            this.nameText = nameText;
            this.scoreText = scoreText;
            mode = NOOB;
        }
    }

    public class QuickSort {
        private int[] numbers;
        private int number;
        private String strArray[];

        public void sort(int[] values) {
            // check for empty or null array
            if (values ==null || values.length==0){
                return;
            }
            this.numbers = values;
            number = values.length;
            quicksort(0, number - 1);
        }

        public void sort(int[] values, String[] values2) {
            // check for empty or null array
            if (values ==null || values.length==0){
                return;
            }
            this.numbers = values;
            this.strArray = values2;
            number = values.length;
            quicksort(0, number - 1);
        }

        private void quicksort(int low, int high) {
            int i = low, j = high;
            // Get the pivot element from the middle of the list
            int pivot = numbers[low + (high-low)/2];

            // Divide into two lists
            while (i <= j) {
                // If the current value from the left list is smaller then the pivot
                // element then get the next element from the left list
                while (numbers[i] < pivot) {
                    i++;
                }
                // If the current value from the right list is larger then the pivot
                // element then get the next element from the right list
                while (numbers[j] > pivot) {
                    j--;
                }

                // If we have found a values in the left list which is larger then
                // the pivot element and if we have found a value in the right list
                // which is smaller then the pivot element then we exchange the
                // values.
                // As we are done we can increase i and j
                if (i <= j) {
                    exchange(i, j);
                    i++;
                    j--;
                }
            }
            // Recursion
            if (low < j)
                quicksort(low, j);
            if (i < high)
                quicksort(i, high);
        }

        private void exchange(int i, int j) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
            if(strArray.length == numbers.length) {
                String strTemp = strArray[i];
                strArray[i] = strArray[j];
                strArray[j] = strTemp;
            }
        }
    }
}
