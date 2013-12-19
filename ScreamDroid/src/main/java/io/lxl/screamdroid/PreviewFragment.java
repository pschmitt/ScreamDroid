package io.lxl.screamdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pschmitt on 12/18/13.
 */
public class PreviewFragment extends Fragment {
    private TextView mPreview;
    private String mText;

    public PreviewFragment() {}

    public void updatePreviewText(String newText) {
        if (mPreview != null)
            mPreview.setText(newText);
        mText = newText;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preview, container, false);
        mPreview = (TextView) rootView.findViewById(R.id.preview_scream);
        mPreview.setText(mText);
        return rootView;
    }
}
