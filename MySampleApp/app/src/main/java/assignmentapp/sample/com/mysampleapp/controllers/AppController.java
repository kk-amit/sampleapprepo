package assignmentapp.sample.com.mysampleapp.controllers;

import android.app.Application;

import assignmentapp.sample.com.mysampleapp.image.ImageLoader;

/**
 * Holds the application level.
 */
public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private ImageLoader mImageLoader;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }


    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(AppController.this);
        }
        return this.mImageLoader;
    }
}