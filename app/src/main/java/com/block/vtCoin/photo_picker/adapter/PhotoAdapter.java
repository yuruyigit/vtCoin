package com.block.vtCoin.photo_picker.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.block.net.util.L;
import com.block.vtCoin.R;
import com.block.vtCoin.photo_picker.PhotoPicker;
import com.block.vtCoin.photo_picker.PhotoPreview;
import com.block.vtCoin.photo_picker.event.OnPickPhotoIconClick;
import com.block.vtCoin.photo_picker.widget.MyMultiPickResultView;
import com.block.vtCoin.util.PermissionsUtils;
import com.block.vtCoin.util.ToastUtil;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by donglua on 15/5/31.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private ArrayList<String> photoPaths;
    private LayoutInflater inflater;

    private Context mContext;
    private OnPickPhotoIconClick onPickPhotoIconClick;


    public void setAction(@MyMultiPickResultView.MultiPicAction int action) {
        this.action = action;
    }

    private int action;
    private int size;

    public PhotoAdapter(Context mContext, ArrayList<String> photoPaths, int size) {
        this.photoPaths = photoPaths;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        padding = dip2Px(4);
        this.size = size;

    }

    public void add(ArrayList<String> photoPaths) {
        if (photoPaths != null && photoPaths.size() > 0) {
            this.photoPaths.addAll(photoPaths);
            notifyDataSetChanged();
        }

    }

    public void refresh(ArrayList<String> photoPaths) {
        this.photoPaths.clear();
        if (photoPaths != null && photoPaths.size() > 0) {
            this.photoPaths.addAll(photoPaths);
        }
        notifyDataSetChanged();
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.__picker_item_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }

    public int dip2Px(int dip) {
        // px/dip = density;
        float density = mContext.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }
    int padding;

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        if (action == MyMultiPickResultView.ACTION_SELECT) {
            L.i("ACTION_SELECT");
            holder.ivPhoto.setPadding(padding, padding, padding, padding);
            if (photoPaths.size() < size && position == getItemCount() - 1) {
                //最后一个始终是+号，点击能够跳去添加图片 当图片数满足时则不显示
                Glide.with(mContext)
                        .load("")
                        .centerCrop()
                        .thumbnail(0.1f)
                        .placeholder(com.block.vtCoin.R.mipmap.apply_picture)
                        .error(com.block.vtCoin.R.mipmap.apply_picture)
                        .into(holder.ivPhoto);
                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (photoPaths != null && photoPaths.size() == size) {
                            ToastUtil.getInstance().showBottomMessage(mContext, "最多只能选择" + size + "张图");
                        } else {
                            if (onPickPhotoIconClick != null)
                                onPickPhotoIconClick.onPickPhotoIconClick();
                            PermissionsUtils.requestReadWriteStorage((Activity) mContext, new Runnable() {
                                @Override
                                public void run() {
                                    PhotoPicker.builder()
                                            .setPhotoCount(size)//可选取图片的数量
                                            .setShowCamera(true)
                                            .setShowGif(true)
                                            .setSelected(photoPaths)
                                            .setPreviewEnabled(true)
                                            .start((Activity) mContext, PhotoPicker.REQUEST_CODE);
                                }
                            });
                        }
                    }
                });
                holder.deleteBtn.setVisibility(View.GONE);
            } else {
                String str = photoPaths.get(position);
                Log.e("file", str);
                Uri uri = Uri.fromFile(new File(photoPaths.get(position)));
                Glide.with(mContext)
                        .load(uri)
                        .centerCrop()
                        .thumbnail(0.1f)
                        .placeholder(R.mipmap.apply_picture)
                        .error(R.mipmap.apply_picture)
                        .into(holder.ivPhoto);
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        photoPaths.remove(position);
                        notifyDataSetChanged();
                    }
                });

                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PhotoPreview.builder()
                                .setPhotos(photoPaths)
                                .setAction(action)
                                .setCurrentItem(position)
                                .start((Activity) mContext);
                    }
                });
            }
        } else if (action == MyMultiPickResultView.ACTION_ONLY_SHOW) {
            L.i("ACTION_ONLY_SHOW");
            Glide.with(mContext)
                    .load(photoPaths.get(position))
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.mipmap.apply_picture)
                    .error(R.mipmap.apply_picture)
                    .into(holder.ivPhoto);

            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoPreview.builder()
                            .setPhotos(photoPaths)
                            .setAction(action)
                            .setCurrentItem(position)
                            .start((Activity) mContext);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return action == MyMultiPickResultView.ACTION_SELECT ? Math.min(photoPaths.size() + 1, size) : photoPaths.size();
    }

    public void setOnPickPhotoIconClick(OnPickPhotoIconClick onPickPhotoIconClick) {
        this.onPickPhotoIconClick = onPickPhotoIconClick;
    }


    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private View vSelected;
        public View cover;
        public View deleteBtn;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            vSelected = itemView.findViewById(R.id.v_selected);
            vSelected.setVisibility(View.GONE);
            cover = itemView.findViewById(R.id.cover);
            cover.setVisibility(View.GONE);
            deleteBtn = itemView.findViewById(R.id.v_delete);
            deleteBtn.setBackgroundResource(com.block.vtCoin.R.mipmap.puls_delete);
            deleteBtn.setVisibility(View.GONE);
        }
    }

}
