package assignmentapp.sample.com.mysampleapp.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import assignmentapp.sample.com.mysampleapp.R;

/**
 * Custom-view group for circle and text combination.
 */
public class AndroidTextCircleLayout extends LinearLayout {

    private String mTextString;

    public AndroidTextCircleLayout(Context context, String textStr) {
        super(context);
        this.mTextString = textStr;
        init();
    }

    public AndroidTextCircleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AndroidTextCircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AndroidTextCircleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.text_items, null);
        TextView textView = (TextView) view.findViewById(R.id.textView2);
        textView.setText(mTextString);
        this.addView(view);
    }
}
