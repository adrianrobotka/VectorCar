package hu.pendroid.dnga.vectorcar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by robot on 2016. 11. 24..
 */

public class MenuAdapter extends ArrayAdapter<ListItem>
{
    List<ListItem> listItems = new ArrayList<>();
    Context context;

    public MenuAdapter(Context context, int textViewResourceId, List<ListItem> listItems) {
        super(context, textViewResourceId, listItems);
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View listItemView = convertView;

        ListItem listItem = getItem(position);

        int color = listItem.colorRes;
        int image = listItem.imageRes;
        String labelString = listItem.labelText;

        if(listItemView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            listItemView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView textView = (TextView) listItemView.findViewById(R.id.textView);
        ImageView imageView = (ImageView)listItemView.findViewById(R.id.imageView);
        imageView.setImageResource(image);
        listItemView.setBackgroundResource(color);
        textView.setText(labelString);

        float width = context.getResources().getDisplayMetrics().widthPixels;
        float row_hight = (float)context.getResources().getDimension(R.dimen.list_item_hight);
        //listItemView.animate(new TranslateAnimation(width, row_hight*position, 0, row_hight*position)).start();

        switch (position) {
            case 0: {
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, GameActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 1: {
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, GameActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 2: {
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, GameActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 3: {
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
                listItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.exit(0);
                    }
                });
                break;
            }
        }

        return listItemView;
    }

}

