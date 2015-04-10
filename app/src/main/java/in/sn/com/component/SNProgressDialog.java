package in.sn.com.component;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import in.sn.com.activity.R;

/**
 * Created by sumanta on 9/4/15.
 */
public class SNProgressDialog extends ProgressDialog {
    Context context = null;

    public SNProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sn_dialog);
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        setCanceledOnTouchOutside(false);
        //		setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    int backPressCount = 0;

    @Override
    public void onBackPressed() {
    }
}