package hu.pendroid.dnga.vectorcar;

/**
 * Created by robot on 2016. 11. 24..
 */

public class ListItem
{
    public static final int imageResources[] = { R.drawable.car, R.drawable.pothole_fatal, R.drawable.pothole_light, R.drawable.round_button };
    public static final int colorResources[] = { R.color.menuColor1, R.color.menuColor2, R.color.menuColor3, R.color.menuColor4, R.color.menuColor5 };
    public static final int labelResources[] = { R.string.menu_item1, R.string.menu_item2, R.string.menu_item3, R.string.menu_item4, R.string.menu_item5 };
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
