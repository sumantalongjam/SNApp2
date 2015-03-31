package in.sn.com.component;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import in.sn.com.activity.R;

/**
 * Created by sumanta on 30/3/15.
 */
public class SNAlertDialog extends Dialog implements android.view.View.OnClickListener{
    public Button okButton;
    public Button cancelButton;
    public Context activity;
    public String title = "";
    public String message = "";
    boolean isConfirmation=false;
    public SNAlertDialog(Context activity) {
        super(activity);
        this.activity=activity;
    }
    public SNAlertDialog(Context activity, boolean isConfirmation) {
        super(activity);
        this.activity=activity;
        this.isConfirmation=isConfirmation;
    }
    public void show(String title, String message){
        this.title = title;
        this.message = message;
        super.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog_layout);
        setCanceledOnTouchOutside(false);
        okButton = (Button)findViewById(R.id.btn_ok);
        cancelButton=(Button)findViewById(R.id.btn_cancel);
        okButton.setTag("OK_BTN");
        if(isConfirmation){
            cancelButton.setTag("CANCEL_BTN");
            findViewById(R.id.buttonDevider).setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
        }
        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        if(this.title!=null && this.title.length()>=1)
            ((TextView)findViewById(R.id.titleText)).setText(this.title);
        ((TextView)findViewById(R.id.txt_alert_message)).setText(this.message);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void onClick(View v) {
        if(isConfirmation) {
            if (v.getTag().toString().equals("CANCEL_BTN"))
                this.dismiss();
            else {
                this.dismiss();
                onConfirmation();
            }
        }
        else {
            if (v.getTag().toString().equals("OK_BTN")) {
                this.dismiss();
            }
        }

    }
    public void onConfirmation(){
    }
}