package assignmentapp.sample.com.mysampleapp.components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.Settings;

import assignmentapp.sample.com.mysampleapp.R;

/**
 * Represents the popup.
 */
public class PopUP {

    /**
     * Holds the Context.
     */
    private Context mContext;

    private Resources mResources;

    /**
     * Popup constructor.
     *
     * @param context context.
     */
    public PopUP(Context context) {
        this.mContext = context;
        mResources = mContext.getResources();
    }

    /**
     * Method represents the AlertDialog.
     *
     * @param message alert message.
     * @return alert dialog.
     */
    public AlertDialog alertDialog(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(mResources.getString(R.string.ok_text),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return alertDialog.show();

    }

    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     */
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle(mResources.getString(R.string.gps_setting_text));

        // Setting Dialog Message
        alertDialog.setMessage(mResources.getString(R.string.gps_msg_text));

        // On pressing Settings button
        alertDialog.setPositiveButton(mResources.getString(R.string.setting_text),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(intent);
                    }
                });

        // on pressing cancel button
        alertDialog.setNegativeButton(mResources.getString(R.string.cancel_text),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Showing Alert Message
        alertDialog.show();
    }

}
