package com.soyummyrecips.anna.soyummyrecipes.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.soyummyrecips.anna.soyummyrecipes.R;
import com.soyummyrecips.anna.soyummyrecipes.Utils.CircleTransform;
import com.soyummyrecips.anna.soyummyrecipes.Utils.Constants;
import com.soyummyrecips.anna.soyummyrecipes.Utils.Utils;
import com.soyummyrecips.anna.soyummyrecipes.activities.HomeActivity;
import com.soyummyrecips.anna.soyummyrecipes.pojo.UserList;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyProfileFragment extends Fragment implements HomeActivity.ImageSelectionCompleteListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Context mContext;
    ImageView img_profile;
    public  static HomeActivity.ImageSelectionCompleteListner mImageSelectionCompleteListner;
    public MyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mImageSelectionCompleteListner = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        img_profile = view.findViewById(R.id.img_profile);
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        Glide.with(mContext)
                .load("")
                .asBitmap()
                .error(R.drawable.food_icon)
                .transform(new CircleTransform(mContext))
                .into(img_profile);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // Select image from camera and gallery
    private void selectImage() {
        try {
            PackageManager pm = mContext.getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, mContext.getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery","Cancel"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, Constants.PICK_IMAGE_CAMERA);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, Constants.PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(mContext, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(mContext, "Camera ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onImageSelectionComplete(Bitmap mBitmap) {
        Log.e("mBitmap", "Pick mBitmap Gallery::>>> ");

    }
    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == Constants.PICK_IMAGE_CAMERA) {
            try {
                Uri selectedImage = data.getData();
                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                Log.e("Activity", "Pick from Camera::>>> ");

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgPath = destination.getAbsolutePath();
                Utils.loadImageInGladeBitmap(mContext,bitmap,img_profile);
                // imageview.setImageBitmap(bitmap);
                //MyProfileFragment.mImageSelectionCompleteListner.onImageSelectionComplete(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == Constants.PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = Utils.getRealPathFromURI(getActivity(),selectedImage);
                destination = new File(imgPath.toString());
                Log.e("Activity", "Pick from Gallery::>>> ");
               // MyProfileFragment.mImageSelectionCompleteListner.onImageSelectionComplete(bitmap);
                //imageview.setImageBitmap(bitmap);
                Utils.loadImageInGladeBitmap(mContext,bitmap,img_profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
