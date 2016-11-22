package com.laichushu.book.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.laichushu.book.utils.UIUtil;
import com.laichushu.book.R;

/**
 * 先加顺序 load -->showPagerView-->createSuccessView
 *
 * @author wangtong
 *
 *在子类中 耗时操作放到 load中，然后load返回一个状态，在showPagerView中根据状态选择 显示的页面
 *如果装在是成功的。那么久显示 createSuccessView
 */
public abstract class LoadingPager extends FrameLayout {
    //定义3种状态常量
    public enum PageState{
        STATE_LOADING,//加载中的状态
        STATE_ERROR,//加载失败的状态
        STATE_SUCCESS,//加载成功的状态
        STATE_EMPTY;//加载成功的状态
    }
    private PageState mState = PageState.STATE_LOADING;//表示界面当前的state，默认是加载中
    private View loadingView;
    private View errorView;
    private View successView;
    private View emptyView;

    public LoadingPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLoadingPage();
    }
    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLoadingPage();
    }
    public LoadingPager(Context context) {
        super(context);
        initLoadingPage();
    }

    /**
     * 天然地往LoadingPage中添加4个view
     */
    private void initLoadingPage(){
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        //1.依次添加4个view对象
        if(emptyView==null){
            emptyView = UIUtil.inflate(R.layout.pager_empty);
        }
        addView(emptyView,params);

        if(loadingView==null){
            loadingView = UIUtil.inflate(R.layout.pager_loading);
        }
        addView(loadingView,params);

        if(errorView==null){
            errorView = View.inflate(getContext(), R.layout.pager_error, null);
            Button btn_reload = (Button) errorView.findViewById(R.id.btn_reload);
            btn_reload.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //1.先显示loadingView
                    mState = PageState.STATE_LOADING;
                    showPage();
                    //2.重新加载数据，然后刷新Page
                    mListener.reLoadData();
                }
            });
        }
        addView(errorView,params);

        if(successView==null){
            successView = createSuccessView();//需要不固定的successView
        }
        if(successView==null){
            throw new IllegalArgumentException("The method createSuccessView() can not return null!");
        }else {
            addView(successView,params);
        }

        //2.显示默认的loadingView
        showPage();

        //3.去请求数据然后刷新page
        loadDataAndRefreshPage();
    }
    /**
     * 根据当前的mState显示对应的View
     */
    private void showPage(){
        //1.先隐藏所有的view
        loadingView.setVisibility(INVISIBLE);
        errorView.setVisibility(INVISIBLE);
        successView.setVisibility(INVISIBLE);
        emptyView.setVisibility(INVISIBLE);
        //2.谁的状态谁显示
        switch (mState) {
            case STATE_LOADING://加载中的状态
                loadingView.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR://加载失败的状态
                errorView.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCESS://加载成功的状态
                successView.setVisibility(View.VISIBLE);
                break;
            case STATE_EMPTY://加载空的状态
                successView.setVisibility(View.VISIBLE);
                break;
        }
    }
    /**
     * 请求数据，然后根据回来的数据去刷新Page
     */
    public void loadDataAndRefreshPage(){
        new Thread(){
            public void run() {

//                //1.去服务器请求数据，
//                Object data = loadData();
//                //2.判断data是否为空，如果为空则为error，否则为success状态
//                mState = data==null?PageState.STATE_ERROR:PageState.STATE_SUCCESS;
//                if (data!=null){
//                    String s = data.toString();
//                    if (s.contains("\"success\":false")){
//                        mState = PageState.STATE_ERROR;
//                    }else if(s.equals("data:[]")){
//                        mState = PageState.STATE_EMPTY;
//                    }else{
//                        mState = PageState.STATE_SUCCESS;
//                    }
//                }
                //3.根据新的state，更新page
                //在主线程更新UI
                mState = PageState.STATE_LOADING;
                UIUtil.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showPage();
                    }
                });
            }
        }.start();
    }
    /**
     * 自定义状态
     */
    public void refreshPage(PageState type){
        mState = type;
        //3.根据新的state，更新page
        //在主线程更新UI
        UIUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }
    /**
     * 获取successView，由于每个界面的successView都不一样，那么应该由每个界面自己实现
     * @return
     */
    public abstract View createSuccessView();

//    /**
//     * 去服务器请求数据，由于我不关心具体的数据类型，只需判断数据是否为空
//     * @return
//     */
//    public abstract Object loadData();
    public interface ReLoadDataListenListener{
        void reLoadData();
    }
    private ReLoadDataListenListener mListener;

    public ReLoadDataListenListener getmListener() {
        return mListener;
    }

    public void setmListener(ReLoadDataListenListener mListener) {
        this.mListener = mListener;
    }
}
