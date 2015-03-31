package in.sn.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import in.sn.com.activity.R;
import in.sn.com.adapter.ChapterAdapter;

/**
 * Created by sumanta on 30/3/15.
 */
public class ChapterFragment extends SNFragment {
    private String subject;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);
        ListView listView = (ListView) view.findViewById(R.id.chapterListView);
        if(getArguments()!=null) {
            subject = getArguments().getString("data");
        }
        String[] chapterList = getChapter(0);
        ChapterAdapter adapter = new ChapterAdapter(getActivity(), chapterList);
        listView.setAdapter(adapter);
        return view;
    }
    private String[] getChapter(int subjectCode) {
        switch (subjectCode) {
            case 0:
                return getResources().getStringArray(R.array.science_chapter);
        }
        return new String[] {"abc"};
    }
}
