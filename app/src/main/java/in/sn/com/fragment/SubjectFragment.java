package in.sn.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import in.sn.com.activity.R;
import in.sn.com.adapter.GridViewAdapter;
import in.sn.com.entity.SubjectEntity;

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
            classNo = getArguments().getString("data");
        }
        ArrayList<SubjectEntity> subjectList = new ArrayList<>();
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        subjectList.add(new SubjectEntity(R.mipmap.science, "SCIENCE"));
        GridViewAdapter adapter = new GridViewAdapter(getActivity(), subjectList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(itemClickListener);
        return view;
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Bundle bundle = new Bundle();
            bundle.putString(SELECTED_SLIDER, "Science Chapters");
            mainActivity.changeFragment(bundle);
        }
    };
}
