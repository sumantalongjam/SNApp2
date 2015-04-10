package in.sn.com.component;

/**
 * Created by sumanta on 9/4/15.
 */
import java.net.URI;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import in.sn.com.util.KeyID;


public class ServerCommunication implements KeyID {

    private static ServerCommunication	serverCommunication;
    public static ServerCommunication server() {
        if (serverCommunication == null) {
            serverCommunication = new ServerCommunication();
        }
        return serverCommunication;
    }
    public String getServerData(String url, List<NameValuePair> param){
        HttpPost httpPost= new HttpPost();
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            if(url==null || url.length()==0){
                return "";
            }
            httpPost.setURI(new URI(url));
            httpPost.setEntity(new UrlEncodedFormEntity(param));
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String responseText = EntityUtils.toString(entity);
            return responseText;
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getActionUrl(String actionIID) {
        String urlString = "http://192.168.1.226:3000";
        if(actionIID != null)
            return urlString + actionIID;
        else
            return urlString;
    }
}

