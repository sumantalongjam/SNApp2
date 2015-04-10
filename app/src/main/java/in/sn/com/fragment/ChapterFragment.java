package in.sn.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import in.sn.com.activity.R;
import in.sn.com.adapter.ChapterAdapter;
import in.sn.com.component.Params;
import in.sn.com.component.SNAsynTask;
import in.sn.com.component.SNProgressDialog;
import in.sn.com.component.ServerCommunication;
import in.sn.com.listener.SNAsynListener;

/**
 * Created by sumanta on 30/3/15.
 */
public class ChapterFragment extends SNFragment {
    private String chapter_id;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);
        listView = (ListView) view.findViewById(R.id.chapterListView);
        if(getArguments()!=null) {
            chapter_id = getArguments().getString("chapter_id");
        }
        getServerData();
        return view;
    }
    private void getServerData() {
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("chapter_id", chapter_id));
        Params param = new Params(ServerCommunication.server().getActionUrl(GET_CHAPTERS), list);
        SNAsynTask asynTask = new SNAsynTask(getActivity(), true, new SNAsynListener() {
            @Override
            public void onPreExecute(SNProgressDialog dialog) {
                dialog.show();
            }
            @Override
            public void onPostExecute(String result) {
                if(result!=null) {
                    try {
                        JSONObject resultObject = new JSONObject(result);
                        if(resultObject.getString("status").equals("success")) {
                            JSONArray respArray = resultObject.getJSONArray("resp");
                            JSONObject respObject = respArray.getJSONObject(0);
                            JSONArray chapterArray = respObject.getJSONArray("chapter_list");
                            List<String> chapterList = new ArrayList<String>();
                            for (int i=0; i<chapterArray.length(); i++) {
                                chapterList.add(chapterArray.getString(i));
                            }
                            ChapterAdapter adapter = new ChapterAdapter(getActivity(), chapterList);
                            listView.setAdapter(adapter);
                        }
                        else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        asynTask.execute(param);
    }
}
