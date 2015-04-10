package in.sn.com.activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import java.io.File;

import in.sn.com.component.SNAlertDialog;
import in.sn.com.component.SNSlidingPanel;
import in.sn.com.fragment.ChapterFragment;
import in.sn.com.fragment.HeaderFragment;
import in.sn.com.fragment.HomeFragment;
import in.sn.com.fragment.SNFragment;
import in.sn.com.fragment.SliderFragment;
import in.sn.com.fragment.SubjectFragment;
import in.sn.com.listener.ChangeFragmentListener;
import in.sn.com.util.KeyID;
import in.sn.com.util.SNGlobal;
import in.sn.com.util.Util;
import in.sn.com.activity.R;


public class MainActivity extends FragmentActivity implements KeyID, ChangeFragmentListener {

    private SNSlidingPanel slidingPaneLayout;
    private Fragment pageFragment;
    private Bundle bundle;
    HeaderFragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingPaneLayout = (SNSlidingPanel) findViewById(R.id.parent);
        setBundle("Home", "Home");
        pageFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.sideNavigationPanel, new SliderFragment()).commit();
        updateFragment(getBundle());
    }

    public Bundle setBundle(String page, String title) {
        bundle = new Bundle();
        bundle.putString(PAGE, page);
        bundle.putString(TITLE, title);
        return bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }



    public void updateFragment(Bundle bundle) {
        if (getCurrentFocus() != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        headerFragment = new HeaderFragment();
        headerFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.headerFragment, headerFragment);
        pageFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragContainer, pageFragment);
        fragmentTransaction.commit();
        slidingPaneLayout.closePane();
    }

    public void showSideNavigationPanel() {
        if (slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else {
            slidingPaneLayout.openPane();
        }
    }

    public void onSliderClick(View view) {
        showSideNavigationPanel();
    }

    @Override
    public void changeFragment(Bundle bundle) {
        if ((bundle.getString(PAGE).toString()).equals("Logout")) {
            SNAlertDialog dialog = new SNAlertDialog(this, true) {
                @Override
                public void onConfirmation() {
                    super.onConfirmation();
                    SNGlobal.editor.putBoolean("isLogout", true);
                    SNGlobal.editor.commit();
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent);
                    finish();
                }
            };
            dialog.show("Warning", "Are you sure you want to logout?");
            return;
        } else {
            fragmentSelector(bundle);
            updateFragment(bundle);
        }
    }
    public void fragmentSelector(Bundle bundle) {
        String data = bundle.getString(PAGE);
        if(data.equalsIgnoreCase("Home")) {
            SNFragment fragment = new HomeFragment();
            fragment.setArguments(bundle);
            pageFragment = fragment;
        }
        else if(data.equalsIgnoreCase("Subjects")){
            SNFragment fragment = new SubjectFragment();
            fragment.setArguments(bundle);
            pageFragment = fragment;
        }
        else if(data.equalsIgnoreCase("Chapters")) {
            SNFragment fragment = new ChapterFragment();
            fragment.setArguments(bundle);
            pageFragment = fragment;
        }

    }
    @Override
    public void onBackPressed() {
        if (slidingPaneLayout.isOpen())
            slidingPaneLayout.closePane();
        else {
            Util.clearBackStack(getSupportFragmentManager());
            super.onBackPressed();
        }
    }
}
