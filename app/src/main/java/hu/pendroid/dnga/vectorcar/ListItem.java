package hu.pendroid.dnga.vectorcar;

/**
 * Created by robot on 2016. 11. 24..
 */

public class ListItem
{
    int colorRes;
    int imageRes;
    String labelText;

    public ListItem(String label, int img, int color)
    {
        labelText = label;
        imageRes = img;
        colorRes = color;
    }
}
