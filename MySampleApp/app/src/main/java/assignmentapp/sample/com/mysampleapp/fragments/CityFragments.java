package assignmentapp.sample.com.mysampleapp.fragments;

import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import assignmentapp.sample.com.mysampleapp.R;
import assignmentapp.sample.com.mysampleapp.Utils.Constants;
import assignmentapp.sample.com.mysampleapp.Utils.Utility;
import assignmentapp.sample.com.mysampleapp.adapters.AppListAdapter;
import assignmentapp.sample.com.mysampleapp.components.GPSTracker;
import assignmentapp.sample.com.mysampleapp.components.PopUP;
import assignmentapp.sample.com.mysampleapp.controllers.IJSONParser;
import assignmentapp.sample.com.mysampleapp.controllers.IWebServiceCallBackListener;
import assignmentapp.sample.com.mysampleapp.controllers.IWebServiceCaller;
import assignmentapp.sample.com.mysampleapp.controllers.JSONParser;
import assignmentapp.sample.com.mysampleapp.controllers.WebServiceCaller;
import assignmentapp.sample.com.mysampleapp.models.CouponDuniaBrandModel;


/**
 * City-Fragments.
 */
public class CityFragments extends Fragment implements IWebServiceCallBackListener {

    private ListView mListView;
    private View mView;
    private AppListAdapter mAppListAdapter;
    private ArrayList<CouponDuniaBrandModel> couponDuniaBrandModels;
    private IWebServiceCaller mWebServiceCaller;
    private GPSTracker mGPSTracker;
    private PopUP mPopUP;
    private TextView mHeaderText;
    private Resources mResources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_city, null);
        couponDuniaBrandModels = new ArrayList<>();

        mPopUP = new PopUP(getActivity());

        mGPSTracker = new GPSTracker(getActivity());

        mListView = (ListView) mView.findViewById(R.id.list);
        mHeaderText = (TextView) mView.findViewById(R.id.headerText);
        mAppListAdapter = new AppListAdapter(getActivity(), couponDuniaBrandModels);
        mListView.setAdapter(mAppListAdapter);

        mResources = getActivity().getResources();

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mWebServiceCaller = new WebServiceCaller(getActivity(), Constants.APP_WEBSERVICE_URL, new JSONObject());
        mWebServiceCaller.setWebServiceCallBackListener(this);
        mWebServiceCaller.executeAsync();
    }

    @Override
    public void onWebServiceProgress(int progressValue) {

    }

    @Override
    public void onWebServiceStatus(boolean status) {

    }


    @Override
    public void onWebServiceCompleted(String data) {
        IJSONParser parser = new JSONParser();
        Location location;
        try {
            couponDuniaBrandModels = (ArrayList<CouponDuniaBrandModel>) parser.getParsedData(data);
            if (couponDuniaBrandModels == null) {
                mPopUP.alertDialog(mResources.getString(R.string.no_network_error));
                return;
            }

            // Check if GPS enable or not. If not enable it.
            if (!mGPSTracker.canGetLocation()) {
                mPopUP.showSettingsAlert();
            } else {
                location = mGPSTracker.getLocation();
                if (location == null) {
                    location = new Location("");
                    location.setLatitude(Constants.DUMMY_LAT);
                    location.setLongitude(Constants.DUMMY_LONG);
                }

                mHeaderText.setText(Utility.getCompleteAddressString(getActivity(), location.getLatitude(), location.getLongitude()));

                // sorting of the list on the basis of current latitude and longitude.
                // Step 1- Set the current location distance into the model.
                // Step 2- Once distance set sort list via Comparator.
                for (CouponDuniaBrandModel couponDuniaBrandModel : couponDuniaBrandModels) {
                    double lat = couponDuniaBrandModel.getLat();
                    double longitude = couponDuniaBrandModel.getLong();
                    double distance = Utility.calculateDistance(lat, longitude, location.getLatitude(), location.getLongitude());
                    couponDuniaBrandModel.setPlaceDistance(distance);
                }
                // Sort the list on the basis of distance.
                Collections.sort(couponDuniaBrandModels, new Comparator<CouponDuniaBrandModel>() {
                    @Override
                    public int compare(CouponDuniaBrandModel c1, CouponDuniaBrandModel c2) {
                        return new Float(c1.getPlaceDistance()).compareTo(new Float(c2.getPlaceDistance()));
                    }
                });


                mAppListAdapter.updateListData(couponDuniaBrandModels);
                mAppListAdapter.notifyDataSetChanged();
            }


        } catch (JSONException e) {
            e.printStackTrace();
            mPopUP.alertDialog(mResources.getString(R.string.no_network_error));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mWebServiceCaller.removeWebServiceCallBackListener();
        mGPSTracker.stopUsingGPS();

    }
}
