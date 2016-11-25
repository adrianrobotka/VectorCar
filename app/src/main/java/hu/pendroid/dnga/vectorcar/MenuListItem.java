package hu.pendroid.dnga.vectorcar;

public class MenuListItem
{
    int colorRes;
    int imageRes;
    String labelText;

    public MenuListItem(String label, int img, int color)
    {
        labelText = label;
        imageRes = img;
        colorRes = color;
    }
}
