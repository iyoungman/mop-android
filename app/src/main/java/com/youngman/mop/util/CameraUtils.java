package com.youngman.mop.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by YoungMan on 2019-07-13.
 */

public class CameraUtils {

    public static File getImageFromAlbum(ContentResolver contentResolver, Uri imageUri) {
        Cursor cursor = null;

        try {
            String[] strings = { MediaStore.Images.Media.DATA };
            cursor = contentResolver.query(imageUri, strings, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();

            return new File(cursor.getString(column_index));

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }
}
