package in.sn.com.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import in.sn.com.activity.MainActivity;
import in.sn.com.util.KeyID;

/**
 * Created by sumanta on 30/3/15.
 */
public class SNFragment extends Fragment implements KeyID {
    protected MainActivity mainActivity;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }
}