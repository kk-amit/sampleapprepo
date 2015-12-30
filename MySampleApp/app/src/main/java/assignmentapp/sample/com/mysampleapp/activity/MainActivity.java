package assignmentapp.sample.com.mysampleapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import assignmentapp.sample.com.mysampleapp.R;
import assignmentapp.sample.com.mysampleapp.components.PopUP;
import assignmentapp.sample.com.mysampleapp.controllers.ConnectionManager;
import assignmentapp.sample.com.mysampleapp.controllers.IConnectionManager;
import assignmentapp.sample.com.mysampleapp.fragments.ErrorFragment;
import assignmentapp.sample.com.mysampleapp.fragments.TabFragment;

/**
 * Application main activity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Hold instances of drawer view in activity.
     */
    private DrawerLayout mDrawerLayout;
    /**
     * Hold instance context.
     */
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        //Inflate the fragment
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        // Network connection checks
        IConnectionManager connectionManager = new ConnectionManager(mContext);
        if (connectionManager.checkInternetConnection()) {
            mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        } else {
            new PopUP(mContext).alertDialog(mContext.getString(R.string.no_network_connectivity));
            mFragmentTransaction.replace(R.id.containerView, new ErrorFragment()).commit();
        }

        //Setup the DrawerLayout and NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return false;
            }

        });

        //Setup Drawer Toggle of the Toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }
}