package com.nszx.bbs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.nszx.bbs.R;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class GridViewActivity extends Fragment {
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    private FlyBanner mBannerNet;
    private String[] mImagesUrl = {
            "https://img.tuguaishou.com/ips_templ_preview/b6/65/2a/lg_1017898_1541166081_5bdc54018cadc.jpg!w280_png?auth_key=2194704000-0-0-e53136d57e6588498f70673cd1bcb024",
            "https://img.tuguaishou.com/ips_templ_preview/c4/b9/84/lg_1028037_1541335214_5bdee8aeb4989.jpg!w280_png?auth_key=2194704000-0-0-19493e780ff28ac8a1c8ffe169b63f87",
            "https://img.tuguaishou.com/ips_templ_preview/ba/d5/0c/lg_1360375_1557222035_5cd1529377580.jpg!w280_png?auth_key=2194704000-0-0-78bb58b686d615affc254eec18a125c5",
            "https://img.tuguaishou.com/ips_templ_preview/ba/d5/0c/lg_1360375_1557222035_5cd1529377580.jpg!w280_png?auth_key=2194704000-0-0-78bb58b686d615affc254eec18a125c5"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bbs_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = getActivity().findViewById(R.id.grid_view);
        //初始化数据
        initData();
        initNetBanner();

        String[] from={"img","text"};

        int[] to={R.id.img,R.id.text};

        adapter=new SimpleAdapter(getActivity(), dataList, R.layout.gridview_item, from, to);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
            }
        });
    }
    private void initNetBanner() {
        mBannerNet = getActivity().findViewById(R.id.banner);

        List<String> imgesUrl = new ArrayList<>();
        for (int i = 0; i < mImagesUrl.length; i++) {
            imgesUrl.add(mImagesUrl[i]);
        }
        mBannerNet.setImagesUrl(imgesUrl);

        mBannerNet.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toasty.warning(getActivity(), "点击了第" + position + "张图片").show();
            }
        });
    }
    void initData() {
        //图标
        int icno[] = { R.drawable.bianji, R.drawable.chazhao, R.drawable.dianzan,
                R.drawable.duihua, R.drawable.fenxiang, R.drawable.shuru, R.drawable.huangguan,
                R.drawable.huiyuan, R.drawable.like, R.drawable.liulan, R.drawable.renzheng, R.drawable.shezhi };
        //图标下的文字
        String name[]={"时钟","信号","宝箱","秒钟","大象","FF","记事本","书签","印象","商店","主题","迅雷"};
        dataList = new ArrayList<>();
        for (int i = 0; i <icno.length; i++) {
            Map<String, Object> map=new HashMap<>();
            map.put("img", icno[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
    }

}