package in.sn.com.component;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by sumanta on 30/3/15.
 */
public class SNSlidingPanel extends SlidingPaneLayout {

    public SNSlidingPanel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SNSlidingPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SNSlidingPanel(Context context) {
        super(context);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if(isOpen()){
            return super.onInterceptTouchEvent(arg0);
        }
        return false;
    }
}