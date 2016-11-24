package hu.pendroid.dnga.vectorcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by robot on 2016. 11. 24..
 */

public class MenuAdapter extends ArrayAdapter<ListItem>
{

    public MenuAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MenuAdapter(Context context, int resource, List<ListItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item, null);
        }

        ListItem p = getItem(position);

        if (p != null) {
            TextView textView = (TextView) v.findViewById(R.id.textView);
            ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
            int color = ListItem.colorResources[position];
            int image = ListItem.imageResources[position];
            int labelString = ListItem.labelResources[position];

            imageView.setImageResource(image);
            imageView.setBackgroundColor(color);
            textView.setText();

            if (tt1 != null) {
                tt1.setText(p.getId());
            }

            if (tt2 != null) {
                tt2.setText(p.getCategory().getId());
            }

            if (tt3 != null) {
                tt3.setText(p.getDescription());
            }
        }

        return v;
    }

}

