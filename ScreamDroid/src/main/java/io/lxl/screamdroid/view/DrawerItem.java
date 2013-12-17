package io.lxl.screamdroid.view;

/**
 * Created by pschmitt on 11/18/13.
 */
public class DrawerItem {
    private String mTitle;
    private int mDrawableResID;

    public DrawerItem(String title, int drawableResID) {
        mTitle = title;
        mDrawableResID = drawableResID;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getDrawableResID() {
        return mDrawableResID;
    }
}
