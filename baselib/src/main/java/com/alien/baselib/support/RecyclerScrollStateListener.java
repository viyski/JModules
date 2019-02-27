package com.alien.baselib.support;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.google.common.base.Preconditions;

public abstract class RecyclerScrollStateListener extends RecyclerView.OnScrollListener {


    private int lastVisibilityPosition;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if (!getData().isEnd && !getData().isLoading && newState == RecyclerView.SCROLL_STATE_IDLE &&
        lastVisibilityPosition >= getAdapterLast(recyclerView)){
            onNextLoad();
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        Preconditions.checkNotNull(layoutManager);

        if (layoutManager instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager staggered = (StaggeredGridLayoutManager) layoutManager;
            lastVisibilityPosition = findMax(staggered.findLastVisibleItemPositions(null));
        }else if (layoutManager instanceof GridLayoutManager){
            GridLayoutManager grid = (GridLayoutManager) layoutManager;
            lastVisibilityPosition = grid.findLastVisibleItemPosition();
        }else{
            LinearLayoutManager linear = (LinearLayoutManager) layoutManager;
            lastVisibilityPosition = linear.findLastVisibleItemPosition();
        }
    }

    private int getAdapterLast(RecyclerView recyclerView) {
        Preconditions.checkNotNull(recyclerView.getAdapter());

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            GridLayoutManager grid = (GridLayoutManager) layoutManager;
            return recyclerView.getAdapter().getItemCount() - grid.getSpanCount() - getData().footerCount;
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager staggered = (StaggeredGridLayoutManager) layoutManager;
            return recyclerView.getAdapter().getItemCount() - staggered.getSpanCount() - getData().footerCount;
        }else{
            return recyclerView.getAdapter().getItemCount() - 1 - getData().footerCount;
        }
    }

    private int findMax(int[] indexs){
        int index = 0;
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] > index){
                index = indexs[i];
            }
        }
        return index;
    }

    abstract StateData getData();

    abstract void onNextLoad();

    public class StateData{

        public int footerCount;
        public boolean isEnd;
        public boolean isLoading;

        public StateData(int footerCount, boolean isEnd, boolean isLoading){
            this.footerCount = footerCount;
            this.isEnd = isEnd;
            this.isLoading = isLoading;
        }
    }

}
