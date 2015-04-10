package in.sn.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import in.sn.com.activity.R;
import in.sn.com.adapter.GridViewAdapter;
import in.sn.com.component.Params;
import in.sn.com.component.SNAsynTask;
import in.sn.com.component.SNProgressDialog;
import in.sn.com.component.ServerCommunication;
import in.sn.com.entity.SubjectEntity;
import in.sn.com.listener.SNAsynListener;

/**
 * Created by sumanta on 30/3/15.
 */
public class SubjectFragment extends SNFragment {
    private String classNo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        if(getArguments()!=null) {
            classNo = getArguments().getString(TITLE);
        }
        ArrayList<SubjectEntity> subjectList = new ArrayList<>();
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE", "sc"));
        GridViewAdapter adapter = new GridViewAdapter(getActivity(), subjectList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(itemClickListener);
        return view;
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SubjectEntity subjectEntity = (SubjectEntity)parent.getAdapter().getItem(position);
            Bundle bundle = new Bundle();
            bundle.putString(PAGE, "Chapters");
            bundle.putString(TITLE, subjectEntity.getSubjectName());
            bundle.putString("chapter_id", getchapterId(subjectEntity.getSubjectCode(), classNo));
            mainActivity.changeFragment(bundle);
        }
    };
    private String getchapterId(String subjectCode, String classNo) {
        if(classNo.equalsIgnoreCase("Class  X"))
            return subjectCode+"_10";
        return null;
    }
}
