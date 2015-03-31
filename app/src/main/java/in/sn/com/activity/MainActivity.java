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
        setBundle("Home");
        pageFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.sideNavigationPanel, new SliderFragment()).commit();
        updateFragment(getBundle());
    }

    public Bundle setBundle(String data) {
        bundle = new Bundle();
        bundle.putString(SELECTED_SLIDER, data);
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
        if ((bundle.getString(SELECTED_SLIDER).toString()).equals("Logout")) {
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
        String data = bundle.getString(SELECTED_SLIDER);
        if(data.equalsIgnoreCase("Home")) {
            SNFragment fragment = new HomeFragment();
            fragment.setArguments(bundle);
            pageFragment = fragment;
        }
        else if(data.equalsIgnoreCase("Science Chapters")) {
            SNFragment fragment = new ChapterFragment();
            fragment.setArguments(bundle);
            pageFragment = fragment;
        }
        else {
            SNFragment fragment = new SubjectFragment();
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

    ImageView image;
    String imageString;

    public String getImageString() {
        if (imageString != null)
            return imageString;
        else
            return "";
    }

    public void onImageClick(ImageView image) {
        this.image = image;
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, 1);
                } else if (items[item].equals("Choose from Library")) {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, 100);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap photo = null;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
            try {
                cropCapturedImage(Uri.fromFile(file));
            } catch (ActivityNotFoundException aNFE) {
            }
        } else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            photo = extras.getParcelable("data");
            if (photo != null) {
                image.setImageBitmap(photo);
                imageString = Util.encodeTobase64(photo);
            }
        } else if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            File file = new File(picturePath);
            try {
                cropCapturedImage(Uri.fromFile(file));
            } catch (ActivityNotFoundException aNFE) {
            }
            photo = BitmapFactory.decodeFile(picturePath);
        }
    }

    public void cropCapturedImage(Uri picUri) {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(picUri, "image/*");
        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", 256);
        cropIntent.putExtra("outputY", 256);
        cropIntent.putExtra("return-data", true);
        startActivityForResult(cropIntent, 2);
    }
}
