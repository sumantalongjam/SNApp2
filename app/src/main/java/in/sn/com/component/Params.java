package in.sn.com.component;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * Created by sumanta on 9/4/15.
 */
public class Params {
    String url;
    List<NameValuePair> params;
    public Params(String url, List<NameValuePair> params) {
        this.url = url;
        this.params = params;
    }
}
