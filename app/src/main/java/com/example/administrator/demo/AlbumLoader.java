package com.example.administrator.demo;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Administrator on 2017/11/28.
 */

public class AlbumLoader extends CursorLoader {

    private static Uri QUERY_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static final String BUCKET_ORDER_BY = "datetaken DESC";

    private static String[] projection = {MediaStore.Images.Media._ID,MediaStore.Images.Media.DISPLAY_NAME,MediaStore.Images.Media.DATA};

    private AlbumLoader(Context context, String[] projection, String selection, String[] selectionArgs) {
        super(context, QUERY_URI, projection, selection, selectionArgs, BUCKET_ORDER_BY);
    }

    public static AlbumLoader getInstance(Context context){
        return new AlbumLoader(context,projection,null,null);
    }
}
