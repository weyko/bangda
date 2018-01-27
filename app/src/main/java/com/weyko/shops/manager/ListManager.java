package com.weyko.shops.manager;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.weyko.shops.R;
import com.weyko.shops.adapter.BaseListAdapter;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.base.BaseManager;
import com.weyko.shops.databinding.LayoutListBinding;
import com.weyko.shops.util.CommonUtils;
import com.weyko.shops.util.ShowUtil;

import java.util.List;

/**
 * Description:列表管理类
 * Created  by: weyko on 2017/7/27.
 */

public class ListManager <T extends BaseListAdapter> implements BaseManager{
    private final int TIME_LOAD_DELAY=800;//数据加载延迟时间
    private LayoutListBinding binding;
    private LinearLayoutManager manager;
    // 开始请求的角标
    private int mStart = 0;
    // 一次请求的数量
    private int mCount =10;
    private T adapter;
    private OnListDataLoader onListDataLoader;
    public ListManager(LayoutListBinding binding,T adapter,OnListDataLoader onListDataLoader) {
        this.binding = binding;
        this.adapter = adapter;
        this.onListDataLoader=onListDataLoader;
        manager= new LinearLayoutManager(BaseApplication.getInstance());
        binding.rvFragmentGet.setLayoutManager(manager);
        binding.srlFramentGet.setColorSchemeColors(CommonUtils.getColor(R.color.colorPrimary));
        scrollRecycleView();
    }
    public void updateAdapter(List<Object>list){
        if(adapter==null){
            return;
        }
        if(mStart==0) {
            adapter.setList(list);
        }else{
            int showPosition=adapter.getItemCount()-2;
            manager.scrollToPositionWithOffset(showPosition,0);
            adapter.addAll(list);
        }
        binding.rvFragmentGet.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void  showLine(){
    }
    public void updateRefreshing(boolean isRefreshing){
        binding.srlFramentGet.setRefreshing(isRefreshing);
    }
    private void scrollRecycleView() {
        binding.srlFramentGet.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRefreshing(true);
                mStart=0;
                loadList();
            }
        });
        binding.rvFragmentGet.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = manager.findLastVisibleItemPosition();

                    /**StaggeredGridLayoutManager*/
//                    int[] into = new int[(manager).getSpanCount()];
//                    lastVisibleItem = findMax(manager.findLastVisibleItemPositions(into));

                    if (manager.getItemCount() == 0) {
                        if (adapter != null) {
                            adapter.updateLoadStatus(BaseListAdapter.LOAD_NONE);
                        }
                        return;

                    }
                    if (lastVisibleItem + 1 == manager.getItemCount()) {
                        if (adapter != null) {
                            adapter.updateLoadStatus(BaseListAdapter.LOAD_PULL_TO);
                            // isLoadMore = true;
                            adapter.updateLoadStatus(BaseListAdapter.LOAD_MORE);
                        }
                        //new Handler().postDelayed(() -> getBeforeNews(time), 1000);
                        mStart++;
                        loadList();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();

                /**StaggeredGridLayoutManager*/
//                int[] into = new int[(manager).getSpanCount()];
//                lastVisibleItem = findMax(manager.findLastVisibleItemPositions(into));
            }
        });
    }

    public void loadList() {
        binding.srlFramentGet.postDelayed(new ListRunnable(onListDataLoader,mStart), TIME_LOAD_DELAY);
    }
    private class ListRunnable implements Runnable{
        private OnListDataLoader onListDataLoader;
        private int start;
        public ListRunnable(OnListDataLoader onListDataLoader, int start) {
            this.onListDataLoader = onListDataLoader;
            this.start = start;
        }

        @Override
        public void run() {
            if(onListDataLoader!=null){
                ShowUtil.d("scrollRecycleView--->mStart="+start);
                onListDataLoader.loadList(start);
            }else{
                updateRefreshing(false);
            }
        }
    }
    /**
     * 设置开始索引
     * @param startIndex
     */
    public void setStartIndex(int startIndex){
        this.mStart=startIndex;
    }
    @Override
    public void onDestory() {

    }
    public interface OnListDataLoader{
      public void loadList(int startIndex);
    }
}
