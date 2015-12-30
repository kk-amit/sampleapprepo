package assignmentapp.sample.com.mysampleapp.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import assignmentapp.sample.com.mysampleapp.R;
import assignmentapp.sample.com.mysampleapp.fragments.BestOfferFragments;
import assignmentapp.sample.com.mysampleapp.fragments.CityFragments;
import assignmentapp.sample.com.mysampleapp.fragments.NearByFragments;
import assignmentapp.sample.com.mysampleapp.fragments.TabFragment;


/**
 * Adapter to set fragments in View-Pager.
 */
public class TabViewAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public TabViewAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NearByFragments();
            case 1:
                return new CityFragments();
            case 2:
                return new BestOfferFragments();
        }
        return null;
    }

    @Override
    public int getCount() {

        return TabFragment.NO_OF_PAGES;

    }

    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {

        Resources resources = mContext.getResources();
        switch (position) {
            case 0:
                return resources.getString(R.string.city_text);
            case 1:
                return resources.getString(R.string.nearby_text);
            case 2:
                return resources.getString(R.string.best_offers_text);
        }
        return null;
    }
}
