package io.lxl.screamdroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class TextInputFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private EditText mScreamInput;

    private OnTextUpdatedListener mCallback;

    // Container Activity must implement this interface
    public interface OnTextUpdatedListener {
        public void onTextUpdated(String newText);
    }


    public TextInputFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnTextUpdatedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTextUpdatedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mScreamInput = (EditText) rootView.findViewById(R.id.input_scream);
        mScreamInput.setImeOptions(EditorInfo.IME_ACTION_DONE);

        mScreamInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mCallback.onTextUpdated(s.toString());
            }
        });


        mScreamInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ((MainActivity) getActivity()).scream();
                    return true;
                }
                return false;
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (sharedPref.getBoolean(getString(R.string.pref_key_discard_input), false)) {
            mScreamInput.setText("");
        }
    }
}