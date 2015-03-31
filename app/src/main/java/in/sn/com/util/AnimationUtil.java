package in.sn.com.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import in.sn.com.activity.R;

/**
 * Created by sumanta on 21/2/15.
 */
public class AnimationUtil {

    public static  void showView(Context context, final View view){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation paramAnimation) {
                view.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation paramAnimation) {}
            @Override
            public void onAnimationEnd(Animation paramAnimation) {
            }
        });
        view.startAnimation(animation);
    }
    public static void hideView(Context context, final View view){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }
        });
        view.startAnimation(animation);
    }
}

