package in.sn.com.component;

import android.content.Context;
import android.os.AsyncTask;
import in.sn.com.listener.SNAsynListener;
import in.sn.com.util.KeyID;

/**
 * Created by sumanta on 9/4/15.
 */
public class SNAsynTask extends AsyncTask<Params, String, String>  implements KeyID {

    private Context context;
    private boolean isDialog;
    private SNAsynListener taskListener;
    SNProgressDialog dialog;

    public SNAsynTask(Context context, boolean isDialog, SNAsynListener asyncTaskListener) {
        this.context = context;
        this.isDialog = isDialog;
        this.taskListener = asyncTaskListener;
    }
    @Override
    protected void onPreExecute() {
        if(isDialog) {
            dialog = new SNProgressDialog(context);
            taskListener.onPreExecute(dialog);
        }
    }
    @Override
    protected String doInBackground(Params... args) {
        return ServerCommunication.server().getServerData(args[0].url, args[0].params);
    }
    @Override
    protected void onPostExecute(final String result) {
        if (dialog!=null && isDialog) {
            dialog.dismiss();
        }
        taskListener.onPostExecute(result);
    }
}