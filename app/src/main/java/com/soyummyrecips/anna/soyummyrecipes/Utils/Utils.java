package com.soyummyrecips.anna.soyummyrecipes.Utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soyummyrecips.anna.soyummyrecipes.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public  static  String readAssetsValues(Context mContext) {
        String json = null;
        try {
            InputStream inputStream = mContext.getAssets().open("receipes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();

        }
        return json;

    }

    public static String getRealPathFromURI(Activity mContext, Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = mContext.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public static int getImageFromDrowable(Context mContext,String imageName) {

        int drawableResourceId = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());

        return drawableResourceId;
    }
    public static void loadImageInGladeBitmap(Context mContext, Bitmap mBitmap, ImageView img_profile){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Glide.with(mContext)
                .load(stream.toByteArray())
                .asBitmap()
                .error(R.drawable.food_icon)
                .transform(new CircleTransform(mContext))
                .into(img_profile);
    }


}
