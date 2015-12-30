package assignmentapp.sample.com.mysampleapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import assignmentapp.sample.com.mysampleapp.R;
import assignmentapp.sample.com.mysampleapp.controllers.AppController;
import assignmentapp.sample.com.mysampleapp.customview.AndroidTextCircleLayout;
import assignmentapp.sample.com.mysampleapp.models.CategoryModel;
import assignmentapp.sample.com.mysampleapp.models.CouponDuniaBrandModel;

/**
 * Adapter to set list of items in List-view.
 */
public class AppListAdapter extends BaseAdapter {
    /**
     * Hold the instance of context.
     */
    private Context mContext;
    /**
     * Model list.
     */
    private List<CouponDuniaBrandModel> mListItems;
    /**
     * Holds the instance of LayoutInflater.
     */
    private LayoutInflater mInflater;

    public AppListAdapter(Context context, ArrayList<CouponDuniaBrandModel> items) {
        this.mContext = context;
        this.mListItems = items;

        mInflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    public void updateListData(ArrayList<CouponDuniaBrandModel> items) {
        this.mListItems = items;
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
        TextView txtAddress;
        LinearLayout container;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        CouponDuniaBrandModel rowItem = (CouponDuniaBrandModel) getItem(position);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            holder.txtAddress = (TextView) convertView.findViewById(R.id.address);
            holder.container = (LinearLayout) convertView.findViewById(R.id.container);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getOutletName());
        holder.txtDesc.setText(rowItem.getCityName());
        holder.txtAddress.setText(rowItem.getAddress());

        for (CategoryModel categoryModel : rowItem.getCategoryModelList()) {
            AndroidTextCircleLayout androidTextCircleLayout = new AndroidTextCircleLayout(mContext, categoryModel.getName());
            holder.container.addView(androidTextCircleLayout);
        }
        AppController.getInstance().getImageLoader().displayImage(rowItem.getLogoUrl(), holder.imageView, "");
        return convertView;
    }
}
