package com.samsung.app.smartwallpaper.model;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.samsung.app.smartwallpaper.WallpaperPreviewDialog;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by ASUS on 2018/5/6.
 */

public class PhotoViewPagerAdapter extends PagerAdapter {
    private static final String TAG = "PhotoViewPagerAdapter";
    private ArrayList<WallpaperItem> mWallpaperItems;
    private Context mContext;

    public PhotoViewPagerAdapter(Context context) {
        this.mContext = context;
    }

    public PhotoViewPagerAdapter(Context context, ArrayList<WallpaperItem> wallpaperItems) {
        this.mContext = context;
        this.mWallpaperItems = wallpaperItems;
    }

    public void setWallpaperItems(ArrayList<WallpaperItem> wallpaperItems){
        mWallpaperItems = wallpaperItems;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        WallpaperItem wallpaperItem = mWallpaperItems.get(position);
        PhotoView photoView = new PhotoView(mContext);
//        photoView.setBackgroundColor(Color.WHITE);
        photoView.setZoomable(true);
        if(wallpaperItem.getWallpaperDrawable() == null) {
            wallpaperItem.setTargetView(photoView);
            wallpaperItem.loadWallpaper(wallpaperItem.getHashCode());
        }else{
            photoView.setScaleType(ImageView.ScaleType.FIT_XY);
            photoView.setImageDrawable(wallpaperItem.getWallpaperDrawable());
        }
        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
            }
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return mWallpaperItems != null ? mWallpaperItems.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
