package com.example.administrator.demo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/28.
 */

public class FirstLayoutManager extends RecyclerView.LayoutManager {

    private int mLastVisiPos = 0;
    private int mFirstVisiPos = 0;

    private int offsetX = 0;
    private int mLeftX = 0;


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (getItemCount()==0){
            removeAndRecycleAllViews(recycler);
            return;
        }

        /*进行初始化操作*/
        mFirstVisiPos = 0;
        mLastVisiPos = getItemCount();

        detachAndScrapAttachedViews(recycler);
        /*开始布局*/
        offsetX = 0;
        mLeftX = 0;
        layoutView(recycler,state);


    }

    private int mItemWidth;

    private void layoutView(RecyclerView.Recycler recycler, RecyclerView.State state) {

        for (int i = 0; i < mLastVisiPos; i++) {


            /*获取一个view*/
            View view = recycler.getViewForPosition(i);
            addView(view);
            /*计算宽高*/
            measureChildWithMargins(view,0,0);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);



            mItemWidth = width;

            layoutDecoratedWithMargins(view,mLeftX,0,mLeftX+width,height);

            mLeftX+=width;
        }
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    private int mLeftIndex;
    private int mRightIndex;
    private int mCenterIndex;

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {

        offsetX+=dx;

        updateIndex();

        removeUnuseView(recycler,state);

        reLayoutViews(recycler);

        offsetChildrenHorizontal(-dx);

        mLeftX-=dx;

        return -dx;
    }

    private void reLayoutViews(RecyclerView.Recycler recycler) {
        int x = 0;
        for (int i = 0; i < mLastVisiPos; i++) {
            if (i>=mLeftIndex&&i<=mRightIndex){
                View view = recycler.getViewForPosition(i);
                addView(view);
                measureChildWithMargins(view,0,0);

//                int width = getDecoratedMeasuredWidth(view);
//                int height = getDecoratedMeasuredHeight(view);
//
//                mItemWidth = width;
//
//                layoutDecoratedWithMargins(view,x,0,x+width,height);
//
//                x+=width;

                scaleView(view);
            }
        }
    }

    private void removeUnuseView(RecyclerView.Recycler recycler, RecyclerView.State state) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (getPosition(view) < mLeftIndex || getPosition(view) > mRightIndex) {
                removeAndRecycleView(view, recycler);
            }
        }
    }




    private void scaleView(View item) {
        //偏移量 : 卡片默认的第一张卡片左边与可视范围左边的偏移量
        float leftX = (getWidth() - mItemWidth) / 2;
        //卡片与中心点的距离
        float distance = item.getX() - leftX;

        //计算绝对距离
        float d = Math.abs(distance);
        d = Math.min(d, mItemWidth);

        /**
         * 距离最大为一个卡片的宽度
         */
        if (d < mItemWidth / 4) {
            item.setScaleX(1);
            item.setScaleY(1);
        } else {
            //卡片缩放值范围1 - 0.9
            float scale = (float) (1.1 - (0.4 * d / mItemWidth));
            item.setScaleX(scale);
            item.setScaleY(scale);
        }
    }

    private void updateIndex() {
        /**
         * 计算中心卡片的下标
         */
        mCenterIndex = (offsetX) / mItemWidth;
        //如果位移量除以 单个卡片的宽度的余数大于半个卡片则说明这个余下的卡片已经超过半个屏幕了
        if (offsetX % mItemWidth > mItemWidth / 2) {
            mCenterIndex++;
        }
        mCenterIndex = Math.max(0, mCenterIndex);
        mCenterIndex = Math.min(getItemCount() - 1, mCenterIndex);

        /**
         * 计算最左边卡片的下标
         */
        mLeftIndex = mCenterIndex - 3 / 2;
        mLeftIndex = Math.max(0, mLeftIndex);

        /**
         * 计算最右边卡片的下标
         */
        mRightIndex = mLeftIndex + 3 - 1;
        mRightIndex = Math.min(getItemCount() - 1, mRightIndex);
    }
}
