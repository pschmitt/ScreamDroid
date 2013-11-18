package io.lxl.screamdroid.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.lxl.screamdroid.R;

/**
 * Custom ArrayAdapter to be displayed in the NavigationDrawer
 * Created by pschmitt on 11/18/13.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItem> {
    Context mContext;
    private ArrayList<DrawerItem> mObjects;

    public DrawerAdapter(Context context, int textViewResourceId, ArrayList<DrawerItem> objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        int mLayoutResourceId = textViewResourceId;
        mObjects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        DrawerItem currentItem = mObjects.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_element, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.drawer_txt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (currentItem != null) {
            viewHolder.textView.setText(currentItem.getTitle());
            viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(currentItem.getDrawableResID()), null, null, null);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }
}
