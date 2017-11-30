package com.example.administrator.demo;

import android.app.LoaderManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CursorAdapter;

public class TestActivity extends AppCompatActivity implements AlbumCollection.AlbumCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AlbumCollection albumCollection = new AlbumCollection();
        albumCollection.onCreate(this,this);
        albumCollection.initLoader();

    }

    @Override
    public void onAlbumData(Cursor data) {
        //在这里设置adapter数据
    }

    @Override
    public void onAlbumReset() {

    }
}
