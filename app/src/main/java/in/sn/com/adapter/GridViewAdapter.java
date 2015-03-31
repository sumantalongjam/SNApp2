package in.sn.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import in.sn.com.activity.R;
import in.sn.com.entity.SubjectEntity;

/**
 * Created by sumanta on 30/3/15.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SubjectEntity> subjectList;
    public GridViewAdapter(Context context, ArrayList<SubjectEntity> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.subjectImage = (ImageView)convertView.findViewById(R.id.subjectImageView);
            viewHolder.subjectText = (TextView)convertView.findViewById(R.id.subjectTextView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SubjectEntity subject = getItem(position);
        if(subject!=null) {
            viewHolder.subjectImage.setBackgroundResource(subject.getSubjectImageId());
            viewHolder.subjectText.setText(subject.getSubjectName());
        }
        return convertView;
    }
    private static class ViewHolder {
        ImageView subjectImage;
        TextView subjectText;
    }
    public int getCount() {
        return subjectList.size();
    }

    public SubjectEntity getItem(int position) {
        return subjectList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }
}