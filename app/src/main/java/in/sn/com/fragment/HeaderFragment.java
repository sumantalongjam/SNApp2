package in.sn.com.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.sn.com.activity.R;

/**
 * Created by sumanta on 21/2/15.
 */
public class HeaderFragment extends SNFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        TextView headerTitle = (TextView) view.findViewById(R.id.headerTextView);
        ImageView question = (ImageView) view.findViewById(R.id.questionBtn);
        String title = getArguments().getString(TITLE, "");
        if(title.equals("Home")) {
            question.setVisibility(View.VISIBLE);
            question.setOnClickListener(askedListener);
        }
        else
            question.setVisibility(View.INVISIBLE);
        headerTitle.setText(title);
        return view;
    }
    private View.OnClickListener askedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
            dialog.setContentView(R.layout.dialog_asked);
            dialog.setCancelable(false);
            ((TextView)dialog.findViewById(R.id.askedTextView)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "not implemented yet", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            ((TextView)dialog.findViewById(R.id.cancelTextView)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    };
}
