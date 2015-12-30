package assignmentapp.sample.com.mysampleapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assignmentapp.sample.com.mysampleapp.R;

/**
 * Near-By Fragments.
 */
public class NearByFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nearby, null);
    }


}
