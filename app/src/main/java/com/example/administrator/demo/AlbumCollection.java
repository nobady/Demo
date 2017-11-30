package com.example.administrator.demo;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/11/28.
 */

public class AlbumCollection implements LoaderManager.LoaderCallbacks<Cursor> {

    private WeakReference<Context> mWeakRef;

    private AlbumCallback mCallBack;
    private LoaderManager mLoaderManager;

    public void onCreate(Activity activity, AlbumCallback callback){
        mWeakRef = new WeakReference<Context>(activity);
        mLoaderManager = activity.getLoaderManager();
        mCallBack = callback;
    }

    public void initLoader(){
        mLoaderManager.initLoader(1,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mWeakRef!=null){
            Context context = mWeakRef.get();
            return AlbumLoader.getInstance(context);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (mCallBack!=null){
            mCallBack.onAlbumData(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (mCallBack!=null){
            mCallBack.onAlbumReset();
        }
    }

    interface AlbumCallback{
        void onAlbumData(Cursor data);
        void onAlbumReset();
    }
}
