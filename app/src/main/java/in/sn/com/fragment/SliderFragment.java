package in.sn.com.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.sn.com.activity.R;
import in.sn.com.util.SNGlobal;
import in.sn.com.util.Util;

/**
 * Created by sumanta on 21/2/15.
 */
public class SliderFragment extends SNFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_slider, container, false);
        TextView userNameTV = (TextView)view.findViewById(R.id.loginUsrName);
        createSlider(view);
        return view;
    }
    public void createSlider(View view){
        LinearLayout childContainer=(LinearLayout)view.findViewById(R.id.sliderChildContent);
        final String[] sliderSeq = new String[]{"Home","Class  I","Class  II","Class  III","Class  IV","Class  V",
                "Class  VI","Class  VII","Class  VIII","Class  IX","Class  X","Logout"};
        for(final String seqData:sliderSeq){
            View row=LayoutInflater.from(mainActivity).inflate(R.layout.slider_row, null);
            TextView textView=(TextView)row.findViewById(R.id.sliderChildTextView);
            ImageView image=(ImageView)row.findViewById(R.id.highlightImageView);
            textView.setText(seqData);
            if(seqData.equals("Home")||seqData.equals("Logout"))
                image.setVisibility(View.INVISIBLE);
            else
                image.setVisibility(View.VISIBLE);
            row.setTag(seqData);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!v.getTag().equals("Logout"))
                        Util.clearBackStack(mainActivity.getSupportFragmentManager());
                    if(v.getTag().equals("Home") || v.getTag().equals("Logout"))
                        mainActivity.setBundle(seqData, seqData);
                    else
                        mainActivity.setBundle("Subjects", seqData);
                    mainActivity.changeFragment(mainActivity.getBundle());
                }
            });
            childContainer.addView(row);
        }
    }
}
