package in.sn.com.adapter;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import in.sn.com.activity.R;

public class ChapterAdapter extends BaseAdapter {

	Context context;
	String[] chapterArray;
	public ChapterAdapter(Context context, String[] chapterArray) {
		this.context = context;
		this.chapterArray = chapterArray;
	}
	@Override
	public int getCount() {
		return chapterArray.length;
	}
	@Override
	public String getItem(int position) {
		return chapterArray[position];
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null)
			convertView = LayoutInflater.from(context).inflate(R.layout.chapter_list_row, parent, false);
		((TextView) convertView.findViewById(R.id.chapterTextView)).setText("Chapter "+(position+1));
		((TextView) convertView.findViewById(R.id.chapterDesTextView)).setText(getItem(position));
		return convertView;
	}

}
