package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import ca.dal.cs.csci3130.fastmoney.R;

public class JobView extends FrameLayout {
    public JobView(Context context) {
        super(context);
        init();
    }

    public JobView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JobView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.activity_job, this);
    }
}