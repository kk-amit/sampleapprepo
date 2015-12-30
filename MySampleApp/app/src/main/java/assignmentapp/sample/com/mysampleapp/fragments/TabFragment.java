package assignmentapp.sample.com.mysampleapp.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assignmentapp.sample.com.mysampleapp.R;
import assignmentapp.sample.com.mysampleapp.adapters.TabViewAdapter;

/**
 * Tab-Fragments.
 */
public class TabFragment extends Fragment {

    /**
     * Holds the instance of TabLayout.
     */
    private TabLayout mTabLayout;
    /**
     * Holds the instance of view-pager.
     */
    private ViewPager mViewPager;
    /**
     * Number of pages in view-pager.ó
     */
    public final static int NO_OF_PAGES = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate tab_layout and setup Views.
        View view = inflater.inflate(R.layout.tab_layout, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        //Set an Apater for the View Pager
        mViewPager.setAdapter(new TabViewAdapter(getActivity(), getChildFragmentManager()));
        // Open the near-by screen.
        mViewPager.setCurrentItem(1);
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });

        return view;

    }
}
