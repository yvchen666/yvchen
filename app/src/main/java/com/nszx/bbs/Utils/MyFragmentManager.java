package com.nszx.bbs.Utils;


import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentManager {
    private static List<Fragment> task = new ArrayList<>();
    private static MyFragmentManager sFragmentManager;
    private onFragmentChangeListener mOnFragmentChangeListener;

    private MyFragmentManager() {
    }

    public static MyFragmentManager getInstance() {
        if (sFragmentManager == null) {
            sFragmentManager = new MyFragmentManager();
        }
        return sFragmentManager;
    }

    // 这个方法是添加一个fragment.最好在base的oncreateview中添加
    public void pushOneFragment(Fragment f) {
        task.add(0, f);
        if (mOnFragmentChangeListener != null) {
            mOnFragmentChangeListener.onAdd();
        }
    }

    // 关闭fragment的时候,调用此方法进行销毁操作
    public void PopOneFragment(Fragment f) {
        if (task.contains(f)) {
            task.remove(f);
            f = null;
            if (mOnFragmentChangeListener != null) {
                mOnFragmentChangeListener.onPop();
            }
        }
    }

    // 跳转到某个fragment

    // 获取最顶层的fragment
    public Fragment getTopFragment() {
        Fragment mySupportFragment = null;
        if (task.size() != 0) {
            mySupportFragment = task.get(0);
        }
        return mySupportFragment;
    }

    public void onViewCreated(Fragment mySupportFragment) {
        if (mOnViewCratedListener!= null) {
            mOnViewCratedListener.onViewCreated(mySupportFragment);
        }
    }

    private onViewCratedListener mOnViewCratedListener;

    public void setOnViewCratedListener(onViewCratedListener onViewCratedListener) {
        mOnViewCratedListener = onViewCratedListener;
    }

    public interface onViewCratedListener{
        void onViewCreated(Fragment mySupportFragment);
    }



    public onFragmentChangeListener getOnFragmentChangeListener() {
        return mOnFragmentChangeListener;
    }

    public void setOnFragmentChangeListener(onFragmentChangeListener onFragmentChangeListener) {
        mOnFragmentChangeListener = onFragmentChangeListener;
    }

    public interface onFragmentChangeListener{

        void onAdd();
        void onPop();

    }



}