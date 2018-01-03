package com.app.aptap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.aptap.action.SelectedImageBitmapAndPath;
import com.app.aptap.net.ApiClient;
import com.app.aptap.net.ApiInterface;
import com.app.aptap.ui.CustomTextView;
import com.app.aptap.util.Constants;
import com.app.aptap.util.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.Manifest.permission_group.CAMERA;

/**
 * Created by @dity@ on 8/26/2017.
 */

public abstract class BaseCompactActivity extends AppCompatActivity {

    public ApiInterface apiInterface;
    private SimpleDateFormat dateFormatter;

    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;

    private SelectedImageBitmapAndPath mSelectedImageBitmapAndPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();

        dateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);

    }

    public void changeStatusBarColor() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.headerColor));
        }
    }

    public ApiInterface getAPIInterfaceService() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        return apiInterface;
    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    /**
     * Create date dialog
     *
     * @param id
     * @return
     */
    @Override
    protected Dialog onCreateDialog(final int id) {
        // TODO Auto-generated method stub
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(BaseCompactActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear + 1, dayOfMonth);
                CustomTextView customTextView = (CustomTextView) findViewById(id);
                customTextView.setText(dateFormatter.format(newDate.getTime()));
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        return datePicker;
    }

    public void showGenderAndLocationSelecationDialog(final int id, String title, final CharSequence[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                CustomTextView customTextView = (CustomTextView) findViewById(id);
                customTextView.setText(items[item]);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    protected CharSequence[] getGenderData() {
        CharSequence[] items = {
                getString(R.string.signup_gender_male), getString(R.string.signup_gender_female)
        };
        return items;
    }

    public CharSequence[] getLocationData() {
        CharSequence[] locations = getResources().getStringArray(R.array.location_names);
        return locations;
    }

    public CharSequence[] getInterestsData() {
        CharSequence[] locations = getResources().getStringArray(R.array.interests_names);
        return locations;
    }

    public CharSequence[] getWeekData() {
        CharSequence[] locations = getResources().getStringArray(R.array.days_names);
        return locations;
    }


    public void showMessageOKCancel(String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void showMessageYesNo(String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("YES", okListener)
                .setNegativeButton("NO", okListener)
                .create()
                .show();
    }

    public void showPictureDialog(SelectedImageBitmapAndPath selectedImageBitmapAndPath) {
        mSelectedImageBitmapAndPath = selectedImageBitmapAndPath;
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    public void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    mSelectedImageBitmapAndPath.imageBitmapData(bitmap);
                    mSelectedImageBitmapAndPath.imagePath(path);
                    Toast.makeText(BaseCompactActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    //imageview.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(BaseCompactActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            mSelectedImageBitmapAndPath.imageBitmapData(thumbnail);
            mSelectedImageBitmapAndPath.imagePath(saveImage(thumbnail));
            Toast.makeText(BaseCompactActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    /***
     *
     * @return
     */
    public SharedPreferences getSharedPreferencesObj() {
       return this.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }

    public void storeStringValueInSP(String key, String value) {
        getEditor(getSharedPreferencesObj()).putString(key, value).commit();
    }

    public String getStringValueInSP(String key) {
        return getSharedPreferencesObj().getString(key, null);
    }


}
