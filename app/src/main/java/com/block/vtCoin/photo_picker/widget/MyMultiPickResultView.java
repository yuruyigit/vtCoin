package com.block.vtCoin.photo_picker.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.block.vtCoin.photo_picker.PhotoPickUtils;
import com.block.vtCoin.photo_picker.adapter.PhotoAdapter;
import com.block.vtCoin.photo_picker.event.OnPickPhotoIconClick;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * 以FrameLayout 装载一个RecyclerView
 */
public class MyMultiPickResultView extends FrameLayout {

    @IntDef({ACTION_SELECT, ACTION_ONLY_SHOW})

    //Tell the compiler not to store annotation data in the .class file
    @Retention(RetentionPolicy.SOURCE)

    //Declare the NavigationMode annotation
    public @interface MultiPicAction {}

    public static final int ACTION_SELECT = 1;//该组件用于图片选择
    public static final int ACTION_ONLY_SHOW = 2;//该组件仅用于图片显示

    private int action;

    private int maxCount;


    android.support.v7.widget.RecyclerView recyclerView;
    com.block.vtCoin.photo_picker.adapter.PhotoAdapter photoAdapter;
    ArrayList<String> selectedPhotos;
    public MyMultiPickResultView(Context context) {
        this(context,null,0);
    }

    public MyMultiPickResultView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyMultiPickResultView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public MyMultiPickResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
        initData(context,attrs);
        initEvent(context,attrs);
    }

    private void initEvent(Context context, AttributeSet attrs) {
    }

    private void initData(Context context, AttributeSet attrs) {
    }

    private void initView(Context context, AttributeSet attrs) {
        recyclerView = new android.support.v7.widget.RecyclerView(context,attrs);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        this.addView(recyclerView);
    }
    /**
     * @param context
     * @param action 动作
     * @param photos 装图片地址的集合
     * @param size   最多可选多少张
     * @param listener 当图片被选择的监听
     */
    public void init(Activity context, @MultiPicAction  int action, ArrayList<String> photos, int size, OnPickPhotoIconClick listener){
        this.action = action;
        if (action == MyMultiPickResultView.ACTION_ONLY_SHOW){//当只用作显示图片时,一行显示3张
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        }

        selectedPhotos = new ArrayList<>();

        this.action = action;
        if (photos != null && photos.size() >0){
            selectedPhotos.addAll(photos);
        }
        photoAdapter = new PhotoAdapter(context, selectedPhotos,size);
        photoAdapter.setOnPickPhotoIconClick(listener);
        photoAdapter.setAction(action);
        recyclerView.setAdapter(photoAdapter);
//        recyclerView.setLayoutFrozen(true);
    }

    public void showPics(List<String> paths){
        if (paths != null){
            selectedPhotos.clear();
            selectedPhotos.addAll(paths);
           photoAdapter.notifyDataSetChanged();
        }
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        if (action == ACTION_SELECT){
            PhotoPickUtils.onActivityResult(requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
                @Override
                public void onPickSuccess(ArrayList<String> photos) {
                    photoAdapter.refresh(photos);
                }

                @Override
                public void onPreviewBack(ArrayList<String> photos) {
                    photoAdapter.refresh(photos);
                }

                @Override
                public void onPickFail(String error) {
                    Toast.makeText(getContext(),error, Toast.LENGTH_LONG).show();
                    selectedPhotos.clear();
                    photoAdapter.notifyDataSetChanged();
                }

                @Override
                public void onPickCancle() {
//                    selectedPhotos.clear();
//                    photoAdapter.notifyDataSetChanged();
                    //Toast.makeText(getContext(),"取消选择",Toast.LENGTH_LONG).show();
                }
            });
        }

    }


    public ArrayList<String> getPhotos() {
        return selectedPhotos;
    }


}
