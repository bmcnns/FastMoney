package ca.dal.cs.csci3130.project.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import ca.dal.cs.csci3130.project.R;

public class EmployeeView extends FrameLayout {
    public EmployeeView(Context context) {
        super(context);
        init();
    }

    public EmployeeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmployeeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.activity_employee, this);
    }
}