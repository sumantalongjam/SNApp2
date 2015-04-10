package in.sn.com.listener;

import in.sn.com.component.SNProgressDialog;

/**
 * Created by sumanta on 9/4/15.
 */
public interface SNAsynListener {
    void onPreExecute(SNProgressDialog dialog);
    void onPostExecute(String result);
}
