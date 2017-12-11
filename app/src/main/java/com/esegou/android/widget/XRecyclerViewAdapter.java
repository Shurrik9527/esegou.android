package com.esegou.android.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class XRecyclerViewAdapter extends RecyclerView.Adapter {

    public static final int SHOW_HEADER = 1;
    public static final int SHOW_FOOTER = 2;
    private ArrayList<View> mHeaderViewInfos;
    private ArrayList<View> mFooterViewInfos;
    private RecyclerView.Adapter mAdapter;

    public XRecyclerViewAdapter(ArrayList<View> haderViewInfos, ArrayList<View> footerViewInfos, RecyclerView.Adapter mAdapter) {
        if (haderViewInfos == null) {
            mHeaderViewInfos = new ArrayList<View>();
        } else {
            mHeaderViewInfos = haderViewInfos;
        }

        if (footerViewInfos == null) {
            mFooterViewInfos = new ArrayList<View>();
        } else {
            mFooterViewInfos = footerViewInfos;
        }
        this.mAdapter = mAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        //header
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return SHOW_HEADER;
        }
        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return SHOW_FOOTER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_HEADER) {
            return new HeaderViewHolder(mHeaderViewInfos.get(0));
        }
        if (viewType == SHOW_FOOTER) {
            return new HeaderViewHolder(mFooterViewInfos.get(0));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //也要划分三个区域
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {//是头部
            return;
        }
        //adapter body
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
                return;
            }
        }
        //footer
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
    }

    public int getHeadersCount() {
        return mHeaderViewInfos.size();
    }

    public int getFootersCount() {
        return mFooterViewInfos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == SHOW_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}